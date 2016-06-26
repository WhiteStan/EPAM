package by.epam.pharmacy.command.factory;

import javax.servlet.http.HttpServletRequest;

import by.epam.pharmacy.command.ActionCommand;
import by.epam.pharmacy.command.EmptyCommand;
import by.epam.pharmacy.resource.MessageManager;
import by.epam.pharmacy.command.CommandEnum;

import java.util.Locale;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            String[] locale = ((String) request.getSession().getAttribute("lang")).split("_");
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute("wrongAction", action
                    + messageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}