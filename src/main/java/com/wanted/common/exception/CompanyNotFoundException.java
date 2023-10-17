package com.wanted.common.exception;

import org.springframework.http.HttpStatus;

public class CompanyNotFoundException extends GeneralHttpException {

    public CompanyNotFoundException() {
        super(HttpStatus.NOT_FOUND, "회사를 찾을 수 없습니다.", null);
    }

}