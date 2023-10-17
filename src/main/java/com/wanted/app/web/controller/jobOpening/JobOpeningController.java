package com.wanted.app.web.controller.jobOpening;

import com.wanted.app.domain.jobOpening.JobOpeningService;
import com.wanted.app.web.dto.jobOpening.JobOpeningRequestDto;
import com.wanted.app.web.dto.jobOpening.JobOpeningUpdateRequestDto;
import com.wanted.app.web.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "채용 공고")
@RestController
@RequiredArgsConstructor
public class JobOpeningController {

    private final JobOpeningService jobOpeningService;

    @Operation(summary = "채용 공고 업로드")
    @PostMapping(value = ApiPath.JOB_OPENING_CREATE)
    public ResponseEntity<Void> uploadJobOpening(
        @Valid @RequestBody JobOpeningRequestDto body
    ) {
        jobOpeningService.create(body);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "채용 공고 수정")
    @PutMapping(ApiPath.JOB_OPENING_UPDATE)
    public ResponseEntity<Void> updateJobOpening(
        @PathVariable Long id,
        @Valid @RequestBody JobOpeningUpdateRequestDto body
    ) {
        jobOpeningService.updateJobOpening(id, body);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "채용 공고 삭제")
    @DeleteMapping(ApiPath.JOB_OPENING_DELETE)
    public ResponseEntity<Void> deleteJobOpening(
        @PathVariable Long id
    ) {
        jobOpeningService.deleteJobOpening(id);
        return ResponseEntity.ok().build();
    }

}
