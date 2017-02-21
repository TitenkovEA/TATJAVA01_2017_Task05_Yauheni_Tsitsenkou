package com.epam.task5.parser;

import com.epam.task5.bean.Dish;
import com.epam.task5.parser.exception.XMLMenuParserException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/20/2017.
 */
public interface XMLMenuParser {
    Map<String, List<Dish>> parse(String filePath) throws XMLMenuParserException;
}
