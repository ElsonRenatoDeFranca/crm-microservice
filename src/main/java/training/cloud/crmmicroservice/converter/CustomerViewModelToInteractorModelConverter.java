package training.cloud.crmmicroservice.converter;

import training.cloud.crmmicroservice.api.dto.CustomerViewModel;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;

public interface CustomerViewModelToInteractorModelConverter extends ModelToModelConverter<CustomerViewModel, CustomerInteractorModel>{
}
