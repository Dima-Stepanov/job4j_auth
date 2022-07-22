package ru.job4j.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.domain.Book;
import ru.job4j.handle.Operation;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * 8. Валидация моделей в Spring REST [#504801]
 * BookController контроллер
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 22.07.2022
 */
@RestController
@RequestMapping("/book")
public class BookController {
    private final Map<Integer, Book> repository = new HashMap<>();
    private int id = 1;

    @PostMapping
    @Validated(Operation.OnCreate.class)
    public Book save(@Valid @RequestBody Book book) {
        book.setId(id++);
        repository.put(id, book);
        return book;
    }
}
