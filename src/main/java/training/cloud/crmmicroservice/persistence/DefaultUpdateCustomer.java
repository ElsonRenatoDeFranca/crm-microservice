package training.cloud.crmmicroservice.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;
import training.cloud.crmmicroservice.persistence.converter.CustomerRepositoryModelToEntityModelConverter;
import training.cloud.crmmicroservice.persistence.entity.CustomerEntity;
import training.cloud.crmmicroservice.persistence.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class DefaultUpdateCustomer implements UpdateCustomer {
    private final CustomerRepository customerRepository;
    private final CustomerRepositoryModelToEntityModelConverter customerRepositoryModelToEntityModelConverter;

    @Override
    public CustomerRepositoryModel update(CustomerRepositoryModel customerRepositoryModel) {
        CustomerEntity customer = customerRepository.findByCustomerId(customerRepositoryModel.getCustomerId());

        return null;
    }

}
