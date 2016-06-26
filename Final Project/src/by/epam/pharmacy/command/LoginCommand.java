package by.epam.pharmacy.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.pharmacy.entity.LoginInfo;
import by.epam.pharmacy.util.Encryptor;
import by.epam.pharmacy.logic.LoginLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import java.util.Locale;

public class LoginCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String login = request.getParameter(JspParamName.PARAM_LOGIN);
        String pass = request.getParameter(JspParamName.PARAM_PASSWORD);
        LoginLogic loginLogic = new LoginLogic();
        LoginInfo loginInfo = loginLogic.checkLogin(login);
        if (loginInfo != null && loginInfo.checkLogin(login, Encryptor.getHash(pass))) {
            request.setAttribute(JspParamName.PARAM_USER, login);
            request.getSession().setAttribute("role", loginInfo.getRole().toUpperCase());
            request.getSession().setAttribute("login", loginInfo.getLogin());
            page = JspPathName.PATH_TO_MAIN_JSP;
            request.getSession().setAttribute(JspParamName.PARAM_PAGE, JspPathName.PATH_TO_MAIN_JSP);
        } else {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_LOGIN,
                    messageManager.getProperty("message.loginerror"));
            page = JspPathName.PATH_TO_LOGIN_JSP;
            request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        }
        return page;
    }
}