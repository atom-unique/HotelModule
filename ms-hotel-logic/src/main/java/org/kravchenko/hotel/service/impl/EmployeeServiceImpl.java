package org.kravchenko.hotel.service.impl;

import lombok.AllArgsConstructor;
import org.kravchenko.hotel.exception.EntityNotFoundException;
import org.kravchenko.hotel.repository.EmployeeRepository;
import org.kravchenko.hotel.service.EmployeeService;
import org.kravchenko.hotel.service.converter.EmployeeConverter;
import org.kravchenko.hotel.service.dto.EmployeeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeConverter employeeConverter;

    @Override
    public EmployeeDto findEmployee(Long id) {
        return employeeConverter.toDto(employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        ));
    }

    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        employeeRepository.save(employeeConverter.toModel(employeeDto));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
