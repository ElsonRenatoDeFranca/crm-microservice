package com.poc.microservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.poc.microservice.exception.UnnaxTransactionMismatchException;
import com.poc.microservice.exception.UnnaxTransactionNotFoundException;
import com.poc.microservice.mapper.UnnaxCatalogMapper;
import com.poc.microservice.model.UnnaxCatalog;
import com.poc.microservice.model.UnnaxCatalogDto;
import com.poc.microservice.repository.UnnaxCatalogRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class WebhookManagerService {
    private final UnnaxCatalogRepository unnaxCatalogRepository;
    private final UnnaxCatalogMapper unnaxCatalogMapper;

    private static final String UNNAX_TRANSACTION_NOT_FOUND_EXCEPTION_MESSAGE = "UnnaxTransaction not found";
    private static final String UNNAX_TRANSACTION_MISMATCH_EXCEPTION_MESSAGE = "UnnaxTransaction already exists";

    public List<UnnaxCatalogDto> findAll() {
        return unnaxCatalogMapper.entityListToDtoList(unnaxCatalogRepository.findAll());
    }

    public UnnaxCatalogDto findByTraceIdentifier(String traceIdentifier) throws UnnaxTransactionNotFoundException {
        var unnaxCatalog = unnaxCatalogRepository.findByTraceIdentifier(traceIdentifier);
        if (unnaxCatalog == null) {
            throw new UnnaxTransactionNotFoundException(UNNAX_TRANSACTION_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return unnaxCatalogMapper.toUnnaxCatalogDto(unnaxCatalogRepository.findByTraceIdentifier(traceIdentifier));
    }

    @Transactional
    public void save(UnnaxCatalogDto unnaxCatalogDto) throws UnnaxTransactionMismatchException {
        var unnaxCatalog = unnaxCatalogRepository.findByTraceIdentifier(unnaxCatalogDto.getTraceIdentifier());

        if (unnaxCatalog == null) {
            UnnaxCatalog newUnnaxTransaction = unnaxCatalogMapper.toUnnaxCatalog(unnaxCatalogDto);
            unnaxCatalogRepository.save(newUnnaxTransaction);
        } else {
            throw new UnnaxTransactionMismatchException(UNNAX_TRANSACTION_MISMATCH_EXCEPTION_MESSAGE);
        }
    }

    @Transactional
    public void deleteByTraceIdentifier(String traceIdentifier) throws UnnaxTransactionNotFoundException {
        var unnaxCatalog = unnaxCatalogRepository.findByTraceIdentifier(traceIdentifier);

        if (unnaxCatalog != null) {
            unnaxCatalogRepository.deleteByTraceIdentifier(traceIdentifier);
        } else {
            throw new UnnaxTransactionNotFoundException(UNNAX_TRANSACTION_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Transactional
    public UnnaxCatalogDto updateByTraceIdentifier(UnnaxCatalogDto unnaxCatalogDto, String traceIdentifier) throws UnnaxTransactionNotFoundException {
        var unnaxCatalog = unnaxCatalogRepository.findByTraceIdentifier(traceIdentifier);

        if (unnaxCatalog != null) {
            unnaxCatalog.setTraceIdentifier(traceIdentifier);
            unnaxCatalog.setRequestCode(unnaxCatalogDto.getRequestCode());
            unnaxCatalog.setOperation(unnaxCatalogDto.getOperation());
            unnaxCatalog.setTargetDestination(unnaxCatalogDto.getTargetDestination());
            unnaxCatalog.setCreationDate(unnaxCatalogDto.getCreationDate());
            unnaxCatalogRepository.save(unnaxCatalog);
        } else {
            throw new UnnaxTransactionNotFoundException(UNNAX_TRANSACTION_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return unnaxCatalogMapper.toUnnaxCatalogDto(unnaxCatalog);
    }

}
