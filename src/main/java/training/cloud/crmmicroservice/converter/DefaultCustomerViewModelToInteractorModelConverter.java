package training.cloud.crmmicroservice.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import training.cloud.crmmicroservice.api.dto.CustomerRequestModel;
import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;


@Component
@RequiredArgsConstructor
public class DefaultCustomerViewModelToInteractorModelConverter implements CustomerViewModelToInteractorModelConverter {
    private final ModelMapper modelMapper;

    @Override
    public CustomerInteractorModel convert(CustomerRequestModel modelFrom) {
        return modelMapper.map(modelFrom, CustomerInteractorModel.class);
    }
}
