package com.poc.microservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.poc.microservice.exception.UnnaxTransactionMismatchException;
import com.poc.microservice.exception.UnnaxTransactionNotFoundException;
import com.poc.microservice.mapper.UnnaxTransactionMapper;
import com.poc.microservice.model.UnnaxTransaction;
import com.poc.microservice.model.UnnaxTransactionDto;
import com.poc.microservice.repository.UnnaxTransactionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UnnaxTransactionManagerService {
    private final UnnaxTransactionRepository unnaxTransactionRepository;
    private final UnnaxTransactionMapper unnaxTransactionMapper;

    private static final String UNNAX_TRANSACTION_NOT_FOUND_EXCEPTION_MESSAGE = "UnnaxTransaction not found";
    private static final String UNNAX_TRANSACTION_MISMATCH_EXCEPTION_MESSAGE = "UnnaxTransaction already exists";

    public List<UnnaxTransactionDto> findAll() {
        return unnaxTransactionMapper.entityListToDtoList(unnaxTransactionRepository.findAll());
    }

    public UnnaxTransactionDto findByTraceIdentifier(String traceIdentifier) throws UnnaxTransactionNotFoundException {
        var unnaxTransaction = unnaxTransactionRepository.findByTraceIdentifier(traceIdentifier);
        if (unnaxTransaction == null) {
            throw new UnnaxTransactionNotFoundException(UNNAX_TRANSACTION_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return unnaxTransactionMapper.toUnnaxTransactionDto(unnaxTransactionRepository.findByTraceIdentifier(traceIdentifier));
    }

    @Transactional
    public void save(UnnaxTransactionDto unnaxTransactionDto) throws UnnaxTransactionMismatchException {
        var unnaxTransaction = unnaxTransactionRepository.findByTraceIdentifier(unnaxTransactionDto.getTraceIdentifier());

        if (unnaxTransaction == null) {
            UnnaxTransaction newUnnaxTransaction = unnaxTransactionMapper.toUnnaxTransaction(unnaxTransactionDto);
            unnaxTransactionRepository.save(newUnnaxTransaction);
        } else {
            throw new UnnaxTransactionMismatchException(UNNAX_TRANSACTION_MISMATCH_EXCEPTION_MESSAGE);
        }
    }

    @Transactional
    public void deleteByTraceIdentifier(String traceIdentifier) throws UnnaxTransactionNotFoundException {
        var unnaxTransaction = unnaxTransactionRepository.findByTraceIdentifier(traceIdentifier);

        if (unnaxTransaction != null) {
            unnaxTransactionRepository.deleteByTraceIdentifier(traceIdentifier);
        } else {
            throw new UnnaxTransactionNotFoundException(UNNAX_TRANSACTION_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Transactional
    public UnnaxTransactionDto updateByTraceIdentifier(UnnaxTransactionDto unnaxTransactionDto, String traceIdentifier) throws UnnaxTransactionNotFoundException {
        var unnaxTransaction = unnaxTransactionRepository.findByTraceIdentifier(traceIdentifier);

        if (unnaxTransaction != null) {
            unnaxTransaction.setTraceIdentifier(traceIdentifier);
            unnaxTransaction.setBankName(unnaxTransactionDto.getBankName());
            unnaxTransactionRepository.save(unnaxTransaction);
        } else {
            throw new UnnaxTransactionNotFoundException(UNNAX_TRANSACTION_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return unnaxTransactionMapper.toUnnaxTransactionDto(unnaxTransaction);
    }

}
