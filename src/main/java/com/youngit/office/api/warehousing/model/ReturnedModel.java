package com.youngit.office.api.warehousing.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "반납 정보")
class ReturnedModel {

    String	returnUniqId; //반납고유ID
    String	officeId; //지역고유ID
    String	returnDate; //반납일
    String	clientUniqId; //거래처고유ID
    String	returnerName; //반납자명
    int	meterReadingInstallQuantity; //검침기 설치수량
    int	meter_readingAsQuantity; //검침기 AS수량
    int	terminal_box_install_quantity; //단자함 설치수량
    int	terminal_box_as_quantity; //단자함 AS수량
    int	external_indicator_install_quantity; //외부표시기 설치수량
    int	external_indicator_as_quantity; //외부표시기 AS수량
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp createAt; //최초등록시점
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp updateAt; //최종수정시점
    String	createdById; //최초등록자ID
    String	updatedById; //최종수정자ID
}
