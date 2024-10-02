package com.youngit.office.api.as.service;

import com.youngit.office.api.as.dto.AsDto;
import com.youngit.office.api.as.dto.AsSearchDto;
import com.youngit.office.api.as.mapper.AsMapper;
import com.youngit.office.api.as.mapstruct.AsMapstruct;
import com.youngit.office.api.as.model.AsModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Transactional
public class AsServiceImpl implements AsService {
    private final AsMapper asMapper;
    private final AsMapstruct asMapstruct;

    @Autowired
    public AsServiceImpl(AsMapper asMapper, AsMapstruct asMapstruct) {
        this.asMapper = asMapper;
        this.asMapstruct = asMapstruct;
    }

    /**
     * AS 조회
     */
    @Override
    public List<AsDto> getOrSearchListAs(AsSearchDto asSearchDto) {
        List<AsModel> asModelList = asMapper.getOrSearchListAs(asSearchDto);
        return asModelList.stream().map(asMapstruct::toDto).toList();
    }
    @Override
    public int countGetOrSearchListAs(AsSearchDto asSearchDto) {
        return asMapper.countGetOrSearchListAs(asSearchDto);
    }
    @Override
    public AsDto getOneAs(String asUniqId) {
        AsModel asModel = asMapper.getOneAs(asUniqId);
        return asMapstruct.toDto(asModel);
    }

    /**
     * AS 등록
     */
    @Override
    public int registerAs(AsDto asDto) {
        AsModel asModel = asMapstruct.toModel(asDto);
        String newAsUniqId = generateNewAsUniqId(asMapper.getLastAsUniqId());
        asModel.setAsUniqId(newAsUniqId);
        return asMapper.registerAs(asModel);
    }

    private String generateNewAsUniqId(String lastAsUniqId) {
        String newAsUniqId = "";
        if (lastAsUniqId == null) {
            newAsUniqId = "AS_00000000000000001";
        } else {
            String lastAsUniqIdNumber = lastAsUniqId.substring(2);
            long newAsUniqIdNumber = Long.parseLong(lastAsUniqIdNumber) + 1;
            newAsUniqId = "AS_" + String.format("%017d", newAsUniqIdNumber);
        }
        return newAsUniqId;
    }

