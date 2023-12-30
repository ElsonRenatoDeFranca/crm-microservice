package training.cloud.crmmicroservice.persistence;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.domain.interactor.FindCustomer;
import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;
import training.cloud.crmmicroservice.persistence.converter.CustomerRepositoryModelToEntityModelConverter;
import training.cloud.crmmicroservice.persistence.entity.Customer;
import training.cloud.crmmicroservice.persistence.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultFindCustomer implements FindCustomer {

    private final CustomerRepository customerRepository;
    private final CustomerRepositoryModelToEntityModelConverter customerRepositoryModelToEntityModelConverter;

    @Override
    public CustomerRepositoryModel findByCustomerId(String customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        return customerRepositoryModelToEntityModelConverter.fromEntity(customer);
    }

    @Override
    public List<CustomerRepositoryModel> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerRepositoryModelToEntityModelConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
