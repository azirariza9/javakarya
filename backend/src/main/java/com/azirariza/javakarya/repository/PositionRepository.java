package com.azirariza.javakarya.repository;




import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.azirariza.javakarya.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position,Position> {
    @Query("SELECT id, code, name,is_delete FROM position WHERE position.is_delete = 0")
    List<Position> getList();

}
