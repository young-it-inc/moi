package com.youngit.office.exception;

import com.youngit.office.api.http.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorExceptionController extends AbstractErrorController {
    public ErrorExceptionController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    public ResponseEntity<ApiResponse<String>> error(HttpServletRequest request) {
        String message = "";
        int resultCode = 0;
        HttpStatus status = getStatus(request);

        if(status == null)
        {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        switch (status) {
            case ACCEPTED -> {
            }

            case NOT_FOUND -> {
                message = "Not Found(404) URI가 없습니다."; //Not Found(404)
                resultCode = 91;
            }
            case UNAUTHORIZED -> {
                message = "Unauthorized(401) 권한이 없습니다."; //Unauthorized(401)
                resultCode = 92;
            }
            case BAD_REQUEST -> {
                message = "Bad Request(400) 잘못된 요청입니다."; //Bad Request(400)
                resultCode = 93;
            }
            case FORBIDDEN -> {
                message = "Forbidden(403) 접근 금지"; //Forbidden(403)
                resultCode = 94;
            }
            case METHOD_NOT_ALLOWED -> {
                message = "Method Not Allowed(405) 허용되지 않은 메소드입니다"; //Method Not Allowed(405)
                resultCode = 95;
            }
            default -> {
                message = "Unknown Error(500)"; //Unknown Error(500)
                resultCode = 96;
            }
        }
        ApiResponse<String> result = new ApiResponse<>(null, resultCode, message);
        return new ResponseEntity<>(result, status);
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        System.out.println("request: " + request);
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        System.out.println("statusCode: " + statusCode);
        if(statusCode != null)
        {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception e) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
