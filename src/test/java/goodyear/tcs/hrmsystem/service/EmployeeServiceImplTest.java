package goodyear.tcs.hrmsystem.service;

import goodyear.tcs.hrmsystem.exception.EmployeeMismatchException;
import goodyear.tcs.hrmsystem.exception.EmployeeNotFoundException;
import goodyear.tcs.hrmsystem.mapper.EmployeeMapper;
import goodyear.tcs.hrmsystem.model.Employee;
import goodyear.tcs.hrmsystem.model.EmployeeDto;
import goodyear.tcs.hrmsystem.repository.EmployeeRepository;
import goodyear.tcs.hrmsystem.service.fixture.EmployeeDtoServiceFixture;
import goodyear.tcs.hrmsystem.service.fixture.EmployeeEntityServiceFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    private static final String EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE = "Employee not found";
    private static final String EMPLOYEE_MISMATCH_EXCEPTION = "Employee already exists";

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;

    @Test
    void shouldReturnListOfEmployeesWhenFindAllIsCalledAndThereAreItemsInTheDatabase() {
        Long id = 1000L;
        String employeeId = "045ABC";
        String firstName = "Ayrton";
        String lastName = "Senna";

        List<Employee> expectedEmployeeList = EmployeeEntityServiceFixture.getEmployees(id, employeeId, firstName, lastName);
        List<EmployeeDto> expectedEmployeeDtoList = EmployeeDtoServiceFixture.getEmployeeDtoList(id, employeeId, firstName, lastName);

        when(employeeRepository.findAll()).thenReturn(expectedEmployeeList);
        when(employeeMapper.employeeEntityListToEmployeeDtoList(any())).thenReturn(expectedEmployeeDtoList);

        List<EmployeeDto> actualEmployeeDtoList = employeeService.findAll();

        verify(employeeRepository, times(1)).findAll();

        assertThat(actualEmployeeDtoList, is(notNullValue()));
        assertThat(actualEmployeeDtoList,
                hasItem(hasProperty("employeeId", is(employeeId))));

    }

    @Test
    void shouldReturnEmptyListWhenFindAllIsCalledAndThereAreNoItemInTheDatabase() {
        Long id = 1000L;
        String employeeId = "045ABC";
        String firstName = "Ayrton";
        String lastName = "Senna";

        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
        when(employeeMapper.employeeEntityListToEmployeeDtoList(any())).thenReturn(Collections.emptyList());

        List<EmployeeDto> actualEmployeeDtoList = employeeService.findAll();

        verify(employeeRepository, times(1)).findAll();

        assertThat(actualEmployeeDtoList, is(notNullValue()));
        assertThat(actualEmployeeDtoList, not(
                hasItem(hasProperty("employeeId", is(employeeId))))
        );

    }

    @Test
    void shouldReturnEmployeeDtoWhenFindByEmployeeIdIsFoundInTheDatabase() {
        Long id = 1000L;
        String employeeId = "045ABC";
        String firstName = "Ayrton";
        String lastName = "Senna";

        Employee expectedEmployee = EmployeeEntityServiceFixture.getEmployee(id, employeeId, firstName, lastName);
        EmployeeDto expectedEmployeeDto = EmployeeDtoServiceFixture.getEmployeeDto(id, employeeId, firstName, lastName);

        when(employeeRepository.findByEmployeeId(eq(employeeId))).thenReturn(expectedEmployee);
        when(employeeMapper.toEmployeeDto(eq(expectedEmployee))).thenReturn(expectedEmployeeDto);

        EmployeeDto actualEmployeeDto = employeeService.findByEmployeeId(employeeId);
        verify(employeeRepository, times(1)).findByEmployeeId(eq(employeeId));

        assertThat(actualEmployeeDto, is(notNullValue()));
        assertThat(actualEmployeeDto, is(equalTo(expectedEmployeeDto)));

    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenEmployeeIdIsNotFoundInTheDatabase() {
        String employeeId = "045ABC";

        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findByEmployeeId(employeeId))
                .withMessage(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE);

    }

    @Test
    void shouldThrowEmployeeMismatchExceptionWhenEmployeeAlreadyExists() {
        Long id = 1000L;
        String employeeId = "045ABC";
        String firstName = "Ayrton";
        String lastName = "Senna";

        Employee expectedEmployee = EmployeeEntityServiceFixture.getEmployee(id, employeeId, firstName, lastName);
        EmployeeDto expectedEmployeeDto = EmployeeDtoServiceFixture.getEmployeeDto(id, employeeId, firstName, lastName);

        when(employeeRepository.findByEmployeeId(eq(employeeId))).thenReturn(expectedEmployee);

        Assertions.assertThatExceptionOfType(EmployeeMismatchException.class)
                .isThrownBy(() -> employeeService.save(expectedEmployeeDto))
                .withMessage(EMPLOYEE_MISMATCH_EXCEPTION);
    }

    @Test
    void shouldSaveEmployeeWhenNewEmployeeIsProvided() {
        Long id = 1000L;
        String employeeId = "045ABC";
        String firstName = "Ayrton";
        String lastName = "Senna";

        Employee expectedEmployee = EmployeeEntityServiceFixture.getEmployee(id, employeeId, firstName, lastName);
        EmployeeDto expectedEmployeeDto = EmployeeDtoServiceFixture.getEmployeeDto(id, employeeId, firstName, lastName);

        when(employeeRepository.findByEmployeeId(eq(employeeId))).thenReturn(null);
        when(employeeMapper.toEmployee(eq(expectedEmployeeDto))).thenReturn(expectedEmployee);

        this.employeeService.save(expectedEmployeeDto);

        verify(employeeRepository, times(1)).findByEmployeeId(eq(employeeId));
    }

    @Test
    void shouldDeleteByEmployeeIdSuccessfullyWhenEmployeeExists() {
        Long id = 1000L;
        String employeeId = "045ABC";
        String firstName = "Ayrton";
        String lastName = "Senna";

        Employee expectedEmployee = EmployeeEntityServiceFixture.getEmployee(id, employeeId, firstName, lastName);

        when(employeeRepository.findByEmployeeId(eq(employeeId))).thenReturn(expectedEmployee);

        this.employeeService.deleteByEmployeeId(employeeId);
        verify(employeeRepository, times(1)).deleteByEmployeeId(eq(employeeId));

    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenEmployeeDoesNotExist() {
        String employeeId = "045ABC";

        when(employeeRepository.findByEmployeeId(eq(employeeId))).thenReturn(null);

        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.deleteByEmployeeId(employeeId))
                .withMessage(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    @Test
    void shouldUpdateByEmployeeIdWhenUpdateByEmployeeIdIsCalledAndEmployeeExists() {
        Long id = 1000L;
        String employeeId = "045ABC";
        String firstName = "Ayrton";
        String lastName = "Senna";

        Employee expectedEmployee = EmployeeEntityServiceFixture.getEmployee(id, employeeId, firstName, lastName);
        EmployeeDto expectedEmployeeDto = EmployeeDtoServiceFixture.getEmployeeDto(id, employeeId, firstName, lastName);

        when(employeeRepository.findByEmployeeId(eq(employeeId))).thenReturn(expectedEmployee);
        when(employeeMapper.toEmployee(eq(expectedEmployeeDto))).thenReturn(expectedEmployee);

        this.employeeService.updateByEmployeeId(employeeId, expectedEmployeeDto);

        verify(employeeRepository, times(1)).save(eq(expectedEmployee));
    }


    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenUpdateByEmployeeIdIsCalledAndEmployeeDoesNotExist() {
        String employeeId = "045ABC";
        Long id = 1000L;
        String firstName = "Ayrton";
        String lastName = "Senna";

        Employee expectedEmployee = EmployeeEntityServiceFixture.getEmployee(id, employeeId, firstName, lastName);
        EmployeeDto expectedEmployeeDto = EmployeeDtoServiceFixture.getEmployeeDto(id, employeeId, firstName, lastName);

        when(employeeRepository.findByEmployeeId(eq(employeeId))).thenReturn(null);
        when(employeeMapper.toEmployee(eq(expectedEmployeeDto))).thenReturn(expectedEmployee);

        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.updateByEmployeeId(employeeId, expectedEmployeeDto))
                .withMessage(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}