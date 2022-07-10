package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Person;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * 0. RESTFull. Описание архитектуры [#6884]
 * PersonRepository CRUD модели Person баз данных fullstack_auth
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 10.07.2022
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
