package com.epam.task5.parser.impl.sax.handler;

import com.epam.task5.bean.Contains;
import com.epam.task5.bean.Description;
import com.epam.task5.bean.Dish;
import com.epam.task5.constant.menu.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

/**
 * Created by Evgeny on 21.02.2017.
 */
public class SAXMenuHandler extends DefaultHandler {
    private final Map<String, List<Dish>> kitchen = new HashMap<>();

    private String menuType;
    private List<Dish> dishes;
    private Dish dish;
    private Description description;
    private List<Contains> containsList;
    private Contains contains;

    public Map<String, List<Dish>> getKitchen() {
        return kitchen;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (localName) {
            case Tag.MENU:
                menuType = attributes.getValue(MenuAttribute.TYPE);
                dishes = new LinkedList<>();
                break;
            case Tag.DISH:
                dish = new Dish();
                dish.setId(attributes.getValue(DishAttribute.ID));
                dish.setPhotoUrl(attributes.getValue(DishAttribute.PHOTO));
                dish.setTitle(attributes.getValue(DishAttribute.TITLE));
                break;
            case Tag.DESCRIPTION:
                description = new Description();
                description.setValue(attributes.getValue(DescriptionAttribute.VALUE));
                description.setPortion(attributes.getValue(DescriptionAttribute.PORTION));
                description.setCost(attributes.getValue(DescriptionAttribute.COST));
                containsList = new LinkedList<>();
                break;
            case Tag.CONTAINS:
                contains = new Contains();
                contains.setNumber(attributes.getValue(ContainsAttribute.NUMBER));
                contains.setIngredient(attributes.getValue(ContainsAttribute.INGREDIENT));
                contains.setCost(attributes.getValue(ContainsAttribute.COST));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (localName) {
            case Tag.MENU:
                kitchen.put(menuType, new ArrayList<>(dishes));
                break;
            case Tag.DISH:
                dish.setDescription(description);
                dishes.add(dish);
                break;
            case Tag.DESCRIPTION:
                description.setContainsList(new ArrayList<>(containsList));
                break;
            case Tag.CONTAINS:
                containsList.add(contains);
                break;
        }
    }
}
