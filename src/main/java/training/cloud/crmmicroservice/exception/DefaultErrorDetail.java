package training.cloud.crmmicroservice.exception;


import lombok.Data;

@Data
public class DefaultErrorDetail  implements ErrorDetail {
    private static final long serialVersionUID = 4706743933616874371L;

    private final String code;
    private final String message;

}
