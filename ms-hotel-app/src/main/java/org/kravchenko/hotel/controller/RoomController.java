package org.kravchenko.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.kravchenko.hotel.service.dto.RoomDto;
import org.kravchenko.hotel.service.dto.RoomPriceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("api/hotel/rooms")
@Tag(name = "Управление номерами отеля",
        description = "Позволяет находить, добавлять, удалять, изменять номера")
public interface RoomController {

    @Operation(
            summary = "Получение номера по id",
            description = "Метод позволяет получить номер по id"
    )
    @GetMapping("/room")
    RoomDto findRoom(@RequestParam Long id);

    @Operation(
            summary = "Получение списка номеров и их цен",
            description = "Метод позволяет получить список номеров и цен на них"
    )
    @GetMapping("/price")
    List<RoomPriceDto> getAllRoomsWithPrice();

    @Operation(
            summary = "Получение списка всех номеров (сортировка по цене)",
            description = "Метод позволяет получить список всех номеров (сортировка по цене)"
    )
    @GetMapping("/all/price")
    List<RoomDto> findAllRoomsSortedByPrice();

    @Operation(
            summary = "Получение списка всех номеров (сортировка по количеству мест)",
            description = "Метод позволяет получить список всех номеров (сортировка по количеству мест)"
    )
    @GetMapping("/all/capacity")
    List<RoomDto> findAllRoomsSortedByCapacity();

    @Operation(
            summary = "Получение списка всех номеров (сортировка по количеству звезд)",
            description = "Метод позволяет получить список всех номеров (сортировка по количеству звезд)"
    )
    @GetMapping("/all/rating")
    List<RoomDto> findAllRoomsSortedByRating();

    @Operation(
            summary = "Получение списка свободных номеров (сортировка по цене)",
            description = "Метод позволяет получить список свободных номеров (сортировка по цене)"
    )
    @GetMapping("/free/price")
    List<RoomDto> findAllFreeRoomsSortedByPrice();

    @Operation(
            summary = "Получение списка свободных номеров (сортировка по количеству мест)",
            description = "Метод позволяет получить список свободных номеров (сортировка по количеству мест)"
    )
    @GetMapping("/free/capacity")
    List<RoomDto> findAllFreeRoomsSortedByCapacity();

    @Operation(
            summary = "Получение списка свободных номеров (сортировка по количеству звезд)",
            description = "Метод позволяет получить список свободных номеров (сортировка по количеству звезд)"
    )
    @GetMapping("/free/rating")
    List<RoomDto> findAllFreeRoomsSortedByRating();

    @Operation(
            summary = "Добавление нового номера",
            description = "Метод позволяет добавить новый номер"
    )
    @PostMapping("/add")
    ResponseEntity<String> addRoom(@RequestBody RoomDto roomDto);

    @Operation(
            summary = "Обновление цены на номер",
            description = "Метод позволяет обновить цену номера"
    )
    @PatchMapping("/update/price")
    ResponseEntity<String> roomPriceUpdate(@RequestParam Long id, @RequestParam int price);

    @Operation(
            summary = "Изменение статуса номера на обслуживаемый/обслуженный",
            description = "Метод позволяет изменить статус номера на обслуживаемый/обслуженный"
    )
    @PatchMapping("/update/status")
    ResponseEntity<String> roomStatusUpdate(@RequestParam Long id, @RequestParam boolean inService);

    @Operation(
            summary = "Получение количества свободных номеров",
            description = "Метод позволяет получить количество свободных номеров"
    )
    @GetMapping("/free/count")
    int getAllFreeRoomsCount();

}
