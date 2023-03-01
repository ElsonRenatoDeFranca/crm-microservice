package training.cloudnative.crmmicroservice.controller;

import training.cloudnative.crmmicroservice.exception.EmployeeMismatchException;
import training.cloudnative.crmmicroservice.exception.EmployeeNotFoundException;
import training.cloudnative.crmmicroservice.model.EmployeeDto;
import training.cloudnative.crmmicroservice.service.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crm")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Save an employee to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Save an employee to database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "406",
                    description = "The employee is already at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.save(employeeDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EmployeeMismatchException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{employeeId}")
    @ResponseBody
    @Operation(summary = "Find employee by employeeId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find an employee by employeeId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Employee not found at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    public ResponseEntity<EmployeeDto> findByEmployeeId(@PathVariable("employeeId") String employeeId) {
        try {
            EmployeeDto employeeDto = employeeService.findByEmployeeId(employeeId);
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{employeeId}")
    @Operation(summary = "Delete by employeeId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete by employeeId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Employee not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    public void deleteByEmployeeId(@PathVariable("employeeId") String employeeId) {
        try {
            employeeService.deleteByEmployeeId(employeeId);
        } catch (EmployeeNotFoundException e) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
    @Operation(summary = "Update by employeeId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "update by employeeId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Employee not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    public void updateByEmployeeId(@RequestBody EmployeeDto employeeDto, @PathVariable("employeeId") String employeeId) {
        try {
            employeeService.updateByEmployeeId(employeeId, employeeDto);
        } catch (EmployeeNotFoundException e) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/{countryName}")
    @ResponseBody
    @Operation(summary = "Find all employees by countryName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find all employees by countryName",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    public ResponseEntity<List<EmployeeDto>> findAllByCountryName(@PathVariable("countryName") String countryName) {
        List<EmployeeDto> employeeDtoList = employeeService.findAllByCountryName(countryName);
        return new ResponseEntity<>(employeeDtoList, HttpStatus.OK);
    }


}
