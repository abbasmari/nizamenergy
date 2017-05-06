package bal;

import bean.LoanAndCustomerIdBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

/**
 * 
 * @author Jhaman-Khatri
 */
public class LoanAndCustomerIdBAL {
	final static Logger logger = Logger.getLogger(LoanAndCustomerIdBAL.class);

	public static ArrayList<LoanAndCustomerIdBean> getLoanAndCustomerId() {

		ResultSet rs = null;

		LoanAndCustomerIdBean bean = null;
		ArrayList<LoanAndCustomerIdBean> RecoveryGraphBeanList = new ArrayList<>();
		int loanId;

		String query = "select distinct(l.loan_id) from payments_details l join customer c on c.customer_id=l.customer_id;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				loanId = rs.getInt(1);
				bean = new LoanAndCustomerIdBean();
				bean.setLoanId(loanId);
				RecoveryGraphBeanList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return RecoveryGraphBeanList;

	}

	public static int getLoanid(int app_id) {

		ResultSet rs = null;
		int loan_id = 0;
		String query = "SELECT  lp.loan_payment_id FROM loan_payments lp "
				+ " INNER JOIN payment_loan pl ON lp.loan_id =pl.loan_id"
				+ " INNER JOIN sold_to st ON pl.soldto_id=st.sold_to_id"
				+ " WHERE st.appliance_id=" + app_id
				+ " AND lp.Amount_Paid IS NULL ";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				loan_id = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);			
			e.printStackTrace();
		}
		return loan_id;

	}

	public static int updateDate(int loanid) {

		int row = 0;
		String query = "UPDATE loan_payments SET due_date=ADDDATE(CURRENT_DATE(), INTERVAL 1 MONTH) WHERE Loan_payment_id="
				+ loanid;
		try (Connection con = Connect.getConnection()) {

			PreparedStatement ps = con.prepareStatement(query);
			row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("data updated");
			} else {
				System.out.println("data not updated");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;

	}

}
