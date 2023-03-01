package training.cloudnative.crmmicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import training.cloudnative.crmmicroservice.model.CustomerDto;
import training.cloudnative.crmmicroservice.service.CrmService;

import java.util.List;

public class CrmResource implements CrmApi {

    private static final Logger log = LoggerFactory.getLogger(CrmResource.class);
    private final CrmService crmService;

    public CrmResource(CrmService crmService) {
        this.crmService = crmService;
    }

    @Override
    public ResponseEntity<Void> save(CustomerDto customerDto) {
        log.info("Saving customer {}", customerDto);
        crmService.save(customerDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CustomerDto>> findAll() {
        log.info("Searching all customers");
        return ResponseEntity.ok(crmService.findAll());
    }

    @Override
    public ResponseEntity<CustomerDto> findByCustomerId(String customerId) {
        log.info("Searching customer by id {}", customerId);
        CustomerDto customerDto = crmService.findByCustomerId(customerId);

        if (customerDto == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(crmService.findByCustomerId(customerId));
    }

    @Override
    public ResponseEntity<Void> deleteByCustomerId(String customerId) {
        log.info("Deleting customer by id {}", customerId);

        crmService.deleteByCustomerId(customerId);
        CustomerDto customerDto = crmService.findByCustomerId(customerId);

        if (customerDto == null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Void> updateByCustomerId(CustomerDto customerDto, String customerId) {
        log.info("Update customer by id {}", customerId);
        crmService.updateByCustomerId(customerDto, customerId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CustomerDto>> findAllByCountryName(String countryName) {
        log.info("Finding all customers by country name {}", countryName);
        return ResponseEntity.ok(crmService.findAllByCountryName(countryName));
    }
}
