package training.cloud.crmmicroservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Data
public class CatalogResponse {
    private String targetDestinationBanco;
    private String bankName;
    private String operation;
}
