package com.nexushr.service.impl;

import com.nexushr.entity.Department;
import com.nexushr.exception.DepartmentNotFoundException;
import com.nexushr.repository.DepartmentRepository;
import com.nexushr.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepositoryrepository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.departmentRepositoryrepository = repository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepositoryrepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepositoryrepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepositoryrepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException("Department not found with id: " + id));
    }

    @Override
    public Department updateDepartment(Long id, Department department) {

        Department existingDepartment = departmentRepositoryrepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException("Department not found with id: " + id));

        existingDepartment.setName(department.getName());
        existingDepartment.setDescription(department.getDescription());

        return departmentRepositoryrepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {

        Department department = departmentRepositoryrepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException("Department not found with id: " + id));

        departmentRepositoryrepository.delete(department);
    }
}