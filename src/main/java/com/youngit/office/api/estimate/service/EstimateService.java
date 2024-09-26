package com.youngit.office.api.estimate.service;

import com.youngit.office.api.email.EmailService;
import com.youngit.office.api.estimate.dto.EstimateDto;
import com.youngit.office.api.estimate.dto.EstimateSearchDto;
import com.youngit.office.api.estimate.mapper.EstimateMapper;
import com.youngit.office.api.estimate.mapstruct.EstimateMapstruct;
import com.youngit.office.api.estimate.model.EstimateModel;
import com.youngit.office.api.estimate.model.EstimateProductModel;
import com.youngit.office.api.file.service.FileService;
import com.youngit.office.util.PriceToWords;
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
import java.text.SimpleDateFormat;
import java.util.List;


@Service
@Transactional
public class EstimateService {

    private final EstimateMapper estimateMapper;
    private final EstimateMapstruct estimateMapstruct;
    private final EmailService emailService;

    private final FileService fileService;
    @Autowired
    public EstimateService(EstimateMapper estimateMapper, EstimateMapstruct estimateMapstruct, EmailService emailService, FileService fileService) {
        this.estimateMapper = estimateMapper;
        this.estimateMapstruct = estimateMapstruct;
        this.emailService = emailService;
        this.fileService = fileService;
    }

    /** 견적서 조회 및 검색
     *
     */
    public List<EstimateDto> getOrSearchListEstimate(EstimateSearchDto estimateSearchDto) {
        List<EstimateModel> resultModel= estimateMapper.getOrSearchListEstimate(estimateSearchDto);
        return resultModel.stream().map(estimateMapstruct::toDto).toList();
    }

    public int countGetOrSearchListEstimate(EstimateSearchDto estimateSearchDto) {
        return estimateMapper.countGetOrSearchListEstimate(estimateSearchDto);
    }

    public EstimateDto getOneEstimate(String estimateUniqNo) {
        EstimateModel estimateModel = estimateMapper.getOneEstimate(estimateUniqNo);
        return estimateMapstruct.toDto(estimateModel);
    }

    /**
     * 견적 번호 생성
     */
    public String getNewEstimateUniqNo()
    {
        String todayDate = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
        String lastEstimateUniqNo = estimateMapper.getLastEstimateUniqNo(todayDate);

        String newEstimateUniqNo;

        if(lastEstimateUniqNo == null) {
            newEstimateUniqNo = todayDate + "0001";
        }
        else {
            int lastNumber = Integer.parseInt(lastEstimateUniqNo.substring(8));
            String newNumber = String.format("%04d", lastNumber + 1);
            newEstimateUniqNo = todayDate + newNumber;
        }
        return  newEstimateUniqNo;
    }

    /**
     * 견적서 등록
     */
    public int registerEstimate(EstimateDto estimateDto) {
        int result = 0;
        EstimateModel estimateModel = estimateMapstruct.toModel(estimateDto);
        estimateModel.setEstimateAmount(calculateEstimateAmount(estimateModel));
        estimateModel.getEstimateProductModelList().forEach(estimateProductModel -> { //개별 품목 총합 계산
            estimateProductModel.setProductTotalPrice(estimateProductModel.getProductUnitPrice() * estimateProductModel.getProductQuantity());
        });
        result = estimateMapper.registerEstimate(estimateModel); //총 견적금액 계산
        result += estimateMapper.registerEstimateProductList(estimateModel.getEstimateProductModelList());
        return result;
    }

    private int calculateEstimateAmount(EstimateModel estimateModel) {
        int estimateAmount = 0;
        List<EstimateProductModel> estimateProductModelList = estimateModel.getEstimateProductModelList();
        for (int i = 0; i < estimateProductModelList.size(); i++) {
            estimateAmount += estimateProductModelList.get(i).getProductUnitPrice();
        }
        return estimateAmount;
    }

