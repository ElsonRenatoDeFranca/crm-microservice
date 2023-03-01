package training.cloudnative.crmmicroservice.resource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.cloudnative.crmmicroservice.model.CustomerDto;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/crm")
public interface CrmApi {
    @PostMapping(value = "/add-customer", produces = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Save a customer to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Save a customer to database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "406",
                    description = "The customer is already at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> save(@RequestBody CustomerDto customerDto);


    @GetMapping(value = "/findall", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find all customers",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<List<CustomerDto>> findAll();


    @GetMapping(value = "/findcustomer/{customerId}", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find customer by customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find a customer by customerId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Customer not found at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<CustomerDto> findByCustomerId(@PathVariable("customerId") String customerId);

    @DeleteMapping("/deletecustomer/{customerId}")
    @Operation(summary = "Delete by customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete by customerId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Customer not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> deleteByCustomerId(@PathVariable("customerId") String customerId);


    @RequestMapping(value = "/updatecustomer/{customerId}", method = RequestMethod.PUT)
    @Operation(summary = "Update by customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "update by customerId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "customer not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> updateByCustomerId(@RequestBody CustomerDto customerDto, @PathVariable("customerId") String customerId);


    @GetMapping("/customers/{countryName}")
    @ResponseBody
    @Operation(summary = "Find all customers by countryName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find all customers by countryName",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    public ResponseEntity<List<CustomerDto>> findAllByCountryName(@PathVariable("countryName") String countryName);

}
