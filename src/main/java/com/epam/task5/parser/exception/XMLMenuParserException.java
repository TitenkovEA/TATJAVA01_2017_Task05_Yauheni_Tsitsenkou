package com.epam.task5.parser.exception;

/**
 * Created by Evgeny on 22.02.2017.
 */
public class XMLMenuParserException extends Exception {
    public XMLMenuParserException() {
    }

    public XMLMenuParserException(String message) {
        super(message);
    }

    public XMLMenuParserException(String message, Exception e) {
        super(message, e);
    }

    public XMLMenuParserException(Exception e) {
        super(e);
    }
}
