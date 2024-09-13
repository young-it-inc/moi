package com.youngit.office.api.code.controller;

import com.youngit.office.api.code.service.CodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Tag(name = "코드 관리")
@RestController
public class CodeController {

    private static final Logger logger = Logger.getLogger(CodeController.class.getName());


    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }



}
