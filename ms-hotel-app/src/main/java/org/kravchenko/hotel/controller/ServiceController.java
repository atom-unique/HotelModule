package org.kravchenko.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.kravchenko.hotel.service.dto.ServiceDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("api/hotel/services")
@Tag(name = "Управление услугами", description = "Позволяет находить, добавлять, изменять услуги")
public interface ServiceController {

    @Operation(
            summary = "Получение общего списка услуг с их ценами",
            description = "Метод позволяет получить список всех услуг с их ценами"
    )
    @GetMapping("/price")
    List<ServicePriceDto> getAllServicesWithPrice();

    @Operation(
            summary = "Добавление новой услуги",
            description = "Метод позволяет добавить новую услугу"
    )
    @PostMapping("/add")
    void addService(@RequestBody ServiceDto serviceDto);

    @Operation(
            summary = "Обновление цены на услугу",
            description = "Метод позволяет обновить цену существующей услуги"
    )
    @PatchMapping("/update")
    void servicePriceUpdate(@RequestParam Long id, @RequestParam int price);

}
