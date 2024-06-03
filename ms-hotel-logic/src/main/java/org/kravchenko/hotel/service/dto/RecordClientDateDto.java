package org.kravchenko.hotel.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Schema(description = "Сущность записи в книге регистрации клиентов, содержит клиента и даты его пребывания")
public class RecordClientDateDto {

    @Schema(description = "Клиент без списка его услуг")
    private ClientNoServicesDto client;

    @Schema(description = "Дата заселения клиента", example = "2024-06-01")
    private LocalDate checkInDate;

    @Schema(description = "Дата выселения клиента", example = "2024-06-05")
    private LocalDate checkOutDate;

}
