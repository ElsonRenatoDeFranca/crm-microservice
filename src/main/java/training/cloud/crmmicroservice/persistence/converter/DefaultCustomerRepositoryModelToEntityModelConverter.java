package training.cloud.crmmicroservice.persistence.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;
import training.cloud.crmmicroservice.persistence.entity.CustomerEntity;

@Component
@RequiredArgsConstructor
public class DefaultCustomerRepositoryModelToEntityModelConverter implements CustomerRepositoryModelToEntityModelConverter {
    private final ModelMapper modelMapper;

    @Override
    public CustomerEntity toEntity(CustomerRepositoryModel customerRepositoryModel) {
        return modelMapper.map(customerRepositoryModel, CustomerEntity.class);
    }

    @Override
    public CustomerRepositoryModel fromEntity(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerRepositoryModel.class);
    }
}
