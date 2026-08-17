package com.nexushr.controller;

import com.nexushr.dto.EmployeeRequestDto;
import com.nexushr.dto.EmployeeResponseDto;
import com.nexushr.entity.Employee;
import com.nexushr.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeResponseDto saveEmployee(@Valid  @RequestBody EmployeeRequestDto employee) {
        return service.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                  @Valid @RequestBody Employee employee) {
        return service.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "Employee deleted successfully";
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {

        return ResponseEntity.ok(service.getEmployeeByEmail(email));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(
            @PathVariable Long departmentId) {

        return ResponseEntity.ok(service.getEmployeesByDepartmentId(departmentId));
    }

    @GetMapping("/page")
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(service.getAllEmployees(page, size).getContent());
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Employee>> getEmployeesSorted(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(service.getAllEmployeesSorted(field,direction));
    }
}