/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Jhaman-Khatri
 */
public class MobileMoneyGraphBean {
    
    double mobileMoney;
    int money;

    public double getMobileMoney() {
        return mobileMoney;
    }

    public void setMobileMoney(double mobileMoney) {
        this.mobileMoney = mobileMoney;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "MobileMoneyGraphBean{" + "mobileMoney=" + mobileMoney + ", money=" + money + '}';
    }

 
    
    
    
    
    
}
