package training.cloud.crmmicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@EqualsAndHashCode(exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="customer", schema = "public")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    @NotBlank
    @Size(max = 60)
    private String customerId;

    @Column(length = 60)
    private String firstName;

    @Column(length = 60)
    private String lastName;

    @Column(length = 60)
    private String countryName;
}
