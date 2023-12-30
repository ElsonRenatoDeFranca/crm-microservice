package training.cloud.crmmicroservice.domain.interactor;

import lombok.RequiredArgsConstructor;
import training.cloud.crmmicroservice.converter.CustomerInteractorModelToRepositoryModelConverter;
import training.cloud.crmmicroservice.converter.CustomerRepositoryModelToInteractorModelConverter;
import training.cloud.crmmicroservice.domain.interactor.input.customer.UpdateCustomerInteractor;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;

@RequiredArgsConstructor
public class DefaultUpdateCustomerInteractor implements UpdateCustomerInteractor {
    private final UpdateCustomer updateCustomer;
    private final CustomerInteractorModelToRepositoryModelConverter interactorModelToRepositoryModelConverter;
    private final CustomerRepositoryModelToInteractorModelConverter repositoryModelToInteractorModelConverter;

    @Override
    public CustomerInteractorModel update(CustomerInteractorModel customerInteractorModel) {
        var repositoryModel = interactorModelToRepositoryModelConverter.convert(customerInteractorModel);
        var updatedModel = updateCustomer.update(repositoryModel);
        return repositoryModelToInteractorModelConverter.convert(updatedModel);
    }
}
