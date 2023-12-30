package training.cloud.crmmicroservice.exception;

import java.io.Serializable;

public interface ErrorDetail extends Serializable {
    String getCode();

    String getMessage();
}
