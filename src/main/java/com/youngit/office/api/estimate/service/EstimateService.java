package com.youngit.office.api.estimate.service;

import com.youngit.office.api.estimate.dto.EstimateDto;
import com.youngit.office.api.estimate.mapper.EstimateMapper;
import com.youngit.office.api.estimate.mapstruct.EstimateMapstruct;
import com.youngit.office.api.estimate.model.EstimateModel;
import com.youngit.office.api.estimate.model.EstimateProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class EstimateService {

    private final EstimateMapper estimateMapper;
    private final EstimateMapstruct estimateMapstruct;
    @Autowired
    public EstimateService(EstimateMapper estimateMapper, EstimateMapstruct estimateMapstruct) {
        this.estimateMapper = estimateMapper;
        this.estimateMapstruct = estimateMapstruct;
    }

    /** 견적서 조회
     *
     * @return
     */
    public List<EstimateDto> getListEstimate() {
        List<EstimateModel> resultModel= estimateMapper.getListEstimate();
        return resultModel.stream().map(estimateMapstruct::toDto).toList();
    }

    public int getCountListEstimate() {
        return estimateMapper.getCountListEstimate();
    }

    public EstimateDto getOneEstimate(String estimateUniqNo) {
        EstimateModel estimateModel = estimateMapper.getOneEstimate(estimateUniqNo);
        return estimateMapstruct.toDto(estimateModel);
    }

    /**
     * 견적 번호 생성
     *
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
        String newEstimateUniqNo = getNewEstimateUniqNo();
        estimateDto.setEstimateUniqNo(newEstimateUniqNo);
        EstimateModel estimateModel = estimateMapstruct.toModel(estimateDto);
        result = estimateMapper.registerEstimate(estimateModel);
        result += estimateMapper.registerEstimateProductList(estimateModel.getEstimateProductModelList());
        return result;
    }

    private int calculateTotalPrice(List<EstimateProductModel> estimateProductModelList) {
        int totalPrice = 0;
        for (int i = 0; i < estimateProductModelList.size(); i++) {
            totalPrice = estimateProductModelList.get(i).getProductUnitPrice() * estimateProductModelList.get(i).getProductQuantity();
        }
        return totalPrice;
    }

    private int calculateEstimateAmount(EstimateModel estimateModel) {
        int estimateAmount = 0;
        List<EstimateProductModel> estimateProductModelList = estimateModel.getEstimateProductModelList();
        for (int i = 0; i < estimateProductModelList.size(); i++) {
            estimateAmount += estimateProductModelList.get(i).getProductTotalPrice();
        }
        return estimateAmount;
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
    public String registerEstimateContract(EstimateDto estimateDto)
    {
        //계약번호 전달
        return null;
    }
}
