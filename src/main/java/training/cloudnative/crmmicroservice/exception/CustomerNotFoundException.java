package training.cloudnative.crmmicroservice.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String exception) {
        super(exception);
    }
}
