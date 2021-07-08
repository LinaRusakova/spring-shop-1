package com.example.springshop1.frontend;


import com.example.springshop1.models.Product;
import com.example.springshop1.services.CartService;
import com.example.springshop1.services.OrderService;
import com.example.springshop1.services.ProductService;
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

import java.util.List;


@Route("main")
public class MainView extends VerticalLayout {

    private final Grid<Product> grid = new Grid<>(Product.class);

    private final CartService cartService;
    private final ProductService productService;


    public MainView(
            OrderService orderService, CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;

        initPage();
//        var title = new Text("Проверка связи");
//        var titleButton = new Button("Нажми меня", event -> event.getSource().setText("Кнопка нажата"));
//
//        var text = new Text("А здесь чуть ниже");
//        var titleHL = new HorizontalLayout();
//        var textHL = new HorizontalLayout();
//        titleHL.add(title, titleButton);
//        textHL.add(text);

//        add(titleHL, textHL);
    }

    private void initPage() {
        initProductGrid();

        add(grid, initCartButton());
    }

    private HorizontalLayout initCartButton() {
        var addToCartButton = new Button("Добавить в корзину", items -> {
            Boolean isAdded = cartService.addListProductToCart(grid.getSelectedItems());
            if (isAdded) {
                Notification.show("Товар успешно добавлен в корзину");
            }
        });

        var toCartButton = new Button("Корзина", item -> {
            UI.getCurrent().navigate("cart");
        });

        return new HorizontalLayout(addToCartButton, toCartButton);
    }

    private void initProductGrid() {
        List<Product> products = productService.getAllProduct();
        grid.setItems(products);
        grid.setColumns("name", "count", "price");
        grid.setSizeUndefined();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        ListDataProvider<Product> dataProvider = DataProvider.ofCollection(products);
        grid.setDataProvider(dataProvider);

        grid.addColumn(new ComponentRenderer<>(item -> {
            var plusButton = new Button("+", i -> {
                item.incrementCount();
                productService.save(item);
                grid.getDataProvider().refreshItem(item);
            });

            var minusButton = new Button("-", i -> {
                item.decreaseCount();
                productService.save(item);
                grid.getDataProvider().refreshItem(item);
            });

            return new HorizontalLayout(plusButton, minusButton);
        }));
    }


}
