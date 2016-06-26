package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.LoginInfoDAO;
import by.epam.pharmacy.entity.LoginInfo;
import by.epam.pharmacy.exception.DAOException;
import by.epam.pharmacy.servlet.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public LoginInfo checkLogin(String enterLogin) {
        LoginInfo login = null;
        LoginInfoDAO loginInfoDAO = new LoginInfoDAO();
        try {
            login = loginInfoDAO.findEntityByName(enterLogin);
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            loginInfoDAO.closeConnection();
        }
        return login;
    }
}
