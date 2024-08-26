package com.youngit.office.api.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "불량상태 정보")
public class BadStateModel {

    String	returnUniqId; //반납고유ID : RTURN_00000000000000(14자리)
    String	productSerialNumber; //제품일련번호 : 00-00-000000
    int	statusNo; //상태순번
    String	badStatusCode; //불량상태코드 : BD001/002/003/004/005/006/007/008/009/010/011/012/013/014/015/016
    // (10핀 insert 사출불량/아이실리콘(기포발생)/실리콘오일불량(D-lens)/led불량/포커스불량/침수(내측외부)/침수(내측)/보드불량(무반응)/보드불량(영샹수신끊김)/통신오류(e-fos반복)/수용가번호오류(특수기호)/실리콘변색(외측)/정상/파손bottom case/파손top case/ 파손main body)

}
