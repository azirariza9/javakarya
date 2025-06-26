package com.azirariza.javakarya.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.azirariza.javakarya.dto.EmployeeDTO;
import com.azirariza.javakarya.entity.Employee;
import com.azirariza.javakarya.entity.Position;
import com.azirariza.javakarya.repository.PositionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private final PositionRepository positionRepository;

    public EmployeeDAOImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public EmployeeDTO getEmployeeWithPositions(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee == null) return null;
       
        List<Position> positions = positionRepository.getList();

        return new EmployeeDTO(positions, employee);
    }

}