package training.cloud.crmmicroservice.domain;

import lombok.RequiredArgsConstructor;
import training.cloud.crmmicroservice.converter.CustomerRepositoryModelToInteractorModelConverter;
import training.cloud.crmmicroservice.domain.input.customer.FindCustomerInteractor;
import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public final class DefaultFindCustomerInteractor implements FindCustomerInteractor {
    private final FindCustomer findCustomer;
    private final CustomerRepositoryModelToInteractorModelConverter converter;

    @Override
    public List<CustomerInteractorModel> findAll() {
        return findCustomer.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerInteractorModel findByCustomerId(String customerId) {
        return converter.convert(findCustomer.findByCustomerId(customerId));
    }

    @Override
    public List<CustomerInteractorModel> findAllByCountryName(String countryName) {
        return findCustomer.findAllByCountryName(countryName)
                .stream().map(converter::convert)
                .collect(Collectors.toList());
    }
}
