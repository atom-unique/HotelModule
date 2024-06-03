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
@Schema(description = "Сущность номера отеля с идентификатором и стоимостью")
public class RoomPriceDto {

    @Schema(description = "Идентификатор", example = "4")
    private Long id;

    @Schema(description = "Стоимость проживания", example = "120")
    private int price;

}
