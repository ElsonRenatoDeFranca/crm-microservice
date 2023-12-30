package training.cloud.crmmicroservice.exception;

import java.util.List;

public interface MultipleErrorDetail {
    List<ErrorDetail> getDetails();
}
