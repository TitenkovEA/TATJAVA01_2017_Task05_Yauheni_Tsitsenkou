package com.epam.task5.parser.impl.dom.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by Evgeny on 21.02.2017.
 */
public final class DOMHelper {
    public static Element getFirstChildElement(Node node) {
        node = node.getFirstChild();
        while (node != null && node.getNodeType() != Node.ELEMENT_NODE) {
            node = node.getNextSibling();
        }
        return (Element) node;
    }
}
