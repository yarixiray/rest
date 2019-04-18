package com.rest.webservices.rest.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryCommandLineRunnner implements CommandLineRunner {

    private static final Logger log =
            LoggerFactory.getLogger(UserRepositoryCommandLineRunnner.class);

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity("Userrepositiry", "Admin");
        userRepositoryJpa.save(user);
        log.info("New user is created: " + user);
        Optional<UserEntity> userWithIdOne = userRepositoryJpa.findById(1L);
        log.info("New user is retrieved: " + user);

        List<UserEntity> users = userRepositoryJpa.findAll();
        log.info("All users: " + users);


    }
}
