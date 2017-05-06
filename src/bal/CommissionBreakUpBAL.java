package bal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import connection.Connect;
import bean.CommissionBreakUpBean;

public class CommissionBreakUpBAL {
	final static Logger logger = Logger.getLogger(CommissionBreakUpBAL.class);
	public static List<CommissionBreakUpBean> getCustomerList(int salesmanId, String applianceName, String date,String timeLimit){
		
		List<CommissionBreakUpBean> commissionBreakUpBeansList = new ArrayList<>();
		
		try(Connection con = Connect.getConnection()) {
			String dateDif = "";
			if(timeLimit.equalsIgnoreCase("BT")){
				dateDif = " AND DATEDIFF(lp.due_date,lp.Paid_Date) >= 3 ";
			}else if(timeLimit.equalsIgnoreCase("OT")){
				dateDif = " AND (DATEDIFF(lp.due_date,lp.Paid_Date) < 3 AND DATEDIFF(lp.due_date,lp.Paid_Date) >= 0) ";
			}else if(timeLimit.equalsIgnoreCase("AT")){
				dateDif = " AND (DATEDIFF(lp.due_date,lp.Paid_Date) < 0 AND DATEDIFF(lp.due_date,lp.Paid_Date) > -7 )";
			}
			
			String d[] = date.split(" to ");
			System.out.println(d[0]);
			System.out.println(d[1]);
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT c.customer_id,"
					+ "c.customer_name, "
					+ "a.appliance_GSMno, "
					+ "lp.Amount_Paid , "
					+ "pl.remaining_balance, "
					+ "lp.Paid_Date , "
					+ "lp.due_date "
					+ "FROM loan_payments lp "
					+ "INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
					+ "INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id "
					+ "INNER JOIN appliance a ON st.appliance_id = a.appliance_id "
					+ "INNER JOIN targets t ON t.salesman_id = st.salesman_id "
					+ "INNER JOIN customer c ON c.customer_id = lp.Customer_id "
					+ "WHERE st.salesman_id =  ? "
					+ "AND lp.Paid_Date >= ? "
					+ "AND lp.Paid_Date <= ? "
					+ " "+dateDif
					+ " AND a.appliance_name = ? "
					+ " AND lp.is_first_installment=0 ");
			ps.setInt(1, salesmanId);
			ps.setString(2, d[0]);
			ps.setString(3, d[1]);
			ps.setString(4, applianceName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				CommissionBreakUpBean bean = new CommissionBreakUpBean();
				rs.getInt("c.customer_id");
				bean.setCustomerName(rs.getString("c.customer_name"));
				bean.setApplianceGsmNumber(rs.getString("a.appliance_GSMno"));
				bean.setAmountPaid(rs.getInt("lp.Amount_Paid"));
				bean.setRemainingAmount(rs.getInt("pl.remaining_balance"));
				bean.setPaidDate(rs.getDate("lp.Paid_Date"));
				bean.setDueDate(rs.getDate("lp.due_date"));
				commissionBreakUpBeansList.add(bean);
			}			
			
		} catch (Exception e) {
			logger.error("",e);
			e.printStackTrace();
		}
		System.out.println(commissionBreakUpBeansList);
		return commissionBreakUpBeansList;
	}
	
	
}
