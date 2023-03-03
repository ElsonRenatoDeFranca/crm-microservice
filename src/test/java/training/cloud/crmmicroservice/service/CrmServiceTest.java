package training.cloud.crmmicroservice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import training.cloud.crmmicroservice.model.CustomerDto;
import training.cloud.crmmicroservice.exception.CustomerMismatchException;
import training.cloud.crmmicroservice.exception.CustomerNotFoundException;
import training.cloud.crmmicroservice.mapper.CrmMapper;
import training.cloud.crmmicroservice.model.Customer;
import training.cloud.crmmicroservice.repository.CrmRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static training.cloud.crmmicroservice.common.JsonReaderMultipleResultsHelper.mockMultipleResultsFromJson;
import static training.cloud.crmmicroservice.common.JsonReaderSingleResultHelper.mockSingleResultFromJson;


@ExtendWith(MockitoExtension.class)
class CrmServiceTest {
    private static final String CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE = "Customer not found";
    private static final String CUSTOMER_MISMATCH_EXCEPTION = "Customer already exists";
    @Mock
    private CrmRepository crmRepository;
    @Mock
    private CrmMapper crmMapper;
    @InjectMocks
    private CrmService crmService;


    @Test
    void shouldReturnListOfEmployeesWhenFindAllIsCalledAndThereAreItemsInTheDatabase() throws IOException {
        CustomerDto customerDto = CustomerDto.builder().id(1L).customerId("123").firstName("Pete").lastName("Sampras").countryName("United States").build();

        List<Customer> expectedCustomerList = mockMultipleResultsFromJson("mock/crm/customerEntityMockResponses.json", Customer.class);
        List<CustomerDto> expectedCustomerDtoList = mockMultipleResultsFromJson("mock/crm/customerDtoMockResponses.json", CustomerDto.class);

        when(crmRepository.findAll()).thenReturn(expectedCustomerList);
        when(crmMapper.customerEntityListToCustomerDtoList(any())).thenReturn(expectedCustomerDtoList);

        List<CustomerDto> actualCustomerDtoList = crmService.findAll();

        verify(crmRepository, times(1)).findAll();

        assertThat(actualCustomerDtoList, is(notNullValue()));
        //assertThat(actualCustomerDtoList,
        //        hasItem(hasProperty("customerId", is("123"))));

    }


    @Test
    void shouldReturnEmptyListWhenFindAllIsCalledAndThereAreNoItemInTheDatabase() {
        String customerId = "123";

        when(crmRepository.findAll()).thenReturn(Collections.emptyList());
        when(crmMapper.customerEntityListToCustomerDtoList(any())).thenReturn(Collections.emptyList());

        List<CustomerDto> actualCustomerDtoList = crmService.findAll();

        verify(crmRepository, times(1)).findAll();

        assertThat(actualCustomerDtoList, is(notNullValue()));
        assertThat(actualCustomerDtoList, not(
                hasItem(hasProperty("customerId", is(customerId))))
        );

    }

    @Test
    void shouldReturnListOfCustomersWhenFindAllByCountryNameIsCalled() throws IOException {
        String countryName = "Brazil";

        List<Customer> expectedCustomerList = mockMultipleResultsFromJson("mock/crm/customerEntityMockResponses.json", Customer.class);
        List<CustomerDto> expectedCustomerDtoList = mockMultipleResultsFromJson("mock/crm/customerDtoMockResponses.json", CustomerDto.class);

        when(crmRepository.findByCountryName(eq(countryName))).thenReturn(expectedCustomerList);
        when(crmMapper.customerEntityListToCustomerDtoList(any())).thenReturn(expectedCustomerDtoList);

        List<CustomerDto> actualCustomerDtoList = crmService.findAllByCountryName(countryName);

        verify(crmRepository, times(1)).findByCountryName(eq(countryName));

        assertThat(actualCustomerDtoList, is(notNullValue()));
        //assertThat(actualCustomerDtoList,
        //        hasItem(hasProperty("countryName", is(countryName))));

    }

    @Test
    void shouldReturnCustomerDtoWhenFindByCustomerIdIsFoundInTheDatabase() throws IOException {
        String customerId = "045ABC";
        Customer expectedCustomer = mockSingleResultFromJson("mock/crm/customerEntityMockResponse.json", Customer.class);
        CustomerDto expectedCustomerDto = mockSingleResultFromJson("mock/crm/customerDtoMockResponse.json", CustomerDto.class);

        when(crmRepository.findByCustomerId(eq(customerId))).thenReturn(expectedCustomer);
        when(crmMapper.toCustomerDto(eq(expectedCustomer))).thenReturn(expectedCustomerDto);

        CustomerDto actualCustomerDto = crmService.findByCustomerId(customerId);
        verify(crmRepository, times(1)).findByCustomerId(eq(customerId));

        assertThat(actualCustomerDto, is(notNullValue()));
        assertThat(actualCustomerDto, is(equalTo(expectedCustomerDto)));

    }

