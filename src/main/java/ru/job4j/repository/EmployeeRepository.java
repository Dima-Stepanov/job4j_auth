package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Employee;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * EmployeeRepository CRUD модели Employee баз данных fullstack_auth
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.07.2022
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
