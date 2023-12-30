package training.cloud.crmmicroservice.domain.interactor;

import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;

import java.util.List;

public interface FindCustomer {
    CustomerRepositoryModel findByCustomerId(String customerId);
    List<CustomerRepositoryModel> findAll();

}
