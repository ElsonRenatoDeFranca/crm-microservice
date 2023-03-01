package training.cloudnative.crmmicroservice.service.fixture;

import training.cloudnative.crmmicroservice.model.EmployeeDto;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDtoServiceFixture {

    public static EmployeeDto getEmployeeDto(Long id, String employeeId, String firstName, String lastName, String countryName) {
        return EmployeeDto.builder()
                .id(id)
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .countryName(countryName)
                .build();
    }

    public static List<EmployeeDto> getEmployeeDtoList(Long id, String employeeId, String firstName, String lastName, String countryName) {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(id)
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .countryName(countryName)
                .build();

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(employeeDto);

        return employeeDtoList;
    }
}
