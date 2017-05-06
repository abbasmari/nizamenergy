/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Jeatnder Khatri
 */
public class Suggestion {

    private int id;
    private int customerId;
    private String appName;
    private int quantity;
    private int scheme;

    public Suggestion() {
    }

    public Suggestion(int id, int customerId, String appName, int quantity, int scheme) {
        this.id = id;
        this.customerId = customerId;
        this.appName = appName;
        this.quantity = quantity;
        this.scheme = scheme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getScheme() {
        return scheme;
    }

    public void setScheme(int scheme) {
        this.scheme = scheme;
    }

    @Override
    public String toString() {
        return "Suggestion{" + "id=" + id + ", customerId=" + customerId + ", appName=" + appName + ", quantity=" + quantity + ", scheme=" + scheme + '}';
    }

}
