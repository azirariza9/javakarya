package com.azirariza.javakarya.repository;




import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.azirariza.javakarya.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position,Position> {
    @Query("SELECT p FROM Position p WHERE p.isDelete = 0")
    List<Position> getList();

}
