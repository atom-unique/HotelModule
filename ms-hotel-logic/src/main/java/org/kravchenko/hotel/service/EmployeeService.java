package org.kravchenko.hotel.service;

import org.kravchenko.hotel.service.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto findEmployee(Long id);

    void addEmployee(EmployeeDto employeeDto);

    void deleteEmployee(Long id);

}
