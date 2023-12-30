package training.cloud.crmmicroservice.converter;

import training.cloud.crmmicroservice.api.dto.CustomerResponseViewModel;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;

public interface CustomerInteractorModelToResponseViewModelConverter  extends ModelToModelConverter<CustomerInteractorModel, CustomerResponseViewModel> {
}
