package com.engagepoint.university.messaging.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer{

    @Produces
    @PersistenceContext(name = "persistenceUnit")
    private EntityManager entityManager;

}
