package com.poc.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@EqualsAndHashCode(exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UnnaxCatalogDto {
    private Long id;
    private String traceIdentifier;
    private String operation;
    private String requestCode;
    private String targetDestination;
    private LocalDateTime creationDate;
}
