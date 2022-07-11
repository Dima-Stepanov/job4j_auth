package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Employee;
import ru.job4j.repository.EmployeeRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * EmployeeService слой бизнес логики управления моделью Employee.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.07.2022
 */
@Service
public class EmployeeService {
    private final EmployeeRepository employees;

    public EmployeeService(EmployeeRepository employees) {
        this.employees = employees;
    }

    public Employee save(Employee employee) {
        return this.employees.save(employee);
    }

    public Optional<Employee> findById(int id) {
        return this.employees.findById(id);
    }

    public void delete(Employee employee) {
        employees.delete(employee);
    }

    public Iterable<Employee> findAll() {
        return this.employees.findAll();
    }
}
