package com.wanted.app.domain.company;

import com.wanted.common.exception.CompanyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public Company findCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
            .orElseThrow(CompanyNotFoundException::new);
    }

}
