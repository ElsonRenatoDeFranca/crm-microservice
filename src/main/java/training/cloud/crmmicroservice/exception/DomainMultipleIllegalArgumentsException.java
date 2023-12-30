package training.cloud.crmmicroservice.exception;

import java.util.Collection;
import java.util.List;

public class DomainMultipleIllegalArgumentsException extends IllegalArgumentException implements MultipleErrorDetail {

    private static final long serialVersionUID = 7141563955697159434L;

    private final List<ErrorDetail> details;

    public DomainMultipleIllegalArgumentsException(String errorCode, String errorMessage) {
        this(List.of(new DefaultErrorDetail(errorCode, errorMessage)));
    }

    public DomainMultipleIllegalArgumentsException(Collection<ErrorDetail> errors) {
        super("Failure occurred: " + errors);
        details = createErrorList(errors);
    }

    private static List<ErrorDetail> createErrorList(Collection<ErrorDetail> errors) {
        return errors == null ? List.of() : List.copyOf(errors);
    }

    @Override
    public List<ErrorDetail> getDetails() {
        return details;
    }

}
