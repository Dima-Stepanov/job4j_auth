package ru.job4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Employee;
import ru.job4j.service.EmployeeService;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * EmployeeController rest
 * реализованы метод получения всех сотрудников,
 * со всеми их аккаунтами (Person),
 * добавление нового аккаунта,
 * изменение и удаление существующих аккаунтов.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.07.2022
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class.getSimpleName());
    private final EmployeeService employees;

    public EmployeeController(EmployeeService employees) {
        this.employees = employees;
    }

    @GetMapping("/")
    public Iterable<Employee> findAll() {
        LOG.info("Get All Employee");
        return this.employees.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        LOG.info("Employee find by id={}", id);
        Optional<Employee> employee = employees.findById(id);
        return new ResponseEntity<>(
                employee.orElse(new Employee()),
                employee.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        LOG.info("Crete employee={}", employee);
        return new ResponseEntity<>(
                this.employees.save(employee),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Employee employee) {
        LOG.info("Update employee={}", employee);
        this.employees.save(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Employee employee = new Employee();
        employee.setId(id);
        this.employees.delete(employee);
        LOG.info("Delete employee={}", id);
        return ResponseEntity.ok().build();
    }
}
