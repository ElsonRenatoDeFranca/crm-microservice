package training.cloud.crmmicroservice.domain.interactor;

import lombok.RequiredArgsConstructor;
import training.cloud.crmmicroservice.domain.interactor.input.customer.FindCustomerInteractor;
import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;

import java.util.List;

@RequiredArgsConstructor
public final class DefaultFindCustomerInteractor implements FindCustomerInteractor {
    private final FindCustomer findCustomer;

    @Override
    public List<CustomerRepositoryModel> findAll() {
        return findCustomer.findAll();
    }

    @Override
    public CustomerRepositoryModel findByCustomerId(String customerId) {
        return findCustomer.findByCustomerId(customerId);
    }
}
