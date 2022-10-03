package goodyear.tcs.hrmsystem.mapper;

import goodyear.tcs.hrmsystem.model.Employee;
import goodyear.tcs.hrmsystem.model.EmployeeDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface EmployeeMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "employeeId", source = "employeeId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "countryName", source = "countryName")
    EmployeeDto toEmployeeDto(Employee employee);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "employeeId", source = "employeeId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "countryName", source = "countryName")
    Employee toEmployee(EmployeeDto employeeDto);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "employeeId", source = "employeeId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    List<EmployeeDto> employeeEntityListToEmployeeDtoList(List<Employee> employees);
}
