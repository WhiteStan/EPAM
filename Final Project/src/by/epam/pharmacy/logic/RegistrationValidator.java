package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.LoginInfoDAO;
import by.epam.pharmacy.entity.LoginInfo;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidator {
    private final static String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}";
    private final static Logger LOG = LogManager.getLogger(RegistrationValidator.class);

    public boolean validate(String login, String password){
        boolean result = false;
        if(validateLogin(login) && validatePassword(password)){
            result=true;
        }
        return result;
    }
    private boolean validatePassword(String data) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(data);
        boolean result = matcher.matches();
        return result;
    }
    private boolean validateLogin(String login) {
        boolean result = false;
        LoginInfoDAO loginInfoDAO = new LoginInfoDAO();
        try {
            LoginInfo loginInfo = loginInfoDAO.findEntityByName(login);
            if(loginInfo == null){
                result = true;
            }
        }
        catch(DAOException e){
            LOG.error(e);
        }
        return result;
    }
}
