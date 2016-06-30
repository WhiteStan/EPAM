package by.epam.pharmacy.entity;

public class
LoginInfo {
    private String login;
    private String password;
    private String role;
    private String name;
    private String email;
    private String address;
    private String phobeNumber;
    private Integer postal;
    private Sex sex;
    private String passportId;

    public LoginInfo() {
    }

    public LoginInfo(String login, String password, String email, String name, String address,
                     String phobeNumber, Integer postal, String sex, String passportId) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phobeNumber = phobeNumber;
        this.postal = postal;
        this.sex = Sex.valueOf(sex.toUpperCase());
        this.passportId = passportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhobeNumber() {
        return phobeNumber;
    }

    public void setPhobeNumber(String phobeNumber) {
        this.phobeNumber = phobeNumber;
    }

    public Integer getPostal() {
        return postal;
    }

    public void setPostal(Integer postal) {
        this.postal = postal;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

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

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public boolean checkLogin(String login, String password) {
        return this.login.equalsIgnoreCase(login) && this.password.equalsIgnoreCase(password);
    }


}
