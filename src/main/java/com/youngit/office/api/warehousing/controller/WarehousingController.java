package com.youngit.office.api.warehousing.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "입출고 관리")
@RestController
public class WarehousingController {

    private static final Logger logger = LoggerFactory.getLogger(WarehousingController.class);
    //배송상태확인(택배사이트로 연결)

}
