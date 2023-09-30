package training.cloud.crmmicroservice.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;
import training.cloud.crmmicroservice.domain.interactor.output.customer.model.CustomerRepositoryModel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultCustomerInteractorModelToRepositoryModelConverterTest {

    private DefaultCustomerInteractorModelToRepositoryModelConverter converter;
    private CustomerInteractorModel customerInteractorModel;
    private CustomerRepositoryModel customerRepositoryModel;
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

        customerInteractorModel = buildCustomerInteractorModel();
        customerRepositoryModel = buildCustomerRepositoryModel();

        modelMapper = mock(ModelMapper.class);
        converter = new DefaultCustomerInteractorModelToRepositoryModelConverter(modelMapper);
    }

    @Test
    void testConvertInteractorToRepositoryModelWhenCustomerInteractorIsProvided() {
        when(modelMapper.map(eq(customerInteractorModel), eq(CustomerRepositoryModel.class))).thenReturn(customerRepositoryModel);

        CustomerRepositoryModel repositoryModel = assertDoesNotThrow(() -> converter.convert(customerInteractorModel));

        assertNotNull(repositoryModel);
        assertEquals(id, repositoryModel.getId());
        assertEquals(customerId, repositoryModel.getCustomerId());
        assertEquals(countryName, repositoryModel.getCountryName());
        assertEquals(firstName, repositoryModel.getFirstName());
        assertEquals(lastName, repositoryModel.getLastName());
    }

    private CustomerInteractorModel buildCustomerInteractorModel() {
        customerInteractorModel = new CustomerInteractorModel();
        customerInteractorModel.setId(id);
        customerInteractorModel.setCustomerId(customerId);
        customerInteractorModel.setLastName(lastName);
        customerInteractorModel.setFirstName(firstName);
        customerInteractorModel.setCountryName(countryName);
        return customerInteractorModel;
    }

    private CustomerRepositoryModel buildCustomerRepositoryModel() {
        CustomerRepositoryModel customerRepositoryModel = new CustomerRepositoryModel();
        customerRepositoryModel.setId(id);
        customerRepositoryModel.setCustomerId(customerId);
        customerRepositoryModel.setLastName(lastName);
        customerRepositoryModel.setFirstName(firstName);
        customerRepositoryModel.setCountryName(countryName);
        return customerRepositoryModel;
    }


}