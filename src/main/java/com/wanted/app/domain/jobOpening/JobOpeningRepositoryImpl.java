package com.wanted.app.domain.jobOpening;

import static com.wanted.app.domain.jobOpening.QJobOpening.jobOpening;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JobOpeningRepositoryImpl implements JobOpeningRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<JobOpening> findJobOpeningListBySearch(String search) {
        return jpaQueryFactory
            .selectFrom(jobOpening)
            .where(searchEq(search))
            .fetch();
    }

    private BooleanExpression searchEq(String search) {
        if (search == null) {
            return Expressions.TRUE;
        }
        return jobOpening.company.name.contains(search)
            .or(jobOpening.company.country.contains(search))
            .or(jobOpening.company.region.contains(search))
            .or(jobOpening.description.contains(search))
            .or(jobOpening.position.contains(search))
            .or(jobOpening.tech.contains(search));
    }

}
