package bal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;
import bean.RankingGraphBean;
import bean.RecoveryGraphBean;
import bean.UserBean;

public class RankingGraphBAL {
	final static Logger logger = Logger.getLogger(RankingGraphBAL.class);

	public static RankingGraphBean getRecoverySalesmanGraph(int loanId,
			int salesman_id, int do_id) {

		ResultSet rs = null;

		RankingGraphBean bean = null;

		double cumulativeDue = 0.0;
		double cumulativeDueTotal = 0.0;
		double amountPaid = 0.0;
		double acceptance;
		double active;
		double sales;
		double recovery;
		double ranking;
		String salesman;

		String query = "SELECT CumalativeDue(?) AS cumalativeDue limit 1;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, loanId);

			rs = stmt.executeQuery();
			while (rs.next()) {
				cumulativeDue = rs.getDouble(1);
				cumulativeDueTotal += cumulativeDue;
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		String query1 = "SELECT paidAmountSalesman(?) AS cumalativeDue limit 1;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query1);
			stmt.setInt(1, do_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				amountPaid = rs.getDouble(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		recovery = (amountPaid * 100) / cumulativeDueTotal;

		try (Connection con = Connect.getConnection()) {

			String query2 = "SELECT recoverySalesman(?), salesmanAcceptance(?),salesmanActiveAppliance(?),"
					+ "salesmanSales(?,?),(recoverySalesman(?)*0.25+ salesmanAcceptance(?)*0.25+salesmanActiveAppliance(?)*0.25+salesmanSales(?,?)*0.25) AS SUM,"
					+ "(SELECT salesman_name from salesman where salesman_id = ? ) as Salesman from sold_to s JOIN salesman sl on sl.salesman_id = s.salesman_id; ";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query2);

			stmt.setInt(1, salesman_id);
			stmt.setInt(2, salesman_id);
			stmt.setInt(3, salesman_id);
			stmt.setInt(4, salesman_id);
			stmt.setInt(5, do_id);
			stmt.setInt(6, salesman_id);
			stmt.setInt(7, salesman_id);
			stmt.setInt(8, salesman_id);
			stmt.setInt(9, salesman_id);
			stmt.setInt(10, do_id);
			stmt.setInt(11, salesman_id);

			// stmt.setInt(11, salesman_id);
			// stmt.setInt(12, salesman_id);
			// stmt.setInt(13, salesman_id);
			// stmt.setInt(14, salesman_id);
			// stmt.setInt(15, salesman_id);
			// stmt.setInt(16, do_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				acceptance = rs.getDouble(2);
				active = rs.getDouble(3);
				sales = rs.getDouble(4);
				ranking = rs.getDouble(5);
				salesman = rs.getString(6);
				bean = new RankingGraphBean();
				bean.setRecovery(recovery);
				bean.setAcceptance(acceptance);
				bean.setActive(active);
				bean.setSales(sales);
				bean.setRanking(ranking);
				bean.setSalesmanName(salesman);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static RankingGraphBean getRecoveryDOGraph(int do_id) {

		ResultSet rs = null;
		RankingGraphBean bean = null;

		// try(Connection con=connection.Connect.getConnection()) {
		//
		// String query =
		// "SELECT recoveryDo("+do_id+"), doAcceptance("+do_id+"),doActiveAppliance("+do_id+"),"
		// +
		// "doSales("+do_id+"),(recoveryDo("+do_id+")*0.25+ doAcceptance("+do_id+")*0.25+doActiveAppliance("+do_id+")*0.25+doSales("+do_id+")*0.25) AS SUM,"
		// +
		// "(SELECT user_name FROM user WHERE user_id = "+do_id+") AS doName FROM sold_to "
		// + "GROUP BY CONCAT(YEAR(sold_date), '/', WEEK(sold_date )) ;";
		//
		// PreparedStatement stmt = (PreparedStatement)
		// con.prepareStatement(query);
		// rs = stmt.executeQuery();
		// while (rs.next()) {
		// recovery=rs.getDouble(1);
		// acceptance=rs.getDouble(2);
		// active=rs.getDouble(3);
		// sales=rs.getDouble(4);
		// ranking=rs.getDouble(5);
		// doName=rs.getString(6);
		//
		// bean = new RankingGraphBean();
		//
		// bean.setRecovery(recovery);
		// bean.setAcceptance(acceptance);
		// bean.setActive(active);
		// bean.setSales(sales);
		// bean.setRanking(ranking);
		// bean.setDoName(doName);
		// }
		//
		// } catch (SQLException e) {
		// logger.error("",e);
		// e.printStackTrace();
		// }
		return bean;
	}

	public static ArrayList<UserBean> getUsers() {

		ResultSet rs = null;
		ArrayList<UserBean> list = new ArrayList<>();
		UserBean bean = null;

		try (Connection con = Connect.getConnection()) {
			String query = "SELECT user_id,user_name from user where user_type = 101;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				bean = new UserBean();
				bean.setUserId(id);
				bean.setUserName(name);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static RankingGraphBean get(int loanId, int do_id) {
		RankingGraphBean bean = null;

		ResultSet rs = null;

		double cumulativeDue = 0.0;
		double cumulativeDueTotal = 0.0;
		double amountPaid = 0.0;
		double acceptance;
		double active;
		double sales;
		double recovery;
		double ranking;
		String doName;
		String query = "SELECT CumalativeDue(?) AS cumalativeDue limit 1;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, loanId);

			rs = stmt.executeQuery();
			while (rs.next()) {
				cumulativeDue = rs.getDouble(1);
				cumulativeDueTotal += cumulativeDue;
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		String query1 = "SELECT paidAmountDo(?) AS cumalativeDue limit 1;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query1);
			stmt.setInt(1, do_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				amountPaid = rs.getDouble(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		recovery = (amountPaid * 100) / cumulativeDueTotal;

		try (Connection con = Connect.getConnection()) {

			String query2 = "SELECT doAcceptance("
					+ do_id
					+ "),doActiveAppliance("
					+ do_id
					+ "),"
					+ "doSales("
					+ do_id
					+ "),(recoveryDo("
					+ do_id
					+ ")*0.25+ doAcceptance("
					+ do_id
					+ ")*0.25+doActiveAppliance("
					+ do_id
					+ ")*0.25+doSales("
					+ do_id
					+ ")*0.25) AS SUM,"
					+ "(SELECT user_name FROM user WHERE user_id = "
					+ do_id
					+ ") AS doName FROM sold_to "
					+ "GROUP BY CONCAT(YEAR(sold_date), '/', WEEK(sold_date )) ;";

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query2);
			rs = stmt.executeQuery();
			while (rs.next()) {
				acceptance = rs.getDouble(1);
				active = rs.getDouble(2);
				sales = rs.getDouble(3);
				ranking = rs.getDouble(4);
				doName = rs.getString(5);

				bean = new RankingGraphBean();
				ranking = ranking + recovery * 0.25;
				bean.setRecovery(recovery);
				bean.setAcceptance(acceptance);
				bean.setActive(active);
				bean.setSales(sales);
				bean.setRanking(ranking);
				bean.setDoName(doName);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static ArrayList<RecoveryGraphBean> getLoanIdDo(int userId) {

		ResultSet rs = null;
		RecoveryGraphBean bean = null;
		ArrayList<RecoveryGraphBean> RecoveryGraphBeanList = new ArrayList<>();

		String query = "SELECT p.loan_id FROM sold_to s JOIN payment_loan p ON p.soldto_id = s.sold_to_id "
				+ "JOIN salesman sl ON sl.salesman_id = s.salesman_id JOIN do_salesman d ON d.salesman_id = sl.salesman_id"
				+ " JOIN user u ON u.user_id = d.do_id WHERE u.user_id  = ?;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bean = new RecoveryGraphBean();
				bean.setLoanId(rs.getInt(1));
				RecoveryGraphBeanList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return RecoveryGraphBeanList;
	}

	public static ArrayList<RecoveryGraphBean> getLoanIdSalesman() {

		ResultSet rs = null;

		RecoveryGraphBean bean = null;
		ArrayList<RecoveryGraphBean> RecoveryGraphBeanList = new ArrayList<>();

		String query = "SELECT s.salesman_id," + "pl.loan_id,"
				+ "u.user_id FROM sold_to s"
				+ " JOIN payment_loan pl ON s.sold_to_id = pl.soldto_id"
				+ " JOIN salesman sl ON sl.salesman_id = s.salesman_id"
				+ " JOIN do_salesman ds ON ds.salesman_id = sl.salesman_id"
				+ " JOIN user u ON u.user_id = ds.do_id;";
		try (Connection con = Connect.getConnection()) {
			Statement stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery(query);
			System.out.println(query);
			while (rs.next()) {
				bean = new RecoveryGraphBean();
				bean.setSalesmanId(rs.getInt(1));
				bean.setLoanId(rs.getInt(2));
				bean.setDoId(rs.getInt(3));
				RecoveryGraphBeanList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return RecoveryGraphBeanList;
	}

	public static ArrayList<RankingGraphBean> getRanking() {

		ArrayList<RecoveryGraphBean> loanId = RankingGraphBAL
				.getLoanIdSalesman();
		RankingGraphBean recoveryBean = null;
		ArrayList<RankingGraphBean> topSalesman = new ArrayList<RankingGraphBean>();
		try {
			for (int i = 0; i < loanId.size(); i++) {
				if (loanId.get(i).getLoanId() > 0) {
					recoveryBean = RankingGraphBAL.getRecoverySalesmanGraph(
							loanId.get(i).getLoanId(), loanId.get(i)
									.getSalesmanId(), loanId.get(i).getDoId());
					if (recoveryBean != null) {
						topSalesman.add(recoveryBean);
					}
				}
			}

			for (int i = 1; i < topSalesman.size(); i++) {
				for (int j = 0; j < topSalesman.size() - 1; j++) {
					if (topSalesman.get(j).getRanking() < topSalesman
							.get(j + 1).getRanking()) {
						double temp = topSalesman.get(j).getRanking();
						String salesman = topSalesman.get(j).getSalesmanName();
						topSalesman.get(j).setRanking(
								topSalesman.get(j + 1).getRanking());
						topSalesman.get(j).setSalesmanName(
								topSalesman.get(j + 1).getSalesmanName());
						topSalesman.get(j + 1).setRanking(temp);
						topSalesman.get(j + 1).setSalesmanName(salesman);
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return topSalesman;
	}

	// public static void main(String[] args)
	// {
	// System.out.print(getLoanIdSalesman());
	// // for(int i=1; i<=8; i++) {
	// // System.out.print(getRecoverySalesmanGraph(i, 2, 17));
	// // }
	// }

}
