package com.youngit.office.api.bill.service;

import com.youngit.office.api.bill.dto.BillDto;
import com.youngit.office.api.bill.dto.BillSearchDto;
import com.youngit.office.api.bill.mapper.BillMapper;
import com.youngit.office.api.bill.mapstruct.BillMapstruct;
import com.youngit.office.api.bill.model.BillDetailModel;
import com.youngit.office.api.bill.model.BillModel;
import com.youngit.office.api.client.dto.ClientDto;
import com.youngit.office.api.client.service.ClientService;
import com.youngit.office.api.email.EmailService;
import com.youngit.office.api.file.service.FileService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    private final BillMapper billMapper;
    private final BillMapstruct billMapstruct;

    private final FileService fileService;
    private final EmailService emailService;
    private final ClientService clientService;

    @Autowired
    public BillServiceImpl(BillMapper billMapper, BillMapstruct billMapstruct, FileService fileService, EmailService emailService, ClientService clientService) {
        this.billMapper = billMapper;
        this.billMapstruct = billMapstruct;
        this.fileService = fileService;
        this.emailService = emailService;
        this.clientService = clientService;
    }


    /**
     * 설치팀 내역서 조회 및 검색
     */
    @Override
    public List<BillDto> getOrSearchListBill(BillSearchDto billSearchDto) {
        List<BillModel> billModel = billMapper.getOrSearchListBill(billSearchDto);
        return billModel.stream().map(billMapstruct::toDto).toList();

    }
    @Override
    public int countGetOrSearchListBill(BillSearchDto billSearchDto)
    {
        return billMapper.countGetOrSearchListBill(billSearchDto);
    }

    @Override
    public BillDto getOneBill(String billId) {
        BillModel billModel = billMapper.getOneBill(billId);
        return billMapstruct.toDto(billModel);
    }

    /**
     * 설치팀 내역서 등록
     */
    @Override
    public int registerBill(BillDto billDto) {
        BillModel billModel = billMapstruct.toModel(billDto);
        return billMapper.registerBill(billModel);
    }

    /**
     * 설치팀 내역서 수정
     */
    @Override
    public int updateBill(BillDto billDto) {
        BillModel billModel = billMapstruct.toModel(billDto);
        return billMapper.updateBill(billModel);
    }

    /**
     * 설치팀 내역서 삭제
     */
    @Override
    public int deleteBill(String billId) {
        return  billMapper.deleteBill(billId);
    }

    /**
     * 설치팀 내역서 엑셀 파일 생성 : 이게 좀 시간걸릴듯 =_=
     */
    @Override
    public byte[] generateBillExcel(String billUniqNo) throws IOException {
        //DB에서 해당 내역서 정보 조회
        BillModel billModel = billMapper.getOneBill(billUniqNo);
        List<BillDetailModel> billDetailModelList = billMapper.getBillDetailModelList(billUniqNo);

        //설치팀 내역서 폼 불러오기
        ClassPathResource resource = new ClassPathResource("form/bill_form.xlsx");
        File file = resource.getFile();

        try(FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis))
        {
            //첫번째 시트 가져오기
            Sheet sheet = workbook.getSheetAt(0);

            /**
             * 기본정보+합계
             */
            //조회한 내역서 정보 입력
            // 날짜: 1행 J열 (0-based index => 0행 9열)
            sheet.getRow(2).getCell(0).setCellValue(billModel.getClientUniqId());

            // 설치팀명: 3행 E열 (0-based index => 7행 3열)
            sheet.getRow(7).getCell(3).setCellValue(billModel.getClientUniqId());

            // 처리월: 3행 E열 (0-based index => 7행 3열)
            sheet.getRow(7).getCell(3).setCellValue(billModel.getClientUniqId());

            // 작성자: 3행 E열 (0-based index => 7행 3열)
            sheet.getRow(7).getCell(3).setCellValue(billModel.getClientUniqId());

            // 입금금액: 8행 J열 (0-based index => 7행 9열)
            sheet.getRow(7).getCell(9).setCellValue(billModel.getClientUniqId());

            // 이월금액: 9행 D열 (0-based index => 8행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            // 합계금액: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            /**
             * 1. 합계표
             */
            // 설치합계: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            // 택배비: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            // A/S합계: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            // PDA통신비: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            // 전월미지급금: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            // 지원금: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            // 지급합계: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());


            // 공제합계: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            /**
             * 1-1.설치,유지보수 계약금액
             */
            // 스타2.2 단가: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            // 스타2.0,맥,H 단가: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            // 원격용외표기(별도)설치 단가: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            // 원격용외표기(같이)설치 단가: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            //유지보수 고정지역(동적): 지역별로 컬럼 구분(지역명/계약금액)
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            //비고
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
            /**
             * 1-2.택배비 내역(동적)
             */
            //합계
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
            if(true) //내역 있다면 열 한칸씩 추가됨(날짜/금액)
            {

            }

            /**
             * 1-3.지원금 내역(동적)
             */
            //합계
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            /**
             * 2.시정조치
             */
            sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());

            /**
             * 3.설치내역
             */
            //신규설치 스타2.2
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //신규설치 스타2.0, 맥, H
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //신규설치 계
            if(true) //내역 있다면 행 한 줄씩 추가됨(수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //원격+외표기 설치 스타2.2
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //원격+외표기 설치 스타2.0, 맥, H
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //원격+외표기 설치 계
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //경과재설치 스타2.2
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //경과재설치 스타2.0, 맥, H
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //경과재설치 계
            if(true) //내역 있다면 행 한 줄씩 추가됨(날짜/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //이전설치 스타2.2
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //이전설치 스타2.0, 맥, H
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //이전설치 계
            if(true) //내역 있다면 행 한 줄씩 추가됨(날짜/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //하자보증 스타2.2
            if(true) //내역 있다면 행 한 줄씩 추가됨(금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //하자보증 스타2.0, 맥, H
            if(true) //내역 있다면 행 한 줄씩 추가됨(금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //하자보증 계
            if(true) //내역 있다면 행 한 줄씩 추가됨(금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //하자보수환급
            if(true) //내역 있다면 행 한 줄씩 추가됨(금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //기타공제 스타2.2
            if(true) //내역 있다면 행 한 줄씩 추가됨(금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //기타공제 스타2.0, 맥, H
            if(true) //내역 있다면 행 한 줄씩 추가됨(금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //기타공제
            if(true) //내역 있다면 행 한 줄씩 추가됨(금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //설치합계 스타2.2
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //설치합계 스타2.0, 맥, H
            if(true) //내역 있다면 행 한 줄씩 추가됨(지역/수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }
            //설치합계 계
            if(true) //내역 있다면 행 한 줄씩 추가됨(수량/금액)
            {
                int count = 0;
                for (int i = 0; i < count; i++) {
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                    sheet.getRow(8).getCell(3).setCellValue(billModel.getClientUniqId());
                }
            }

            /**
             * A/S내역
             */

            //고정지역(동적)

            //검침기/단자재부착

            //정상/기타처리 외

            //이전설치시 제품철거

            //기타공제

            //A/S합계


            /**
             * 총계
             */
            // 스타2.2 총수량: 30행 K열 (0-based index => 29행 10열)
            sheet.getRow(29).getCell(10).setCellValue(billModel.getClientUniqId());
            // 스타2.2 총금액: 30행 K열 (0-based index => 29행 10열)
            sheet.getRow(29).getCell(10).setCellValue(billModel.getClientUniqId());

            // 스타2.0, 맥, H 총수량: 30행 K열 (0-based index => 29행 10열)
            sheet.getRow(29).getCell(10).setCellValue(billModel.getClientUniqId());
            // 스타2.0, 맥, H 총금액: 30행 K열 (0-based index => 29행 10열)
            sheet.getRow(29).getCell(10).setCellValue(billModel.getClientUniqId());

            // 계 총수량: 30행 K열 (0-based index => 29행 10열)
            sheet.getRow(29).getCell(10).setCellValue(billModel.getClientUniqId());
            // 계 총금액: 30행 K열 (0-based index => 29행 10열)
            sheet.getRow(29).getCell(10).setCellValue(billModel.getClientUniqId());

            //엑셀 파일을 바이트 배열로 변환
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    /**
     * 설치팀 내역서 리스트 엑셀 파일 생성
     */
    @Override
    public byte[] generateExcelBillList(List<BillDto> billDtoList) throws IOException {
        List<BillModel> billModelList = billDtoList.stream().map(billMapstruct::toModel).toList();
        try(Workbook workbook = new XSSFWorkbook())
        {
            Sheet sheet = workbook.createSheet("설치팀 내역서 목록");

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
            titleCell.setCellValue("설치팀 내역서 목록");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8)); // 0행 0열부터 0행 8열까지 병합

            //헤더 행 생성
            Row headerRow = sheet.createRow(1);
            String[] headers = {"거래처명", "견적분류", "견적방법", "견적번호", "견적명", "견적일", "견적금액"}; // "착수일", "완료예정일", "완료일"는 제거함
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(centerStyle);
                cell.setCellStyle(headerStyle);
                sheet.setColumnWidth(i, 20 * 300); // 열 너비 설정
            }

            // 데이터 행 생성
            for (int i = 0; i < billModelList.size(); i++) {
                BillModel billModel = billModelList.get(i);
                Row row = sheet.createRow(i + 2);

                row.createCell(0).setCellValue(billModel.getClientUniqId()); //거래처명
                row.createCell(1).setCellValue(billModel.getClientUniqId()); //견적분류
                row.createCell(2).setCellValue(billModel.getClientUniqId()); //견적방법
                row.createCell(3).setCellValue(billModel.getClientUniqId()); //견적번호
                row.createCell(4).setCellValue(billModel.getClientUniqId()); //견적명
                row.createCell(5).setCellValue(billModel.getClientUniqId()); //견적일
                row.createCell(9).setCellValue(billModel.getClientUniqId()); //견적금액

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
     * 설치팀 내역서 메일 발송(엑셀)
     */
    @Override
    public int sendEmailBill(BillDto billDto)
    {
        String from = "";
        String clientUniqId = billDto.getClientUniqId();
        ClientDto clientDto = clientService.getOneClient(clientUniqId);
        String to = clientDto.getEmail();
        String subject = "2024년 9월 설치팀 내역서";
        String text = billDto.getWorkName();
        try {
            byte[] byteFile = generateBillExcel(billDto.getClientUniqId());
            File file = fileService.convertByteArrayToFile(byteFile);
            int result = emailService.sendEmailBillAttachment(from, to, subject, text, billDto, file);
            return result; //성공 시 0 반환
        } catch (IOException e) {
            e.printStackTrace();
            return -1; //예외 발생 시 -1 반환
        } catch (MessagingException e)
        {
            e.printStackTrace();
            return -2; //메일 전송 오류 시 -2 반환
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
