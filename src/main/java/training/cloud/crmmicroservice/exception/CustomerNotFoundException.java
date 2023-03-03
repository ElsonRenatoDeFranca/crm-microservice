package training.cloud.crmmicroservice.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String exception) {
        super(exception);
    }
}
