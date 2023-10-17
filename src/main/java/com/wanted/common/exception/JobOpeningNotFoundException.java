package com.wanted.common.exception;

import org.springframework.http.HttpStatus;

public class JobOpeningNotFoundException extends GeneralHttpException {

    public JobOpeningNotFoundException() {
        super(HttpStatus.NOT_FOUND, "채용공고를 찾을 수 없습니다.", null);
    }

}