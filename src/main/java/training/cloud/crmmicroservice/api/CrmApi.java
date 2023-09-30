package training.cloud.crmmicroservice.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.cloud.crmmicroservice.model.CustomerDto;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.BAD_REQUEST_CODE;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.BAD_REQUEST_DESCRIPTION;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.CREATION_CODE;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.CREATION_DESCRIPTION;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.CRM_API_ROOT_PATH;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.DELETE_DESCRIPTION;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.NOT_FOUND_CODE;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.NOT_FOUND_DESCRIPTION;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.OFFERING_API_ROOT_PATH;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.OK_CODE;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.SERVICE_UNAVAILABLE_CODE;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.SERVICE_UNAVAILABLE_DESCRIPTION;
import static training.cloud.crmmicroservice.api.ApiDocumentationConstants.UPDATE_DESCRIPTION;

@RequestMapping(CRM_API_ROOT_PATH )
public interface CrmApi {
    @PostMapping(value = "/customers", produces = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Save a customer to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CREATION_CODE,
                    description = CREATION_DESCRIPTION ,
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = BAD_REQUEST_CODE,
                    description = BAD_REQUEST_DESCRIPTION,
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = SERVICE_UNAVAILABLE_CODE,
                    description = SERVICE_UNAVAILABLE_DESCRIPTION,
                    content = @Content)
    })
    ResponseEntity<Void> save(@Valid @RequestBody CustomerDto customerDto);


    @GetMapping(value = "/customers", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK_CODE,
                    description = "Find all customers",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = SERVICE_UNAVAILABLE_CODE,
                    description = SERVICE_UNAVAILABLE_DESCRIPTION,
                    content = @Content)
    })
    ResponseEntity<List<CustomerDto>> findAll();


    @GetMapping(value = "/customers/{customerId}", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find customer by customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK_CODE,
                    description = "Find a customer by customerId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = NOT_FOUND_CODE,
                    description = NOT_FOUND_DESCRIPTION,
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = SERVICE_UNAVAILABLE_CODE,
                    description = SERVICE_UNAVAILABLE_DESCRIPTION,
                    content = @Content)
    })
    ResponseEntity<CustomerDto> findByCustomerId(@PathVariable("customerId") String customerId);

    @DeleteMapping("/customers/{customerId}")
    @Operation(summary = "Delete by customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK_CODE,
                    description = DELETE_DESCRIPTION,
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = NOT_FOUND_CODE,
                    description = NOT_FOUND_DESCRIPTION,
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = SERVICE_UNAVAILABLE_CODE,
                    description = SERVICE_UNAVAILABLE_DESCRIPTION,
                    content = @Content)
    })
    ResponseEntity<Void> deleteByCustomerId(@PathVariable("customerId") String customerId);


    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.PUT)
    @Operation(summary = "Update by customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK_CODE,
                    description = UPDATE_DESCRIPTION,
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = NOT_FOUND_CODE,
                    description = NOT_FOUND_DESCRIPTION,
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = SERVICE_UNAVAILABLE_CODE,
                    description = SERVICE_UNAVAILABLE_DESCRIPTION,
                    content = @Content)
    })
    ResponseEntity<Void> updateByCustomerId(@Valid @RequestBody CustomerDto customerDto, @PathVariable("customerId") String customerId);


    @GetMapping("/customers/{countryName}")
    @ResponseBody
    @Operation(summary = "Find all customers by countryName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK_CODE,
                    description = "Find all customers by countryName",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = SERVICE_UNAVAILABLE_CODE,
                    description = SERVICE_UNAVAILABLE_DESCRIPTION,
                    content = @Content)
    })
    ResponseEntity<List<CustomerDto>> findAllByCountryName(@PathVariable("countryName") String countryName);

}
