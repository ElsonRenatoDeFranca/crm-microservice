package goodyear.tcs.hrmsystem.controller;

import goodyear.tcs.hrmsystem.exception.EmployeeMismatchException;
import goodyear.tcs.hrmsystem.exception.EmployeeNotFoundException;
import goodyear.tcs.hrmsystem.model.EmployeeDto;
import goodyear.tcs.hrmsystem.service.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crm")
public class HrmSystemController {

    private final EmployeeServiceImpl crmSystemService;

    public HrmSystemController(EmployeeServiceImpl crmSystemService) {
        this.crmSystemService = crmSystemService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return new ResponseEntity<>(crmSystemService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        try {
            crmSystemService.save(employeeDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EmployeeMismatchException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{employeeId}")
    @ResponseBody
    public ResponseEntity<EmployeeDto> findByEmployeeId(@PathVariable("employeeId") String employeeId) {
        EmployeeDto employeeDto = crmSystemService.findByEmployeeId(employeeId);

        if(employeeDto != null){
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{employeeId}")
    public void deleteByEmployeeId(@PathVariable String employeeId) {
        try {
            crmSystemService.deleteByEmployeeId(employeeId);
        } catch (EmployeeNotFoundException e) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{employeeId}")
    public void updateByEmployeeId(@PathVariable String employeeId, @RequestBody EmployeeDto employeeDto) {
        try {
            crmSystemService.updateByEmployeeId(employeeId, employeeDto);
        } catch (EmployeeNotFoundException e) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



}
