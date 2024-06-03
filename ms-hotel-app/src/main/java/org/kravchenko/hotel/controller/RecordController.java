package org.kravchenko.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.kravchenko.hotel.service.dto.RecordClientDateDto;
import org.kravchenko.hotel.service.dto.RecordClientWithRoomDto;
import org.kravchenko.hotel.service.dto.RoomNoClientsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("api/hotel/records")
@Tag(name = "Управление книгой регистрации",
        description = "Позволяет заселять, выселять клиента, получать различную информацию о клиентах и номерах")
public interface RecordController {

    @Operation(
            summary = "Заселение клиента",
            description = "Метод позволяет заселить клиента в номер"
    )
    @PostMapping("/checkin")
    void checkIn(
            @RequestParam Long clientId,
            @RequestParam Long employeeId,
            @RequestParam Long roomId,
            @RequestParam String checkOutDate,
            @RequestBody List<Long> services
    );

    @Operation(
            summary = "Выселение клиента",
            description = "Метод позволяет выселить клиента из номера"
    )
    @PatchMapping("/checkout")
    void checkOut(@RequestParam String name, @RequestParam String surname);

    @Operation(
            summary = "Получение количества постояльцев",
            description = "Метод позволяет получить количество постояльцев"
    )
    @GetMapping("/clients/count")
    int getAllFreeRoomsCount();

    @Operation(
            summary = "Получение списка клиентов и их номеров",
            description = "Метод позволяет получить список клиентов и их номеров"
    )
    @GetMapping("/clients/list")
    List<RecordClientWithRoomDto> getClientsWithRooms();

    @Operation(
            summary = "Получение списка клиентов и их номеров (сортировка по дате выселения)",
            description = "Метод позволяет получить список клиентов и их номеров (сортировка по дате выселения)"
    )
    @GetMapping("/clients/list/date")
    List<RecordClientWithRoomDto> getClientsWithRoomsByCheckOut();

    @Operation(
            summary = "Получение суммы оплаты за номер",
            description = "Метод позволяет получить сумму оплаты за номер"
    )
    @GetMapping("/room/total")
    int getTotalForRoom(@RequestParam Long id);

    @Operation(
            summary = "Посмотреть трех последних постояльцев номера и даты их пребывания",
            description = "Метод позволяет посмотреть трех последних постояльцев номера и даты их пребывания)"
    )
    @GetMapping("/room/clients/last")
    List<RecordClientDateDto> getLastRoomClients(@RequestParam Long roomId);

    @Operation(
            summary = "Получить список номеров, которые будут свободны по определенной дате в будущем",
            description = "Метод позволяет получить список номеров, которые будут свободны по определенной дате в будущем"
    )
    @GetMapping("/rooms/free/date")
    List<RoomNoClientsDto> getAllFreeRoomsByDate(@RequestParam String dateLine);

}
