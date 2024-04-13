package training.cloud.crmmicroservice.domain;

import lombok.RequiredArgsConstructor;
import training.cloud.crmmicroservice.domain.input.customer.DeleteCustomerInteractor;
import training.cloud.crmmicroservice.domain.interactor.DeleteCustomer;
import training.cloud.crmmicroservice.domain.interactor.FindCustomer;
import training.cloud.crmmicroservice.domain.interactor.UpdateCustomer;

@RequiredArgsConstructor
public class DefaultDeleteCustomerInteractor implements DeleteCustomerInteractor {
    private final DeleteCustomer deleteCustomer;
    private final UpdateCustomer updateCustomer;
    private final FindCustomer findCustomer;

    @Override
    public void softDelete(String customerId) {
        var customer = findCustomer.findByCustomerId(customerId);
        var updatedCustomer = updateCustomer.update(customer);
    }

    @Override
    public void hardDelete(String customerId) {
        deleteCustomer.deleteCustomerById(customerId);
    }
}
