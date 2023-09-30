package training.cloud.crmmicroservice.converter;

import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;
import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;

public interface CustomerInteractorModelToRepositoryModelConverter extends ModelToModelConverter<CustomerInteractorModel, CustomerRepositoryModel> {
}
