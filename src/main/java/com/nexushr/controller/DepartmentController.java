package com.nexushr.controller;

import com.nexushr.entity.Department;
import com.nexushr.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService service;
// here use constructor injection
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity< Department> createDepartment(@Valid  @RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveDepartment(department));
    }

    @GetMapping
    public ResponseEntity < List<Department>> getAllDepartments() {
        return ResponseEntity.ok(service.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDepartmentById(id));
    }

    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
          @Valid  @RequestBody Department department) {

        return service.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        service.deleteDepartment(id);
        return "Department deleted successfully";
    }
}