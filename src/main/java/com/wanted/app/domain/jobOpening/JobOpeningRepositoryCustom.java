package com.wanted.app.domain.jobOpening;

import java.util.List;

public interface JobOpeningRepositoryCustom {

    List<JobOpening> findJobOpeningListBySearch(String search);

}
