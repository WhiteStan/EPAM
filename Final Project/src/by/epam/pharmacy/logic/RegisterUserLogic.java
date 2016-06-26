package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.LoginInfoDAO;
import by.epam.pharmacy.entity.LoginInfo;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Lenovo on 14.04.2016.
 */
public class RegisterUserLogic {
    private final static Logger LOG = LogManager.getLogger(RegisterUserLogic.class);

    public boolean doRegistration(LoginInfo login) {
        boolean result = false;
        LoginInfoDAO loginInfoDAO = new LoginInfoDAO();
        RegistrationValidator validator = new RegistrationValidator();
        try {
                loginInfoDAO.addUser(login);
                result = true;
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            loginInfoDAO.closeConnection();
        }
        return result;
    }
}
