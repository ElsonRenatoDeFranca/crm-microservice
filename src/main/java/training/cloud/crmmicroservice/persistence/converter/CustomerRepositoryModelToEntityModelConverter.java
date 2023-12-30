package training.cloud.crmmicroservice.persistence.converter;

import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;
import training.cloud.crmmicroservice.persistence.entity.Customer;

public interface CustomerRepositoryModelToEntityModelConverter extends ModelEntityConverter<CustomerRepositoryModel, Customer>{
}
