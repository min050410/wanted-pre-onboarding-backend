package com.wanted.common.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends GeneralHttpException {

    public MemberNotFoundException() {
        super(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.", null);
    }

}