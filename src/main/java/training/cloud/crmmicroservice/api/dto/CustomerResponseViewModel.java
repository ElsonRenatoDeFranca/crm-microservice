package training.cloud.crmmicroservice.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "id" })
public class CustomerResponseViewModel extends AbstractCustomerViewModel {
}
