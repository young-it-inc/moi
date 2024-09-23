package com.youngit.office.api.client.service;

import com.youngit.office.api.client.dto.ClientDto;
import com.youngit.office.api.client.dto.ClientManagerDto;
import com.youngit.office.api.client.dto.ClientSearchDto;
import com.youngit.office.api.client.mapper.ClientMapper;
import com.youngit.office.api.client.mapstruct.ClientMapstruct;
import com.youngit.office.api.client.model.ClientManagerModel;
import com.youngit.office.api.client.model.ClientModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientMapper clientMapper;
    private final ClientMapstruct clientMapstruct;

    @Autowired
    public ClientService(ClientMapper clientMapper, ClientMapstruct clientMapstruct) {
        this.clientMapper = clientMapper;
        this.clientMapstruct = clientMapstruct;
    }


    /** 거래처 조회 및 검색
     * @param : clientUniqId
     * @return : List<ClientDto>, ClientDto
     */
    public List<ClientDto> getOrSearchListClient(ClientSearchDto clientSearchDto) {
        List<ClientModel> resultModel = clientMapper.getOrSearchListClient(clientSearchDto);
        List<ClientDto> resultDto = resultModel.stream().map(clientMapstruct::toDto).toList();
        return resultDto;
    }

    public int countGetOrSearchListClient(ClientSearchDto clientSearchDto) {
        return clientMapper.countGetOrSearchListClient(clientSearchDto);
    }

    public ClientDto getOneClient(String clientUniqId) {
        ClientModel clientModel = clientMapper.getOneClient(clientUniqId);
        clientModel.setClientManagerModelList(clientMapper.getListClientManager(clientUniqId));
        ClientDto resultDto = clientMapstruct.toDto(clientModel);
        return resultDto;

    }

    /**
     * 거래처 등록
     */
    public int registerClient(ClientDto clientDto) {

        //clientUniqId: 고유번호(BCNC_15자리) 등록하기 위해 가장 최근의 고유번호를 가져옴
        String lastClientUniqId = clientMapper.getLastClientUniqId();
        String newClientUniqId = generateNewClientUniqId(lastClientUniqId);
        clientDto.setClientUniqId(newClientUniqId);
        ClientModel clientModel = clientMapstruct.toModel(clientDto);

        int result = 0;
        result = clientMapper.registerClient(clientModel);

        //담당자 추가됐을 시 담당자도 등록
        if(clientModel.getClientManagerModelList() != null)
        {
            for(ClientManagerModel clientManagerModel : clientModel.getClientManagerModelList())
            {
                clientManagerModel.setClientUniqId(newClientUniqId);
                result += clientMapper.registerClientManager(clientManagerModel);
            }
        }
        //clientTypeCode:
        return result;
    }

    private String generateNewClientUniqId(String lastClientUniqId) {
        if(lastClientUniqId == null)
            return "BCNC_000000000000001";
        String prefix = "BCNC_";
        String numberPart = lastClientUniqId.substring(prefix.length());
        int newNumber = Integer.parseInt(numberPart) + 1;
        String newNubmerStr = String.format("%015d", newNumber); //0으로 패딩하여 15자리로 맞춤
        return prefix + newNubmerStr;
    }


    public boolean checkBizNumber(String bizNumber) {

        return clientMapper.checkBizNumber(bizNumber);
    }

    /**
     * 거래처 리스트 엑셀 파일 생성
     */
    public byte[] generateExcelClientList(List<ClientDto> clientDtoList) throws IOException {

        List<ClientModel> clientModelList = clientDtoList.stream().map(clientMapstruct::toModel).toList();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("거래처 목록");

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
            titleCell.setCellValue("거래처 목록");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8)); // 0행 0열부터 0행 8열까지 병합

            //헤더 행 생성
            Row headerRow = sheet.createRow(1);
            String[] headers = {"구분", "거래처명", "사업자등록번호", "전화번호", "FAX번호", "대표자", "주소", "업태", "종목"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(centerStyle);
                cell.setCellStyle(headerStyle);
                sheet.setColumnWidth(i, 20 * 300); // 열 너비 설정
            }

            // 데이터 행 생성
            for (int i = 0; i < clientModelList.size(); i++) {
                ClientModel clientModel = clientModelList.get(i);
                Row row = sheet.createRow(i + 2);

                row.createCell(0).setCellValue(clientModel.getClientTypeCode()); //구분
                row.createCell(1).setCellValue(clientModel.getClientName()); //거래처명
                row.createCell(2).setCellValue(clientModel.getBusinessRegistrationNumber()); //사업자등록번호
                row.createCell(3).setCellValue(clientModel.getPhoneNumber()); //전화번호
                row.createCell(4).setCellValue(clientModel.getFaxNumber()); //FAX번호
                row.createCell(5).setCellValue(clientModel.getRepresentativeName()); //대표자
                row.createCell(6).setCellValue(clientModel.getAddress()); //주소
                row.createCell(7).setCellValue(clientModel.getBusinessType()); //업태
                row.createCell(8).setCellValue(clientModel.getBusinessCategory()); //종목

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
     * 거래처 수정
     */
    public int updateClient(ClientDto clientDto) {
        ClientModel clientModel = clientMapstruct.toModel(clientDto);
        int result = clientMapper.updateClient(clientModel);
        if(clientDto.getClientManagerDtoList() != null)
        {
            for(ClientManagerDto clientManagerDto : clientDto.getClientManagerDtoList())
            {
                if(clientManagerDto.getClientUniqId() == null)
                {
                    clientManagerDto.setClientUniqId(clientDto.getClientUniqId());
                }
                ClientManagerModel clientManagerModel = clientMapstruct.toModel(clientManagerDto);
                result += clientMapper.updateClientManager(clientManagerModel);
            }
        }
        return result;
    }

    /**
     * 거래처 삭제
     */
    public int deleteClient(String clientUniqId) {

        return clientMapper.deleteClient(clientUniqId);
    }
}
