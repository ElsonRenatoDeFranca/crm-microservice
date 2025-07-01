package training.cloud.crmmicroservice.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training.cloud.crmmicroservice.model.dto.CatalogResponse;
import training.cloud.crmmicroservice.service.CatalogService;

import java.util.List;

@RestController
@RequestMapping("/api/cecoban")
@AllArgsConstructor
public class CatalogManagerController {

    private final CatalogService catalogService;

    @GetMapping("/catalogs")
    public ResponseEntity<List<CatalogResponse>> getCatalog(@PathVariable String customerId) {
        List<CatalogResponse> catalog = catalogService.execute(customerId);
        return ResponseEntity.ok(catalog);
    }
}