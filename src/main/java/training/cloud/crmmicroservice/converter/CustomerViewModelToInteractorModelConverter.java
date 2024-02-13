package training.cloud.crmmicroservice.converter;

import training.cloud.crmmicroservice.api.dto.CustomerRequestModel;
import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;

public interface CustomerViewModelToInteractorModelConverter extends ModelToModelConverter<CustomerRequestModel, CustomerInteractorModel>{
}
