package training.cloud.crmmicroservice.domain.interactor.checker;

import lombok.RequiredArgsConstructor;
import training.cloud.crmmicroservice.domain.interactor.FindCustomer;
import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;
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
