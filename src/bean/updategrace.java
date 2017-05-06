/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author waseem
 */
public class updategrace {
    
    private int loan_id;
    private Date due_date;
    private int grace;

    public updategrace() {
    }

    public updategrace(int loan_id, Date due_date, int grace) {
        this.loan_id = loan_id;
        this.due_date = due_date;
        this.grace = grace;
    }

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public int getGrace() {
        return grace;
    }

    public void setGrace(int grace) {
        this.grace = grace;
    }

    @Override
    public String toString() {
        return "updategrace{" + "loan_id=" + loan_id + ", due_date=" + due_date + ", grace=" + grace + '}';
    }

   
}
