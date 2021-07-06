package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.PersonMapper;
import org.example.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        Person newPerson = new Person();
        newPerson.setId(2);
        newPerson.setAge(20);
        newPerson.setName("cc");

        try (SqlSession session = sqlSessionFactory.openSession()) {
            PersonMapper mapper = session.getMapper(PersonMapper.class);
            for (int i = 0; i < 5; i++) {
//                if (i == 3) {
//                    mapper.save(newPerson);
//                }
                Person person = mapper.selectPerson(1);
                LOGGER.info("====={}====query person:{}",i, person);
            }

            session.commit();
        }
    }
}
