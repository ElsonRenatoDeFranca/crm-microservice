package training.cloud.crmmicroservice.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import training.cloud.crmmicroservice.model.dto.CatalogDTO;
import training.cloud.crmmicroservice.model.dto.CatalogResponse;
import training.cloud.crmmicroservice.model.entity.Catalog;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CatalogMapper {
    Catalog toEntity(CatalogDTO catalog);
    CatalogDTO toDomain(Catalog catalog);
    CatalogResponse toCatalogResponse(CatalogDTO catalogDTO);
}