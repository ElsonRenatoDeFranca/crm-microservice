package goodyear.tcs.hrmsystem.service.fixture;

import goodyear.tcs.hrmsystem.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeEntityServiceFixture {
    public static Employee getEmployee(String id, String employeeId, String firstName, String lastName) {
        return Employee.builder()
                .id(id)
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public static List<Employee> getEmployees(String id, String employeeId, String firstName, String lastName) {
        Employee employee = Employee.builder().employeeId(id)
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        return employees;
    }
}
