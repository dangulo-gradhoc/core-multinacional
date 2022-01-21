package com.multinacional.core.model.service.entitymanager;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;

@Getter
@Setter
public class MultinacionalEntityManagerService {

    @Autowired
    @Qualifier(value = "multinacionalEntityManager")
    protected EntityManager entityManager;
}
