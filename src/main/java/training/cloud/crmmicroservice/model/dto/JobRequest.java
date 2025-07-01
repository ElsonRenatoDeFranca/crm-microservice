package training.cloud.crmmicroservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Data
public class JobRequest {
    private String jobId;
    private String status;
}
