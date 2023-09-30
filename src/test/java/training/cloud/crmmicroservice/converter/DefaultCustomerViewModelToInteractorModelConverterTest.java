package training.cloud.crmmicroservice.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import training.cloud.crmmicroservice.api.dto.CustomerViewModel;
import training.cloud.crmmicroservice.domain.interactor.model.CustomerInteractorModel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultCustomerViewModelToInteractorModelConverterTest {
    private DefaultCustomerViewModelToInteractorModelConverter converter;
    private ModelMapper modelMapper;
    private CustomerViewModel customerViewModel;
    private CustomerInteractorModel customerInteractorModel;

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

        customerViewModel = buildCustomerViewModel();
        customerInteractorModel = buildCustomerInteractorModel();

        modelMapper = mock(ModelMapper.class);
        converter = new DefaultCustomerViewModelToInteractorModelConverter(modelMapper);
    }

    @Test
    void testConvertViewModelToInteractorModelWhenViewModelIsProvided() {
        when(modelMapper.map(eq(customerViewModel), eq(CustomerInteractorModel.class))).thenReturn(customerInteractorModel);

        CustomerInteractorModel interactorModel = assertDoesNotThrow(() -> converter.convert(customerViewModel));

        assertNotNull(interactorModel);
        assertEquals(id, interactorModel.getId());
        assertEquals(customerId, interactorModel.getCustomerId());
        assertEquals(countryName, interactorModel.getCountryName());
        assertEquals(firstName, interactorModel.getFirstName());
        assertEquals(lastName, interactorModel.getLastName());
    }


    private CustomerViewModel buildCustomerViewModel() {
        customerViewModel = new CustomerViewModel();
        customerViewModel.setId(id);
        customerViewModel.setCustomerId(customerId);
        customerViewModel.setLastName(lastName);
        customerViewModel.setFirstName(firstName);
        customerViewModel.setCountryName(countryName);
        return customerViewModel;
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

}