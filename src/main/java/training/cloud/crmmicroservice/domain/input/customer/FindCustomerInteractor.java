package training.cloud.crmmicroservice.domain.input.customer;

import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;

import java.util.List;

public interface FindCustomerInteractor {
    List<CustomerInteractorModel> findAll();
    CustomerInteractorModel findByCustomerId(String customerId);

    List<CustomerInteractorModel> findAllByCountryName(String countryName);

}
