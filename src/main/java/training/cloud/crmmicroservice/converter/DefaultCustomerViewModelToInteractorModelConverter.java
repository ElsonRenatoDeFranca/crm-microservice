package training.cloud.crmmicroservice.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.api.dto.CustomerViewModel;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;


@Component
@RequiredArgsConstructor
public class DefaultCustomerViewModelToInteractorModelConverter implements CustomerViewModelToInteractorModelConverter {
    private final ModelMapper modelMapper;

    @Override
    public CustomerInteractorModel convert(CustomerViewModel modelFrom) {
        return modelMapper.map(modelFrom, CustomerInteractorModel.class);
    }
}
