/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import bean.ActiveInActiveGraph;
import connection.Connect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.ocpsoft.pretty.time.PrettyTime;

/**
 * 
 * @author Jetandar-Khatri
 */
public class ActiveInActiveGraphBAL {

	final static Logger logger = Logger.getLogger(ActiveInActiveGraphBAL.class);

	public static HashMap<String, String> getRecoveryRateBeforeDueDate() {
		System.out.println("ActiveInActiveGraphBAL.getRecoveryRateBeforeDueDate()");
		HashMap<String, String> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection.prepareCall("{call get_recovery_rate_before_due_date()}");
				ResultSet rs = prepareCall.executeQuery();
				// -----------------------------------------------------------------------------
				while (rs.next()) {
					map.put("amount", rs.getDouble(1) + "");
					map.put("recoveryRate", rs.getDouble(2) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getRecoveryRateAfterDueDate() {
		System.out.println("ActiveInActiveGraphBAL.getRecoveryRateAfterDueDate()");
		HashMap<String, String> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection.prepareCall("{call get_recovery_rate_after_due_date()}");
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					map.put("defaulters", rs.getInt(1) + "");
					map.put("parTen", rs.getDouble(2) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ActiveInActiveGraph getValueByDistrict(int districId) {
		System.out.println("ActiveInActiveGraphBAL.getValue()");
		ActiveInActiveGraph bean = new ActiveInActiveGraph();
		double value;
		int active;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call get_active_appliances_widget_by_district(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery();
				// End Stored Procedure
				while (rs.next()) {
					active = rs.getInt(1);
					value = rs.getDouble(2);
					bean.setActive(active);
					bean.setValue(value);
					// list.add(bean);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static HashMap<String, String> getTotalOutstandingLoans() {
		System.out.println("ActiveInActiveGraphBAL.get_outstanding_payment()");
		HashMap<String, String> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection.prepareCall("{call get_outstanding_payment()}");
				ResultSet rs = prepareCall.executeQuery();
				// End Stored Procedure
				while (rs.next()) {
					map.put("amount", rs.getInt(1) + "");
					map.put("portfolio", rs.getInt(2) + "");
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getDoOverduePayments(int districId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection.prepareCall("{call get_over_due_customers(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery(); // -----------------------------------------------------------------------------
				while (rs.next()) {
					map = new HashMap<>();
					map.put("total", rs.getInt(1) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getDoDefaulters(int districId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection.prepareCall("{call get_do_wise_defaulters(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery(); // -----------------------------------------------------------------------------
				while (rs.next()) {
					map = new HashMap<>();
					map.put("total", rs.getInt(1) + "");
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getDoSales(int districId) {

		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection.prepareCall("{call get_do_sales(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery(); // -----------------------------------------------------------------------------
				while (rs.next()) {
					map = new HashMap<>();
					map.put("toatlApps", rs.getInt(1) + "");
					map.put("currentMonthApps", rs.getInt(2) + "");
					map.put("installed", rs.getInt(3) + "");
					map.put("currentWeekApps", rs.getInt(4) + "");
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getDoPerformanceDetails(int userId, String to, String from) { //
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = null;
				if (to.isEmpty() && from.isEmpty()) {
					prepareCall = connection.prepareCall("{call get_do_fos_sales(?)}");
					prepareCall.setInt(1, userId);
				} else {
					prepareCall = connection.prepareCall("{call get_do_fos_sales_to_from(?,?,?)}");
					prepareCall.setInt(1, userId);
					prepareCall.setString(2, to);
					prepareCall.setString(3, from);
				}
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					map = new HashMap<>();
					map.put("fos", rs.getInt(1) + "");
					map.put("nds", rs.getInt(2) + "");
					map.put("active_nds", rs.getInt(3) + "");
					map.put("installed", rs.getInt(4) + "");
					map.put("total", rs.getInt(5) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getDoNextFiveDaysDueDateRemaining(int districId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection.prepareCall("{call get_do_next_five_days_due(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					map = new HashMap<>();
					map.put("customer", rs.getString(1) + "");
					map.put("fo", rs.getString(2) + "");
					map.put("remainingDays", rs.getInt(3) + "");
					list.add(map);
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static HashMap<String, String> getLoanStatus(int userId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection.prepareCall("{call count_do_loan_statuses(?)}");
				prepareCall.setInt(1, userId);
				ResultSet rs = prepareCall.executeQuery(); // -----------------------------------------------------------------------------
				if (rs.next()) {
					map = new HashMap<>();
					map.put("maintained", rs.getInt(1) + "");
					map.put("defaulter", rs.getInt(2) + "");
					map.put("owned", rs.getInt(3) + "");
					map.put("late", rs.getInt(4) + "");
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getDoInstallations(int districId) {

		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call count_total_do_installations_of_current_month(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery(); // -----------------------------------------------------------------------------
				while (rs.next()) {
					map = new HashMap<>();
					map.put("total", rs.getInt(1) + "");
					map.put("perc", rs.getDouble(2) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getDoRecovery(int districId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection.prepareCall("{call get_do_recovery_rate(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery(); // -----------------------------------------------------------------------------
				while (rs.next()) {
					map = new HashMap<>();
					map.put("amount", Math.round(rs.getDouble(1)) + "");
					map.put("percentage", Math.round(rs.getDouble(2)) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getDoTopFiveNds(int userId, String to, String from) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = null;
				if (to.isEmpty() && from.isEmpty()) {
					prepareCall = connection.prepareCall("{call get_do_top_five_nds(?)}");
					prepareCall.setInt(1, userId);
				} else {
					prepareCall = connection.prepareCall("{call get_do_top_five_nds_to_from(?,?,?)}");
					prepareCall.setInt(1, userId);
					prepareCall.setString(2, to);
					prepareCall.setString(3, from);
				}
				ResultSet rs = prepareCall.executeQuery(); 
				while (rs.next()) {
					map = new HashMap<>();
					map.put("ndName", rs.getString(1) + "");
					map.put("installed", rs.getInt(2) + "");
					map.put("total", rs.getInt(3) + "");
					list.add(map);
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int insertComments(String text, int userId, int applianceId) {

		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection.prepareCall("{call insert_comment(?,?,?)}");
				prepareCall.setString(1, text);
				prepareCall.setInt(2, userId);
				prepareCall.setInt(3, applianceId);
				ResultSet rs = prepareCall.executeQuery(); // -----------------------------------------------------------------------------
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return 0;
	}

	public static ArrayList<HashMap<String, String>> getAllComments(int applianceId, String from, String to) {
		PrettyTime p = new PrettyTime();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		ResultSet rs = null;
		CallableStatement prepareCall = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				if (to.isEmpty() && from.isEmpty()) {
					prepareCall = connection.prepareCall("{call get_comments_for_loan_book(?)}");
					prepareCall.setInt(1, applianceId);
					rs = prepareCall.executeQuery();
				} else {
					prepareCall = connection.prepareCall("{call get_comments_for_loan_book_from(?,?,?)}");
					prepareCall.setInt(1, applianceId);
					prepareCall.setString(2, from);
					prepareCall.setString(3, to);
					rs = prepareCall.executeQuery();
				}
				while (rs.next()) {
					map = new HashMap<>();
					map.put("text", rs.getString("chat_text"));
					Timestamp dateTime = rs.getTimestamp("txt_date_time");
					Date date = new Date(dateTime.getTime());
					System.out.println(p.format(date));
					map.put("dateTime", p.format(date));
					map.put("userId", rs.getInt("sender") + "");
					map.put("applianceId", rs.getInt("appliance_id") + "");
					map.put("userName", rs.getString("u.user_name"));
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("get_comments_for_loan_book\n", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int countUnseenMessages(int userId) {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection.prepareCall("{CALL count_comments(?)}");
			prepareCall.setInt(1, userId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("count_comments/n", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getUnseenMessagesForLoanBook(int userId) {
		PrettyTime p = new PrettyTime();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection.prepareCall("{CALL get_unseen_messages(?)}");
			prepareCall.setInt(1, userId);
			ResultSet result = prepareCall.executeQuery();
			// End Stored Procedure
			while (result.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("messageFrom", result.getString("user_name"));
				Timestamp dateTime = result.getTimestamp("txt_date_time");
				Date date = new Date(dateTime.getTime());
				map.put("messageDate", p.format(date));
				map.put("applianceId", result.getString("appliance_id"));
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("get_unseen_messages/n", e);
			e.printStackTrace();
		}
		return list;

	}// end of getting all message number from appliance_message

	public static void updateMessageStatus(int applianceId, int userId) {
		Statement s = null;
		String query = "UPDATE sa_cc_comments_chat SET seen = 1 WHERE `appliance_id` = " + applianceId
				+ " and sender !=" + userId;
		System.out.println(query);
		int row = 0;
		try (Connection con = Connect.getConnection();) {
			s = con.createStatement();
			row = s.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
			con.close();
		} catch (Exception e) {
			logger.error("updateMessageStatus/n", e);
			e.printStackTrace();
		}

	}

	public static void main(String[] arg) {
		countUnseenMessages(107);
	}

}
