package test.by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.LoginInfo;
import by.epam.pharmacy.logic.LoginLogic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lenovo on 19.06.2016.
 */
public class LoginInfoDaoTest {
    @Test
    public void findEntityByNameTest(){
        LoginLogic loginLogic = new LoginLogic();
        LoginInfo loginInfo = loginLogic.checkLogin("test");
        assertEquals("GUEST", loginInfo.getRole());
    }
}
