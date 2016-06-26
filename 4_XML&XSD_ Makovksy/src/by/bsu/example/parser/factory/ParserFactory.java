package by.bsu.example.parser.factory;

import by.bsu.example.parser.BaseParser;
import by.bsu.example.command.client.CommandEnum;
import by.bsu.example.parser.client.ParserEnum;
import by.bsu.example.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lenovo on 06.04.2016.
 */
public class ParserFactory {
    public BaseParser create(HttpServletRequest request) {
        String parser = request.getParameter("parser");
        BaseParser current = null;
        if (parser == null || parser.isEmpty()) {
            return null;
        }
        try {
            ParserEnum currentEnum = ParserEnum.valueOf(parser.toUpperCase());
            current = currentEnum.getCurrentParser();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", parser
                    + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
