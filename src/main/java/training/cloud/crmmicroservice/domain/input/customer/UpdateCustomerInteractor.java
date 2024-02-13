package training.cloud.crmmicroservice.domain.input.customer;

import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;

public interface UpdateCustomerInteractor {
    CustomerInteractorModel update(CustomerInteractorModel customerInteractorModel);
}
