package com.example.springshop1.frontend;


import com.example.springshop1.models.Product;
import com.example.springshop1.service.OrderService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;


@Route("cart")
public class CartView extends VerticalLayout {
    private final Grid<Product> grid = new Grid<>(Product.class);

    private final OrderService orderService;

    public CartView(OrderService orderService) {
        this.orderService = orderService;

        initCartGrid();
        add(grid);
        var toMainButton = new Button("На главную",
                event -> UI.getCurrent().navigate("main"));
        add(toMainButton);
        var saveCartButton = new Button("Сохранить корзину",
                event -> {
                    orderService.saveCartToDB(orderService.getProducts());
                    Notification.show("Ваша Корзина была успешно сохранена");
                    UI.getCurrent().navigate("cart");

                });
        add(toMainButton);
    }

    private void initCartGrid() {
        var products = orderService.getProducts();

        grid.setItems(products);
        grid.setColumns("name", "count");
        grid.setSizeUndefined();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        ListDataProvider<Product> dataProvider = DataProvider.ofCollection(products);
        grid.setDataProvider(dataProvider);

        grid.addColumn(new ComponentRenderer<>(item -> {
            var plusButton = new Button("+", i -> {
                orderService.increaseProductCount(item);
                grid.getDataProvider().refreshItem(item);
            });

            var minusButton = new Button("-", i -> {
                orderService.decreaseProductCount(item);
                grid.getDataProvider().refreshItem(item);
            });

            return new HorizontalLayout(plusButton, minusButton);
        }));
    }
}
