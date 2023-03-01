package training.cloudnative.crmmicroservice.service;

import training.cloudnative.crmmicroservice.exception.EmployeeMismatchException;
import training.cloudnative.crmmicroservice.exception.EmployeeNotFoundException;
import training.cloudnative.crmmicroservice.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> findAll();

    EmployeeDto findByEmployeeId(String employeeId);

    void save(EmployeeDto employeeDto) throws EmployeeMismatchException;

    void deleteByEmployeeId(String employeeId) throws EmployeeNotFoundException;

    EmployeeDto updateByEmployeeId(String employeeId, EmployeeDto employeeDto)  throws EmployeeNotFoundException;

    List<EmployeeDto> findAllByCountryName(String countryName);
}
