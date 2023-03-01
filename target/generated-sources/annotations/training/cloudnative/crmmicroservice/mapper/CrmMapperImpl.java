package training.cloudnative.crmmicroservice.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import training.cloudnative.crmmicroservice.model.Customer;
import training.cloudnative.crmmicroservice.model.Customer.CustomerBuilder;
import training.cloudnative.crmmicroservice.model.CustomerDto;
import training.cloudnative.crmmicroservice.model.CustomerDto.CustomerDtoBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-01T17:52:30-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class CrmMapperImpl implements CrmMapper {

    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.id( customer.getId() );
        customerDto.customerId( customer.getCustomerId() );
        customerDto.firstName( customer.getFirstName() );
        customerDto.lastName( customer.getLastName() );
        customerDto.countryName( customer.getCountryName() );

        return customerDto.build();
    }

    @Override
    public Customer toCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        CustomerBuilder customer = Customer.builder();

        customer.id( customerDto.getId() );
        customer.customerId( customerDto.getCustomerId() );
        customer.firstName( customerDto.getFirstName() );
        customer.lastName( customerDto.getLastName() );
        customer.countryName( customerDto.getCountryName() );

        return customer.build();
    }

    @Override
    public List<CustomerDto> customerEntityListToCustomerDtoList(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }

        List<CustomerDto> list = new ArrayList<CustomerDto>( customers.size() );
        for ( Customer customer : customers ) {
            list.add( toCustomerDto( customer ) );
        }

        return list;
    }
}
