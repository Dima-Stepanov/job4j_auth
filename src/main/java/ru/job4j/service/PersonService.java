package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * PersonService слой бизнес логики управления моделью Person.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 10.07.2022
 */
@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> findById(int id) {
        return this.personRepository.findById(id);
    }

    public void delete(Person person) {
        this.personRepository.delete(person);
    }

    public Iterable<Person> findAll() {
        return this.personRepository.findAll();
    }

}
