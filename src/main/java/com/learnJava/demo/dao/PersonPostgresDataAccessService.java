package com.learnJava.demo.dao;

import com.learnJava.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.sql.Types;

@Repository("postgres")
public class PersonPostgresDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonPostgresDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) throws Exception {
        final String query = "INSERT INTO person (id, name) values(?, ?)";
        Object [] params = new Object[] {id, person.getName()};
        try {
            System.out.println("success");
            jdbcTemplate.update(query, params);
            return 1;
        }
        catch(Exception e) {
            System.out.println("failure");
            System.out.println(e.toString());
            return 0;
        }
    }

    @Override
    public List<Person> selectAllPeople() {
        final String query = "SELECT id, name FROM person";
        return jdbcTemplate.query(query, (resultSet, i) ->
                new Person(UUID.fromString(resultSet.getString("id")), resultSet.getString("name")));
    }

    @Override
    public Optional<Person> selectAPerson(UUID id) {
        final String query = "SELECT id, name FROM person where id = ?";
        Object [] params = new Object[] {id};
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, params, (resultSet, i) ->
                new Person(UUID.fromString(resultSet.getString("id")), resultSet.getString("name"))));
    }

    @Override
    public int deletePerson(UUID id) {
        final String query = "DELETE FROM person WHERE id = ?";
        Object [] params = new Object[] {id};
        try {
            System.out.println("success");
            jdbcTemplate.update(query, params);
            return 1;
        }
        catch(Exception e) {
            System.out.println("failure");
            System.out.println(e.toString());
            return 0;
        }
    }

    @Override
    public int UpdatePerson(UUID id, Person person) {
        final String query = "UPDATE person SET name = ? WHERE id = ?";
        Object [] params = new Object[] {person.getName(), id};
        try {
            System.out.println("success");
            jdbcTemplate.update(query, params);
            return 1;
        }
        catch(Exception e) {
            System.out.println("failure");
            System.out.println(e.toString());
            return 0;
        }
    }
}
