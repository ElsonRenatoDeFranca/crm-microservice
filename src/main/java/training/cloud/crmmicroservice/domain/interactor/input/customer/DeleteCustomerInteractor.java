package training.cloud.crmmicroservice.domain.interactor.input.customer;

import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;

public interface DeleteCustomerInteractor {

    void softDelete(CustomerInteractorModel customerInteractorModel);
    void hardDelete(CustomerInteractorModel customerInteractorModel);
}
