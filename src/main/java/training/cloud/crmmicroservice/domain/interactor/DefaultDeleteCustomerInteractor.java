package training.cloud.crmmicroservice.domain.interactor;

import lombok.RequiredArgsConstructor;
import training.cloud.crmmicroservice.domain.interactor.input.customer.DeleteCustomerInteractor;

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
