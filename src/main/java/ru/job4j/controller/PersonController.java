package ru.job4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.service.PersonService;

import java.util.Optional;


/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * PersonController Rest контроллера.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 10.07.2022
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class.getSimpleName());
    private final PersonService persons;
    private final RestTemplate rest;
    private static final String API_EMPLOYEE = "http://localhost:8080/employee/";
    private static final String API_EMPLOYEE_ID = "http://localhost:8080/employee/{id}";

    public PersonController(PersonService persons, RestTemplate rest) {
        this.persons = persons;
        this.rest = rest;
    }

    @GetMapping("/")
    public Iterable<Person> findAll() {
        LOG.info("Get All persons");
        Iterable<Person> result = this.persons.findAll();
        for (Person person : result) {
            person.setEmployee(
                    rest.getForObject(
                            API_EMPLOYEE_ID,
                            Employee.class,
                            person.getEmployee().getId())
            );
        }
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        LOG.info("Person find by id={}", id);
        var person = this.persons.findById(id);
        person.ifPresent(value -> value.setEmployee(
                rest.getForObject(
                        API_EMPLOYEE_ID,
                        Employee.class,
                        value.getEmployee().getId())
        ));
        return new ResponseEntity<>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        LOG.info("Create person={}", person);
        Employee employee = rest.postForObject(API_EMPLOYEE, person.getEmployee(), Employee.class);
        person.setEmployee(employee);
        return new ResponseEntity<>(
                this.persons.save(person),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        LOG.info("Update person={}", person);
        Employee employee = rest.postForObject(API_EMPLOYEE, person.getEmployee(), Employee.class);
        person.setEmployee(employee);
        this.persons.save(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<Person> person = this.persons.findById(id);
        if (person.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        this.persons.delete(person.get());
        rest.delete(API_EMPLOYEE_ID, person.get().getEmployee().getId());
        LOG.info("Delete person id={}", id);
        return ResponseEntity.ok().build();
    }
}
