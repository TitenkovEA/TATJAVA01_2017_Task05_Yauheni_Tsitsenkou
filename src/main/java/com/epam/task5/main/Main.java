package com.epam.task5.main;

import com.epam.task5.bean.Dish;
import com.epam.task5.parser.XMLMenuParser;
import com.epam.task5.parser.exception.XMLMenuParserException;
import com.epam.task5.parser.factory.XMLMenuParserFactory;
import com.epam.task5.parser.impl.dom.DOMMenuParser;
import com.epam.task5.parser.impl.sax.SAXMenuParser;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/20/2017.
 */
public class Main {
    private final static String XML_MENU_PATH = "menu.xml";

    public static void main(String[] args) throws XMLMenuParserException {
        XMLMenuParserFactory factory = XMLMenuParserFactory.getInstance();
        XMLMenuParser domParser = factory.getDOMParser();
        XMLMenuParser saxParser = factory.getSAXParser();
        XMLMenuParser staxParser = factory.getStAXParser();

        printMenu(domParser.parse(XML_MENU_PATH));
        printMenu(saxParser.parse(XML_MENU_PATH));
        printMenu(staxParser.parse(XML_MENU_PATH));
    }

    private static void printMenu(Map<String, List<Dish>> menu) {
        for (Map.Entry<String, List<Dish>> entry : menu.entrySet()){
            System.out.println(entry.getKey());
            for (Dish dish : entry.getValue()) {
                System.out.println(dish);
            }
        }
    }
}
