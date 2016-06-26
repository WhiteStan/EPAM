package by.bsu.example.entity;

/**
 * Created by Lenovo on 10.04.2016.
 */
public class LoginInfo {
    private String login;
    private String password;
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean checkLogin(String login, String password){
        return this.login.equals(login) && this.password.equals(password) ? true : false;
    }
}
