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
public class ActiveInActiveGraph {

	private double value;
	private int active;

	public ActiveInActiveGraph() {

	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "ActiveInActiveGraph [value=" + value + ", active=" + active
				+ "]";
	}
	
	

}
