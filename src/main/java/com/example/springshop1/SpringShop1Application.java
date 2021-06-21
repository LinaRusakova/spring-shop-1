package com.example.springshop1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringShop1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringShop1Application.class, args);
    }

    //USER - пользователь
    //ROLE - Роль - Менеджер(Контролирование количества товаров, Просмотр покупателей),
    // Покупатель(заказ товара), если успеем добавим Администратора
    //PRODUCT - Товар(если успеем, то добавление файлов(например фото товара))
    //REVIEWS - Отзывы
    //Cart - корзина
    //Двухфакторная авторизация


    //TODO
    // реализовать добавление количества единиц товара
    // сделать проверку на "0" количества товара
}
