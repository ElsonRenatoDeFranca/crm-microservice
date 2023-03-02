package training.cloud.crmmicroservice.model;

import lombok.*;

@EqualsAndHashCode(exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {
    private Long id;
    private String customerId;
    private String firstName;
    private String lastName;
    private String countryName;
}
