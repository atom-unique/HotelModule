package org.kravchenko.hotel.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kravchenko.hotel.model.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Schema(description = "Сущность клиента")
public class ClientDto {

    @Schema(description = "Идентификатор", example = "12")
    private Long id;

    @Schema(description = "Имя", example = "Bill")
    private String name;

    @Schema(description = "Фамилия", example = "Gates")
    private String surname;

    @Schema(description = "Список услуг")
    private List<Service> services;

}
