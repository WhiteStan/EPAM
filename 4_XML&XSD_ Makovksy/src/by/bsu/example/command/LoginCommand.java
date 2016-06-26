package by.bsu.example.command;
import javax.servlet.http.HttpServletRequest;

import by.bsu.example.logic.Encryptor;
import by.bsu.example.logic.LoginLogic;
import by.bsu.example.resource.ConfigurationManager;
import by.bsu.example.resource.MessageManager;
import by.bsu.example.servlet.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;

public class LoginCommand extends ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private final static Logger LOG = LogManager.getLogger(Controller.class);
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
// извлечение из запроса логина и пароля
        try{
        request.setCharacterEncoding("UTF-8");}
        catch (UnsupportedEncodingException e){
            LOG.info(e);
        }
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
// проверка логина и пароля
        if (LoginLogic.checkLogin(login, pass)) {
            request.setAttribute("user", login);
            request.setAttribute("lang", locale);
// определение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}