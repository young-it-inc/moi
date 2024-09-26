package com.youngit.office.api.install.service;

import com.youngit.office.api.install.dto.InstallDto;
import com.youngit.office.api.install.dto.InstallSearchDto;
import com.youngit.office.api.install.mapper.InstallMapper;
import com.youngit.office.api.install.mapstruct.InstallMapstruct;
import com.youngit.office.api.install.model.InstallModel;
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
public class InstallService {

    private final InstallMapper installMapper;
    private final InstallMapstruct installMapstruct;
    @Autowired
    public InstallService(InstallMapper installMapper, InstallMapstruct installMapstruct) {
        this.installMapper = installMapper;
        this.installMapstruct = installMapstruct;
    }

    /**
     * 설치 조회
     */
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
    public int registerBatchInstall(String id, MultipartFile file) throws Exception {

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

}
