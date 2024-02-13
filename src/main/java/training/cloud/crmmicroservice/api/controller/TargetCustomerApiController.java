package training.cloud.crmmicroservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.api.dto.CustomerResponseModel;
import training.cloud.crmmicroservice.api.dto.CustomerRequestModel;
import training.cloud.crmmicroservice.converter.CustomerInteractorModelToResponseModelConverter;
import training.cloud.crmmicroservice.converter.CustomerRepositoryModelToInteractorModelConverter;
import training.cloud.crmmicroservice.converter.CustomerViewModelToInteractorModelConverter;
import training.cloud.crmmicroservice.domain.input.customer.DeleteCustomerInteractor;
import training.cloud.crmmicroservice.domain.input.customer.FindCustomerInteractor;
import training.cloud.crmmicroservice.domain.input.customer.InsertCustomerInteractor;
import training.cloud.crmmicroservice.domain.input.customer.UpdateCustomerInteractor;
import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TargetCustomerApiController {

    private final FindCustomerInteractor findCustomerInteractor;
    private final InsertCustomerInteractor insertCustomerInteractor;
    private final UpdateCustomerInteractor updateCustomerInteractor;
    private final DeleteCustomerInteractor deleteCustomerInteractor;
    private final CustomerViewModelToInteractorModelConverter customerViewModelToInteractorModelConverter;
    private final CustomerInteractorModelToResponseModelConverter customerInteractorModelToResponseModelConverter;
    private final CustomerRepositoryModelToInteractorModelConverter customerRepositoryModelToInteractorModelConverter;

    public ResponseEntity<CustomerResponseModel> save(CustomerRequestModel customerViewModel) {
        CustomerInteractorModel customerInteractorModel = customerViewModelToInteractorModelConverter.convert(customerViewModel);
        CustomerInteractorModel insertedCustomer = insertCustomerInteractor.insert(customerInteractorModel);
        CustomerResponseModel responseViewModel = customerInteractorModelToResponseModelConverter.convert(insertedCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseViewModel);
    }

    public ResponseEntity<List<CustomerResponseModel>> findAll() {
        var customerInteractorModels = findCustomerInteractor.findAll();
        var responseViewModels = customerInteractorModels
                .stream()
                .map(customerInteractorModel -> customerInteractorModelToResponseModelConverter.convert(customerInteractorModel))
                .collect(Collectors.toList());
        
        return ResponseEntity.status(HttpStatus.OK).body(responseViewModels);
    }

    public ResponseEntity<CustomerResponseModel> findByCustomerId(String customerId) {
        var customerInteractorModel = findCustomerInteractor.findByCustomerId(customerId);
        var customerResponseModel = customerInteractorModelToResponseModelConverter.convert(customerInteractorModel);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponseModel);
    }

    public ResponseEntity<Void> deleteByCustomerId(String customerId) {
        var customerInteractorModel = findCustomerInteractor.findByCustomerId(customerId);

        if(customerInteractorModel == null){
            throw new EntityNotFoundException(String.format("Nothing was found with customerId='%s'", customerId));
        }
        deleteCustomerInteractor.hardDelete(customerId);
        return null;
    }

    public ResponseEntity<Void> updateByCustomerId(CustomerRequestModel customerViewModel, String customerId) {
        var customerInteractorModel = findCustomerInteractor.findByCustomerId(customerId);

        if(customerInteractorModel == null){
            throw new EntityNotFoundException(String.format("CustomerId='%s' doesn't exist", customerId));
        }
        customerInteractorModel.setCountryName(customerViewModel.getCountryName());
        customerInteractorModel.setFirstName(customerViewModel.getFirstName());
        customerInteractorModel.setLastName(customerViewModel.getLastName());
        customerInteractorModel.setCustomerId(customerViewModel.getCustomerId());
        updateCustomerInteractor.update(customerInteractorModel);

        return null;
    }

    public ResponseEntity<List<CustomerResponseModel>> findAllByCountryName(String countryName) {
        var customerInteractorModels = findCustomerInteractor.findAllByCountryName(countryName).stream()
                .map(customerInteractorModelToResponseModelConverter::convert)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customerInteractorModels);
    }

}
