package com.wanted.app.domain.jobOpening;

import com.wanted.app.domain.BaseTimeEntity;
import com.wanted.app.domain.company.Company;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobOpening extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String position;

    @Column(nullable = false, length = 10)
    private String tech;

    @Lob
    private String description;

    @Column(nullable = false)
    private Long compensation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;


    @Builder
    private JobOpening(
        Company company,
        String position,
        Long compensation,
        String description,
        String tech) {
        this.company = company;
        this.position = position;
        this.compensation = compensation;
        this.description = description;
        this.tech = tech;
    }

    public boolean isAccessibleToJobOpening(Company company) {
        if (company == null) {
            return false;
        }

        Long requestedCompanyId = company.getId();
        Long companyId = this.company.getId();
        return companyId.equals(requestedCompanyId);
    }

    public void update(JobOpening jobOpening) {
        this.position = jobOpening.position;
        this.compensation = jobOpening.compensation;
        this.description = jobOpening.description;
        this.tech = jobOpening.tech;
    }

}