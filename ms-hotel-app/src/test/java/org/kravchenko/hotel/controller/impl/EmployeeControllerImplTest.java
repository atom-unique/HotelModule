package org.kravchenko.hotel.controller.impl;

import org.kravchenko.hotel.service.EmployeeService;
import org.kravchenko.hotel.service.dto.EmployeeDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Listeners(MockitoTestNGListener.class)
public class EmployeeControllerImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeControllerImpl employeeController;

    @Test
    public void findEmployee_ValidId_ReturnsEmployeeDto() {
        Long employeeId = 1L;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employeeId);

        when(employeeService.findEmployee(employeeId)).thenReturn(employeeDto);

        EmployeeDto result = employeeController.findEmployee(employeeId);

        assertNotNull(result);
        assertEquals(result.getId(), employeeId);
        verify(employeeService, times(1)).findEmployee(employeeId);
    }

    @Test
    public void addEmployee_ValidEmployeeDto_AddsEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeController.addEmployee(employeeDto);

        verify(employeeService, times(1)).addEmployee(employeeDto);
    }

    @Test
    public void deleteEmployee_ValidId_DeletesEmployee() {
        Long employeeId = 1L;

        employeeController.deleteEmployee(employeeId);

        verify(employeeService, times(1)).deleteEmployee(employeeId);
    }

}