    /**
     * 견적서 엑셀 파일 생성
     */
    public byte[] generateEstimateExcel(String estimateUniqNo) throws IOException {
        //DB에서 해당 견적서 정보 조회
        EstimateModel estimateModel = estimateMapper.getOneEstimate(estimateUniqNo);
        List<EstimateProductModel> estimateProductModelList = estimateMapper.getEstimateProductList(estimateUniqNo);

        //견적서 폼 불러오기
        ClassPathResource resource = new ClassPathResource("form/estimate_form.xlsx");
        File file = resource.getFile();

        System.out.println("일금 " + PriceToWords.convert(estimateModel.getEstimateAmount()));
        try(FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis))
        {
            //첫번째 시트 가져오기
            Sheet sheet = workbook.getSheetAt(0);

            //조회한 견적서 정보 입력
            // 회사명: 3행 1열 (0-based index => 2행 0열)
            sheet.getRow(2).getCell(0).setCellValue(estimateModel.getClientName());

            // 견적번호: 8행 D열 (0-based index => 7행 3열)
            sheet.getRow(7).getCell(3).setCellValue(estimateModel.getEstimateUniqNo());

            // 견적일: 8행 J열 (0-based index => 7행 9열)
            sheet.getRow(7).getCell(9).setCellValue(estimateModel.getEstimateDate().toString());

            // 견적명: 9행 D열 (0-based index => 8행 3열)
            sheet.getRow(8).getCell(3).setCellValue(estimateModel.getEstimateNameCode());

            // 합계금액: 10행 D열 (0-based index => 9행 3열)
            sheet.getRow(9).getCell(3).setCellValue("￦" + PriceToWords.formatPrice(estimateModel.getEstimateAmount()));

            sheet.getRow(9).getCell(8).setCellValue("(일금 " + PriceToWords.convert(estimateModel.getEstimateAmount()) + ")");

            // 제품 리스트 작성 (제품은 13행부터 시작)
            int startRow = 12; // 13행 (0-based index: 12)
            for (int i = 0; i < estimateProductModelList.size(); i++) {
                EstimateProductModel product = estimateProductModelList.get(i);
                Row row = sheet.createRow(startRow + i); // 새로운 행 생성

                // No (13행 A열 => 12행 0열)
                row.createCell(0).setCellValue(i + 1);

                // 품명 (13행 B열 => 12행 1열)
                row.createCell(1).setCellValue(product.getMaterialUniqId());

                // 단위 (13행 G열 => 12행 6열)
                row.createCell(6).setCellValue(product.getMaterialUniqId());

                // 수량 (13행 I열 => 12행 8열)
                row.createCell(8).setCellValue(product.getProductQuantity());

                // 단가 (13행 J열 => 12행 9열)
                row.createCell(9).setCellValue(product.getProductUnitPrice());

                // 금액(부가세 포함) (13행 K열 => 12행 10열)
                row.createCell(10).setCellValue(product.getProductTotalPrice());

            }
            // 최종 합계 금액: 30행 K열 (0-based index => 29행 10열)
            sheet.getRow(29).getCell(10).setCellValue(estimateModel.getEstimateAmount());

            //엑셀 파일을 바이트 배열로 변환
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    /**
     * 견적서 리스트 엑셀 파일 생성
     */
    public byte[] generateExcelEstimateList(List<EstimateDto> estimateDtoList) throws IOException {

        List<EstimateModel> estimateModelList = estimateDtoList.stream().map(estimateMapstruct::toModel).toList();
        try(Workbook workbook = new XSSFWorkbook())
        {
            Sheet sheet = workbook.createSheet("견적서 목록");

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
            titleCell.setCellValue("견적서 목록");
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
            for (int i = 0; i < estimateModelList.size(); i++) {
                EstimateModel estimateModel = estimateModelList.get(i);
                Row row = sheet.createRow(i + 2);

                row.createCell(0).setCellValue(estimateModel.getClientName()); //거래처명
                row.createCell(1).setCellValue(estimateModel.getEstimateCode()); //견적분류
                row.createCell(2).setCellValue(estimateModel.getEstimateMethod()); //견적방법
                row.createCell(3).setCellValue(estimateModel.getEstimateUniqNo()); //견적번호
                row.createCell(4).setCellValue(estimateModel.getEstimateNameCode()); //견적명
                row.createCell(5).setCellValue(estimateModel.getEstimateDate()); //견적일
                row.createCell(9).setCellValue(estimateModel.getEstimateAmount()); //견적금액

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
     * 견적서 수정
     */
    public int updateEstimate(EstimateDto estimateDto) {
        int result = 0;
        EstimateModel estimateModel = estimateMapstruct.toModel(estimateDto);

        result = estimateMapper.updateEstimate(estimateModel);
        result += estimateMapper.deleteEstimateProductList(estimateModel.getEstimateUniqNo()); //기존 등록된 제품 삭제 후
        result += estimateMapper.registerEstimateProductList(estimateModel.getEstimateProductModelList()); //새로 제품 등록
        return result;
    }

    /**
     * 견적서 삭제
     */
    public int deleteEstimate(String estimateUniqNo) {
        int result = 0;
        result = estimateMapper.deleteEstimate(estimateUniqNo); //사실상 삭제x
        result += estimateMapper.deleteEstimateProductList(estimateUniqNo); //삭제o
        return result;
    }

    /**
     * 견적서 계약 진행
     */
    public int registerEstimateContract(EstimateDto estimateDto)
    {
        //계약번호 전달
        return 0;
    }

    /**
     * 견적서 메일 발송(엑셀)
     */
    public int sendEmailEstimate(EstimateDto estimateDto) {

        String from = "";
        String to = estimateDto.getEmail();
        String subject = estimateDto.getEstimateNote();
        String text = estimateDto.getClientName();
        try {
            byte[] byteFile = generateEstimateExcel(estimateDto.getEstimateUniqNo());
            File file = fileService.convertByteArrayToFile(byteFile);
            int result = emailService.sendEmailEstimateWithAttachment(from, to, subject, text, estimateDto, file);
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
