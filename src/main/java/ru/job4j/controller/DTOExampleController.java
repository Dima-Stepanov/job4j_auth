package ru.job4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.Employee;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.8. Rest
 * 7. DTO [#504800]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.07.2022
 */
@RestController
@RequestMapping("/dto")
public class DTOExampleController {
    private static final Logger LOG = LoggerFactory.getLogger(DTOExampleController.class.getSimpleName());
    private final Map<Integer, Employee> employeeService = new HashMap<>(Map.ofEntries(
            Map.entry(1, Employee.of(1, "Dima", "Stir", 1111)),
            Map.entry(2, Employee.of(2, "Masha", "Penash", 22222)),
            Map.entry(3, Employee.of(3, "Dasha", "Chebir", 33333))
    ));

    @PatchMapping("/dtoEx")
    public Employee dtoEx(@RequestBody Employee employee)
            throws InvocationTargetException,
            IllegalAccessError,
            IllegalAccessException {
        Employee current = employeeService.get(employee.getId());
        if (current == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Method[] methods = current.getClass().getDeclaredMethods();
        LOG.info("**************{}", methods.length);
        Map<String, Method> namePerMethod = new HashMap<>();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("get") || name.startsWith("set")) {
                namePerMethod.put(name, method);
            }
        }

        for (String name : namePerMethod.keySet()) {
            if (name.startsWith("get")) {
                Method getMethod = namePerMethod.get(name);
                Method setMethod = namePerMethod.get(name.replace("get", "set"));
                if (setMethod == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Impossible invoke set method from object : "
                                    + current
                                    + ", Check set and get pairs.");
                }
                var newValue = getMethod.invoke(employee);
                if (newValue != null) {
                    setMethod.invoke(current, newValue);
                }
            }
        }
        return employeeService.put(employee.getId(), employee);
    }

}
