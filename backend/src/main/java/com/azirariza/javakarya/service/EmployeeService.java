package com.azirariza.javakarya.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.azirariza.javakarya.dao.EmployeeDAO;
import com.azirariza.javakarya.dto.EmployeeDTO;
import com.azirariza.javakarya.entity.Employee;
import com.azirariza.javakarya.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeDAO employeeDAO,EmployeeRepository employeeRepository) {
        this.employeeDAO = employeeDAO;
        this.employeeRepository = employeeRepository;
    }

    public Page<Employee> getListForPagination(int page, int size) {
        return employeeRepository.getListForPagination(PageRequest.of(page, size));
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

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO getEmployeeWithPositions(int id) {
        return employeeDAO.getEmployeeWithPositions(id);
    }
}
