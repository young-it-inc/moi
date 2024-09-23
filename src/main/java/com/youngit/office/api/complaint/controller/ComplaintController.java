package com.youngit.office.api.complaint.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "민원 관리")
@RestController
@RequestMapping("/api")
public class ComplaintController {

    private static final Logger logger = LoggerFactory.getLogger(ComplaintController.class);
}
