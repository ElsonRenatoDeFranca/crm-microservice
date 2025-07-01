package training.cloud.crmmicroservice.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_CATALOG")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {

    @Id
    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "TARGET_DESTINATION_BANCO")
    private String targetDestinationBanco;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "OPERATION")
    private String operation;

}
