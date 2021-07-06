package com.example.springjpademo;

import com.example.springjpademo.domain.Address;
import com.example.springjpademo.domain.Person;
import com.example.springjpademo.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SpringJpaDemoApplication implements CommandLineRunner {
    @Autowired
    private PersonDao personDao;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<Person> byId = personDao.findById(1);
        Person person = byId.get();
        System.out.println(person);
    }
}
