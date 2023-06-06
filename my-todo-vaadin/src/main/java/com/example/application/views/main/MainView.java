package com.example.application.views.main;

import com.example.application.views.main.model.Todo;
import com.example.application.views.main.repository.TodoRepo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private TodoRepo repo;
    TextField taskField = new TextField();
    Button addButton = new Button("Adicionar");
    VerticalLayout todoList = new VerticalLayout();
    Button clearButton = new Button("limpar lembretes finalizados");
    Button clearAllButton = new Button("limpar lembretes");

    public MainView(TodoRepo repo) {
        this.repo = repo;

        add(
                new H1("Lembretes importantes:"),
                new HorizontalLayout(taskField, addButton),
                todoList,
                clearButton,
                clearAllButton
        );

        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addButton.addClickShortcut(Key.ENTER);
        addButton.addClickListener(e -> {
            repo.save(new Todo(taskField.getValue()));
            taskField.clear();
            taskField.focus();

            refreshTodos();
        });

        clearButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        clearButton.addClickListener(e -> {
            repo.deleteByDone(true);
            refreshTodos();
        });

        clearAllButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        clearAllButton.addClickListener(e -> {
            repo.deleteAll();
            refreshTodos();
        });

        refreshTodos();
    }

    private void refreshTodos() {
        todoList.removeAll();

        repo.findAll()
                .stream()
                .map(TodoLayout::new)
                .forEach(todoList::add);
    }

    class TodoLayout extends HorizontalLayout {
        Checkbox done = new Checkbox();
        TextField task = new TextField();

        public TodoLayout(Todo todo) {
            add(done,task);
            setDefaultVerticalComponentAlignment(Alignment.CENTER);

            Binder<Todo> binder = new Binder<>(Todo.class);
            binder.bindInstanceFields(this);
            binder.setBean(todo);

            binder.addValueChangeListener(e ->{
                repo.save(binder.getBean());
            });
        }
    }
}

