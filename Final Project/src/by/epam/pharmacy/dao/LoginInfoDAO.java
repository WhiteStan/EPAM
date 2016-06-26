package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.LoginInfo;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 13.04.2016.
 */
public class LoginInfoDAO extends AbstractDAO<LoginInfo> {
    private final static String GET_ALL_USERS = "SELECT login FROM person";
    private final static String GET_USER_BY_NAME = "SELECT login,password, role FROM person WHERE login=?";
    private final static String ADD_USER = "INSERT INTO person(`login`, `password`, `name`, `e-mail`, `address`, `phoneNumber`, " +
            "`postal`, `sex`, passportId) VALUES (?,?,?,?,?,?,?,?,?)";
    private final static Logger LOG = LogManager.getLogger(LoginInfoDAO.class);

    public List<LoginInfo> findAll() throws DAOException {
        List<LoginInfo> logins = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_ALL_USERS);
            ResultSet resultSet =
                    st.executeQuery();
            while (resultSet.next()) {
                LoginInfo login = new LoginInfo();
                login.setLogin(resultSet.getString("login"));
                logins.add(login);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return logins;
    }

    public LoginInfo findEntityByName(String login) throws DAOException {
        LoginInfo resLogin = null;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(GET_USER_BY_NAME);
            st.setString(1, login);
            ResultSet resultSet = st.executeQuery();
            if(resultSet.next()) {
                resLogin = new LoginInfo();
                resLogin.setLogin(resultSet.getString("login"));
                resLogin.setPassword(resultSet.getString("password"));
                resLogin.setRole(resultSet.getString("role"));
            }
        }  catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return resLogin;
    }

    public boolean addUser(LoginInfo login) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.prepareStatement(ADD_USER);
            st.setString(1, login.getLogin());
            st.setString(2, login.getPassword());
            st.setString(3, login.getName());
            st.setString(4, login.getEmail());
            st.setString(5, login.getAddress());
            st.setString(6, login.getPhobeNumber());
            st.setInt(7, login.getPostal());
            st.setString(8, login.getSex().toString());
            st.setString(9, login.getPassportId());
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connection.closeStatement(st);
        }
        return result;
    }
}
