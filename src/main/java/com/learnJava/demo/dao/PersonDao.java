package com.learnJava.demo.dao;

import com.learnJava.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id, Person person) throws Exception;

    default int insertPerson(Person person) throws Exception {
        return insertPerson(UUID.randomUUID(), person);
    }
    List<Person> selectAllPeople ();
    Optional<Person> selectAPerson(UUID id);
    int deletePerson(UUID id);
    int UpdatePerson(UUID id, Person person);
}
