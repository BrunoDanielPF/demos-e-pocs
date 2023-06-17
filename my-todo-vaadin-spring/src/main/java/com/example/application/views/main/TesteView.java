package com.example.application.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/teste")
public class TesteView extends VerticalLayout{

    public TesteView() {
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(new Button("Button 1"));
        add(new Button("Button 2"));
        add(new Button("Button 3"));
        setAlignItems(Alignment.CENTER);
    }
}
