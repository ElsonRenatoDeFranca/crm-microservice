package com.poc.microservice.resource;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.poc.microservice.exception.UnnaxTransactionMismatchException;
import com.poc.microservice.exception.UnnaxTransactionNotFoundException;
import com.poc.microservice.model.UnnaxTransactionDto;
import com.poc.microservice.service.UnnaxTransactionManagerService;

import java.util.List;

@RestController
@AllArgsConstructor
public class UnnaxTransactionManagerController implements UnnaxTransactionManagerApi {

    private static final Logger log = LoggerFactory.getLogger(UnnaxTransactionManagerController.class);
    private final UnnaxTransactionManagerService webhookManagerService;

    @Override
    public ResponseEntity<Void> save(UnnaxTransactionDto unnaxTransactionDto) {
        log.info("Saving unnaxTransactionDto {}", unnaxTransactionDto);
        try {
            webhookManagerService.save(unnaxTransactionDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UnnaxTransactionMismatchException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<UnnaxTransactionDto>> findAll() {
        log.info("Searching all UnnaxTransactionDto");
        return ResponseEntity.ok(webhookManagerService.findAll());
    }

    @Override
    public ResponseEntity<UnnaxTransactionDto> findByTraceIdentifier(String traceIdentifier) {
        log.info("Searching traceIdentifier {}", traceIdentifier);
        UnnaxTransactionDto unnaxTransactionDto = webhookManagerService.findByTraceIdentifier(traceIdentifier);

        if (unnaxTransactionDto == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(webhookManagerService.findByTraceIdentifier(traceIdentifier));
    }

    @Override
    public ResponseEntity<Void> deleteByTraceIdentifier(String traceIdentifier) {
        log.info("Deleting UnnaxTransactionDto by traceIdentifier {}", traceIdentifier);

        try {
            webhookManagerService.deleteByTraceIdentifier(traceIdentifier);
            UnnaxTransactionDto unnaxTransactionDto = webhookManagerService.findByTraceIdentifier(traceIdentifier); ;

            if (unnaxTransactionDto == null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } catch (UnnaxTransactionNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<UnnaxTransactionDto> updateByTraceIdentifier(UnnaxTransactionDto unnaxTransactionDto, String traceIdentifier) {
        log.info("Update UnnaxTransactionDto by traceIdentifier {}", traceIdentifier);

        try {
            UnnaxTransactionDto unnaxTransactionDtoResponse = webhookManagerService.updateByTraceIdentifier(unnaxTransactionDto, traceIdentifier);
            return ResponseEntity.ok(unnaxTransactionDtoResponse);
        } catch (UnnaxTransactionNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
