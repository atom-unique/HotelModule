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
@Schema(description = "Сущность номера отеля без списка клиентов")
public class RoomNoClientsDto {

    @Schema(description = "Идентификатор", example = "4")
    private Long id;

    @Schema(description = "Стоимость проживания", example = "120")
    private int price;

    @Schema(description = "Вместимость номера", example = "2")
    private int capacity;

    @Schema(description = "Рейтинг комфорта", example = "4")
    private int rating;

}
