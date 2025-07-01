package training.cloud.crmmicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import training.cloud.crmmicroservice.model.entity.Catalog;

import java.util.List;

public interface CatalogRepository  extends JpaRepository<Catalog, String> {
    List<Catalog> findByCustomerId(String customerId);
}
