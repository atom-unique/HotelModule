package org.kravchenko.hotel.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Schema(description = "Сущность сотрудника отеля")
public class EmployeeDto {

    @Schema(description = "Идентификатор", example = "9")
    private Long id;

    @Schema(description = "Имя", example = "Steve")
    private String name;

    @Schema(description = "Фамилия", example = "Jobs")
    private String surname;

    @Schema(description = "Должность", example = "Administrator")
    private String position;

}
