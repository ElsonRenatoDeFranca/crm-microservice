package training.cloud.crmmicroservice.domain.interactor.input.customer;

import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;

import java.util.List;

public interface FindCustomerInteractor {
    List<CustomerRepositoryModel> findAll();
    CustomerRepositoryModel findByCustomerId(String customerId);
}
