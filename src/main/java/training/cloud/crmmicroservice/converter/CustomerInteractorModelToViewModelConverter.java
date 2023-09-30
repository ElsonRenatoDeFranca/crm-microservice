package training.cloud.crmmicroservice.converter;

import training.cloud.crmmicroservice.api.dto.CustomerViewModel;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;

public interface CustomerInteractorModelToViewModelConverter extends ModelToModelConverter<CustomerInteractorModel, CustomerViewModel>{
}
