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
@Schema(description = "Сущность услуги для клиента с наименованием и ценой")
public class ServicePriceDto {

    @Schema(description = "Идентификатор", example = "8")
    private Long id;

    @Schema(description = "Наименование услуги", example = "Parking")
    private String name;

    @Schema(description = "Стоимость услуги", example = "25")
    private int price;

}
