package training.cloud.crmmicroservice.domain.interactor;

import lombok.RequiredArgsConstructor;
import training.cloud.crmmicroservice.converter.CustomerInteractorModelToRepositoryModelConverter;
import training.cloud.crmmicroservice.converter.CustomerRepositoryModelToInteractorModelConverter;
import training.cloud.crmmicroservice.domain.interactor.input.customer.InsertCustomerInteractor;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;

@RequiredArgsConstructor
public class DefaultInsertCustomerInteractor implements InsertCustomerInteractor {
    private final InsertCustomer insertCustomer;
    private final CustomerInteractorModelToRepositoryModelConverter repositoryModelConverter;
    private final CustomerRepositoryModelToInteractorModelConverter interactorModelConverter;

    @Override
    public CustomerInteractorModel insert(CustomerInteractorModel customerInteractorModel) {
        var customerRepositoryModel = repositoryModelConverter.convert(customerInteractorModel);
        var insertedCustomer = insertCustomer.insert(customerRepositoryModel);
        return interactorModelConverter.convert(insertedCustomer);
    }
}
