package by.bsu.example.logic;

import by.bsu.example.entity.LoginInfo;
import by.bsu.example.servlet.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class LoginLogic {
    private static ArrayList<LoginInfo> lst = new ArrayList<>();
    private final  static String GET_LOGIN = "SELECT login, password FROM login WHERE login=?";
    private final static Logger LOG = LogManager.getLogger(Controller.class);
    public static boolean checkLogin(String enterLogin, String enterPass) {
        String url = "jdbc:mysql://localhost:3306/epam_project";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "Цупфф3фа");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(url, prop);
            PreparedStatement st = null;
            try {
                st = cn.prepareStatement(GET_LOGIN);
                ResultSet rs = null;
                try {
                    st.setString(1, enterLogin);
                    rs = st.executeQuery();
                    while (rs.next()) {
                        LoginInfo loginInfo = new LoginInfo();
                        loginInfo.setLogin(rs.getString(1));
                        loginInfo.setPassword(rs.getString(2));
                        lst.add(loginInfo);
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    } else {
                        LOG.info(
                                "BD read error");
                    }
                }
            } finally {
                if (st != null) {
                    st.close();
                } else {
                    LOG.info("Statement creation failed");
                }
            }
        } catch (SQLException e) {
            LOG.info("DB connection error: " + e);
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    LOG.info("Сonnection close error: " + e);
                }
            }
        }
        boolean result = false;
        for(LoginInfo login : lst){
            if(login.checkLogin(enterLogin, Encryptor.getHash(enterPass))){
                result = true;
            }
        }
        return result;
    }
}
