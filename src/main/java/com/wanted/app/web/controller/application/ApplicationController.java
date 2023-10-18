package com.wanted.app.web.controller.application;

import com.wanted.app.domain.application.ApplicationService;
import com.wanted.app.web.dto.application.ApplicationRequestDto;
import com.wanted.app.web.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "지원 내역")
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(summary = "채용 공고 지원")
    @PostMapping(value = ApiPath.JOB_APPLY)
    public ResponseEntity<Void> uploadApplication(
        @Valid @RequestBody ApplicationRequestDto body
    ) {
        applicationService.create(body);
        return ResponseEntity.ok().build();
    }

}
