package training.cloud.crmmicroservice.domain.interactor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.domain.interactor.input.customer.FindCustomerInteractor;
import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;

import java.util.List;

@RequiredArgsConstructor
public final class DefaultFindCustomerInteractor implements FindCustomerInteractor {
    @Override
    public List<CustomerRepositoryModel> findAll() {
        return null;
    }

    @Override
    public CustomerRepositoryModel findByCustomerId(String customerId) {
        return null;
    }
}
