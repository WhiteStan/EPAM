package by.bsu.example.parser.client;

import by.bsu.example.command.*;
import by.bsu.example.parser.BaseParser;
import by.bsu.example.parser.DOMBuilder;
import by.bsu.example.parser.SAXBuilder;
import by.bsu.example.parser.StAXBuilder;

/**
 * Created by Lenovo on 06.04.2016.
 */
public enum ParserEnum {
    DOM {
        {
            this.parser = new DOMBuilder();
        }
    },
    SAX {
        {
            this.parser = new SAXBuilder();
        }
    },
    STAX{
        {
            this.parser = new StAXBuilder();
        }
    };
    BaseParser parser;
    public BaseParser getCurrentParser() {
        return parser;
    }
}
