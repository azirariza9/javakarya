package com.azirariza.javakarya.dao;

import com.azirariza.javakarya.dto.EmployeeDTO;

public interface EmployeeDAO {
    EmployeeDTO getEmployeeWithPositions(int id);
}
