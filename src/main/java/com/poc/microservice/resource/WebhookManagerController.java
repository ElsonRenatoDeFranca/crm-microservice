package com.poc.microservice.resource;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.poc.microservice.exception.UnnaxTransactionMismatchException;
import com.poc.microservice.exception.UnnaxTransactionNotFoundException;
import com.poc.microservice.model.UnnaxCatalogDto;
import com.poc.microservice.service.WebhookManagerService;

import java.util.List;

@RestController
@AllArgsConstructor
public class WebhookManagerController implements WebhookManagerApi {

    private static final Logger log = LoggerFactory.getLogger(WebhookManagerController.class);
    private final WebhookManagerService webhookManagerService;

    @Override
    public ResponseEntity<Void> save(UnnaxCatalogDto unnaxCatalogDto) {
        log.info("Saving unnaxCatalogDto {}", unnaxCatalogDto);
        try {
            webhookManagerService.save(unnaxCatalogDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UnnaxTransactionMismatchException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Override
    public ResponseEntity<List<UnnaxCatalogDto>> findAll() {
        log.info("Searching all UnnaxCatalogDto");
        return ResponseEntity.ok(webhookManagerService.findAll());
    }

    @Override
    public ResponseEntity<UnnaxCatalogDto> findByTraceIdentifier(String traceIdentifier) {
        log.info("Searching traceIdentifier {}", traceIdentifier);
        UnnaxCatalogDto unnaxCatalogDto = webhookManagerService.findByTraceIdentifier(traceIdentifier);

        if (unnaxCatalogDto == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(webhookManagerService.findByTraceIdentifier(traceIdentifier));
    }

    @Override
    public ResponseEntity<Void> deleteByTraceIdentifier(String traceIdentifier) {
        log.info("Deleting UnnaxTransactionDto by traceIdentifier {}", traceIdentifier);

        try {
            webhookManagerService.deleteByTraceIdentifier(traceIdentifier);
            UnnaxCatalogDto unnaxTransactionDto = webhookManagerService.findByTraceIdentifier(traceIdentifier); ;

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
    public ResponseEntity<UnnaxCatalogDto> updateByTraceIdentifier(UnnaxCatalogDto unnaxCatalogDto, String traceIdentifier) {
        log.info("Update UnnaxTransactionDto by traceIdentifier {}", traceIdentifier);

        try {
            UnnaxCatalogDto unnaxCatalogDtoResponse = webhookManagerService.updateByTraceIdentifier(unnaxCatalogDto, traceIdentifier);
            return ResponseEntity.ok(unnaxCatalogDtoResponse);
        } catch (UnnaxTransactionNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
