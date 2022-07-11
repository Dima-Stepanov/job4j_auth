package ru.job4j.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * 1. Интеграция Rest сервисов через RestTemplate [#96916]
 * Report, модель, которая будет содержать информацию о добавленных записях Person в виде отчета.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.07.2022
 */
public class Report {
    private int id;
    private String name;
    private LocalDateTime created = LocalDateTime.now();
    private Person person;

    public static Report of(int id, String name, Person person) {
        Report report = new Report();
        report.id = id;
        report.name = name;
        report.person = person;
        return report;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        return id == report.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Report{id=" + id + ", name='" + name + '\''
                + ", created=" + created + ", person=" + person + '}';
    }
}
