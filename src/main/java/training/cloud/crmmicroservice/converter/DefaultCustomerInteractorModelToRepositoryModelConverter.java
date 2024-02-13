package training.cloud.crmmicroservice.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;
import training.cloud.crmmicroservice.domain.output.customer.model.CustomerRepositoryModel;


@Component
@RequiredArgsConstructor
public class DefaultCustomerInteractorModelToRepositoryModelConverter implements CustomerInteractorModelToRepositoryModelConverter {
    private final ModelMapper modelMapper;

    @Override
    public CustomerRepositoryModel convert(CustomerInteractorModel modelFrom) {
        return modelMapper.map(modelFrom, CustomerRepositoryModel.class);
    }
}
