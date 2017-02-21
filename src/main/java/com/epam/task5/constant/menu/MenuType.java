package com.epam.task5.constant.menu;

/**
 * Created by Evgeny on 21.02.2017.
 */
public enum MenuType {
    COLD_SNACKS("ХОЛОДНЫЕ ЗАКУСКИ"),
    HOT_SNACKS("ГОРЯЧИЕ ЗАКУСКИ"),
    BREAKFASTS("ЗАВТРАКИ"),
    SALADS("САЛАТЫ"),
    SOUPS("СУПЫ"),
    FISH_DISHES("РЫБНЫЕ БЛЮДА"),
    MEAT_DISHES("МЯСНЫЕ БЛЮДА"),
    GARNISHES("ГАРНИРЫ"),
    GRILL_DISHES("БЛЮДА НА МАНГАЛЕ"),
    FROM_THE_CHIEF("ОТ ШЕФ-ПОВАРА"),
    ATTACHMENT("ПРИЛОЖЕНИЕ"),
    DESERT("ДЕСЕРТ");

    private String value;

    MenuType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
