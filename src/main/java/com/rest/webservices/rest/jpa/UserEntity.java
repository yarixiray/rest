package com.rest.webservices.rest.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Table - UserEntity
@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String role;

    public UserEntity() {
    }

    public UserEntity(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
