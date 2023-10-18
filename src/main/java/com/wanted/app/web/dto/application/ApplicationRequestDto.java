package com.wanted.app.web.dto.application;

import com.wanted.app.domain.application.Application;
import com.wanted.app.domain.jobOpening.JobOpening;
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
    private Long jobOpeningId;

    @NotNull
    private Long memberId;

    public Application toApplication(JobOpening jobOpening, Member member) {
        return Application.builder()
            .jobOpening(jobOpening)
            .member(member)
            .build();
    }

}