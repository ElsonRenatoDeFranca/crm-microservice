package training.cloud.crmmicroservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import training.cloud.crmmicroservice.mapper.CatalogMapper;
import training.cloud.crmmicroservice.model.dto.CatalogResponse;
import training.cloud.crmmicroservice.repository.CatalogRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultCatalogService implements CatalogService {

    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper;

    @Override
    public List<CatalogResponse> execute(String customerId) {
        var catalogDTO = catalogRepository.findByCustomerId(customerId);
        var catalogDTOs = catalogDTO.stream().map(catalogMapper::toDomain).toList();
        return catalogDTOs.stream().map(catalogMapper::toCatalogResponse).toList();
    }
}