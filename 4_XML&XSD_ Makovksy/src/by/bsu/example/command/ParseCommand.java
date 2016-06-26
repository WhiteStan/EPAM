package by.bsu.example.command;

import by.bsu.example.parser.BaseParser;
import by.bsu.example.parser.factory.ParserFactory;
import by.bsu.example.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;

/**
 * Created by Lenovo on 06.04.2016.
 */
public class ParseCommand extends ActionCommand {
    private static final String PATH_TO_XML = "/resources/Banks.xml";
    private static final String PARAM_NAME_SET_OF_DEPOSITS = "lst";
    private static final String PARAM_NAME_PARSER_TYPE = "parserType";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ParserFactory factory = new ParserFactory();
        BaseParser parser = factory.create(request);
        parser.buildSetDeposits(request.getServletContext().getRealPath(PATH_TO_XML));
        request.setAttribute(PARAM_NAME_SET_OF_DEPOSITS, parser.getDeposits());
        request.setAttribute(PARAM_NAME_PARSER_TYPE, request.getParameter("parser"));
        page = ConfigurationManager.getProperty("path.page.output");
        return page;
    }
}
