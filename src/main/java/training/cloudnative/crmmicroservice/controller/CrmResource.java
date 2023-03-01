package training.cloudnative.crmmicroservice.controller;

import training.cloudnative.crmmicroservice.model.CustomerDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CrmResource implements CrmApi{

    @Override
    public ResponseEntity<CustomerDto> save(CustomerDto customerDto) {
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
    public void deleteByCustomerId(String customerId) {

    }

    @Override
    public void updateByCustomerId(CustomerDto customerDto, String customerId) {

    }

    @Override
    public ResponseEntity<List<CustomerDto>> findAllByCountryName(String countryName) {
        return null;
    }
}
