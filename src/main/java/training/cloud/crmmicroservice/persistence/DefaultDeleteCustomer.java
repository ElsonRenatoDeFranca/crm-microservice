package training.cloud.crmmicroservice.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.domain.interactor.DeleteCustomer;
import training.cloud.crmmicroservice.persistence.repository.CustomerRepository;


@Component
@RequiredArgsConstructor
public class DefaultDeleteCustomer  implements DeleteCustomer {

    private final CustomerRepository customerRepository;

    @Override
    public void deleteCustomerById(String customerId) {
        customerRepository.deleteByCustomerId(customerId);
    }

}
