package goodyear.tcs.hrmsystem.service.fixture;

import goodyear.tcs.hrmsystem.model.Employee;
import goodyear.tcs.hrmsystem.model.EmployeeDto;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDtoServiceFixture {

    public static EmployeeDto getEmployeeDto(String id, String employeeId, String firstName, String lastName) {
        return EmployeeDto.builder()
                .id(id)
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public static List<EmployeeDto> getEmployeeDtoList(String id, String employeeId, String firstName, String lastName) {
        EmployeeDto employeeDto = EmployeeDto.builder().employeeId(id)
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(employeeDto);

        return employeeDtoList;
    }
}
