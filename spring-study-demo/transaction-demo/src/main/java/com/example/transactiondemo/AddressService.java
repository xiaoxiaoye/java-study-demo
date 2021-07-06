package com.example.transactiondemo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddressService {
    private final JdbcTemplate template;

    public AddressService(JdbcTemplate template) {
        this.template = template;
    }

//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertAddress() {
        template.execute("INSERT INTO test.address (person_id, address) VALUES (3, 'hangzhou')");
    }
}
