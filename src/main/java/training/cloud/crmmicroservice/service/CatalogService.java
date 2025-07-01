package training.cloud.crmmicroservice.service;


import training.cloud.crmmicroservice.model.dto.CatalogResponse;

import java.util.List;

public interface CatalogService {
    List<CatalogResponse> execute(String customerId);
}