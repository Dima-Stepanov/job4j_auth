package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * 6. ResponseEntity [#504799]
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 18.07.2022
 */
@RestController
@RequestMapping("/example")
public class ExampleController {
    /**
     * ResponseEntity.ok()
     */
    @GetMapping("/example1")
    public ResponseEntity<?> example1() {
        return ResponseEntity.ok("Example1");
    }

    /**
     * ResponseEntity.of()
     */
    @GetMapping("/example2")
    public ResponseEntity<?> example2() {
        return ResponseEntity.of(Optional.of(new HashMap() {{
            put("key", "value");
        }}));
    }

    /**
     * ResponseEntity конструктор
     */
    @GetMapping("/example3")
    public ResponseEntity<?> example3() {
        Object body = new HashMap<>() {{
            put("key", "value");
        }};
        ResponseEntity<?> entity = new ResponseEntity<>(
                body,
                new MultiValueMapAdapter<>(
                        Map.of(
                                "Job4jCustomHeader",
                                List.of("job4j.midle"))),
                HttpStatus.OK
        );
        return entity;
    }

    /**
     * ResponseEntity. BodyBuilder
     * MediaType.TEXT_PLAIN
     * пример передачи текста
     */
    @GetMapping("/example4")
    public ResponseEntity<String> example4() {
        var body = new HashMap<>() {{
            put("key", "value");
        }}.toString();
        var entity = ResponseEntity.status(HttpStatus.CONFLICT)
                .header("Job4jCustomHeader", "job4j")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(body.length())
                .body(body);
        return entity;
    }

    /**
     * ResponseEntity. BodyBuilder
     * MediaType.APPLICATION_PDF
     * пример раздачи файла.
     */
    @GetMapping("/example5")
    public ResponseEntity<byte[]> example5() throws IOException {
        var content = Files.readAllBytes(Path.of("./file/book.pdf"));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(content.length)
                .body(content);
    }

    /**
     * ResponseEntity. BodyBuilder
     * раздавать файл с заголовком ответа
     * "Content-type: application/octet-stream"
     */
    @GetMapping("/example6")
    public byte[] example6() throws IOException {
        return Files.readAllBytes(Path.of("./pom.xml"));
    }
}
