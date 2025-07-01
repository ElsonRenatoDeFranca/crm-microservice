package training.cloud.crmmicroservice.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import training.cloud.crmmicroservice.mapper.TransactionMapper;
import training.cloud.crmmicroservice.model.dto.JobRequest;
import training.cloud.crmmicroservice.model.dto.TransactionDTO;
import training.cloud.crmmicroservice.repository.TransactionRepository;

import java.time.LocalDateTime;


@Slf4j
@AllArgsConstructor
@Data
@Service
public class DefaultJobIdService implements JobIdService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public void execute(String customerId, JobRequest jobRequest) {
        var transactionDTO = new TransactionDTO(
                customerId,
                jobRequest.getJobId(),
                LocalDateTime.now(),
                jobRequest.getStatus()
        );

        var transactionEntity = transactionMapper.toEntity(transactionDTO);
        transactionRepository.save(transactionEntity);
    }
}