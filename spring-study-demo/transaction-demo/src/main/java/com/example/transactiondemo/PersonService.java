package com.example.transactiondemo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonService {
    private final JdbcTemplate template;

    public PersonService(JdbcTemplate template) {
        this.template = template;
    }

//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertPerson() {
        template.execute("INSERT INTO test.person (id, name, age) VALUES (3, 'cc', 18)");
    }
}
