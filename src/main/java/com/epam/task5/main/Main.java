package com.epam.task5.main;

import com.epam.task5.bean.Dish;
import com.epam.task5.parser.impl.dom.DOMMenuParser;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/20/2017.
 */
public class Main {
    public static void main(String[] args) throws IOException, SAXException {
        Map<String, List<Dish>> kitchen = null;
        DOMMenuParser domMenuParser = new DOMMenuParser();
        kitchen = domMenuParser.parse("menu.xml");
        for (Map.Entry<String, List<Dish>> entry : kitchen.entrySet()){
            System.out.println(entry.getKey());
            for (Dish dish : entry.getValue()) {
                System.out.println(dish);
            }
        }
    }
}
