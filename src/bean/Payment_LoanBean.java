/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;


public class Payment_LoanBean {
    
	 private String appliances_Number;
	    private int status;
	    private String customer_name;
	    private String customer_phone;
	    private String district;
	    private Double loan_amount;
	    private Double remaining_balance;
	    private int grace_period;
	    private Double paid_amount;
	    private int appliance_key;
	    private int loan_key;
	    private int customer_id;
	    private String salesman;
	    private Date terminate;
	    private int scheme;
	    private double dueAmount;
	    private double amountPaid;
	    private double cumalativeDueAmount;
	    private double cumalativePaidAmount;
	    private double downPayment;
	    private int maintained;
	    private int defaulted;
	    private int owned;

	    public Payment_LoanBean(){}
	    
	    public int getCustomer_id() {
	        return customer_id;
	    }

	    public Date getTerminate() {
	        return terminate;
	    }

	    public void setTerminate(Date terminate) {
	        this.terminate = terminate;
	    }

	    public String getSalesman() {
	        return salesman;
	    }

	    public void setSalesman(String salesman) {
	        this.salesman = salesman;
	    }

	    public void setCustomer_id(int customer_id) {
	        this.customer_id = customer_id;
	    }
	   
	    public int getAppliance_key() {
	        return appliance_key;
	    }

	    public void setAppliance_key(int appliance_key) {
	        this.appliance_key = appliance_key;
	    }

	    public int getLoan_key() {
	        return loan_key;
	    }

	    public void setLoan_key(int loan_key) {
	        this.loan_key = loan_key;
	    }

	    public String getAppliances_Number() {
	        return appliances_Number;
	    }

	    public void setAppliances_Number(String appliances_Number) {
	        this.appliances_Number = appliances_Number;
	    }

	    public int getStatus() {
	        return status;
	    }

	    public void setStatus(int status) {
	        this.status = status;
	    }

	    public String getCustomer_name() {
	        return customer_name;
	    }

	    public void setCustomer_name(String customer_name) {
	        this.customer_name = customer_name;
	    }

	    public String getCustomer_phone() {
	        return customer_phone;
	    }

	    public void setCustomer_phone(String customer_phone) {
	        this.customer_phone = customer_phone;
	    }

	    public String getDistrict() {
	        return district;
	    }

	    public void setDistrict(String district) {
	        this.district = district;
	    }

	    public Double getLoan_amount() {
	        return loan_amount;
	    }

	    public void setLoan_amount(Double loan_amount) {
	        this.loan_amount = loan_amount;
	    }

	    public Double getRemaining_balance() {
	        return remaining_balance;
	    }

	    public void setRemaining_balance(Double remaining_balance) {
	        this.remaining_balance = remaining_balance;
	    }

	    public int getGrace_period() {
	        return grace_period;
	    }

	    public void setGrace_period(int grace_period) {
	        this.grace_period = grace_period;
	    }

	    public Double getPaid_amount() {
	        return paid_amount;
	    }

	    public void setPaid_amount(Double paid_amount) {
	        this.paid_amount = paid_amount;
	    }

	    public int getScheme() {
	        return scheme;
	    }

	    public void setScheme(int scheme) {
	        this.scheme = scheme;
	    }
	    
	    public double getDueAmount() {
			return dueAmount;
		}

		public void setDueAmount(double dueAmount) {
			this.dueAmount = dueAmount;
		}

		public double getAmountPaid() {
			return amountPaid;
		}

		public void setAmountPaid(double amountPaid) {
			this.amountPaid = amountPaid;
		}

		public double getCumalativeDueAmount() {
			return cumalativeDueAmount;
		}

		public void setCumalativeDueAmount(double cumalativeDueAmount) {
			this.cumalativeDueAmount = cumalativeDueAmount;
		}

		public double getCumalativePaidAmount() {
			return cumalativePaidAmount;
		}

		public void setCumalativePaidAmount(double cumalativePaidAmount) {
			this.cumalativePaidAmount = cumalativePaidAmount;
		}
		
		public double getDownPayment() {
			return downPayment;
		}

		public void setDownPayment(double downPayment) {
			this.downPayment = downPayment;
		}
		
		public int getMaintained() {
			return maintained;
		}

		public void setMaintained(int maintained) {
			this.maintained = maintained;
		}

		public int getDefaulted() {
			return defaulted;
		}

		public void setDefaulted(int defaulted) {
			this.defaulted = defaulted;
		}
		
		

		public int getOwned() {
			return owned;
		}

		public void setOwned(int owned) {
			this.owned = owned;
		}

		@Override
		public String toString() {
			return "Payment_LoanBean [appliances_Number=" + appliances_Number
					+ ", status=" + status + ", customer_name=" + customer_name
					+ ", customer_phone=" + customer_phone + ", district="
					+ district + ", loan_amount=" + loan_amount
					+ ", remaining_balance=" + remaining_balance
					+ ", grace_period=" + grace_period + ", paid_amount="
					+ paid_amount + ", appliance_key=" + appliance_key
					+ ", loan_key=" + loan_key + ", customer_id=" + customer_id
					+ ", salesman=" + salesman + ", terminate=" + terminate
					+ ", scheme=" + scheme + ", dueAmount=" + dueAmount
					+ ", amountPaid=" + amountPaid + ", cumalativeDueAmount="
					+ cumalativeDueAmount + ", cumalativePaidAmount="
					+ cumalativePaidAmount + "]";
		}
	     
	    
}
