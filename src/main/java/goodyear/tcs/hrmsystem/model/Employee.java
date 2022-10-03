package goodyear.tcs.hrmsystem.model;

import lombok.*;

import javax.persistence.*;


@Entity
@EqualsAndHashCode(exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String employeeId;

    @Column(length = 255)
    private String firstName;

    @Column(length = 255)
    private String lastName;

    @Column(length = 255)
    private String countryName;
}
