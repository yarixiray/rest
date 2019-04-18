package com.rest.webservices.rest.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {

}
