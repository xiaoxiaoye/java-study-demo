package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.model.Person;

import java.util.List;

public interface PersonMapper {
    Person selectPerson(@Param("id") Integer id);
    int save(Person person);
}
