package com.azirariza.javakarya.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.azirariza.javakarya.entity.Employee;
import com.azirariza.javakarya.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

       public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Page<Employee>getListForPagination(Pageable pageable){
        return employeeRepository.getListForPagination(pageable);
    }

  
    public Optional<Employee> getById(int id) {
        return employeeRepository.getActiveById(id);
    }

    public Employee insert(Employee employee) {
        return employeeRepository.save(employee);
    }

    
    public Employee update(int id, Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedEmployee.getName());
                    existing.setBirthDate(updatedEmployee.getBirthDate());
                    existing.setPosition(updatedEmployee.getPosition());
                    existing.setIdNumber(updatedEmployee.getIdNumber());
                    existing.setGender(updatedEmployee.getGender());
                    return employeeRepository.save(existing);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    public void delete (int id){
        employeeRepository.deleteById(id);
    }
}
