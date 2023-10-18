package com.wanted.app.web.dto.application;

import com.wanted.app.domain.application.Application;
import com.wanted.app.domain.company.Company;
import com.wanted.app.domain.member.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ApplicationRequestDto {

    @NotNull
    private Long companyId;

    @NotNull
    private Long memberId;

    public Application toApplication(Company company, Member member) {
        return Application.builder()
            .company(company)
            .member(member)
            .build();
    }

}