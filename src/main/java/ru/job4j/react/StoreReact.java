package ru.job4j.react;

import java.util.ArrayList;
import java.util.List;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.1. WebFlux
 * 1. Реактивное программирование [#328881]
 * StoreReact демонстрирует преимущество реактивного программирования.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 23.07.2022
 */
public class StoreReact {
    private final List<String> data = List.of("first", "second", "third",
            "fourth", "fifth", "sixth");

    public void getByReact(Observe<String> observe) throws InterruptedException {
        for (String datum : data) {
            Thread.sleep(1000);
            observe.receive(datum);
        }
    }

    public List<String> get() throws InterruptedException {
        List<String> rsl = new ArrayList<>();
        for (String datum : data) {
            Thread.sleep(1000);
            rsl.add(datum);
        }
        return rsl;
    }

    public static void main(String[] args) throws InterruptedException {
        var store = new StoreReact();
        store.getByReact(System.out::println);
    }
}
