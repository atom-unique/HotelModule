package org.kravchenko.hotel.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kravchenko.hotel.model.Client;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Schema(description = "Сущность услуги для клиента")
public class ServiceDto {

    @Schema(description = "Идентификатор", example = "8")
    private Long id;

    @Schema(description = "Наименование услуги", example = "Parking")
    private String name;

    @Schema(description = "Описание услуги", example = "Parking space in the parking lot")
    private String description;

    @Schema(description = "Стоимость услуги", example = "25")
    private int price;

    @Schema(description = "Список клиентов, воспользовавшихся услугой")
    private List<Client> clients;

}
