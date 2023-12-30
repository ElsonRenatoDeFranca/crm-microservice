package training.cloud.crmmicroservice.domain.interactor;

import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;

public interface UpdateCustomer {
    CustomerRepositoryModel update(CustomerRepositoryModel customerRepositoryModel);
}
