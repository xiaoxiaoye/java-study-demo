package com.example.springjpademo;

import com.example.springjpademo.domain.Person;
import com.example.springjpademo.repository.PersonDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SpringJpaDemoApplicationTests {

    @Autowired
    private PersonDao personDao;

    @Test
    void contextLoads() {
    }

    @Test
    void testPersonQuery() {
        Optional<Person> byId = personDao.findById(1);
        System.out.println("byId:"+byId.get());
    }

}
