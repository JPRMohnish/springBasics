package com.learnJava.demo.dao;

import com.learnJava.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("In Memory DAO")
public class InMemoryPersonDataAccessService implements PersonDao {
    public static List<Person> DB =  new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) throws Exception {
        System.out.println("hello adding person");
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectAPerson(UUID id) {
        System.out.println(id);
        return DB.stream()
                .filter(person -> {
                    if(id == null) return false;
                    return person.getId().equals(id);
                })
                .findFirst();
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> person = selectAPerson(id);
        if(person.isEmpty()) {
            return 0;
        }
        DB.remove(person.get());
        return 1;
    }

    @Override
    public int UpdatePerson(UUID id, Person person) {
        Optional<Person> p = selectAPerson(id);
        int index = DB.indexOf(p.get());
        return  index < 0? 0: Boolean.compare(DB.set(index, new Person(id, person.getName())).equals(person), true);
    }
}
