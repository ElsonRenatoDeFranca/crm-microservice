package goodyear.tcs.hrmsystem.controller;

import goodyear.tcs.hrmsystem.model.EmployeeDto;
import goodyear.tcs.hrmsystem.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HrmSystemControllerTest {

    private MockMvc mvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private JacksonTester<EmployeeDto> jsonSuperHero;


    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void findByEmployeeId() {
    }

    @Test
    void deleteByEmployeeId() {
    }

    @Test
    void updateByEmployeeId() {
    }
}