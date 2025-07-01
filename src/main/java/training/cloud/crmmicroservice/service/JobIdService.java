package training.cloud.crmmicroservice.service;


import training.cloud.crmmicroservice.model.dto.JobRequest;

public interface JobIdService {
    void execute(String customerId, JobRequest jobRequest);
}