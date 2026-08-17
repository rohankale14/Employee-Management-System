package com.nexushr.service.impl;

import com.nexushr.dto.EmployeeRequestDto;
import com.nexushr.dto.EmployeeResponseDto;
import com.nexushr.entity.Department;
import com.nexushr.entity.Employee;
import com.nexushr.enums.EmployeeStatus;
import com.nexushr.exception.DepartmentNotFoundException;
import com.nexushr.exception.EmployeeNotFoundException;
import com.nexushr.repository.DepartmentRepository;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }





    @Override
    public EmployeeResponseDto saveEmployee(EmployeeRequestDto dto) {

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + dto.getDepartmentId()));

        Employee employee = new Employee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setDesignation(dto.getDesignation());
        employee.setSalary(dto.getSalary());
        employee.setGender(dto.getGender());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setAddress(dto.getAddress());
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setJoiningDate(dto.getJoiningDate());
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeResponseDto response = new EmployeeResponseDto();

        response.setId(savedEmployee.getId());
        response.setFirstName(savedEmployee.getFirstName());
        response.setLastName(savedEmployee.getLastName());
        response.setEmail(savedEmployee.getEmail());
        response.setPhoneNumber(savedEmployee.getPhoneNumber());
        response.setDesignation(savedEmployee.getDesignation());
        response.setSalary(savedEmployee.getSalary());
        response.setGender(savedEmployee.getGender());
        response.setDateOfBirth(savedEmployee.getDateOfBirth());
        response.setAddress(savedEmployee.getAddress());
        response.setStatus(savedEmployee.getStatus());
        response.setJoiningDate(savedEmployee.getJoiningDate());
        response.setDepartmentName(savedEmployee.getDepartment().getName());

        return response;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setDepartment(employee.getDepartment());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
       return employeeRepository.findByEmail(email)
               .orElseThrow(() ->
                       new EmployeeNotFoundException(
                               "Employee not found with email: " + email));
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {

        departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + departmentId));

        return employeeRepository.findByDepartmentId(departmentId);
    }

    @Override
    public Page<Employee> getAllEmployees(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> getAllEmployeesSorted(String field,String direction) {

   Sort sort = direction.equalsIgnoreCase("desc")
     ? Sort.by(field).descending()
     : Sort.by(field).ascending();
        return employeeRepository.findAll(sort);
    }
}