package com.azirariza.javakarya.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.azirariza.javakarya.repository.PositionRepository;
import com.azirariza.javakarya.entity.Position;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position>getList(){
        return positionRepository.getList();
    }

}
