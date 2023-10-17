package com.wanted.app.web.response;

import java.util.Map;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidationErrorResponse {

    private final int statusCode = HttpStatus.BAD_REQUEST.value();

    private final Map<String, String> fields;

    private final String message = "형식에 맞지않은 요청입니다.";

    public ValidationErrorResponse(Map<String, String> fields) {
        this.fields = fields;
    }
}