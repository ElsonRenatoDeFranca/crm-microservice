package goodyear.tcs.hrmsystem.service.fixture;

import goodyear.tcs.hrmsystem.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeEntityServiceFixture {
    public static Employee getEmployee(Long id, String employeeId, String firstName, String lastName, String countryName) {
        return Employee.builder()
                .id(id)
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .countryName(countryName)
                .build();
    }

    public static List<Employee> getEmployees(Long id, String employeeId, String firstName, String lastName, String countryName) {
        Employee employee = Employee.builder()
                .id(id)
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .countryName(countryName)
                .build();

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        return employees;
    }
}
