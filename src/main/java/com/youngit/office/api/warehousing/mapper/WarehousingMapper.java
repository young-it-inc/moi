package com.youngit.office.api.warehousing.mapper;

import com.youngit.office.api.product.model.ProductModel;
import com.youngit.office.api.warehousing.dto.WarehousingSearchDto;
import com.youngit.office.api.warehousing.model.WarehousingModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehousingMapper {

    List<WarehousingModel> getOrSearchListWarehousing(WarehousingSearchDto warehousingSearchDto);
    int countGetOrSearchListWarehousing(WarehousingSearchDto warehousingSearchDto);
    ProductModel getOneWarehousing(String warehousingSerialNumber);

    int registerWarehousing(WarehousingModel warehousingModel);

    int updateWarehousing(WarehousingModel warehousingModel);

    int deleteWarehousing(String warehousingSerialNumber);
}
