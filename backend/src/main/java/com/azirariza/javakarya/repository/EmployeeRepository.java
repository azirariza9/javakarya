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
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT id, name, birth_date, position, id_number, gender,is_delete FROM employee WHERE employee.is_delete = 0")
    Page<Employee> getListForPagination(Pageable pageable);

    @Query("SELECT id, name, birth_date, position, id_number, gender,is_delete FROM employee WHERE employee.id = :id AND employee.is_delete = 0")
    Optional<Employee> getActiveById (@Param("id") int id);

}
