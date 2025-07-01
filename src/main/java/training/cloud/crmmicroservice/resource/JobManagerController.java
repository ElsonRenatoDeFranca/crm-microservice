package training.cloud.crmmicroservice.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training.cloud.crmmicroservice.model.dto.JobRequest;
import training.cloud.crmmicroservice.service.JobIdService;

@RestController
@RequestMapping("/api/cecoban")
@AllArgsConstructor
public class JobManagerController {

    private final JobIdService jobIdService;

    @PostMapping("/jobs")
    public ResponseEntity<Void> saveJob(
            @PathVariable String customerId,
            @RequestBody JobRequest jobRequest
    ) {
        jobIdService.execute(customerId, jobRequest);
        return ResponseEntity.accepted().build();
    }

}