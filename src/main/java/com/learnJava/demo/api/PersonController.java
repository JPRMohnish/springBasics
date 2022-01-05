package com.learnJava.demo.api;

import com.learnJava.demo.model.Person;
import com.learnJava.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(@Qualifier("Person Service") PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/add")
    public String addPerson(@RequestBody  Person person) {
        try {
            personService.addPerson(person);
            System.out.println(" =======> Add Person Request");
            return "Triggered Successfully";
        }
        catch(Exception e) {
            return e.toString();
        }
    }

    @GetMapping("/all")
    public List<Person> getAllPersons () {
        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    public Person getAPerson(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public int updatePerson(@PathVariable("id") UUID id,  @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public int deletePerson(@PathVariable("id") UUID id) {
        return personService.deletePerson(id);
    }
}
