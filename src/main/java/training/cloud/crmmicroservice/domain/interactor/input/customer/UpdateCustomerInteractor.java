package training.cloud.crmmicroservice.domain.interactor.input.customer;

import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;

public interface UpdateCustomerInteractor {
    CustomerInteractorModel update(CustomerInteractorModel customerInteractorModel);
}
