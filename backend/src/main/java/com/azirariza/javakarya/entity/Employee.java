package com.azirariza.javakarya.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    @Column(nullable = false)
    private String name;

    @JsonProperty("birth_date")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @JsonProperty("position")
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @JsonProperty("id_number")
    @Column(name = "id_number", unique = true, nullable = false)
    private int idNumber;

    @JsonProperty("gender")
    @Column(nullable = false)
    private int gender;

    @JsonProperty("is_delete")
    @Column(name = "is_delete", nullable = false)
    private int isDelete;

    public Employee() {
    }

    public Employee(int id, String name, LocalDate birthDate, Position position, int idNumber, int gender,
            int isDelete) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.position = position;
        this.idNumber = idNumber;
        this.gender = gender;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
