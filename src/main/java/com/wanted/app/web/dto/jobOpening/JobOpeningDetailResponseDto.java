package com.wanted.app.web.dto.jobOpening;

import com.wanted.app.domain.company.Company;
import com.wanted.app.domain.jobOpening.JobOpening;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class JobOpeningDetailResponseDto {

    private Long id;
    private String name;
    private String country;
    private String region;
    private String position;
    private Long compensation;
    private String tech;
    private String description;
    private List<Long> anotherJobOpening;

    public static JobOpeningDetailResponseDto of(JobOpening jobOpening) {
        Company company = jobOpening.getCompany();

        return new JobOpeningDetailResponseDto(
            jobOpening.getId(),
            company.getName(),
            company.getCountry(),
            company.getRegion(),
            jobOpening.getPosition(),
            jobOpening.getCompensation(),
            jobOpening.getTech(),
            jobOpening.getDescription(),
            getAnotherJobOpening(company)
        );
    }

    private static List<Long> getAnotherJobOpening(Company company) {
        return company.getJobOpeningList()
            .stream()
            .map(JobOpening::getId)
            .collect(Collectors.toList());
    }

}
