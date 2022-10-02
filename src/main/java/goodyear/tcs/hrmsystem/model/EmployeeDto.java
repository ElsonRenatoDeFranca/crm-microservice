package goodyear.tcs.hrmsystem.model;

import lombok.*;


@EqualsAndHashCode(exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDto {
    private String id;
    private String employeeId;
    private String firstName;
    private String lastName;
}
