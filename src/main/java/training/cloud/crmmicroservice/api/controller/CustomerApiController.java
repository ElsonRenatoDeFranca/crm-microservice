package training.cloud.crmmicroservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import training.cloud.crmmicroservice.api.CustomerApi;
import training.cloud.crmmicroservice.api.dto.CustomerResponseModel;
import training.cloud.crmmicroservice.api.dto.CustomerRequestModel;
import training.cloud.crmmicroservice.model.CustomerDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerApiController implements CustomerApi {
    private final TargetCustomerApiController targetCustomerApiController;

    @Override
    public ResponseEntity<Void> save(CustomerRequestModel customerRequestModel) {
        targetCustomerApiController.save(customerRequestModel);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CustomerResponseModel>> findAll() {
        return targetCustomerApiController.findAll();
    }

    @Override
    public ResponseEntity<CustomerResponseModel> findByCustomerId(String customerId) {
        return targetCustomerApiController.findByCustomerId(customerId);
    }

    @Override
    public ResponseEntity<Void> deleteByCustomerId(String customerId) {
        targetCustomerApiController.deleteByCustomerId(customerId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateByCustomerId(CustomerRequestModel customerRequestModel, String customerId) {
        return targetCustomerApiController.updateByCustomerId(customerRequestModel, customerId);
    }

    @Override
    public ResponseEntity<List<CustomerResponseModel>> findAllByCountryName(String countryName) {

        return targetCustomerApiController.findAllByCountryName(countryName);
    }
}
