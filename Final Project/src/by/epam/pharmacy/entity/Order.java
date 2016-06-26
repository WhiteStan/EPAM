package by.epam.pharmacy.entity;


import java.util.Date;

/**
 * Created by Lenovo on 11.06.2016.
 */
public class Order {
    private int totalPrice;
    private String login;
    private Integer id;
    private String status;
    private Date timeOfDelivery;
    private boolean valid;

    public Order() {

    }
    public Order(int totalPrice, String login) {
        this.totalPrice = totalPrice;
        this.login = login;
    }
    public Order(int totalPrice, String login, int id){
        this.totalPrice = totalPrice;
        this.login = login;
        this.id = id;
    }

    public Order(int totalPrice, String login, int id, String status, Date timeOfDelivery, boolean valid) {
        this.totalPrice = totalPrice;
        this.login = login;
        this.id = id;
        this.status = status;
        this.timeOfDelivery = timeOfDelivery;
        this.valid = valid;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimeOfDelivery() {
        return timeOfDelivery;
    }

    public void setTimeOfDelivery(Date timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
