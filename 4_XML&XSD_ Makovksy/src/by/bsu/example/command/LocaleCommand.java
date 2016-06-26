package by.bsu.example.command;

import by.bsu.example.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lenovo on 30.03.2016.
 */
public class LocaleCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String locale = null;
        String lang = (String)request.getSession().setAttribute("locale");
        if (lang.equals("en_US")) {
            locale = "ru_RU";
        } else if (language.equals("ru_RU")) {
            locale = "en_US";
        }
        request.getSession().setAttribute("locale", locale);
        page = ConfigurationManager.getProperty(request.getParameter("page"));
        return page;
    }
}
