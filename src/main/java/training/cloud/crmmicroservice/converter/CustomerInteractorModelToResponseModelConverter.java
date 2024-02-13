package training.cloud.crmmicroservice.converter;

import training.cloud.crmmicroservice.api.dto.CustomerResponseModel;
import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;

public interface CustomerInteractorModelToResponseModelConverter extends ModelToModelConverter<CustomerInteractorModel, CustomerResponseModel> {
}
