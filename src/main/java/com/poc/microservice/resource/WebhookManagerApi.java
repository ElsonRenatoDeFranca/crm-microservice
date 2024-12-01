package com.poc.microservice.resource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.poc.microservice.model.UnnaxCatalogDto;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/cecoban")
public interface WebhookManagerApi {
    @PostMapping(value = "/catalog", produces = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Save webhook response to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Save a catalog response to database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "406",
                    description = "The webhook response is already at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> save(@Valid @RequestBody UnnaxCatalogDto unnaxCatalogDto);


    @GetMapping(value = "/catalog", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find all catalog responses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find all catalog responses",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<List<UnnaxCatalogDto>> findAll();


    @GetMapping(value = "/catalog/{traceIdentifier}", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find by traceIdentifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find by traceIdentifier",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "traceIdentifier not found at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<UnnaxCatalogDto> findByTraceIdentifier(@PathVariable("traceIdentifier") String traceIdentifier);

    @DeleteMapping("/catalog/{traceIdentifier}")
    @Operation(summary = "Delete by traceIdentifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete by traceIdentifier",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "traceIdentifier not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> deleteByTraceIdentifier(@PathVariable("traceIdentifier") String traceIdentifier);


    @PutMapping(value = "/catalog/{traceIdentifier}")
    @Operation(summary = "Update by traceIdentifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "update by traceIdentifier",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "traceIdentifier not found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<UnnaxCatalogDto> updateByTraceIdentifier(@Valid @RequestBody UnnaxCatalogDto unnaxCatalogDto, @PathVariable("traceIdentifier") String traceIdentifier);


}
