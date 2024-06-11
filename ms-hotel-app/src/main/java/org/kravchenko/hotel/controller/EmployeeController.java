package org.kravchenko.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.kravchenko.hotel.service.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("api/hotel/employees")
@Tag(name = "Управление сотрудниками", description = "Позволяет находить, добавлять, удалять сотрудников")
public interface EmployeeController {

    @Operation(
            summary = "Получение сотрудника по id",
            description = "Метод позволяет получить сотрудника по id"
    )
    @GetMapping("/employees")
    EmployeeDto findEmployee(@RequestParam Long id);

    @Operation(
            summary = "Добавление нового сотрудника",
            description = "Метод позволяет добавить нового сотрудника"
    )
    @PostMapping("/add")
    ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employeeDto);

    @Operation(
            summary = "Удаление сотрудника по id",
            description = "Метод позволяет удалить сотрудника по id"
    )
    @DeleteMapping("/delete")
    ResponseEntity<String> deleteEmployee(@RequestParam Long id);

}
