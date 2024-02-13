package training.cloud.crmmicroservice.domain.checker;

import lombok.RequiredArgsConstructor;
import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;
import training.cloud.crmmicroservice.exception.DefaultCustomerNotFound;

@RequiredArgsConstructor
public class DefaultCustomerChecker implements CustomerChecker {
    private final FindCustomer findCustomer;

    @Override
    public void checkByCustomerId(String customerId) {
        CustomerRepositoryModel customerRepositoryModel = findCustomer.findByCustomerId(customerId);
        if (customerRepositoryModel == null) {
            throw new DefaultCustomerNotFound();
        }
    }
}
