package com.wanted.app.web.dto.jobOpening;

import com.wanted.app.domain.company.Company;
import com.wanted.app.domain.jobOpening.JobOpening;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class JobOpeningRequestDto {

    @NotNull
    private Long companyId;

    @NotBlank(message = "채용포지션은 공백일 수 없습니다")
    @Size(max = 64, message = "채용포지션은 최대 64글자여야 합니다")
    private String position;

    @NotNull
    private Long compensation;

    @NotBlank(message = "설명은 공백일 수 없습니다")
    @Size(max = 200, message = "설명은 최대 200글자여야 합니다")
    private String description;

    @NotBlank(message = "사용기술은 공백일 수 없습니다")
    @Size(max = 10, message = "사용기술은 최대 10글자여야 합니다")
    private String tech;

    public JobOpening toJobOpening(Company company) {
        return JobOpening.builder()
            .company(company)
            .position(position)
            .compensation(compensation)
            .description(description)
            .tech(tech)
            .build();
    }

}