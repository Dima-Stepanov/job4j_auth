package ru.job4j.react;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.1. WebFlux
 * 1. Реактивное программирование [#328881]
 * Observe шаблон наблюдатель.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 23.07.2022
 */
public interface Observe<T> {
    void receive(T model);
}
