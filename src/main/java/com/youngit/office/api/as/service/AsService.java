package com.youngit.office.api.as.service;

import com.youngit.office.api.as.dto.AsDto;
import com.youngit.office.api.as.dto.AsSearchDto;
import com.youngit.office.api.as.mapper.AsMapper;
import com.youngit.office.api.as.mapstruct.AsMapstruct;
import com.youngit.office.api.as.model.AsModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Transactional
public class AsService {

    private final AsMapper asMapper;
    private final AsMapstruct asMapstruct;

    @Autowired
    public AsService(AsMapper asMapper, AsMapstruct asMapstruct) {
        this.asMapper = asMapper;
        this.asMapstruct = asMapstruct;
    }

    /**
     * AS 조회
     */
    public List<AsDto> getOrSearchListAs(AsSearchDto asSearchDto) {
        List<AsModel> asModelList = asMapper.getOrSearchListAs(asSearchDto);
        return asModelList.stream().map(asMapstruct::toDto).toList();
    }
    public int countGetOrSearchListAs(AsSearchDto asSearchDto) {
        return asMapper.countGetOrSearchListAs(asSearchDto);
    }
    public AsDto getOneAs(String asUniqId) {
        AsModel asModel = asMapper.getOneAs(asUniqId);
        return asMapstruct.toDto(asModel);
    }

    /**
     * AS 등록
     */
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
    public int registerBatchAs(String id, MultipartFile file) throws Exception
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

                asModel.setOfficeId(row.getCell(0).getStringCellValue()); //배정고유번호
                asModel.setCustomerNumber(row.getCell(1).getStringCellValue()); //관리사업소
                asModel.setCustomerName(row.getCell(2).getStringCellValue()); //접수년월
                asModel.setCustomerAddress(row.getCell(3).getStringCellValue()); //주차
                asModel.setCustomerPhoneNumber(row.getCell(4).getStringCellValue()); //수용가번호
                asModel.setMeterCaliber(row.getCell(5).getStringCellValue()); //수용가이름
                asModel.setMeterNumber(row.getCell(6).getStringCellValue()); //주소
                asModel.setMeterReadingLocation(row.getCell(7).getStringCellValue()); //수용가연락처
                asModel.setTerminalBoxLocation(row.getCell(8).getStringCellValue()); //수용가연락처2
                asModel.setMetermanName(row.getCell(9).getStringCellValue()); //A/S신청코드
                asModel.setDeductionReason(row.getCell(10).getStringCellValue()); //A/S접수사유
                asModel.setAsReceiptDate(row.getCell(11).getStringCellValue()); //접수일자
                asModel.setAsRequestDate(row.getCell(12).getStringCellValue()); //A/S요청일
                asModel.setMetermanName(row.getCell(13).getStringCellValue()); ;  //검침원이름
                asModel.setMetermanPhoneNumber(row.getCell(14).getStringCellValue()); //검침원 전화번호
                asModel.setMeterNumber(row.getCell(15).getStringCellValue()); //계량기번호
                asModel.setMeterCaliber(row.getCell(16).getStringCellValue());//계량기구경
                asModel.setAsEtc(row.getCell(17).getStringCellValue());//특이사항
                asModel.setOldCustomerNumber(row.getCell(18).getStringCellValue());//구수용가번호

                //유지보수고유ID 생성
                String lastAsUniqId = asMapper.getLastAsUniqId();
                String newAsUniqId = generateNewAsUniqId(lastAsUniqId);
                asModel.setAsUniqId(newAsUniqId);

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
    public int updateAs(AsDto asDto) {
        int result = 0;
        AsModel asModel = asMapstruct.toModel(asDto);
        return asMapper.updateAs(asModel);
    }

    /**
     * AS 삭제
     */
    public int deleteAs(String asUniqId) {
        return asMapper.deleteAs(asUniqId);
    }


}
