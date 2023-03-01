package training.cloudnative.crmmicroservice.service;

import training.cloudnative.crmmicroservice.exception.CustomerMismatchException;
import training.cloudnative.crmmicroservice.exception.CustomerNotFoundException;
import training.cloudnative.crmmicroservice.exception.EmployeeMismatchException;
import training.cloudnative.crmmicroservice.mapper.CrmMapper;
import training.cloudnative.crmmicroservice.model.Customer;
import training.cloudnative.crmmicroservice.model.CustomerDto;
import training.cloudnative.crmmicroservice.repository.CrmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CrmService {
    private final CrmRepository crmRepository;
    private final CrmMapper crmMapper;

    public CrmService(CrmRepository crmRepository, CrmMapper crmMapper) {
        this.crmRepository = crmRepository;
        this.crmMapper = crmMapper;
    }

    public List<CustomerDto> findAll() {
        return crmMapper.customerEntityListToCustomerDtoList(crmRepository.findAll());
    }

    public CustomerDto findByCustomerId(String customerId) {
        return crmMapper.toCustomerDto(crmRepository.findByCustomerId(customerId));
    }

    @Transactional
    public void save(CustomerDto customerDto) throws CustomerMismatchException {
        Customer customer = crmRepository.findByCustomerId(customerDto.getCustomerId());

        if (customer == null) {
            Customer newCustomer = crmMapper.toCustomer(customerDto);
            crmRepository.save(newCustomer);
        } else {
            throw new EmployeeMismatchException("Customer already exists");
        }
    }

    @Transactional
    public void deleteByCustomerId(String customerId) throws CustomerNotFoundException {
        Customer customer = crmRepository.findByCustomerId(customerId);

        if (customer != null) {
            crmRepository.deleteByCustomerId(customerId);
        } else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Transactional
    public CustomerDto updateByCustomerId(String customerId, CustomerDto customerDto) throws CustomerNotFoundException {
        Customer existingCustomer = crmRepository.findByCustomerId(customerId);
        Customer updatedCustomer = crmMapper.toCustomer(customerDto);

        if (existingCustomer != null) {
            updatedCustomer.setCustomerId(existingCustomer.getCustomerId());
            crmRepository.deleteByCustomerId(customerId);
            crmRepository.save(updatedCustomer);
        } else {
            throw new CustomerNotFoundException("Customer not found");
        }
        return crmMapper.toCustomerDto(updatedCustomer);
    }

    public List<CustomerDto> findAllByCountryName(String countryName) {
        return crmMapper.customerEntityListToCustomerDtoList(crmRepository.findByCountryName(countryName));
    }

}
