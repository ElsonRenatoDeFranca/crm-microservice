package training.cloud.crmmicroservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_TRANSACTION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "JOB_ID")
    private String jobId;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "STATUS")
    private String status;
}