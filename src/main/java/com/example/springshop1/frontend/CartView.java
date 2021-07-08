package com.example.springshop1.frontend;


import com.example.springshop1.utils.Cart;
import com.example.springshop1.models.Product;
import com.example.springshop1.services.CartService;
import com.example.springshop1.services.OrderService;
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
    private final Cart cart;
    private CartService cartService;

    public CartView(Cart cart, OrderService orderService) {
        this.cart = cart;
        this.orderService = orderService;

        initCartGrid();
        add(grid);
        var toMainPageButton = new Button("На главную",
                event -> UI.getCurrent().navigate("main"));
        add(toMainPageButton);
        var toOrderListButton = new Button("Заказы",
                event -> UI.getCurrent().navigate("orders"));
        add(toOrderListButton);
        var createOrderButton = new Button("Заказть",
                event -> {
                    orderService.createOrder(cartService.getCart());
                    Notification.show("Ваша Корзина была успешно сохранена");
                    UI.getCurrent().navigate("orders");

                });
        add(createOrderButton);
    }

    private void initCartGrid() {

        var products = orderService.getCart();

        grid.setItems(products);
        grid.setColumns("Product's Name", "Count", "Price per one", "Cost");
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
