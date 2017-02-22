package com.epam.task5.parser.impl.dom;

import com.epam.task5.bean.Contains;
import com.epam.task5.bean.Description;
import com.epam.task5.bean.Dish;
import com.epam.task5.constant.menu.*;
import com.epam.task5.parser.XMLMenuParser;
import com.epam.task5.parser.exception.XMLMenuParserException;
import com.epam.task5.parser.impl.dom.util.DOMHelper;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.*;

/**
 * Created by Yauheni_Tsitsenkou on 2/20/2017.
 */
public class DOMMenuParser implements XMLMenuParser {
    private final static Logger logger = LogManager.getLogger(DOMMenuParser.class);

    public Map<String, List<Dish>> parse(String filePath) throws XMLMenuParserException {
        DOMParser parser = new DOMParser();

        try {
            parser.parse(filePath);
        } catch (SAXException | IOException e) {
            logger.error(e);
            throw new XMLMenuParserException(e);
        }

        Document document = parser.getDocument();

        Element root = document.getDocumentElement();

        Map<String, List<Dish>> kitchen = new HashMap<>();
        List<Dish> dishes = null;

        NodeList menuNodes = root.getElementsByTagName(Tag.MENU);

        for (int i = 0; i < menuNodes.getLength(); i++) {
            Element menuElement = (Element) menuNodes.item(i);
            dishes = getDishes(menuElement);
            kitchen.put(menuElement.getAttribute(MenuAttribute.TYPE), dishes);
        }

        return kitchen;
    }

    private List<Dish> getDishes(Element menuElement) {
        List<Dish> dishes = new LinkedList<>();
        NodeList dishNodes = menuElement.getElementsByTagName(Tag.DISH);
        Dish dish = null;
        Element dishElement = null;
        for (int i = 0; i < dishNodes.getLength(); i++) {
            dish = new Dish();
            dishElement = (Element) dishNodes.item(i);
            dish.setId(dishElement.getAttribute(DishAttribute.ID));
            dish.setTitle(dishElement.getAttribute(DishAttribute.TITLE));
            dish.setPhotoUrl(dishElement.getAttribute(DishAttribute.PHOTO));
            dish.setDescription(getDescription(dishElement));
            dishes.add(dish);
        }
        return new ArrayList<>(dishes);
    }

    private Description getDescription(Element dishElement) {
        Element descriptionElement = DOMHelper.getFirstChildElement(dishElement);
        Description description = new Description();
        description.setValue(descriptionElement.getAttribute(DescriptionAttribute.VALUE));
        description.setPortion(descriptionElement.getAttribute(DescriptionAttribute.PORTION));
        description.setCost(descriptionElement.getAttribute(DescriptionAttribute.COST));
        if (descriptionElement.getElementsByTagName(Tag.CONTAINS) != null) {
            description.setContainsList(getContainsList(descriptionElement));
        }
        return description;
    }

    private List<Contains> getContainsList(Element descriptionElement) {
        NodeList containsNodes = descriptionElement.getElementsByTagName(Tag.CONTAINS);
        List<Contains> containsList = new LinkedList<>();
        Contains contains = null;
        for (int i = 0; i < containsNodes.getLength(); i++) {
            contains = new Contains();
            Element containsElement = (Element) containsNodes.item(i);
            contains.setNumber(containsElement.getAttribute(ContainsAttribute.NUMBER));
            contains.setIngredient(containsElement.getAttribute(ContainsAttribute.INGREDIENT));
            contains.setCost(containsElement.getAttribute(ContainsAttribute.COST));
            containsList.add(contains);
        }
        return new ArrayList<>(containsList);
    }
}
