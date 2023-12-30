package training.cloud.crmmicroservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import training.cloud.crmmicroservice.api.CustomerApi;
import training.cloud.crmmicroservice.api.dto.CustomerResponseViewModel;
import training.cloud.crmmicroservice.api.dto.CustomerViewModel;
import training.cloud.crmmicroservice.model.CustomerDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerApiController implements CustomerApi {
    private final TargetCustomerApiController targetCustomerApiController;

    @Override
    public ResponseEntity<Void> save(CustomerViewModel customerViewModel) {
        targetCustomerApiController.save(customerViewModel);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CustomerResponseViewModel>> findAll() {
        return targetCustomerApiController.findAll();
    }

    @Override
    public ResponseEntity<CustomerResponseViewModel> findByCustomerId(String customerId) {
        return targetCustomerApiController.findByCustomerId(customerId);
    }

    @Override
    public ResponseEntity<Void> deleteByCustomerId(String customerId) {
        targetCustomerApiController.deleteByCustomerId(customerId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateByCustomerId(CustomerViewModel customerViewModel, String customerId) {
        return targetCustomerApiController.updateByCustomerId(customerViewModel, customerId);
    }

    @Override
    public ResponseEntity<List<CustomerDto>> findAllByCountryName(String countryName) {
        return null;
    }
}
