package ru.job4j.domain;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * Employee модель данных сотрудник компании.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.07.2022
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Column(unique = true)
    private int inn;
    private LocalDate hiring = LocalDate.now();

    public static Employee of(String name, String surname, int inn) {
        Employee employee = new Employee();
        employee.name = name;
        employee.surname = surname;
        employee.inn = inn;
        return employee;
    }

    public static Employee of(int id, String name, String surname, int inn) {
        Employee employee = new Employee();
        employee.id = id;
        employee.name = name;
        employee.surname = surname;
        employee.inn = inn;
        return employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public LocalDate getHiring() {
        return hiring;
    }

    public void setHiring(LocalDate hiring) {
        this.hiring = hiring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\''
                + ", inn=" + inn + ", hiring=" + hiring + '}';
    }
}
