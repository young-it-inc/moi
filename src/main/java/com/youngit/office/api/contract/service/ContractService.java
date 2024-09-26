package com.youngit.office.api.contract.service;

import com.youngit.office.api.contract.dto.ContractDto;
import com.youngit.office.api.contract.dto.ContractSearchDto;
import com.youngit.office.api.contract.mapper.ContractMapper;
import com.youngit.office.api.contract.mapstruct.ContractMapstruct;
import com.youngit.office.api.contract.model.ContractModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class ContractService {

    private final ContractMapper contractMapper;
    private final ContractMapstruct contractMapstruct;
    @Autowired
    public ContractService(ContractMapper contractMapper, ContractMapstruct contractMapstruct) {
        this.contractMapper = contractMapper;
        this.contractMapstruct = contractMapstruct;
    }

    /**
     * 계약 조회
     */
    public List<ContractDto> getOrSearchListContract(ContractSearchDto contractSearchDto) {
        List<ContractModel> contractModelList;
        contractModelList = contractMapper.getOrSearchListContract(contractSearchDto);
        return contractModelList.stream().map(contractMapstruct::toDto).toList();
    }

    public int countGetOrSearchListContract(ContractSearchDto contractSearchDto) {
        return contractMapper.countGetOrSearchListContract(contractSearchDto);

    }

    public ContractDto getOneContract(String contractUniqNo) {
        ContractModel contractModel = contractMapper.getOneContract(contractUniqNo);
        return contractMapstruct.toDto(contractModel);
    }

    /**
     * 계약번호 생성
     * @return
     */
    public String getNewContractUniqNo() {
        String todayDate = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
        String lastContractUniqNo = contractMapper.getLastContractUniqNo(todayDate);

        String newContractUniqNo;
        if (lastContractUniqNo == null) {
            newContractUniqNo = todayDate + "0001";
        } else {
            int lastNumber = Integer.parseInt(lastContractUniqNo.substring(8));
            String newNumber = String.format("%04d", lastNumber + 1);
            newContractUniqNo = todayDate + newNumber;
        }
        return newContractUniqNo;
    }

    /**
     * 계약 등록
     * @param contractDto
     * @return
     */
    @Transactional //중간에 에러가 나면 이전 실행된 쿼리문 롤백
    public int registerContract(ContractDto contractDto) {
        int result = 0;
        ContractModel contractModel = contractMapstruct.toModel(contractDto);
        result = contractMapper.registerContract(contractModel);
        result += contractMapper.registerContractDetail(contractModel.getContractDetailList());
        result += contractMapper.registerContractProduct(contractModel.getContractProductList());
        return result;
    }

    /**
     * 계약 수정 (detail, product는 삭제 후 다시 등록)
     */
    @Transactional
    public int updateContract(ContractDto contractDto) {
        int result = 0;
        ContractModel contractModel = contractMapstruct.toModel(contractDto);
        result = contractMapper.updateContract(contractModel);
        result += contractMapper.deleteContractDetail(contractModel.getContractUniqNo());
        result += contractMapper.registerContractDetail(contractModel.getContractDetailList());
        result += contractMapper.deleteContractProduct(contractModel.getContractUniqNo());
        result += contractMapper.registerContractProduct(contractModel.getContractProductList());
        return result;
    }

    /**
     * 계약 삭제 (o_contract 에서 is_used = 'N'으로 변경, detail, product는 삭제 X)
     */
    public int deleteContract(String contractUniqNo) {
        int result = contractMapper.deleteContract(contractUniqNo);
        return result;
    }

    /**
     * 계약 리스트 엑셀 다운로드
     */
    public byte[] generateExcelEstimateList(List<ContractDto> contractDtoList) throws IOException {

        List<ContractModel> contractModelList = contractDtoList.stream().map(contractMapstruct::toModel).toList();

        try(Workbook workbook = new XSSFWorkbook())
        {
            Sheet sheet = workbook.createSheet("계약 목록");
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

            // 첫 번째 행에 '거래처목록' 추가 및 셀 병합
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("계약 목록");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8)); // 0행 0열부터 0행 8열까지 병합

            //헤더 행 생성
            Row headerRow = sheet.createRow(1);
            String[] headers = {"거래처명", "계약분류", "계약방법", "계약번호", "계약명", "계약일", "착수일", "완료예정일", "완료일", "계약금액"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(centerStyle);
                cell.setCellStyle(headerStyle);
                sheet.setColumnWidth(i, 20 * 300); // 열 너비 설정
            }

            // 데이터 행 생성
            for (int i = 0; i < contractModelList.size(); i++) {
                ContractModel contractModel = contractModelList.get(i);
                Row row = sheet.createRow(i + 2);

                row.createCell(0).setCellValue(contractModel.getClientUniqId()); //거래처명
                row.createCell(1).setCellValue(contractModel.getContractCode()); //계약분류
                row.createCell(2).setCellValue(contractModel.getContractMethod()); //계약방법
                row.createCell(3).setCellValue(contractModel.getContractUniqNo()); //계약번호
                row.createCell(4).setCellValue(contractModel.getContractName()); //계약명
                row.createCell(5).setCellValue(contractModel.getContractDate()); //계약일
                row.createCell(6).setCellValue(contractModel.getOpeningDate()); //착수일
                row.createCell(7).setCellValue(contractModel.getApproximateDate()); //완료예정일
                row.createCell(8).setCellValue(contractModel.getDueDate()); //완료일
                row.createCell(9).setCellValue(contractModel.getContractAmount()); //계약금액

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
