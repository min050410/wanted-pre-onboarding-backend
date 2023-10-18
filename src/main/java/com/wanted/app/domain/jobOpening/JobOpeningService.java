package com.wanted.app.domain.jobOpening;

import com.wanted.app.domain.company.Company;
import com.wanted.app.domain.company.CompanyService;
import com.wanted.app.web.dto.jobOpening.JobOpeningDetailResponseDto;
import com.wanted.app.web.dto.jobOpening.JobOpeningRequestDto;
import com.wanted.app.web.dto.jobOpening.JobOpeningResponseDto;
import com.wanted.app.web.dto.jobOpening.JobOpeningUpdateRequestDto;
import com.wanted.app.web.response.ListResponse;
import com.wanted.common.exception.JobOpeningNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JobOpeningService {

    private final CompanyService companyService;

    private final JobOpeningRepository jobOpeningRepository;

    /**
     * 채용 공고 등록
     */
    @Transactional
    public void create(JobOpeningRequestDto body) {
        Company company = companyService.findCompanyById(body.getCompanyId());

        JobOpening jobOpening = body.toJobOpening(company);
        jobOpeningRepository.save(jobOpening);
    }

    /**
     * 채용 공고 수정
     */
    @Transactional
    public void updateJobOpening(Long id, JobOpeningUpdateRequestDto body) {
        JobOpening jobOpening = findById(id);

        JobOpening requestJobOpening = body.toJobOpening();
        jobOpening.update(requestJobOpening);
    }

    /**
     * 채용 공고 삭제
     */
    public void deleteJobOpening(Long id) {
        JobOpening jobOpening = findById(id);

        jobOpeningRepository.delete(jobOpening);
    }

    /**
     * 채용 공고 조회
     */
    public ListResponse<JobOpeningResponseDto> findAllJobOpening() {
        List<JobOpening> jobOpeningList = jobOpeningRepository.findAll();
        List<JobOpeningResponseDto> jobOpeningResponseDtoList =
            JobOpeningResponseDto.listOf(jobOpeningList);
        return ListResponse.of(jobOpeningResponseDtoList);
    }

    /**
     * 채용 공고 상세 조회
     */
    public JobOpeningDetailResponseDto findJobOpeningDetail(Long id) {
        JobOpening jobOpening = findById(id);

        return JobOpeningDetailResponseDto.of(jobOpening);
    }

    /**
     * 채용 공고 검색
     */
    public ListResponse<JobOpeningResponseDto> findJobOpeningsBySearch(String search) {
        List<JobOpening> jobOpeningList = jobOpeningRepository.findJobOpeningListBySearch(search);
        List<JobOpeningResponseDto> jobOpeningResponseDtoList =
            JobOpeningResponseDto.listOf(jobOpeningList);
        return ListResponse.of(jobOpeningResponseDtoList);
    }

    @Transactional(readOnly = true)
    public JobOpening findById(Long id) {
        return jobOpeningRepository.findById(id)
            .orElseThrow(JobOpeningNotFoundException::new);
    }

}
