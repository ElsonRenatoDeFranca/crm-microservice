package goodyear.tcs.hrmsystem.exception;

public class EmployeeNotFoundException  extends RuntimeException{
    public EmployeeNotFoundException(String exception) {
        super(exception);
    }
}
