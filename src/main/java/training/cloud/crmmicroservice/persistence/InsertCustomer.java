package training.cloud.crmmicroservice.persistence;

import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;

public interface InsertCustomer {
    CustomerRepositoryModel insert(CustomerRepositoryModel customerRepositoryModel);
}
