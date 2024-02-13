package training.cloud.crmmicroservice.persistence.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;
import training.cloud.crmmicroservice.persistence.entity.Customer;

@Component
@RequiredArgsConstructor
public class DefaultCustomerRepositoryModelToEntityModelConverter implements CustomerRepositoryModelToEntityModelConverter {
    private final ModelMapper modelMapper;

    @Override
    public Customer toEntity(CustomerRepositoryModel model) {
        return modelMapper.map(model, Customer.class);
    }

    @Override
    public CustomerRepositoryModel fromEntity(Customer entity) {
        return modelMapper.map(entity, CustomerRepositoryModel.class);
    }
}
