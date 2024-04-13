package training.cloud.crmmicroservice.persistence.converter;

import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;
import training.cloud.crmmicroservice.persistence.entity.CustomerEntity;

public interface CustomerRepositoryModelToEntityModelConverter extends ModelEntityConverter<CustomerRepositoryModel, CustomerEntity>{
}
