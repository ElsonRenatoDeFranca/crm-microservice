package training.cloudnative.crmmicroservice.service;

import training.cloudnative.crmmicroservice.exception.EmployeeMismatchException;
import training.cloudnative.crmmicroservice.exception.EmployeeNotFoundException;
import training.cloudnative.crmmicroservice.mapper.EmployeeMapper;
import training.cloudnative.crmmicroservice.model.Employee;
import training.cloudnative.crmmicroservice.model.EmployeeDto;
import training.cloudnative.crmmicroservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.employeeEntityListToEmployeeDtoList(employees);
    }

    @Override
    public EmployeeDto findByEmployeeId(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employeeMapper.toEmployeeDto(employee);
    }

    @Override
    @Transactional
    public void save(EmployeeDto employeeDto) throws EmployeeMismatchException {
        Employee employee = employeeRepository.findByEmployeeId(employeeDto.getEmployeeId());

        if (employee == null) {
            Employee newEmployee = employeeMapper.toEmployee(employeeDto);
            employeeRepository.save(newEmployee);
        } else {
            throw new EmployeeMismatchException("Employee already exists");
        }
    }

    @Override
    @Transactional
    public void deleteByEmployeeId(String employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee != null) {
            employeeRepository.deleteByEmployeeId(employeeId);
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    @Transactional
    public EmployeeDto updateByEmployeeId(String employeeId, EmployeeDto employeeDto) throws EmployeeNotFoundException {
        Employee existingEmployee = employeeRepository.findByEmployeeId(employeeId);
        Employee updatedEmployee = employeeMapper.toEmployee(employeeDto);

        if (existingEmployee != null) {
            updatedEmployee.setEmployeeId(existingEmployee.getEmployeeId());
            employeeRepository.deleteByEmployeeId(employeeId);
            employeeRepository.save(updatedEmployee);
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employeeMapper.toEmployeeDto(updatedEmployee);
    }

    @Override
    public List<EmployeeDto> findAllByCountryName(String countryName) {
        List<Employee> employeeDtoList = employeeRepository.findByCountryName(countryName);
        return employeeMapper.employeeEntityListToEmployeeDtoList(employeeDtoList);
    }
}
