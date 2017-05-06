package bean;

public class MobileMoneyGraphDoBean {
	
	 
	    int money;
	    double mobileMoneyPercentage;
		public int getMoney() {
			return money;
		}
		public void setMoney(int money) {
			this.money = money;
		}
		public double getMobileMoneyPercentage() {
			return mobileMoneyPercentage;
		}
		public void setMobileMoneyPercentage(double mobileMoneyPercentage) {
			this.mobileMoneyPercentage = mobileMoneyPercentage;
		}
		@Override
		public String toString() {
			return "MobileMoneyGraphDoBean [money=" + money
					+ ", mobileMoneyPercentage=" + mobileMoneyPercentage + "]";
		}
	    
	    

}
