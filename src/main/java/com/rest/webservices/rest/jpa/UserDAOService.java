package com.rest.webservices.rest.jpa;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDAOService {
    /*
     * UserEntity jack = new UserEntity("Jack","Admin");
     * UserEntity jill = new UserEntity("Jill","Admin");
     *
     * entityManager.persist(jack);
     *
     * Persistance Context
     * jack.setRole("User");
     * jill.setRole("User");
     * */
    @PersistenceContext
    private EntityManager entityManager;

    public long insert(UserEntity user) {
        //open transaction
        entityManager.persist(user);
        //close transaction
        return user.getId();
    }
}
