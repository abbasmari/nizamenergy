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
public class TechnicanDetailsBean {
    
    String technican_name;
    String technican_ticketNo;
    String elapse_time;

    public TechnicanDetailsBean() {
    }

    public TechnicanDetailsBean(String technican_name, String technican_ticketNo, String elapse_time) {
        this.technican_name = technican_name;
        this.technican_ticketNo = technican_ticketNo;
        this.elapse_time = elapse_time;
    }

    public String getTechnican_name() {
        return technican_name;
    }

    public void setTechnican_name(String technican_name) {
        this.technican_name = technican_name;
    }

    public String getTechnican_ticketNo() {
        return technican_ticketNo;
    }

    public void setTechnican_ticketNo(String technican_ticketNo) {
        this.technican_ticketNo = technican_ticketNo;
    }

    

    public String getElapse_time() {
        return elapse_time;
    }

    public void setElapse_time(String elapse_time) {
        this.elapse_time = elapse_time;
    }

    @Override
    public String toString() {
        return "TechnicanDetailsBean{" + "technican_name=" + technican_name + ", technican_ticketNo=" + technican_ticketNo + ", elapse_time=" + elapse_time + '}';
    }

   
    
}
