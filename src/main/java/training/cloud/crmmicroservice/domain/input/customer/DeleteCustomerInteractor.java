package training.cloud.crmmicroservice.domain.input.customer;

public interface DeleteCustomerInteractor {

    void softDelete(String  customerId);
    void hardDelete(String customerId);
}
