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
@Schema(description = "Сущность номера отеля")
public class RoomDto {

    @Schema(description = "Список клиентов, проживающих в номере")
    List<Client> clients;

    @Schema(description = "Идентификатор", example = "4")
    private Long id;

    @Schema(description = "Стоимость проживания", example = "120")
    private int price;

    @Schema(description = "Вместимость номера", example = "2")
    private int capacity;

    @Schema(description = "Рейтинг комфорта", example = "4")
    private int rating;

    @Schema(description = "Флаг занятости номера", example = "false")
    private boolean isOccupied;

    @Schema(description = "Флаг нахождения номера на обслуживании", example = "true")
    private boolean isInService;

}
