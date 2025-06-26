package com.azirariza.javakarya.entity;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "position")
@SQLDelete(sql = "UPDATE position SET is_delete = 1 WHERE id = ?")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    @JsonProperty("code")
    @Column(unique = true, nullable = false)
    private String code;

    @JsonProperty("name")
    @Column(nullable = false)
    private String name;

    @JsonProperty("is_delete")
    @Column(name = "is_delete",nullable = false)
    private int isDelete;

    public Position() {
    }

    public Position(int id, String code, String name, int isDelete) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
