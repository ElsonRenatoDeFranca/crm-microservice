package training.cloud.crmmicroservice.exception;

import java.util.List;

public class DefaultCustomerNotFound  extends DomainMultipleIllegalArgumentsException {

    private static final long serialVersionUID = 3907962266523197219L;

    private static final String CUSTOMER_NOT_FOUND_ERROR_CODE = "PV-BCK-ACC-078";
    private static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer not found.";

    public DefaultCustomerNotFound() {
        super(List.of(new DefaultErrorDetail(CUSTOMER_NOT_FOUND_ERROR_CODE, CUSTOMER_NOT_FOUND_MESSAGE)));
    }

}
