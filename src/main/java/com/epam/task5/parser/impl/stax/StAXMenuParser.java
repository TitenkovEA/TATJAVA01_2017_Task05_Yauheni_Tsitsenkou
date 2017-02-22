package com.epam.task5.parser.impl.stax;

import com.epam.task5.bean.Contains;
import com.epam.task5.bean.Description;
import com.epam.task5.bean.Dish;
import com.epam.task5.constant.menu.*;
import com.epam.task5.parser.XMLMenuParser;
import com.epam.task5.parser.exception.XMLMenuParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Yauheni_Tsitsenkou on 2/20/2017.
 */
public class StAXMenuParser implements XMLMenuParser {
    private static final Logger logger = LogManager.getLogger(StAXMenuParser.class);

    private static final Map<String, List<Dish>> kitchen = new HashMap<>();

    private String menuType;
    private List<Dish> dishes;
    private Dish dish;
    private Description description;
    private List<Contains> containsList;
    private Contains contains;

    public Map<String, List<Dish>> parse(String filePath) throws XMLMenuParserException {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream input = new FileInputStream(filePath);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);

            int tegType;
            while (reader.hasNext()) {
                tegType = reader.next();
                switch (tegType) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement(reader);
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        endElement(reader);
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {

            throw new XMLMenuParserException(e);
        }
        return kitchen;
    }

    private void startElement(XMLStreamReader reader) {
        switch (reader.getLocalName()) {
            case Tag.MENU:
                menuType = reader.getAttributeValue(null, MenuAttribute.TYPE);
                dishes = new LinkedList<>();
                break;
            case Tag.DISH:
                dish = new Dish();
                dish.setId(reader.getAttributeValue(null, DishAttribute.ID));
                dish.setTitle(reader.getAttributeValue(null, DishAttribute.TITLE));
                dish.setPhotoUrl(reader.getAttributeValue(null, DishAttribute.PHOTO));
                break;
            case Tag.DESCRIPTION:
                description = new Description();
                description.setValue(reader.getAttributeValue(null, DescriptionAttribute.VALUE));
                description.setPortion(reader.getAttributeValue(null, DescriptionAttribute.PORTION));
                description.setCost(reader.getAttributeValue(null, DescriptionAttribute.COST));
                containsList = new LinkedList<>();
                break;
            case Tag.CONTAINS:
                contains = new Contains();
                contains.setNumber(reader.getAttributeValue(null, ContainsAttribute.NUMBER));
                contains.setIngredient(reader.getAttributeValue(null, ContainsAttribute.INGREDIENT));
                contains.setCost(reader.getAttributeValue(null, ContainsAttribute.COST));
                break;
        }
    }

    private void endElement(XMLStreamReader reader) {
        switch (reader.getLocalName()) {
            case Tag.MENU:
                kitchen.put(menuType, dishes);
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
