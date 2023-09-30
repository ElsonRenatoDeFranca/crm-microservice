package training.cloud.crmmicroservice.domain.interactor.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AbstractCustomerModel implements Serializable {
    private static final long serialVersionUID = -8316024030271035104L;
    private Long id;
    private String customerId;
    private String firstName;
    private String lastName;
    private String countryName;
}
