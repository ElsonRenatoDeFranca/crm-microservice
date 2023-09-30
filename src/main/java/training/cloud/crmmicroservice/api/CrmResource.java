package training.cloud.crmmicroservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import training.cloud.crmmicroservice.exception.CustomerMismatchException;
import training.cloud.crmmicroservice.exception.CustomerNotFoundException;
import training.cloud.crmmicroservice.model.CustomerDto;
import training.cloud.crmmicroservice.service.CrmService;

import java.util.List;

@RestController
public class CrmResource implements CrmApi {

    private static final Logger log = LoggerFactory.getLogger(CrmResource.class);
    private final CrmService crmService;

    public CrmResource(CrmService crmService) {
        this.crmService = crmService;
    }

    @Override
    public ResponseEntity<Void> save(CustomerDto customerDto) {
        log.info("Saving customer {}", customerDto);
        try {
            crmService.save(customerDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CustomerMismatchException e) {
            return ResponseEntity.badRequest().build();
        }
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

        try {
            crmService.deleteByCustomerId(customerId);
            CustomerDto customerDto = crmService.findByCustomerId(customerId);

            if (customerDto == null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Void> updateByCustomerId(CustomerDto customerDto, String customerId) {
        log.info("Update customer by id {}", customerId);

        try {
            crmService.updateByCustomerId(customerDto, customerId);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<CustomerDto>> findAllByCountryName(String countryName) {
        log.info("Finding all customers by country name {}", countryName);
        return ResponseEntity.ok(crmService.findAllByCountryName(countryName));
    }
}
