package training.cloud.crmmicroservice.domain.input.customer;

import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;

public interface InsertCustomerInteractor {
    CustomerInteractorModel insert (CustomerInteractorModel customerInteractorModel);
}
