package goodyear.tcs.hrmsystem.controller;

import goodyear.tcs.hrmsystem.exception.EmployeeMismatchException;
import goodyear.tcs.hrmsystem.exception.EmployeeNotFoundException;
import goodyear.tcs.hrmsystem.model.EmployeeDto;
import goodyear.tcs.hrmsystem.service.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hrm")
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
        EmployeeDto employeeDto = employeeService.findByEmployeeId(employeeId);

        if(employeeDto != null){
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public void deleteByEmployeeId(@PathVariable String employeeId) {
        try {
            employeeService.deleteByEmployeeId(employeeId);
        } catch (EmployeeNotFoundException e) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{employeeId}")
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
    public void updateByEmployeeId(@PathVariable String employeeId, @RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.updateByEmployeeId(employeeId, employeeDto);
        } catch (EmployeeNotFoundException e) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



}
