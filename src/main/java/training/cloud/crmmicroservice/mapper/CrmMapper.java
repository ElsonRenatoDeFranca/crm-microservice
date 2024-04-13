package training.cloud.crmmicroservice.mapper;


import training.cloud.crmmicroservice.model.CustomerDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import training.cloud.crmmicroservice.persistence.entity.CustomerEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CrmMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "countryName", source = "countryName")
    CustomerDto toCustomerDto(CustomerEntity customer);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "countryName", source = "countryName")
    CustomerEntity toCustomerEntity(CustomerDto customerDto);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    List<CustomerDto> customerEntityListToCustomerDtoList(List<CustomerEntity> customers);
}
