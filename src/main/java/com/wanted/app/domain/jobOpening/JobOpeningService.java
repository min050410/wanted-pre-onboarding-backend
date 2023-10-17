package com.wanted.app.domain.jobOpening;

import com.wanted.app.domain.company.Company;
import com.wanted.app.domain.company.CompanyService;
import com.wanted.app.web.dto.jobOpening.JobOpeningRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JobOpeningService {

    private final JobOpeningRepository jobOpeningRepository;

    private final CompanyService companyService;

    /**
     * 채용 공고 등록
     */
    @Transactional
    public void create(JobOpeningRequestDto body) {
        Company company = companyService.findCompanyById(body.getCompanyId());

        JobOpening jobOpening = body.toJobOpening(company);
        jobOpeningRepository.save(jobOpening);
    }

}
