package training.cloud.crmmicroservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Data
public class CatalogDTO {
    private final String customerId;//banco de origem (CITI_BAN)
    private final String targetDestinationBanco;
    private final String bankName;
    private final String operation;
}