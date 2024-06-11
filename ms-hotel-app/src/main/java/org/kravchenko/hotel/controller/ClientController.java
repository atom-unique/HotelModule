package org.kravchenko.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.kravchenko.hotel.service.dto.ClientDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("api/hotel/clients")
@Tag(name = "Управление клиентами", description = "Позволяет находить, добавлять, удалять клиентов")
public interface ClientController {

    @Operation(
            summary = "Получение клиента по id",
            description = "Метод позволяет получить клиента по id"
    )
    @GetMapping("/client")
    ClientDto findClient(@RequestParam Long id);

    @Operation(
            summary = "Получение списка услуг клиента по id",
            description = "Метод позволяет получить список услуг клиента по id клиента"
    )
    @GetMapping("/client/services")
    List<ServicePriceDto> getClientServices(@RequestParam Long id);

    @Operation(
            summary = "Добавление нового клиента",
            description = "Метод позволяет добавить нового клиента"
    )
    @PostMapping("/add")
    ResponseEntity<String> addClient(@RequestBody ClientDto clientDto);

    @Operation(
            summary = "Удаление клиента по id",
            description = "Метод позволяет удалить клиента по id"
    )
    @DeleteMapping("/delete")
    ResponseEntity<String> deleteClient(@RequestParam Long id);

}
