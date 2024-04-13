package training.cloud.crmmicroservice.persistence;

import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;

public interface UpdateCustomer {
    CustomerRepositoryModel update(CustomerRepositoryModel customerRepositoryModel);
}
