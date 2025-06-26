package com.azirariza.javakarya.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azirariza.javakarya.dto.EmployeeDTO;
import com.azirariza.javakarya.entity.Employee;
import com.azirariza.javakarya.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/index")
    public ResponseEntity<Page<Employee>> index(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size) {
        return ResponseEntity.ok(employeeService.getListForPagination(page, size));
    }

    @GetMapping("/{id}/add-edit")
    public ResponseEntity<EmployeeDTO> addEdit(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getEmployeeWithPositions(id));
    }

    @PostMapping
    public ResponseEntity<Employee> insert(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.insert(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(
            @PathVariable int id,
            @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
