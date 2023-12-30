package training.cloud.crmmicroservice.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;
import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;
@Component
@RequiredArgsConstructor
public class DefaultCustomerRepositoryModelToInteractorModelConverter implements CustomerRepositoryModelToInteractorModelConverter{
    private final ModelMapper modelMapper;
    @Override
    public CustomerInteractorModel convert(CustomerRepositoryModel modelFrom) {
        return modelMapper.map(modelFrom, CustomerInteractorModel.class);

    }
}
