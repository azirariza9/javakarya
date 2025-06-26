package com.azirariza.javakarya.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.azirariza.javakarya.entity.Employee;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    @Query("SELECT e FROM Employee e WHERE e.isDelete = 0")
    Page<Employee> getListForPagination(Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.id = :id AND e.isDelete = 0")
    Optional<Employee> getActiveById(@Param("id") int id);

}
