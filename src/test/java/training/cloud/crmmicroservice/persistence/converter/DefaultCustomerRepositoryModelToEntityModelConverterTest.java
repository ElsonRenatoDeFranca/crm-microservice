package training.cloud.crmmicroservice.persistence.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;
import training.cloud.crmmicroservice.persistence.entity.Customer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultCustomerRepositoryModelToEntityModelConverterTest {

    private DefaultCustomerRepositoryModelToEntityModelConverter converter;
    private CustomerRepositoryModel customerRepositoryModel;
    private Customer customerEntity;
    private ModelMapper modelMapper;

    private Long id;
    private String customerId;
    private String firstName;
    private String lastName;
    private String countryName;


    @BeforeEach
    void setUp() {
        id = 1L;
        customerId = "customer-id";
        firstName = "first-name";
        lastName = "last-name";
        countryName = "country-name";

        buildCustomerRepositoryModel();
        buildCustomerEntity();

        modelMapper = mock(ModelMapper.class);
        converter = new DefaultCustomerRepositoryModelToEntityModelConverter(modelMapper);
    }

    @Test
    void testConvertRepositoryModelToEntityModelWhenCustomerRepositoryIsProvided() {
        when(modelMapper.map(eq(customerRepositoryModel), eq(Customer.class))).thenReturn(customerEntity);

        Customer customerEntityModel = assertDoesNotThrow(() -> converter.toEntity(customerRepositoryModel));

        assertNotNull(customerEntityModel);
        assertEquals(id, customerEntityModel.getId());
        assertEquals(customerId, customerEntityModel.getCustomerId());
        assertEquals(countryName, customerEntityModel.getCountryName());
        assertEquals(firstName, customerEntityModel.getFirstName());
        assertEquals(lastName, customerEntityModel.getLastName());
    }

    @Test
    void testConvertCustomerEntityModelToRepositoryModelWhenCustomerEntityModelIsProvided() {
        when(modelMapper.map(eq(customerEntity), eq(CustomerRepositoryModel.class))).thenReturn(customerRepositoryModel);

        CustomerRepositoryModel repositoryModel = assertDoesNotThrow(() -> converter.fromEntity(customerEntity));

        assertNotNull(repositoryModel);
        assertEquals(id, repositoryModel.getId());
        assertEquals(customerId, repositoryModel.getCustomerId());
        assertEquals(countryName, repositoryModel.getCountryName());
        assertEquals(firstName, repositoryModel.getFirstName());
        assertEquals(lastName, repositoryModel.getLastName());
    }

    private void buildCustomerRepositoryModel() {
        customerRepositoryModel = new CustomerRepositoryModel();
        customerRepositoryModel.setId(id);
        customerRepositoryModel.setCustomerId(customerId);
        customerRepositoryModel.setLastName(lastName);
        customerRepositoryModel.setFirstName(firstName);
        customerRepositoryModel.setCountryName(countryName);

    }

    private void buildCustomerEntity() {
        customerEntity = new Customer();
        customerEntity.setId(id);
        customerEntity.setCustomerId(customerId);
        customerEntity.setLastName(lastName);
        customerEntity.setFirstName(firstName);
        customerEntity.setCountryName(countryName);
    }


}