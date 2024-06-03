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
@Schema(description = "Сущность клиента без списка его услуг")
public class ClientNoServicesDto {

    @Schema(description = "Имя", example = "Bill")
    private String name;

    @Schema(description = "Фамилия", example = "Gates")
    private String surname;

}
