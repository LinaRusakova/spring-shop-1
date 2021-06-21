package com.example.springshop1.frontend;


import com.example.springshop1.models.Product;
import com.example.springshop1.repository.ProductRepository;
import com.example.springshop1.service.OrderService;
import com.vaadin.flow.component.Text;
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


@Route("main")
public class MainView extends VerticalLayout {

    private final Grid<Product> grid = new Grid<>(Product.class);

    private final ProductRepository productRepository;
    private final OrderService orderService;

    public MainView(ProductRepository productRepository,
                    OrderService orderService) {
        this.productRepository = productRepository;
        this.orderService = orderService;

        initPage();
        var title = new Text("Проверка связи");
        var titleButton = new Button("Нажми меня", event -> event.getSource().setText("Кнопка нажата"));

        var text = new Text("А здесь чуть ниже");
        var titleHL = new HorizontalLayout();
        var textHL = new HorizontalLayout();
        titleHL.add(title, titleButton);
        textHL.add(text);

        add(titleHL, textHL);
    }

    private void initPage() {
        initProductGrid();

        add(grid, initCartButton());
    }

    private HorizontalLayout initCartButton() {
        var addToCartButton = new Button("Добавить в корзину", items -> {
            orderService.addProduct(grid.getSelectedItems());
            Notification.show("Товар успешно добавлен в корзину");
        });

        var toCartButton = new Button("Корзина", item -> {
            UI.getCurrent().navigate("cart");
        });

        return new HorizontalLayout(addToCartButton, toCartButton);
    }

    private void initProductGrid() {
        var products = productRepository.findAll();
        grid.setItems(products);
        grid.setColumns("name", "count");
        grid.setSizeUndefined();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        ListDataProvider<Product> dataProvider = DataProvider.ofCollection(products);
        grid.setDataProvider(dataProvider);

        grid.addColumn(new ComponentRenderer<>(item -> {
            var plusButton = new Button("+", i -> {
                item.incrementCount();
                productRepository.save(item);
                grid.getDataProvider().refreshItem(item);
            });

            var minusButton = new Button("-", i -> {
                item.decreaseCount();
                productRepository.save(item);
                grid.getDataProvider().refreshItem(item);
            });

            return new HorizontalLayout(plusButton, minusButton);
        }));
    }


}
