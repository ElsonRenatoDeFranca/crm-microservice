package goodyear.tcs.hrmsystem.service;

import goodyear.tcs.hrmsystem.exception.EmployeeMismatchException;
import goodyear.tcs.hrmsystem.exception.EmployeeNotFoundException;
import goodyear.tcs.hrmsystem.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> findAll();

    EmployeeDto findByEmployeeId(String employeeId);

    void save(EmployeeDto employeeDto) throws EmployeeMismatchException;

    void deleteByEmployeeId(String employeeId) throws EmployeeNotFoundException;

    EmployeeDto updateByEmployeeId(String employeeId, EmployeeDto employeeDto)  throws EmployeeNotFoundException;

    List<EmployeeDto> findAllByCountryName(String countryName);
}