    /**
     * AS 일괄 엑셀 등록
     */
    @Override
    public int registerBatchAs(String memberId, MultipartFile file) throws Exception
    {
        try(InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream))
        {
            Sheet sheet= workbook.getSheetAt(0); //첫번째 시트
            int totalRows = sheet.getPhysicalNumberOfRows();
            int successCount = 0;


            for(int rowIndex = 1; rowIndex < totalRows; rowIndex ++) //첫번째 행은 생략
            {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue; // 빈 행 처리

                AsModel asModel = new AsModel();

                //null 허용x 항목(*): A/S고유ID(asUniqId), 수용가번호(customerNumber), 수용가성명(customerName), A/S년월(asYearMonth), A/S주차(asWeek),
                //               A/S처리상태코드(asStateCode), 대체등록여부(isReplaced), 외부표시기설치여부(isExternalIndicatorInstalled), 외함설치여부(isEnclosureInstalled), 공제여부(isDeducted),
                //               최초등록시점(createdAt), 최종등록자ID(createdByMemberUniqId), 사용여부(isUsed)

                // (-): 일괄등록시 보통 기입

                asModel.setAssignNumber(row.getCell(0).getStringCellValue()); //배정고유번호 -
                asModel.setOfficeId(row.getCell(1).getStringCellValue()); //관리사업소 -
                asModel.setCustomerName(row.getCell(2).getStringCellValue()); //접수년월 *
                asModel.setCustomerAddress(row.getCell(3).getStringCellValue()); //주차 * 안쓰면 0
                asModel.setCustomerPhoneNumber(row.getCell(4).getStringCellValue()); //수용가번호 *
                asModel.setMeterCaliber(row.getCell(5).getStringCellValue()); //수용가이름 *
                asModel.setMeterNumber(row.getCell(6).getStringCellValue()); //주소
                asModel.setMeterReadingLocation(row.getCell(7).getStringCellValue()); //수용가연락처
                asModel.setTerminalBoxLocation(row.getCell(8).getStringCellValue()); //수용가연락처2
                asModel.setMetermanName(row.getCell(9).getStringCellValue()); //A/S신청코드
                asModel.setDeductionReason(row.getCell(10).getStringCellValue()); //A/S접수사유
                asModel.setAsReceiptDate(row.getCell(11).getStringCellValue()); //접수일자
                asModel.setAsRequestDate(row.getCell(12).getStringCellValue()); //A/S요청일
                asModel.setMetermanName(row.getCell(13).getStringCellValue()); ; //검침원이름
                asModel.setMetermanPhoneNumber(row.getCell(14).getStringCellValue()); //검침원 전화번호
                asModel.setMeterNumber(row.getCell(15).getStringCellValue()); //계량기번호
                asModel.setMeterCaliber(row.getCell(16).getStringCellValue()); //계량기구경
                asModel.setAsEtc(row.getCell(17).getStringCellValue()); //특이사항
                asModel.setOldCustomerNumber(row.getCell(18).getStringCellValue()); //구수용가번호

                //유지보수고유ID 생성
                String lastAsUniqId = asMapper.getLastAsUniqId();
                String newAsUniqId = generateNewAsUniqId(lastAsUniqId);
                asModel.setAsUniqId(newAsUniqId); //A/S고유ID *

                //공제여부
                asModel.setIsDeducted("N");

                //DB 저장
                asMapper.registerAs(asModel);
                successCount++;
            }
            return successCount; // 성공한 개수 반환
        } catch (IOException e) {
            throw new Exception("엑셀 파일 처리 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * AS 수정
     */
    @Override
    public int updateAs(AsDto asDto) {
        int result = 0;
        AsModel asModel = asMapstruct.toModel(asDto);
        return asMapper.updateAs(asModel);
    }

    /**
     * AS 삭제
     */
    @Override
    public int deleteAs(String asUniqId) {
        return asMapper.deleteAs(asUniqId);
    }


    /**
     * AS 리스트 엑셀 파일 생성
     */
    @Override
    public byte[] generateExcelAsList(List<AsDto> asDtoList) throws IOException {

        List<AsModel> asModelList = asDtoList.stream().map(asMapstruct::toModel).toList();
        try(Workbook workbook = new XSSFWorkbook())
        {
            Sheet sheet = workbook.createSheet("AS 목록");

            //타이틀 스타일 정의
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            //헤더 스타일 정의
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            CellStyle centerStyle = workbook.createCellStyle();
            centerStyle.setAlignment(HorizontalAlignment.CENTER);
            centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // 첫 번째 행에 '설치 목록' 추가 및 셀 병합
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("AS 목록");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8)); // 0행 0열부터 0행 8열까지 병합

            //헤더 행 생성
            Row headerRow = sheet.createRow(1);
            String[] headers = {"경과", "상태", "배정번호", "관리사업소", "접수일", "A/S횟수", "수용가번호", "수용가성명", "수용가주소", "수용가연락처",
                    "수용가연락처2", "검침원명", "검침원연락처", "A/S전영상", "A/S후영상", "계량기번호", "구경", "A/S처리일", "A/S결과", "A/S원인코드", "단가",
                    "A/S서브코드", "A/S직원", "설치길이", "A/S접수사유", "A/S메모", "A/S특이사항", "검침기위치","단자함위치", "설치일", "설치자",
                    "제품구분", "검침기일련번호", "단자함일련번호", "외부표시기", "외함설치", "설치환경", "통신선상태", "거래처고유ID", "A/S고유ID", "시공사고유ID",
                    "설치고유ID"}; // 240930 계량기번호 추가
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(centerStyle);
                cell.setCellStyle(headerStyle);
                sheet.setColumnWidth(i, 20 * 300); // 열 너비 설정
            }

            // 데이터 행 생성
            for (int i = 0; i < asModelList.size(); i++) {
                AsModel asModel = asModelList.get(i);
                Row row = sheet.createRow(i + 2);

                /*
                row.createCell(0).setCellValue(asModel.getInspectionDate()); //경과
                row.createCell(1).setCellValue(asModel.getOfficeId()); //상태
                row.createCell(2).setCellValue(asModel.getEstimateMethod()); //배정번호
                row.createCell(3).setCellValue(asModel.getEstimateUniqNo()); //관리사업소
                row.createCell(4).setCellValue(asModel.getEstimateNameCode()); //접수일
                row.createCell(5).setCellValue(asModel.getEstimateDate()); //A/S횟수
                row.createCell(6).setCellValue(asModel.getEstimateAmount()); //수용가번호
                row.createCell(7).setCellValue(asModel.getEstimateAmount()); //수용가성명
                row.createCell(8).setCellValue(asModel.getEstimateAmount()); //수용가주소
                row.createCell(9).setCellValue(asModel.getEstimateAmount()); //수용가연락처

                row.createCell(10).setCellValue(asModel.getEstimateAmount()); //수용가연락처2
                row.createCell(11).setCellValue(asModel.getEstimateAmount()); //검침원명
                row.createCell(12).setCellValue(asModel.getEstimateAmount()); //검침원연락처
                row.createCell(13).setCellValue(asModel.getEstimateAmount()); //A/S전영상
                row.createCell(14).setCellValue(asModel.getEstimateAmount()); //A/S후영상
                row.createCell(15).setCellValue(asModel.getEstimateAmount()); //240930 계량기번호 추가
                row.createCell(16).setCellValue(asModel.getEstimateAmount()); //계량기 구경
                row.createCell(17).setCellValue(asModel.getEstimateAmount()); //A/S처리일
                row.createCell(18).setCellValue(asModel.getEstimateAmount()); //A/S결과
                row.createCell(19).setCellValue(asModel.getEstimateAmount()); //A/S원인코드

                row.createCell(20).setCellValue(asModel.getEstimateAmount()); //단가
                row.createCell(21).setCellValue(asModel.getEstimateAmount()); //A/S서브코드
                row.createCell(22).setCellValue(asModel.getEstimateAmount()); //A/S직원
                row.createCell(23).setCellValue(asModel.getEstimateAmount()); //설치길이
                row.createCell(24).setCellValue(asModel.getEstimateAmount()); //A/S접수사유
                row.createCell(25).setCellValue(asModel.getEstimateAmount()); //A/S메모
                row.createCell(26).setCellValue(asModel.getEstimateAmount()); //A/S특이사항
                row.createCell(27).setCellValue(asModel.getEstimateAmount()); //검침기위치
                row.createCell(28).setCellValue(asModel.getEstimateAmount()); //단자함위치
                row.createCell(29).setCellValue(asModel.getEstimateAmount()); //설치일

                row.createCell(30).setCellValue(asModel.getEstimateAmount()); //설치자
                row.createCell(31).setCellValue(asModel.getEstimateAmount()); //제품구분
                row.createCell(32).setCellValue(asModel.getEstimateAmount()); //검침기일련번호
                row.createCell(33).setCellValue(asModel.getEstimateAmount()); //단자함일련번호
                row.createCell(34).setCellValue(asModel.getEstimateAmount()); //외부표시기
                row.createCell(35).setCellValue(asModel.getEstimateAmount()); //외함설치
                row.createCell(36).setCellValue(asModel.getEstimateAmount()); //설치환경
                row.createCell(37).setCellValue(asModel.getEstimateAmount()); //통신선상태
                row.createCell(38).setCellValue(asModel.getEstimateAmount()); //거래처고유ID
                row.createCell(39).setCellValue(asModel.getEstimateAmount()); //A/S고유ID

                row.createCell(40).setCellValue(asModel.getEstimateAmount()); //시공사고유ID
                row.createCell(41).setCellValue(asModel.getEstimateAmount()); //설치고유ID
*/

                // 데이터 행 가운데 정렬 스타일 적용
                for (int j = 0; j < headers.length; j++) {
                    row.getCell(j).setCellStyle(centerStyle);
                }
            }
            // 엑셀 파일을 바이트 배열로 변환
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }
}
