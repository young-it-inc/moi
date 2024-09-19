package com.youngit.office.api.file.controller;

import com.youngit.office.api.http.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "파일 관리")
@RestController
public class FileController {

    //게시글 작성 시 업로드

    public ApiResponse<String> uploadFile() {
        return null;
    }
}
