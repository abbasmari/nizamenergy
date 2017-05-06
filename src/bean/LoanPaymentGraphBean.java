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
public class LoanPaymentGraphBean {
    
    private int amount;
    
    private double payment;

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "LoanPaymentGraphBean{" + "amount=" + amount + ", payment=" + payment + '}';
    }


    
    
    
}
