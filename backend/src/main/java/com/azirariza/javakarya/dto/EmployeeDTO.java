package com.azirariza.javakarya.dto;

import com.azirariza.javakarya.entity.*;

import java.util.List;

public record EmployeeDTO(
        List<Position> positionList,
        Employee employee) {
}
