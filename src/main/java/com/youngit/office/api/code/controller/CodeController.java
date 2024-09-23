package com.youngit.office.api.code.controller;

import com.youngit.office.api.code.service.CodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "코드 관리")
@RestController
@RequestMapping("/api")
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);


    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }



}
