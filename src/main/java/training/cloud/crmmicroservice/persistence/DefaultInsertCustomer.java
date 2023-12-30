package training.cloud.crmmicroservice.persistence;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.domain.interactor.InsertCustomer;
import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;
import training.cloud.crmmicroservice.persistence.converter.CustomerRepositoryModelToEntityModelConverter;
import training.cloud.crmmicroservice.persistence.entity.Customer;
import training.cloud.crmmicroservice.persistence.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class DefaultInsertCustomer implements InsertCustomer {

    private final CustomerRepository customerRepository;
    private final CustomerRepositoryModelToEntityModelConverter customerRepositoryModelToEntityModelConverter;

    @Override
    public CustomerRepositoryModel insert(CustomerRepositoryModel customerRepositoryModel) {
        Customer toSaveCustomer = customerRepositoryModelToEntityModelConverter.toEntity(customerRepositoryModel);
        Customer savedCustomer = customerRepository.save(toSaveCustomer);
        return customerRepositoryModelToEntityModelConverter.fromEntity(savedCustomer);
    }
}
