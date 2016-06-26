package by.bsu.example.command;

import by.bsu.example.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lenovo on 11.04.2016.
 */
public class ReturnCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.main");

        request.setAttribute("lang", locale);
        request.getSession().invalidate();
        return page;
    }
}
