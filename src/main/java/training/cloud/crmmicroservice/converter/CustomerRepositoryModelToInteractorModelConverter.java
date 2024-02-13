package training.cloud.crmmicroservice.converter;

import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;
import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;

public interface CustomerRepositoryModelToInteractorModelConverter extends ModelToModelConverter<CustomerRepositoryModel, CustomerInteractorModel> {
}