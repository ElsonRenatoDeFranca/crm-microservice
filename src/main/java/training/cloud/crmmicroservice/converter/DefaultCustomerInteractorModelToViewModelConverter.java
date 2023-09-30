package training.cloud.crmmicroservice.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.api.dto.CustomerViewModel;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;

@Component
@RequiredArgsConstructor
public class DefaultCustomerInteractorModelToViewModelConverter implements CustomerInteractorModelToViewModelConverter {
    private final ModelMapper modelMapper;

    @Override
    public CustomerViewModel convert(CustomerInteractorModel modelFrom) {
        return modelMapper.map(modelFrom, CustomerViewModel.class);
    }
}
