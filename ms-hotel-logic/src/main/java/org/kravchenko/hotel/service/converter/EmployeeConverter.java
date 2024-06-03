package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Employee;
import org.kravchenko.hotel.service.dto.EmployeeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeConverter {

    Employee toModel(EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);

}
