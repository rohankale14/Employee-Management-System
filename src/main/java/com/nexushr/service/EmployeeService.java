package com.nexushr.service;

import com.nexushr.dto.EmployeeRequestDto;
import com.nexushr.dto.EmployeeResponseDto;
import com.nexushr.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    Employee getEmployeeByEmail(String email);
    
    List<Employee> getEmployeesByDepartmentId(Long departmentId);

    Page<Employee> getAllEmployees(int page, int size);

    List<Employee> getAllEmployeesSorted(String field,String direction);
}