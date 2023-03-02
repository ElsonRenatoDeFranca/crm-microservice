package training.cloud.crmmicroservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training.cloud.crmmicroservice.exception.CustomerMismatchException;
import training.cloud.crmmicroservice.exception.CustomerNotFoundException;
import training.cloud.crmmicroservice.mapper.CrmMapper;
import training.cloud.crmmicroservice.model.Customer;
import training.cloud.crmmicroservice.model.CustomerDto;
import training.cloud.crmmicroservice.repository.CrmRepository;

import java.util.List;

@Service
public class CrmService {
    private final CrmRepository crmRepository;
    private final CrmMapper crmMapper;

    private static final String CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE = "Customer not found";
    private static final String CUSTOMER_MISMATCH_EXCEPTION_MESSAGE = "Customer already exists";


    public CrmService(CrmRepository crmRepository, CrmMapper crmMapper) {
        this.crmRepository = crmRepository;
        this.crmMapper = crmMapper;
    }

    public List<CustomerDto> findAll() {
        return crmMapper.customerEntityListToCustomerDtoList(crmRepository.findAll());
    }

    public CustomerDto findByCustomerId(String customerId) throws CustomerNotFoundException {
        Customer customer = crmRepository.findByCustomerId(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return crmMapper.toCustomerDto(customer);
    }

    @Transactional
    public void save(CustomerDto customerDto) throws CustomerMismatchException {
        Customer customer = crmRepository.findByCustomerId(customerDto.getCustomerId());

        if (customer == null) {
            Customer newCustomer = crmMapper.toCustomer(customerDto);
            crmRepository.save(newCustomer);
        } else {
            throw new CustomerMismatchException(CUSTOMER_MISMATCH_EXCEPTION_MESSAGE);
        }
    }

    @Transactional
    public void deleteByCustomerId(String customerId) throws CustomerNotFoundException {
        Customer customer = crmRepository.findByCustomerId(customerId);

        if (customer != null) {
            crmRepository.deleteByCustomerId(customerId);
        } else {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Transactional
    public CustomerDto updateByCustomerId(CustomerDto customerDto, String customerId) throws CustomerNotFoundException {
        Customer existingCustomer = crmRepository.findByCustomerId(customerId);
        Customer updatedCustomer = crmMapper.toCustomer(customerDto);

        if (existingCustomer != null) {
            updatedCustomer.setCustomerId(existingCustomer.getCustomerId());
            crmRepository.deleteByCustomerId(customerId);
            crmRepository.save(updatedCustomer);
        } else {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return crmMapper.toCustomerDto(updatedCustomer);
    }

    public List<CustomerDto> findAllByCountryName(String countryName) {
        return crmMapper.customerEntityListToCustomerDtoList(crmRepository.findByCountryName(countryName));
    }

}
