package training.cloud.crmmicroservice.domain.interactor;

import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;

import java.util.List;

public interface FindCustomer {
    CustomerRepositoryModel findByCustomerId(String customerId);
    List<CustomerRepositoryModel> findAll();
    List<CustomerRepositoryModel> findAllByCountryName(String countryName);
}
