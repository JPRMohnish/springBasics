package com.learnJava.demo.service;

import com.learnJava.demo.dao.PersonDao;
import com.learnJava.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("Person Service")
public class PersonService {
    private final PersonDao personDao;
    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) throws Exception {
        return personDao.insertPerson(person);
    }
    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectAPerson(id);
    }
    public int deletePerson(UUID id) {
        return personDao.deletePerson(id);
    }
    public int updatePerson(UUID id, Person person) {
        return personDao.UpdatePerson(id, person);
    }
}
