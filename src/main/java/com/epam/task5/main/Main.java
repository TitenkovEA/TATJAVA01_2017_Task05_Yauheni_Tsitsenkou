package com.epam.task5.main;

import com.epam.task5.bean.Contains;
import com.epam.task5.bean.Description;
import com.epam.task5.bean.Dish;
import com.epam.task5.parser.XMLMenuParser;
import com.epam.task5.parser.exception.XMLMenuParserException;
import com.epam.task5.parser.factory.XMLMenuParserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Formatter;
import java.util.List;
import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/20/2017.
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private final static String XML_MENU_PATH = "menu.xml";

    private final static String PHOTO = "Фото";
    private final static String TITLE = "Название";
    private final static String DESCRIPTION = "Описание";
    private final static String PORTION = "Порция";
    private final static String COST = "Цена";

    private final static String TABLE_HEAD_FORMAT = "%s\n";
    private final static String TABLE_ROW_FORMAT = "|%5s|%30s|%40s|%10s|%5s|\n";
    private static Formatter formatter;

    public static void main(String[] args) {
        XMLMenuParserFactory factory = XMLMenuParserFactory.getInstance();
        XMLMenuParser domParser = factory.getDOMParser();
        XMLMenuParser saxParser = factory.getSAXParser();
        XMLMenuParser staxParser = factory.getStAXParser();

        try {
            System.out.println("DOM menu parser:");
            formatMenuTable(domParser.parse(XML_MENU_PATH));
            System.out.println(formatter);

            System.out.println("SAX menu parser:");
            formatMenuTable(saxParser.parse(XML_MENU_PATH));
            System.out.println(formatter);

            System.out.println("StAX menu parser:");
            formatMenuTable(staxParser.parse(XML_MENU_PATH));
            System.out.println(formatter);
        } catch (XMLMenuParserException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    private static void formatMenuTable(Map<String, List<Dish>> menu) {
        formatter = new Formatter();
        for (Map.Entry<String, List<Dish>> entry : menu.entrySet()){
            formatter.format(TABLE_HEAD_FORMAT, entry.getKey());
            formatter.format(TABLE_ROW_FORMAT,
                    PHOTO, TITLE, DESCRIPTION, PORTION, COST);
            for (Dish dish : entry.getValue()) {
                formatDishRow(dish);
            }
        }
    }

    private static void formatDishRow(Dish dish) {
        Description description = dish.getDescription();
        formatter.format(TABLE_ROW_FORMAT,
                dish.getPhotoUrl(),
                dish.getTitle(),
                description.getValue(),
                description.getPortion(),
                description.getCost());

        for (Contains contains : description.getContainsList()) {
            formatter.format(TABLE_ROW_FORMAT,
                    "",
                    "",
                    contains.getNumber() + "." + contains.getIngredient(),
                    "",
                    contains.getNumber() + "." + contains.getCost());
        }
    }
}
