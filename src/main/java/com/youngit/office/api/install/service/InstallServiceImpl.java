package com.youngit.office.api.install.service;

import com.youngit.office.api.install.dto.InstallDto;
import com.youngit.office.api.install.dto.InstallSearchDto;
import com.youngit.office.api.install.mapper.InstallMapper;
import com.youngit.office.api.install.mapstruct.InstallMapstruct;
import com.youngit.office.api.install.model.InstallModel;
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
public class InstallServiceImpl implements InstallService {

    private final InstallMapper installMapper;
    private final InstallMapstruct installMapstruct;
    @Autowired
    public InstallServiceImpl(InstallMapper installMapper, InstallMapstruct installMapstruct) {
        this.installMapper = installMapper;
        this.installMapstruct = installMapstruct;
    }

    /**
     * 설치 조회 및 검색
     */
    @Override
    public List<InstallDto> getOrSearchListInstall(InstallSearchDto installSearchDto) {
        List<InstallModel> resultModelList = installMapper.getOrSearchListInstall(installSearchDto);
        return resultModelList.stream().map(installMapstruct::toDto).toList();
    }
    public int countGetOrSearchListInstall(InstallSearchDto installSearchDto) {
        return installMapper.countGetOrSearchListInstall(installSearchDto);
    }
    public InstallDto getOneInstall(String installUniqId) {
        InstallModel resultModel = installMapper.getOneInstall(installUniqId);
        return installMapstruct.toDto(resultModel);
    }

    /**
     * 설치 등록
     */
    public int registerInstall(InstallDto installDto) {
        //installUiqId: 고유번호 등록하기 위해 가장 최근 고유번호 가져옴.
        String lastInstallUniqId = installMapper.getLastInstallUniqId();
        String newInstallUniqId = generateNewInstallUniqId(lastInstallUniqId);
        installDto.setInstallUniqId(newInstallUniqId);
        InstallModel installModel = installMapstruct.toModel(installDto);
        return installMapper.registerInstall(installModel);
    }
    private String generateNewInstallUniqId(String lastInstallUniqId) {
        if(lastInstallUniqId == null)
            return "INSTL_00000000000001";
        String prefix = "INSTL_";
        String numberPart = lastInstallUniqId.substring(prefix.length());
        int newNumber = Integer.parseInt(numberPart) + 1;
        String newNumberStr = String.format("%014d", newNumber);
        return prefix + newNumberStr;
    }

