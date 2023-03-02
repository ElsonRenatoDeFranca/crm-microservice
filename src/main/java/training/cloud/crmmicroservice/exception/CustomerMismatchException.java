package training.cloud.crmmicroservice.exception;

public class CustomerMismatchException extends RuntimeException{
    public CustomerMismatchException(String exception) {
        super(exception);
    }
}
