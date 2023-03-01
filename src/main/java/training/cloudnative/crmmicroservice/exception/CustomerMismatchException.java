package training.cloudnative.crmmicroservice.exception;

public class CustomerMismatchException extends RuntimeException{
    public CustomerMismatchException(String exception) {
        super(exception);
    }
}
