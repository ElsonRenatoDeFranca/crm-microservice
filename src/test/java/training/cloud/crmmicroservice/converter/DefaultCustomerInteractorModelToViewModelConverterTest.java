package training.cloud.crmmicroservice.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import training.cloud.crmmicroservice.api.dto.CustomerRequestModel;
import training.cloud.crmmicroservice.domain.model.CustomerInteractorModel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultCustomerInteractorModelToViewModelConverterTest {

    private DefaultCustomerInteractorModelToViewModelConverter converter;
    private ModelMapper modelMapper;
    private CustomerInteractorModel customerInteractorModel;
    private CustomerRequestModel customerViewModel;

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
        customerViewModel = buildCustomerViewModel();

        modelMapper = mock(ModelMapper.class);
        converter = new DefaultCustomerInteractorModelToViewModelConverter(modelMapper);
    }


    @Test
    void testConvert() {
        when(modelMapper.map(eq(customerInteractorModel), eq(CustomerRequestModel.class))).thenReturn(customerViewModel);
        CustomerRequestModel viewModel = assertDoesNotThrow(() -> converter.convert(customerInteractorModel));
        assertNotNull(viewModel);
        assertEquals(id, viewModel.getId());
        assertEquals(customerId, viewModel.getCustomerId());
        assertEquals(countryName, viewModel.getCountryName());
        assertEquals(firstName, viewModel.getFirstName());
        assertEquals(lastName, viewModel.getLastName());
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

    private CustomerRequestModel buildCustomerViewModel() {
        customerViewModel = new CustomerRequestModel();
        customerViewModel.setId(id);
        customerViewModel.setCustomerId(customerId);
        customerViewModel.setLastName(lastName);
        customerViewModel.setFirstName(firstName);
        customerViewModel.setCountryName(countryName);
        return customerViewModel;
    }
}