package training.cloud.crmmicroservice.domain.interactor.input.customer;

import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;

public interface InsertCustomerInteractor {
    CustomerInteractorModel insert (CustomerInteractorModel customerInteractorModel);
}
