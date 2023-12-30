package training.cloud.crmmicroservice.domain.interactor.input.customer;

public interface DeleteCustomerInteractor {

    void softDelete(String  customerId);
    void hardDelete(String customerId);
}
