package training.cloud.crmmicroservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.api.dto.CustomerResponseViewModel;
import training.cloud.crmmicroservice.api.dto.CustomerViewModel;
import training.cloud.crmmicroservice.converter.CustomerInteractorModelToResponseViewModelConverter;
import training.cloud.crmmicroservice.converter.CustomerInteractorModelToViewModelConverter;
import training.cloud.crmmicroservice.converter.CustomerRepositoryModelToInteractorModelConverter;
import training.cloud.crmmicroservice.converter.CustomerViewModelToInteractorModelConverter;
import training.cloud.crmmicroservice.domain.interactor.input.customer.DeleteCustomerInteractor;
import training.cloud.crmmicroservice.domain.interactor.input.customer.FindCustomerInteractor;
import training.cloud.crmmicroservice.domain.interactor.input.customer.InsertCustomerInteractor;
import training.cloud.crmmicroservice.domain.interactor.input.customer.UpdateCustomerInteractor;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;
import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;
import training.cloud.crmmicroservice.model.CustomerDto;

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
    private final CustomerInteractorModelToResponseViewModelConverter customerInteractorModelToResponseViewModelConverter;
    private final CustomerRepositoryModelToInteractorModelConverter customerRepositoryModelToInteractorModelConverter;

    public ResponseEntity<CustomerResponseViewModel> save(CustomerViewModel customerViewModel) {
        CustomerInteractorModel customerInteractorModel = customerViewModelToInteractorModelConverter.convert(customerViewModel);
        CustomerInteractorModel insertedCustomer = insertCustomerInteractor.insert(customerInteractorModel);
        CustomerResponseViewModel responseViewModel = customerInteractorModelToResponseViewModelConverter.convert(insertedCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseViewModel);
    }

    public ResponseEntity<List<CustomerResponseViewModel>> findAll() {
        var customerInteractorModels = findCustomerInteractor.findAll()
                .stream()
                .map(customerRepositoryModelToInteractorModelConverter::convert)
                .collect(Collectors.toList());

        var responseViewModels = customerInteractorModels
                .stream()
                .map(customerInteractorModel -> customerInteractorModelToResponseViewModelConverter.convert(customerInteractorModel))
                .collect(Collectors.toList());
        
        return ResponseEntity.status(HttpStatus.OK).body(responseViewModels);
    }

    public ResponseEntity<CustomerResponseViewModel> findByCustomerId(String customerId) {
        var customerRepositoryModel = findCustomerInteractor.findByCustomerId(customerId);
        var customerInteractorModel = customerRepositoryModelToInteractorModelConverter.convert(customerRepositoryModel);
        var customerResponseModel = customerInteractorModelToResponseViewModelConverter.convert(customerInteractorModel);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponseModel);
    }

    public ResponseEntity<Void> deleteByCustomerId(String customerId) {
        var customerRepositoryModel = findCustomerInteractor.findByCustomerId(customerId);

        if(customerRepositoryModel == null){
            throw new EntityNotFoundException(String.format("Nothing was found with customerId='%s'", customerId));
        }
        deleteCustomerInteractor.hardDelete(customerId);
        return null;
    }

    public ResponseEntity<Void> updateByCustomerId(CustomerViewModel customerViewModel, String customerId) {
        return null;
    }

    public ResponseEntity<List<CustomerDto>> findAllByCountryName(String countryName) {
        return null;
    }

}
