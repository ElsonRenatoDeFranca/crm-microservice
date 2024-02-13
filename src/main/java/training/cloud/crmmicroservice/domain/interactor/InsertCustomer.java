package training.cloud.crmmicroservice.domain.interactor;

import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;

public interface InsertCustomer {
    CustomerRepositoryModel insert(CustomerRepositoryModel customerRepositoryModel);
}
