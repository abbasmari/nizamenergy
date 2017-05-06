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
public class soldApplianceGraphBean {
    
    
	private double value;
	private int handover;
    

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getHandover() {
        return handover;
    }

    public void setHandover(int handover) {
        this.handover = handover;
    }

    @Override
    public String toString() {
        return "soldApplianceGraphBean{" + "value=" + value + ", handover=" + handover + '}';
    }

 
    
}
