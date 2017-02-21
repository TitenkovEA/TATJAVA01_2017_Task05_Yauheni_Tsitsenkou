package com.epam.task5.parser.factory;

import com.epam.task5.parser.XMLMenuParser;
import com.epam.task5.parser.impl.dom.DOMMenuParser;
import com.epam.task5.parser.impl.sax.SAXMenuParser;
import com.epam.task5.parser.impl.stax.StAXMenuParser;

/**
 * Created by Evgeny on 21.02.2017.
 */
public class XMLMenuParserFactory {
    private static final XMLMenuParserFactory instance = new XMLMenuParserFactory();

    private final XMLMenuParser domParser = new DOMMenuParser();
    private final XMLMenuParser saxParser = new SAXMenuParser();
    private final XMLMenuParser staxParser = new StAXMenuParser();

    private XMLMenuParserFactory() {
    }

    public static XMLMenuParserFactory getInstance() {
        return instance;
    }

    public XMLMenuParser getSAXParser() {
        return saxParser;
    }

    public XMLMenuParser getStAXParser() {
        return staxParser;
    }

    public XMLMenuParser getDOMParser() {
        return domParser;

    }
}
