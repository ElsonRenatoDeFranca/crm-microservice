package training.cloudnative.crmmicroservice.exception;

public class EmployeeMismatchException extends RuntimeException{
    public EmployeeMismatchException(String exception) {
        super(exception);
    }
}
