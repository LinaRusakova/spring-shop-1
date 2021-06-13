package com.example.springshop1.frontend;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("main")
public class MainView extends VerticalLayout {
    public MainView() {
        var titleHL = new HorizontalLayout();
        var textHL = new HorizontalLayout();

        var title = new Text("Проверка связи");
        var titleButton = new Button("Нажми меня", event -> event.getSource().setText("Кнопка нажата"));

        var text = new Text("А здесь чуть ниже");

        titleHL.add(title, titleButton);
        textHL.add(text);

        add(titleHL, textHL);

    }


}