    @Test
    void shouldThrowCustomerNotFoundExceptionWhenCustomerIdIsNotFoundInTheDatabase() {
        String customerId = "2022";
        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> crmService.findByCustomerId(customerId))
                .withMessage(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);

    }

    @Test
    void shouldThrowCustomerMismatchExceptionWhenCustomerAlreadyExists() throws IOException {
        String customerId = "789";
        Customer expectedCustomer = mockSingleResultFromJson("mock/crm/customerEntityMockResponse.json", Customer.class);
        CustomerDto expectedCustomerDto = mockSingleResultFromJson("mock/crm/customerDtoMockResponse.json", CustomerDto.class);

        when(crmRepository.findByCustomerId(eq(customerId))).thenReturn(expectedCustomer);

        Assertions.assertThatExceptionOfType(CustomerMismatchException.class)
                .isThrownBy(() -> crmService.save(expectedCustomerDto))
                .withMessage(CUSTOMER_MISMATCH_EXCEPTION);
    }

    @Test
    void shouldSaveCustomerWhenNewCustomerIsProvided() throws IOException {
        String customerId = "789";
        Customer expectedCustomer = mockSingleResultFromJson("mock/crm/customerEntityMockResponse.json", Customer.class);
        CustomerDto expectedCustomerDto = mockSingleResultFromJson("mock/crm/customerDtoMockResponse.json", CustomerDto.class);

        when(crmRepository.findByCustomerId(eq(customerId))).thenReturn(null);
        when(crmMapper.toCustomer(eq(expectedCustomerDto))).thenReturn(expectedCustomer);

        this.crmService.save(expectedCustomerDto);

        verify(crmRepository, times(1)).findByCustomerId(eq(customerId));
    }

    @Test
    void shouldDeleteByCustomerIdSuccessfullyWhenCustomerExists() throws IOException {
        String customerId = "789";
        Customer expectedCustomer = mockSingleResultFromJson("mock/crm/customerEntityMockResponse.json", Customer.class);

        when(crmRepository.findByCustomerId(eq(customerId))).thenReturn(expectedCustomer);

        this.crmService.deleteByCustomerId(customerId);
        verify(crmRepository, times(1)).deleteByCustomerId(eq(customerId));

    }

    @Test
    void shouldThrowCustomerNotFoundExceptionWhenCustomerDoesNotExist() {
        String customerId = "045ABC";

        when(crmRepository.findByCustomerId(eq(customerId))).thenReturn(null);

        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> crmService.deleteByCustomerId(customerId))
                .withMessage(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    @Test
    void shouldUpdateByCustomerIdWhenUpdateByCustomerIdIsCalledAndCustomerExists() throws IOException {
        String customerId = "789";
        Customer expectedCustomer = mockSingleResultFromJson("mock/crm/customerEntityMockResponse.json", Customer.class);
        CustomerDto expectedCustomerDto = mockSingleResultFromJson("mock/crm/customerDtoMockResponse.json", CustomerDto.class);

        when(crmRepository.findByCustomerId(eq(customerId))).thenReturn(expectedCustomer);
        when(crmMapper.toCustomer(eq(expectedCustomerDto))).thenReturn(expectedCustomer);

        this.crmService.updateByCustomerId(expectedCustomerDto, customerId);

        verify(crmRepository, times(1)).deleteByCustomerId(eq(customerId));
        verify(crmRepository, times(1)).save(eq(expectedCustomer));
    }


    @Test
    void shouldThrowCustomerNotFoundExceptionWhenUpdateByCustomerIdIsCalledAndCustomerDoesNotExist() throws IOException {
        String customerId = "045ABC";
        Customer expectedCustomer = mockSingleResultFromJson("mock/crm/customerEntityMockResponse.json", Customer.class);
        CustomerDto expectedCustomerDto = mockSingleResultFromJson("mock/crm/customerDtoMockResponse.json", CustomerDto.class);

        when(crmRepository.findByCustomerId(eq(customerId))).thenReturn(null);
        when(crmMapper.toCustomer(eq(expectedCustomerDto))).thenReturn(expectedCustomer);

        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> crmService.updateByCustomerId(expectedCustomerDto, customerId))
                .withMessage(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}