    /**
     * 설치 일괄 엑셀 등록
     */
    public int registerBatchInstall(String memberId, MultipartFile file) throws Exception {

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream))
        {
            Sheet sheet= workbook.getSheetAt(0); //첫번째 시트
            int totalRows = sheet.getPhysicalNumberOfRows();
            int successCount = 0;

            for(int rowIndex = 1; rowIndex < totalRows; rowIndex ++) //첫번째 행은 생략
            {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue; // 빈 행 처리

                InstallModel installModel = new InstallModel();

                //null 허용x 항목: 설치고유ID, 수용가번호, 수용가명, 설치불가여부, 최초등록시점, 최초등록자ID, 사용여부, 공제여부(기본:'N')
                installModel.setOfficeId(row.getCell(0).getStringCellValue()); //관리사업소
                installModel.setCustomerNumber(row.getCell(1).getStringCellValue()); //수용가번호
                installModel.setCustomerName(row.getCell(2).getStringCellValue()); //성명
                installModel.setCustomerAddress(row.getCell(3).getStringCellValue()); //주소
                installModel.setCustomerPhoneNumber(row.getCell(4).getStringCellValue()); //수용가연락처
                installModel.setMeterCaliber(row.getCell(5).getStringCellValue()); //구경크기
                installModel.setMeterNumber(row.getCell(6).getStringCellValue()); //계량기번호
                installModel.setMeterReadingLocation(row.getCell(7).getStringCellValue()); //계량기위치
                installModel.setTerminalBoxLocation(row.getCell(8).getStringCellValue()); //단자함위치
                installModel.setMetermanName(row.getCell(9).getStringCellValue()); //검침원명
                installModel.setMetermanPhoneNumber(row.getCell(10).getStringCellValue()); //검침원전화번호
                installModel.setEtc(row.getCell(11).getStringCellValue()); //특이사항
                installModel.setInstallAgreeDate(row.getCell(12).getStringCellValue()); //설치동의일

                //설치고유ID 생성
                String lastInstallUniqId = installMapper.getLastInstallUniqId();
                String newInstallUniqId = generateNewInstallUniqId(lastInstallUniqId);
                installModel.setInstallUniqId(newInstallUniqId);
                //DB 저장
                installMapper.registerInstall(installModel);
                successCount++;
            }
            return successCount; // 성공한 개수 반환
        } catch (IOException e) {
            throw new Exception("엑셀 파일 처리 중 오류 발생: " + e.getMessage());
        }
    }
    /**
     * 설치 수정
     */
    public int updateInstall(InstallDto installDto) {
        InstallModel installModel = installMapstruct.toModel(installDto);
        return installMapper.updateInstall(installModel);
    }

    /**
     * 설치 삭제
     */
    public int deleteInstall(String installUniqId) {
        return installMapper.deleteInstall(installUniqId);
    }

    /**
     * 설치 리스트 엑셀 파일 생성
     */
    public byte[] generateExcelInstallList(List<InstallDto> installDtoList) throws IOException {

        List<InstallModel> installModelList = installDtoList.stream().map(installMapstruct::toModel).toList();
        try(Workbook workbook = new XSSFWorkbook())
        {
            Sheet sheet = workbook.createSheet("설치 목록");

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
            titleCell.setCellValue("설치 목록");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8)); // 0행 0열부터 0행 8열까지 병합

            //헤더 행 생성
            Row headerRow = sheet.createRow(1);
            String[] headers = {"동의", "설치관리사업소", "상태표시", "상태", "계약거래처", "설치협력업체", "수용가번호", "수용가명", "주소", "설치불가사유", "계량기번호", "계량기구경", "수용가연락처", "수용가특이사항", "검침원명", "검침원연락처", "접수일", "작업자", "설치지침사진", "검침기버전", "검침기일련번호", "단자함일련번호", "설치환경", "통신선상태", "현장설치일", "최초설치일", "최종완료일", "이력", "설치계약번호", "설치고유ID", "시공사ID"}; // "착수일", "완료예정일", "완료일"는 제거함
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(centerStyle);
                cell.setCellStyle(headerStyle);
                sheet.setColumnWidth(i, 20 * 300); // 열 너비 설정
            }

            // 데이터 행 생성
            for (int i = 0; i < installModelList.size(); i++) {
                InstallModel installModel = installModelList.get(i);
                Row row = sheet.createRow(i + 2);

                row.createCell(0).setCellValue(installModel.getInstallAgreeDate()); //동의
                row.createCell(1).setCellValue(installModel.getOfficeId()); //설치관리사업소
                row.createCell(2).setCellValue(installModel.getInstallStateCode()); //상태표시
                row.createCell(3).setCellValue(installModel.getInstallStateCode()); //상태
                row.createCell(4).setCellValue(installModel.getClientUniqId()); //계약거래처
                row.createCell(5).setCellValue(installModel.getClientBuilderId()); //설치협력업체
                row.createCell(6).setCellValue(installModel.getOfficeId()); //수용가번호
                row.createCell(7).setCellValue(installModel.getCustomerName()); //수용가명
                row.createCell(8).setCellValue(installModel.getCustomerNumber()); //수용가번호
                row.createCell(9).setCellValue(installModel.getCustomerAddress()); //주소

                row.createCell(10).setCellValue(installModel.getInstallImpossibleReason()); //설치불가사유
                row.createCell(11).setCellValue(installModel.getMeterNumber()); //계량기번호
                row.createCell(12).setCellValue(installModel.getMeterCaliber()); //계량기구경
                row.createCell(13).setCellValue(installModel.getCustomerPhoneNumber()); //수용가연락처
                row.createCell(14).setCellValue(installModel.getEtc()); //수용가특이사항
                row.createCell(15).setCellValue(installModel.getMetermanName()); //검침원명
                row.createCell(16).setCellValue(installModel.getMetermanPhoneNumber()); //검침원연락처
                row.createCell(17).setCellValue(installModel.getInstallReceiptDate()); //접수일
                row.createCell(18).setCellValue(installModel.getWorkerName()); //작업자
                row.createCell(19).setCellValue(installModel.getInstallSignaturePath()); //설치지침사진

                row.createCell(20).setCellValue(installModel.getMeterReadingVersion()); //검침기버전
                row.createCell(21).setCellValue(installModel.getMeterReadingSerialNumber()); //검침기일련번호
                row.createCell(22).setCellValue(installModel.getTerminalBoxSerialNumber()); //단자함일련번호
                //  row.createCell(23).setCellValue(installModel.getEstimateAmount()); //설치환경
                row.createCell(24).setCellValue(installModel.getCableStatusCode()); //통신선상태
                row.createCell(25).setCellValue(installModel.getInstallDate()); //현장설치일
                //   row.createCell(26).setCellValue(installModel.getdate()); //최초설치일
                //   row.createCell(27).setCellValue(installModel.getEstimateAmount()); //최종완료일
                //  row.createCell(28).setCellValue(installModel.getEstimateAmount()); //이력
                row.createCell(29).setCellValue(installModel.getInstallContractIdx()); //설치계약번호

                row.createCell(30).setCellValue(installModel.getInstallUniqId()); //설치고유ID
                row.createCell(31).setCellValue(installModel.getClientBuilderId()); //시공사ID

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

    /**
     * 설치 리스트 PDF 파일 생성
     */
    public byte[] generatePdfInstallList(List<InstallDto> installDtoList) {

        return null;
    }

}
