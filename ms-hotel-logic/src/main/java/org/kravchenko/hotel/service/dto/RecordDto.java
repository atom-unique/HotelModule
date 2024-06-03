package org.kravchenko.hotel.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kravchenko.hotel.model.Client;
import org.kravchenko.hotel.model.Employee;
import org.kravchenko.hotel.model.Room;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Schema(description = "Сущность записи в книге регистрации клиентов")
public class RecordDto {

    @Schema(description = "Идентификатор", example = "3")
    private Long id;

    @Schema(description = "Дата заселения клиента", example = "2024-06-01")
    private LocalDate checkInDate;

    @Schema(description = "Дата выселения клиента", example = "2024-06-05")
    private LocalDate checkOutDate;

    @Schema(description = "Флаг выселения клиента", example = "false")
    private boolean isCheckedOut;

    @Schema(description = "Номер, в который заселен клинт")
    private Room room;

    @Schema(description = "Клиент")
    private Client client;

    @Schema(description = "Сотрудник, заселивший клинта")
    private Employee employee;

}
