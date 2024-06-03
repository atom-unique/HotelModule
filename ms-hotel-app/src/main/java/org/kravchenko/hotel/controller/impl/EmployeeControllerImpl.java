package org.kravchenko.hotel.controller.impl;

import lombok.RequiredArgsConstructor;
import org.kravchenko.hotel.controller.EmployeeController;
import org.kravchenko.hotel.service.EmployeeService;
import org.kravchenko.hotel.service.dto.EmployeeDto;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeDto findEmployee(Long id) {
        return employeeService.findEmployee(id);
    }

    public void addEmployee(EmployeeDto employeeDto) {
        employeeService.addEmployee(employeeDto);
    }

    public void deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
    }
}
