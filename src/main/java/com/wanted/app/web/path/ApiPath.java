package com.wanted.app.web.path;

public class ApiPath {

    // 채용 공고
    public static final String JOB_OPENING_CREATE = "/api/v1/opening";
    public static final String JOB_OPENING_DELETE = "/api/v1/opening/delete/{id}";
    public static final String JOB_OPENING_UPDATE = "/api/v1/opening/update/{id}";
    public static final String JOB_OPENING_VIEW_FILTER = "/api/v1/opening/filter";
    public static final String JOB_OPENING_VIEW_DETAIL = "/api/v1/opening/detail/{id}";


    // 지원
    public static final String JOB_APPLY = "/api/v1/apply";

    // 에러 핸들러
    public static final String ERROR = "/api/v1/error";
}