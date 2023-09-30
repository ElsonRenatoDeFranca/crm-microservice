package training.cloud.crmmicroservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import training.cloud.crmmicroservice.api.CrmApi;
import training.cloud.crmmicroservice.model.CustomerDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CrmApiController implements CrmApi {

    @Override
    public ResponseEntity<Void> save(CustomerDto customerDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerDto>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> findByCustomerId(String customerId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteByCustomerId(String customerId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateByCustomerId(CustomerDto customerDto, String customerId) {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerDto>> findAllByCountryName(String countryName) {
        return null;
    }
}
