package com.wanted.app.domain.application;

import com.wanted.app.domain.company.Company;
import com.wanted.app.domain.company.CompanyService;
import com.wanted.app.domain.member.Member;
import com.wanted.app.domain.member.MemberService;
import com.wanted.app.web.dto.application.ApplicationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final CompanyService companyService;

    private final MemberService memberService;

    private final ApplicationRepository applicationRepository;

    /**
     * 회사 지원
     */
    @Transactional
    public void create(ApplicationRequestDto body) {
        Company company = companyService.findCompanyById(body.getCompanyId());
        Member member = memberService.findMemberById(body.getMemberId());

        Application application = body.toApplication(company, member);
        applicationRepository.save(application);
    }


}
