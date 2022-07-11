package ru.job4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Person;
import ru.job4j.domain.Report;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * 1. Интеграция Rest сервисов через RestTemplate [#96916]
 * ReportsController контроллер для формирования отчета действий над моделью Person.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.07.2022
 */
@RestController
@RequestMapping("/report")
public class ReportsController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportsController.class.getSimpleName());
    private final RestTemplate rest;
    private static final String API_PERSON = "http://localhost:8080/person/";
    private static final String API_PERSON_ID = "http://localhost:8080/person/{id}";

    public ReportsController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping("/")
    public List<Report> findAll() {
        List<Person> persons = rest.exchange(
                API_PERSON, HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {
                }
        ).getBody();
        return persons.stream()
                .map(p -> Report.of(p.getId(), String.valueOf(p.getId()), p))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> findById(@PathVariable int id) {
        Optional<Person> remote = Optional.ofNullable(rest.getForObject(API_PERSON_ID, Person.class, id));
        LOG.info("Result person={}", remote.get());
        Optional<Report> result = Optional.empty();
        if (remote.isPresent()) {
            result = Optional.of(Report.of(id, String.valueOf(id), remote.get()));
        }
        return new ResponseEntity<>(
                result.orElse(Report.of(-1, "-1", new Person())),
                remote.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person result = rest.postForObject(API_PERSON, person, Person.class);
        return new ResponseEntity<>(
                result,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        rest.put(API_PERSON, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_PERSON_ID, id);
        return ResponseEntity.ok().build();
    }
}
