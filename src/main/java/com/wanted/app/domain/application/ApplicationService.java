package com.wanted.app.domain.application;

import com.wanted.app.domain.jobOpening.JobOpening;
import com.wanted.app.domain.jobOpening.JobOpeningService;
import com.wanted.app.domain.member.Member;
import com.wanted.app.domain.member.MemberService;
import com.wanted.app.web.dto.application.ApplicationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final JobOpeningService jobOpeningService;

    private final MemberService memberService;

    private final ApplicationRepository applicationRepository;

    /**
     * 채용 공고 지원
     */
    @Transactional
    public void create(ApplicationRequestDto body) {
        JobOpening jobOpening = jobOpeningService.findJobOpeningById(body.getJobOpeningId());
        Member member = memberService.findMemberById(body.getMemberId());

        Application application = body.toApplication(jobOpening, member);
        applicationRepository.save(application);
    }


}
