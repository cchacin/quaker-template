package myservice;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Service {

    private final Consumer<Todo> saveTodo;
    private final Function<Long, Todo> getTodo;
    private final Supplier<List<Todo>> getTodos;

    public Service(
            final Consumer<Todo> saveTodo,
            final Function<Long, Todo> getTodo,
            final Supplier<List<Todo>> getTodos) {
        this.saveTodo = saveTodo;
        this.getTodo = getTodo;
        this.getTodos = getTodos;
    }

    public void save(final Todo todo) {
        saveTodo.accept(todo);
    }

    public Todo get(final Long id) {
        return getTodo.apply(id);
    }

    public List<Todo> getAll() {
        return getTodos.get();
    }
}
