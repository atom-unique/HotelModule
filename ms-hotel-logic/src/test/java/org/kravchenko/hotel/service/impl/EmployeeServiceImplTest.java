package org.kravchenko.hotel.service.impl;

import org.kravchenko.hotel.exception.EntityNotFoundException;
import org.kravchenko.hotel.model.Employee;
import org.kravchenko.hotel.repository.EmployeeRepository;
import org.kravchenko.hotel.service.converter.EmployeeConverter;
import org.kravchenko.hotel.service.dto.EmployeeDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.FileAssert.fail;

@Listeners({
        MockitoTestNGListener.class
})
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeConverter employeeConverter;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void findEmployee_EmployeeFound_ReturnsEmployeeDto() {
        Long employeeId = 1L;
        Employee employee = new Employee();
        employee.setId(employeeId);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeConverter.toDto(employee)).thenReturn(employeeDto);

        EmployeeDto result = employeeService.findEmployee(employeeId);

        assertNotNull(result);
        assertEquals(result.getId(), employeeId);
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeConverter, times(1)).toDto(employee);
    }

    @Test
    public void findEmployee_EmployeeNotFound_ThrowsEntityNotFoundException() {
        Long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        try {
            employeeService.findEmployee(employeeId);
            fail("Expected EntityNotFoundException to be thrown");
        } catch (EntityNotFoundException e) {
            assertEquals(e.getMessage(), "Entity not found by parameter : " + employeeId);
        }

        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    public void addEmployee_ValidEmployeeDto_SavesEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("Name");
        employeeDto.setSurname("Surname");
        employeeDto.setPosition("Position");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Name");
        employee.setSurname("Surname");
        employee.setPosition("Position");

        when(employeeConverter.toModel(employeeDto)).thenReturn(employee);

        employeeService.addEmployee(employeeDto);

        verify(employeeConverter, times(1)).toModel(employeeDto);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void deleteEmployee_ValidId_DeletesEmployee() {
        Long employeeId = 1L;

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

}