package com.poc.microservice.mapper;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.poc.microservice.model.UnnaxCatalog;
import com.poc.microservice.model.UnnaxCatalogDto;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UnnaxCatalogMapper {

    UnnaxCatalogDto toUnnaxCatalogDto(UnnaxCatalog unnaxCatalog);

    UnnaxCatalog toUnnaxCatalog(UnnaxCatalogDto unnaxCatalogDto);

    List<UnnaxCatalogDto> entityListToDtoList(List<UnnaxCatalog> unnaxCatalogs);
}
