package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.LoginInfo;
import by.epam.pharmacy.util.Encryptor;
import by.epam.pharmacy.logic.RegisterUserLogic;
import by.epam.pharmacy.logic.RegistrationValidator;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Command to register new user
 */
public class RegisterCommand implements ActionCommand {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_ADDRESS = "address";
    private static final String PARAM_POSTAL = "postal";
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_SEX = "sex";
    private static final String PARAM_PASSPORTID = "passportId";

    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        String email = request.getParameter(PARAM_EMAIL);
        String name = request.getParameter(PARAM_NAME);
        String address = request.getParameter(PARAM_ADDRESS);
        String strPostal = request.getParameter(PARAM_POSTAL);
        String passportId = request.getParameter(PARAM_PASSPORTID);
        Integer postal = null;
        if(strPostal != null) {
            postal = Integer.parseInt(strPostal);
        }
        String phone = request.getParameter(PARAM_PHONE);
        String sex = request.getParameter(PARAM_SEX);
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        RegistrationValidator registrationValidator = new RegistrationValidator();
        if(registrationValidator.validate(login, password)) {
            LoginInfo loginInfo = new LoginInfo(login, Encryptor.getHash(password), email, name, address,
                    phone, postal, sex, passportId);
            RegisterUserLogic registerUserLogic = new RegisterUserLogic();
            registerUserLogic.doRegistration(loginInfo);
            page = JspPathName.PATH_TO_MAIN_JSP;
            request.getSession().setAttribute(JspParamName.PARAM_PAGE, JspPathName.PATH_TO_MAIN_JSP);
        }
        else {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_REGISTRATION,
                    messageManager.getProperty("message.registrerror"));
            page = JspPathName.PATH_TO_REGISTRATION_JSP;
            request.getSession().setAttribute(JspParamName.PARAM_PAGE, JspPathName.PATH_TO_REGISTRATION_JSP);
        }
        return page;
    }
}
