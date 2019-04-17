package com.rest.webservices.rest.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDaoServiceCommandLineRunnner implements CommandLineRunner {

    private static final Logger log =
            LoggerFactory.getLogger(UserDaoServiceCommandLineRunnner.class);

    @Autowired
    private UserDAOService userDAOService = new UserDAOService();

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity("Jack", "Admin");
        userDAOService.insert(user);
        log.info("New user is created: " + user);
    }
}
