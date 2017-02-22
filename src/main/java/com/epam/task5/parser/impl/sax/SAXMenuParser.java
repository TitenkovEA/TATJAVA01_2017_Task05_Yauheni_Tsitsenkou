package com.epam.task5.parser.impl.sax;

import com.epam.task5.bean.Dish;
import com.epam.task5.parser.XMLMenuParser;
import com.epam.task5.parser.exception.XMLMenuParserException;
import com.epam.task5.parser.impl.sax.handler.SAXMenuHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Yauheni_Tsitsenkou on 2/20/2017.
 */
public class SAXMenuParser implements XMLMenuParser {
    private final static Logger logger = LogManager.getLogger(SAXMenuParser.class);

    public Map<String, List<Dish>> parse(String filePath) throws XMLMenuParserException {
        SAXMenuHandler handler = new SAXMenuHandler();

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(filePath));
        } catch (SAXException | IOException e) {
            logger.error(e);
            throw new XMLMenuParserException(e);
        }

        return handler.getKitchen();
    }
}
