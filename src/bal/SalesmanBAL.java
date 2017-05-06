/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import static bal.UserBAL.st;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.ocpsoft.pretty.time.PrettyTime;

import bean.AllSalesmansCommission;
import bean.SalesManBean;
import bean.Salesman;
import bean.SalesmanIdBean;
import bean.SuperAdminSalesmanRecoveriesBean;
import bean.TargetBean;
import bean.TechnicianBean;
import bean.UpComingRevocoveries;
import connection.Connect;

/**
 * 
 * @author Jetander Kumar
 */
public class SalesmanBAL {

	final static Logger logger = Logger.getLogger(SalesmanBAL.class);

	
	

	
	public static double getETA(int technicianId) {

		ResultSet rs = null;

		double couunt = 0;

		try (Connection con = Connect.getConnection()) {

			String query = "select sum(elapse_time) as sum from alerts where technician_id=? and elapse_time is not null;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, technicianId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				couunt = rs.getDouble(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return couunt;
	} // end of getting all customers form Db`

	public static int getETACount(int technicianId) {

		ResultSet rs = null;

		int couunt = 0;

		try (Connection con = Connect.getConnection()) {

			String query = "select count(elapse_time) as counter from alerts where technician_id=? and elapse_time is not null;;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, technicianId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				couunt = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return couunt;
	} // end of getting all customers form D

	public static int getTotalTicketsResolved(int technicianId) {

		ResultSet rs = null;

		int couunt = 0;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT Count(technician_id) FROM alerts where technician_id= ? and status= 3;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, technicianId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				couunt = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return couunt;
	} // end of getting all customers form Db`

	public static ArrayList<TechnicianBean> getTechnicianList(int do_id) {

		ResultSet rs = null;

		TechnicianBean bean = null;
		ArrayList<TechnicianBean> list = new ArrayList<>();
		int technId, doId;
		double salary;
		String firstName, address, phoneNo, cnic;
		Date joiningDate;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT technician_id, do_id, technican_name, technican_phone, technican_salary, technican_address, technican_cnic,created FROM technician where do_id=? order by tech_status;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, do_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				technId = rs.getInt(1);
				doId = rs.getInt(2);
				firstName = rs.getString(3);
				phoneNo = rs.getString(4);
				salary = rs.getDouble(5);
				address = rs.getString(6);
				cnic = rs.getString(7);
				joiningDate = rs.getDate(8);
				bean = new TechnicianBean(technId, doId, firstName, phoneNo,
						address, salary, cnic, joiningDate);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db`

	public static int updateTicket(int id, String ticket, String technician,
			int status, int technican_id) {

		Date date1 = new Date();
		Calendar calendar = Calendar.getInstance(TimeZone
				.getTimeZone("Asia/Karachi"));
		Date currentDate = calendar.getTime();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

		// Use Madrid's time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone("Asia/Karachi"));
		df2.setTimeZone(TimeZone.getTimeZone("Asia/Karachi"));

		System.out.println("Date and time in Madrid: " + df.format(date1));
		System.out.println("Date and time in Madrid: "
				+ df2.format(currentDate));

		String query = "UPDATE alerts SET ticket_no='" + ticket + "', status="
				+ status + ", technician = '" + technician
				+ "' , alert_time = '" + df.format(date1)
				+ "' ,technician_id = " + technican_id + " WHERE alerts_id = "
				+ id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static int updateTechnician(int tech_id, int status) {

		String query = "UPDATE technician SET tech_status=" + status
				+ " WHERE technician_id = " + tech_id + ";";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static String getTecnicanNameFromPhoneNo(String gsm, int do_id) {

		ResultSet rs = null;

		String query = "SELECT technican_name FROM technician WHERE technican_phone=? and do_id=?";

		String name = "";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, gsm);
			stmt.setInt(2, do_id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}

			// System.out.println(id);
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return name;
	}

	public static int getTecnicanIDFromPhoneNo(String gsm) {

		ResultSet rs = null;

		String query = "SELECT technician_id FROM technician WHERE technican_phone=?";
		int id = 0;

		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setString(1, gsm);

			rs = stmt.executeQuery();
			if (rs.next()) {

				id = rs.getInt(1);
			}

			// System.out.println(id);
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return id;
	}

	public static boolean getAvailable(int technicianId) {

		ResultSet rs = null;

		boolean found = false;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT technician_id FROM technician WHERE technician_id = ? AND tech_status!=1;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, technicianId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				found = true;
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return found;
	} // end of getting all customers form Db`

	public static int getTotalTickets(int technicianId) {

		ResultSet rs = null;

		int couunt = 0;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT Count(technician_id) FROM alerts where technician_id=?;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, technicianId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				couunt = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return couunt;
	} // end of getting all customers form Db`

	public static int getMaxID() {

		ResultSet rs = null;

		String query = "SELECT MAX(salesman_id) FROM salesman";
		int id = 0;
		try (Connection con = Connect.getConnection()) {

			st = con.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				id = rs.getInt(1);
			}

			System.out.println(id);
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return id;
	}

	
	
	public static void discountCustomer(int customerId, int ApplianceId, int discount, String discount_message) {
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			
			
			System.err.println(customerId+" // "+ApplianceId+" // "+discount+" // "+discount_message);
			String query = "UPDATE `loan_payments` lp " 
					+ " JOIN `payment_loan` pl ON pl.`loan_id` = lp.`loan_id` "
					+ " JOIN `sold_to` st ON st.`sold_to_id` = pl.`soldto_id`  "
					+ " JOIN `eligibility` e ON e.`eligibility_id` = st.`appliance_id` "
					+ " SET lp.`discount_amount` = '"+discount+"', lp.`discount_message` = '"+discount_message+"' "
					+ " WHERE lp.`Customer_id` = '"+customerId+"' AND st.`appliance_id` = '"+ApplianceId+"' "
					+ " AND lp.`Paid_Date` IS NULL AND lp.`Amount_Paid` IS NULL ";
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted");
			} else {
				System.out.println("Data is not inserted");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void addOwnScheme(int customerId, int applianceId,
			String customer_appliance_name, int customer_appliance_scheme, int customer_advance_payment, 
			int customer_monthly_payment, int customer_total_payment) {
		
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			
			String query = "INSERT INTO `custom_payment_loan` (`payment_loan_id`, `appliance_name`, `assets_quantity`, `total_amount`, " 
					+ " `remaining_amount`, `total_installements`, `remaining_installments`, `monthly_installment_amount`, down_payment, `created_on`) "
					+ " VALUES(( " 
					+ " SELECT pl.`loan_id` FROM `payment_loan` pl " 
					+ " JOIN `sold_to` st ON st.`sold_to_id` = pl.`soldto_id` "  
					+ " JOIN `eligibility` e ON e.`eligibility_id` = st.`appliance_id` "
					+ " WHERE st.`customer_id` = '"+customerId+"' AND st.`appliance_id` = '"+applianceId+"' ), "
					+ "'"+customer_appliance_name+"', 1, '"+customer_total_payment+"', '"+customer_total_payment+"', "
					+ "'"+customer_appliance_scheme+"', '"+customer_appliance_scheme+"', '"+customer_monthly_payment+"', '"+customer_advance_payment+"', NOW())";
			System.err.println("/////////////"+query);
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted");
			} else {
				System.out.println("Data is not inserted");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void customerFine(int customerId, int ApplianceId, int fine, String fine_message) {
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			
			
			System.err.println(customerId+" // "+ApplianceId+" // "+fine+" // "+fine_message);
			String query = "UPDATE `loan_payments` lp " 
					+ " JOIN `payment_loan` pl ON pl.`loan_id` = lp.`loan_id` "
					+ " JOIN `sold_to` st ON st.`sold_to_id` = pl.`soldto_id`  "
					+ " JOIN `eligibility` e ON e.`eligibility_id` = st.`appliance_id` "
					+ " SET lp.`fine_amount` = '"+fine+"', lp.`fine_message` = '"+fine_message+"' "
					+ " WHERE lp.`Customer_id` = '"+customerId+"' AND st.`appliance_id` = '"+ApplianceId+"' "
					+ "AND lp.`Paid_Date` IS NULL AND lp.`Amount_Paid` IS NULL ";
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted");
			} else {
				System.out.println("Data is not inserted");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addLateCustomersFine(int late_panality, int late_days) {
		int row = 0;
		System.err.println("///////////////////////////// addLateCustomersFine");
		try (Connection con = Connect.getConnection()) {
			String query = "INSERT INTO customer_late_fine(late_days, fine_amount, created_date) VALUES("+late_days+", "+late_panality+", CURDATE())";
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted");
			} else {
				System.out.println("Data is not inserted");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void discountForCustomers(int discount_amount, String discount_purpose) {
		int row = 0;
		System.err.println("///////////// discountForCustomers");
		try (Connection con = Connect.getConnection()) {
			String query = "INSERT INTO customer_discount_scheme(discount_amount, discount_detail, created_date) VALUES("+discount_amount+", "+discount_purpose+", NOW())";
			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted");
			} else {
				System.out.println("Data is not inserted");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static int insertTechnican(int do_id, String Fullname, String Phone,
			double basicSalery, String cnic, String address) {

		String query = "INSERT INTO technician (do_id, technican_name,technican_phone,technican_salary,technican_cnic,technican_address, created,tech_status) VALUES("
				+ do_id
				+ ",'"
				+ Fullname
				+ "','"
				+ Phone
				+ "',"
				+ basicSalery
				+ ",'" + cnic + "','" + address + "', CURDATE()," + 0 + ");";
		System.out.println("Query : " + query);
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			st.executeUpdate(query);
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return 0;
	} // end of addUser

	public static ArrayList<Salesman> getSalesMan() {
		System.out.println("SalesmanBAL.get_salesman_for_superadmin()");
		Salesman bean = null;
		ArrayList<Salesman> list = new ArrayList<>();
		int salesmanId, lateCustomer, totalSales, userId;
		double salary;
		String firstName, district, districtName, address, phoneNo, cnic, userName;
		Date joiningDate;
		try (Connection connection = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = connection
					.prepareCall("{call get_salesman_for_superadmin()}");
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				salesmanId = rs.getInt(1);
				firstName = rs.getString(2);
				cnic = rs.getString(3);
				district = rs.getString(4);
				address = rs.getString(5);
				phoneNo = rs.getString(6);
				salary = rs.getDouble(7);
				joiningDate = rs.getDate(8);
				lateCustomer = rs.getInt(9);
				totalSales = rs.getInt(10);
				userId = rs.getInt(11);
				userName = rs.getString(12);
				districtName = rs.getString(13);
				bean = new Salesman();
				bean.setSalesmanId(salesmanId);
				bean.setName(firstName);
				bean.setCnicNo(cnic);
				bean.setDistrict(district);
				bean.setAddress(address);
				bean.setBasicSalary(salary);
				bean.setJoiningDate(joiningDate);
				bean.setPhoneNo(phoneNo);
				bean.setLateCustomer(lateCustomer);
				bean.setTotalSales(totalSales);
				bean.setUserId(userId);
				bean.setUserName(userName);
				bean.setDistrictName(districtName);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all Salesman form Db`

	public static int addUser_Admin(String Fullname, String cnic, int location,
			String city, String area, String Phone) {

		String query = "insert into  salesman(salesman_name,salesman_cnic,salesman_district,salesman_address,salesman_phone_no,salesman_basic_sallery,salesman_date_join)"
				+ "Values('"
				+ Fullname
				+ "','"
				+ cnic
				+ "','"
				+ location
				+ "','"
				+ city
				+ "','"
				+ area
				+ "','"
				+ Phone
				+ "','CURDATE()');";
		System.out.println("Query : " + query);

		int row = 0;
		try (Connection con = Connect.getConnection()) {

			st = con.createStatement();
			row = st.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is inserted");
			} else {
				System.out.println("Data is not inserted");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	} // end of addUser

	public static ArrayList<SalesManBean> getDistrictSalesman(int doId) {
		System.out.println("CustomerBAL.get_do_salesman(?)");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.format(new Date());
		SalesManBean bean = null;
		ArrayList<SalesManBean> SallesmanList = new ArrayList<SalesManBean>();
		ArrayList<SalesManBean> list1 = new ArrayList<SalesManBean>();
		int salesman_id, latecustomer, totalSales;
		double monthlyIncome;
		String sallesman_name, cnicNo, address, district, phone;
		Date datejoin;

		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				// Begin Stored Procedure Calling -- Jetander
				CallableStatement prepareCall = con
						.prepareCall("{call get_do_salesman(?)}");
				prepareCall.setInt(1, doId);
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					sallesman_name = rs.getString(1);
					cnicNo = rs.getString(2);
					address = rs.getString(3);
					district = rs.getString(4);
					address = rs.getString(5);
					monthlyIncome = rs.getDouble(6);
					datejoin = rs.getDate(7);
					System.err.println("Date of Joining " + datejoin);
					phone = rs.getString(8);
					salesman_id = rs.getInt(9);
					latecustomer = rs.getInt(10);
					totalSales = rs.getInt(11);
					// end procedure call.
					bean = new SalesManBean();
					bean.setName(sallesman_name);
					bean.setCnic(cnicNo);
					bean.setAddress(address);
					bean.setDistrict(district);
					bean.setDatejoin(datejoin);
					bean.setSallery(monthlyIncome);
					bean.setPhone_number(phone);
					bean.setSalesmanId(salesman_id);
					bean.setLateCustomer(latecustomer);
					bean.setTotalSales(totalSales);
					SallesmanList.add(bean);

				}

				for (SalesManBean salesManBean : SallesmanList) {

					String dateOFJoining = salesManBean.getDatejoin()
							.toString();
					String currentDate = dateFormat.format(new Date())
							.toString();

					// HH converts hour in 24 hours format (0-23), day
					// calculation
					SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");

					Date d1 = null;
					Date d2 = null;

					long diffDays = 0;

					try {
						d1 = format.parse(dateOFJoining);
						d2 = format.parse(currentDate);

						// in milliseconds
						long diff = d2.getTime() - d1.getTime();
						diffDays = diff / (24 * 60 * 60 * 1000);
						System.out.println(diffDays + " days ");

					} catch (Exception e) {
						e.printStackTrace();
					}

					int per = 0;
					int sales = TargetsBAL
							.getSalesOfCurrentMonthBySalesmanId(salesManBean
									.getSalesmanId());
					// int salesManBean.getDatejoin() -
					if (diffDays <= 31 && sales <= 50) {
						list1.add(salesManBean);
					} else {

						TargetBean targetBean = TargetsBAL
								.getTargetsBySalesmanId(salesManBean
										.getSalesmanId());

						String[][] customers = CustomerBAL
								.getCustomerBySalesmanId(salesManBean
										.getSalesmanId());
						System.err.println("Length===" + customers.length);

						try {
							int achive = TargetsBAL.getRecovries(
									salesManBean.getSalesmanId(),
									targetBean.getTarget_start_on(),
									targetBean.getTarget_end_on());
							System.out.println("Achive::::::: " + achive);
							per = (100 / customers.length) * achive;

							if (per >= 98) {
								// list1.add(salesManBean);
								System.err.println("persentage=========== "
										+ per);
								// filter2 = new SalesManBean();

								if (sales != 0) {
									if (sales <= 50) {
										list1.add(salesManBean);
									} else {
										// list1.add(salesManBean);
									}
								} else {
								}

							} else {
							}
							System.out.print(per + " persentage\n");
						} catch (Exception e) {
							e.getMessage();
						}
					}
				}

			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list1;
	} // end of getting all Salesman form particular District

	public static ArrayList<SalesManBean> getSallesman(int doId) {
		ResultSet rs = null;
		System.out.println("CustomerBAL.get_do_salesman(?)");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.format(new Date());
		SalesManBean bean = null;
		ArrayList<SalesManBean> SallesmanList = new ArrayList<SalesManBean>();
		int salesman_id, latecustomer, totalSales;
		double monthlyIncome;
		String sallesman_name, cnicNo, address, district, phone;
		Date datejoin;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				// Begin Stored Procedure Calling -- Jetander
				CallableStatement prepareCall = con
						.prepareCall("{call get_do_salesman(?)}");
				prepareCall.setInt(1, doId);
				rs = prepareCall.executeQuery();
				while (rs.next()) {
					sallesman_name = rs.getString(1);
					cnicNo = rs.getString(2);
					address = rs.getString(3);
					district = rs.getString(4);
					address = rs.getString(5);
					monthlyIncome = rs.getDouble(6);
					datejoin = rs.getDate(7);
					// System.err.println("Date of Joining " + datejoin);
					phone = rs.getString(8);
					salesman_id = rs.getInt(9);
					latecustomer = rs.getInt(10);
					totalSales = rs.getInt(11);
					// end procedure call.
					bean = new SalesManBean();
					bean.setName(sallesman_name);
					bean.setCnic(cnicNo);
					bean.setAddress(address);
					bean.setDistrict(district);
					bean.setDatejoin(datejoin);
					bean.setSallery(monthlyIncome);
					bean.setPhone_number(phone);
					bean.setSalesmanId(salesman_id);
					bean.setLateCustomer(latecustomer);
					bean.setTotalSales(totalSales);
					bean.setFoname(rs.getString("fo_name"));
					SallesmanList.add(bean);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return SallesmanList;
	} // end of getting all Salesman form particular District

	public static SalesManBean getFirstFilterSallesman(int salesmanId) {

		ResultSet rs = null;

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.format(new Date());
		System.err.println(dateFormat.format(new Date()));
		SalesManBean bean = null;
		ArrayList<SalesManBean> SallesmanList = new ArrayList<SalesManBean>();

		int salesman_id, latecustomer;
		double monthlyIncome;
		String sallesman_name, cnicNo, address, district, phone;
		Date datejoin;

		// First Filteration
		String query = "SELECT salesman_name,"
				+ " salesman_cnic,"
				+ " salesman_address, "
				+ " salesman_district,"
				+ " salesman_address,"
				+ " salesman_basic_sallery,"
				+ " salesman_date_join,"
				+ " salesman_phone_no ,"
				+ " s.salesman_id ,"
				+ " SalesmanTotalLateCustomer(s.salesman_id) AS latecustomer"
				+ " FROM salesman s"
				+ " JOIN do_salesman ON s.salesman_id=do_salesman.salesman_id WHERE s.salesman_id="
				+ +salesmanId
				+ " AND (DATEDIFF(s.salesman_date_join, CURDATE()) <= -30)"
				+ " ORDER BY salesman_id DESC;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				sallesman_name = rs.getString(1);
				cnicNo = rs.getString(2);
				address = rs.getString(3);
				district = rs.getString(4);
				address = rs.getString(5);
				monthlyIncome = rs.getDouble(6);
				datejoin = rs.getDate(7);
				System.err.println("Date of Joining " + datejoin);
				phone = rs.getString(8);
				salesman_id = rs.getInt(9);
				latecustomer = rs.getInt(10);
				bean = new SalesManBean();
				bean.setName(sallesman_name);
				bean.setCnic(cnicNo);
				bean.setAddress(address);
				bean.setDistrict(district);
				bean.setDatejoin(datejoin);
				bean.setSallery(monthlyIncome);
				bean.setPhone_number(phone);
				bean.setSalesmanId(salesman_id);
				bean.setLateCustomer(latecustomer);
				SallesmanList.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	} // end of getting all customers form Db

	public static double totalcommision(int salesman_id) {

		ResultSet rs = null;

		double total_amount = 0;

		String query = "SELECT DISTINCT(appliance_name) AS apname ,(SELECT CountNumberAppliance(apname,"
				+ salesman_id + ")) FROM appliance";
		int count;
		String name;
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			System.out.println(query);

			while (rs.next()) {
				name = rs.getString(1);
				if (name.equalsIgnoreCase("5 wott")) {
					count = rs.getInt(2);

					if ((count >= 10) && (count <= 20)) {
						total_amount = count * 5;
					} else if ((count >= 21) && (count <= 35)) {
						total_amount = count * 10;
					} else if ((count >= 36) && (count <= 50)) {
						total_amount = count * 20;
					} else if ((count >= 51)) {
						total_amount = count * 30;
					}

				} else if (name.equalsIgnoreCase("10 wott")) {
					count = rs.getInt(2);

					if ((count >= 10) && (count <= 20)) {
						total_amount = count * 15;
					} else if ((count >= 21) && (count <= 35)) {
						total_amount = count * 10;
					} else if ((count >= 36) && (count <= 50)) {
						total_amount = count * 20;
					} else if ((count >= 51)) {
						total_amount = count * 30;
					}

				} else if (name.equalsIgnoreCase("15 wott")) {
					count = rs.getInt(2);

					if ((count >= 10) && (count <= 20)) {
						total_amount = count * 35;
					} else if ((count >= 21) && (count <= 35)) {
						total_amount = count * 45;
					} else if ((count <= 36) && (count <= 50)) {
						total_amount = count * 55;
					} else if ((count <= 51)) {
						total_amount = count * 65;
					}

				} else if (name.equalsIgnoreCase("20 wott")) {
					count = rs.getInt(2);

					if ((count >= 10) && (count <= 20)) {
						total_amount = count * 45;
					} else if ((count >= 21) && (count <= 35)) {
						total_amount = count * 55;
					} else if ((count >= 36) && (count <= 50)) {
						total_amount = count * 65;
					} else if ((count >= 51)) {
						total_amount = count * 75;
					}

				}

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return total_amount;
	}

	public static double totalRecovery(int salesman_id) {

		ResultSet rs = null;

		double total_amount = 0;

		String query = "SELECT (SUM(ci.installments_amount)*0.03) AS recovery_total FROM customer_installments ci\n"
				+ "JOIN sold_to sld ON ci.customer_id=sld.customer_id\n"
				+ "JOIN salesman sl ON sld.salesman_id=sl.salesman_id\n"
				+ "WHERE ci.paid_date>ci.due_date AND sl.salesman_id="
				+ salesman_id;

		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			System.out.println(query);

			while (rs.next()) {
				total_amount = rs.getDouble(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return total_amount;
	}

	public static SalesManBean getSalesManByID(int salesmanId) {
		SalesManBean bean = null;
		int salesman_id, total_customer, customer_on_installment, customer_on_cash, commsion_date;
		String name, cnic, phoneNo, district, address, employee_status, phone_number2, licence_no, degree;
		double salary, sal_lat, sal_lng, percentage, salesman_basic_sallery;
		Date date, date_of_birth, start_date, end_date;
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Date newDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(newDate);
		c.add(Calendar.MONTH, -1);
		c.add(Calendar.DATE, 1);
		String formated = formate.format(c.getTime());
		System.out.println(formated);

		// made by Jetander
		try (Connection connection = Connect.getConnection()) {
			// Begin Procedure Call / Jetander
			CallableStatement prepareCall = connection
					.prepareCall("{call get_salesman_by_id(?)}");
			prepareCall.setInt(1, salesmanId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				salesman_id = rs.getInt(1);
				name = rs.getString(2);
				cnic = rs.getString(3);
				district = rs.getString(4);
				address = rs.getString(5);
				phoneNo = rs.getString(6);
				salary = rs.getDouble(7);
				date = rs.getDate(8);
				total_customer = rs.getInt(9);
				customer_on_installment = rs.getInt(10);
				employee_status = rs.getString(11);

				sal_lat = rs.getDouble(16);
				sal_lng = rs.getDouble(17);
				date_of_birth = rs.getDate(18);
				phone_number2 = rs.getString(19);
				licence_no = rs.getString(20);
				degree = rs.getString(21);
				start_date = rs.getDate(22);
				end_date = rs.getDate(23);
				percentage = rs.getDouble(24);
				salesman_basic_sallery = rs.getDouble(25);
				commsion_date = rs.getInt("commission_date");
				customer_on_cash = total_customer - customer_on_installment;
				System.err.println("commm" + customer_on_cash);
				bean = new SalesManBean();
				bean.set__commission_date(commsion_date);
				bean.setSalesmanId(salesman_id);
				bean.setName(name);

				bean.setCnic(cnic);
				bean.setDatejoin(date);
				bean.setDistrict(district);
				bean.setCustomer_on_installments(customer_on_installment);
				bean.setTotal_customer(total_customer);
				bean.setPhone_number(phoneNo);
				bean.setSallery(salary);
				bean.setAddress(address);
				bean.setCustomer_on_cash(customer_on_cash);
				bean.setEmployee_status(employee_status);
				bean.setBeforeTime(rs.getInt(12));
				bean.setOnTime(rs.getInt(13));
				bean.setAfterTime(rs.getInt(14));
				bean.setTotalSales(rs.getInt(15));
				bean.setSal_lat(sal_lat);
				bean.setSal_lng(sal_lng);
				bean.setDate_of_birth(date_of_birth);
				bean.setPhone_number2(phone_number2);
				bean.setLicence_no(licence_no);
				bean.setDegree(degree);
				bean.setStart_date(start_date);
				bean.setEnd_date(end_date);
				bean.setPercentage(percentage);
				bean.setSalesman_basic_sallery(salesman_basic_sallery);
				bean.setGender(rs.getString("gender"));
				bean.setMarritalStatus(rs.getString("marrital_status"));
				bean.setBlood_group(rs.getString("blood_group"));
				bean.setVehicle(rs.getString("vehicle"));

				bean.setEducated(rs.getString("educated"));
				bean.setInstitue(rs.getString("institute"));
				bean.setImageId(rs.getInt("image_id"));
				bean.setBeforeTime(rs.getInt("before_time_percentage"));
				bean.setOnTime(rs.getInt("on_time_percentage"));
				bean.setAfterTime(rs.getInt("after_time_percentage"));
				bean.setTahsel(rs.getString("city"));
				bean.setFoname(rs.getString("fo_name"));
				bean.setDoname(rs.getString("user_name"));
				bean.setPer_sale(rs.getInt("per_sale"));
				bean.setVle_acount_no(rs.getString("vle_acount_no"));
				bean.setDoid(rs.getInt("user_id"));
				bean.setFoid(rs.getInt("fo_id"));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	} // end of Salesman

	public static ArrayList<HashMap<String, String>> getDefaultCustomer(
			int salesmanId) {
		System.out.print("SalesmanBAL.getDefaultCustomer()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		// made by Jetander
		try (Connection connection = Connect.getConnection();) {
			// Begin Procedure Call // Jetander
			CallableStatement cs = connection
					.prepareCall("{call get_default_customers(?)}");
			cs.setInt(1, salesmanId);
			ResultSet rs = cs.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaData.getColumnLabel(a + 1);
			}

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (String string : columns) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;
	}

	public static Salesman getSalesMan(int salesmanID) {

		ResultSet rs = null;

		Salesman bean = null;
		int salesmanId;
		double salary;
		String firstName, district, address, phoneNo, cnic;
		Date joiningDate;
		try (Connection con = Connect.getConnection();) {

			String query = "SELECT salesman_id, salesman_name, salesman_cnic, salesman_district, salesman_address, salesman_phone_no, salesman_basic_sallery,salesman_date_join FROM salesman WHERE salesman_id=?;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, salesmanID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				salesmanId = rs.getInt(1);
				firstName = rs.getString(2);
				cnic = rs.getString(3);
				district = rs.getString(4);
				address = rs.getString(5);
				phoneNo = rs.getString(6);
				salary = rs.getDouble(7);
				joiningDate = rs.getDate(8);
				bean = new Salesman();
				bean.setSalesmanId(salesmanId);
				bean.setName(firstName);
				bean.setCnicNo(cnic);
				bean.setDistrict(district);
				bean.setAddress(address);
				bean.setBasicSalary(salary);
				bean.setJoiningDate(joiningDate);
				bean.setPhoneNo(phoneNo);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	} // end of getting all customers form Db`

	public static int addUserAdmin(String Fullname, String cnic,
			String location, int city, double basicSalery, String Phone,
			String joining_date, int city_id, String password,
			String beforeTime, String onTime, String afterTime) {

		ResultSet rs = null;

		String query = "INSERT INTO salesman(salesman_name,salesman_cnic,salesman_address,salesman_district,salesman_basic_sallery,salesman_phone_no,salesman_date_join,city_id,password,before_time_percentage,on_time_percentage,after_time_percentage)\n"
				+ "VALUES('"
				+ Fullname
				+ "','"
				+ cnic
				+ "','"
				+ location
				+ "',"
				+ city
				+ ",'"
				+ basicSalery
				+ "','"
				+ Phone
				+ "','"
				+ joining_date
				+ "',"
				+ city_id
				+ ",'"
				+ password
				+ "','"
				+ beforeTime
				+ "','"
				+ onTime + "','" + afterTime + "')";
		System.out.println("Query : " + query);
		try (Connection con = Connect.getConnection();) {
			st = con.createStatement();
			st.executeUpdate(query);
			String queryForSalesmanId = "SELECT salesman_id ,salesman_date_join FROM salesman  ORDER BY salesman_id DESC LIMIT 1";
			rs = st.executeQuery(queryForSalesmanId);
			int id = 0;
			String dueDate = "";
			String currentDate = "";
			if (rs.next()) {
				id = rs.getInt(1);
				Date joiningDate = rs.getDate(2);
				currentDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(joiningDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(joiningDate);
				cal.add(Calendar.DATE, 30);

				dueDate = new SimpleDateFormat("yyyy-MM-dd").format(cal
						.getTime());
				System.out.println("Due Date : " + dueDate);

			}

			System.out.println("ID: " + id);
			System.out.println("joining Date " + currentDate);
			System.out.println("Due Date " + dueDate);

			st.executeUpdate("INSERT INTO targets " + "(salesman_id, "
					+ "total_target, " + "target_start_on, "
					+ "target_end_on, " + "is_sales_lock) " + "VALUES ('" + id
					+ "', " + "'4', " + "'" + currentDate + "', " + "'"
					+ dueDate + "', " + "'1')");

			// System.out.println(getMaxID());

		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return 0;
	} // end of addUser

	public static int addTarget(int salesmanId) {
		System.out.print("SalesmanBAL.get_last_target_date(?)");

		int ID = 0;
		try (Connection connection = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = connection
					.prepareCall("{call get_last_target_date(?)}");
			prepareCall.setInt(1, salesmanId);
			ResultSet rs = prepareCall.executeQuery();
			// String query = "SELECT target_id, target_start_on , target_end_on
			// FROM targets WHERE salesman_id = "
			// + salesmanId + " ORDER BY target_id DESC ";
			// st = connection.Connect.con.createStatement();
			// rs = st.executeQuery(query);
			Date lastDate = null;
			if (rs.next()) {
				lastDate = rs.getDate(3);
			}

			// String currentDate = new
			// SimpleDateFormat("yyyy-MM-dd").format(lastDate);
			Calendar cal = Calendar.getInstance();
			if (lastDate != null) {
				cal.setTime(lastDate);
				cal.add(Calendar.DATE, 1);
			}
			/*
			 * String newDate = new SimpleDateFormat("yyyy-MM-dd").format(cal
			 * .getTime());
			 */
			/*
			 * Calendar cal2 = Calendar.getInstance(); cal2.setTime(new
			 * SimpleDateFormat("yyyy-MM-dd").parse(newDate));
			 * cal2.add(cal2.DATE, 30);
			 */

			String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(cal
					.getTime());
			/*
			 * String toDate = new SimpleDateFormat("yyyy-MM-dd").format(cal2
			 * .getTime());
			 */
			prepareCall = connection.prepareCall("{call addTarget(?,?,?)}");
			prepareCall.setInt(1, salesmanId);
			prepareCall.setString(2, fromDate);
			// prepareCall.setString(3, toDate);
			prepareCall.setInt(3, 0);
			rs = prepareCall.executeQuery();
			if (rs.next()) {
				ID = rs.getInt("target_id");
				System.out.println("ID : " + ID);
			} else {
				System.out.println("Not Inserted");
			}

			// ps.close();

			// st.executeUpdate("INSERT INTO targets " + "(salesman_id, "
			// + "total_target, " + "target_start_on, "
			// + "target_end_on, " + "is_sales," + "is_recovery) "
			// + "VALUES ('" + salesmanId + "', " + "'0', " + "'"
			// + fromDate + "', " + "'" + toDate + "', " + "'0'," + "'1')");

			// dueDate = new
			// SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			// System.out.println("Due Date : "+dueDate);
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return 0;
	}

	public static int allowRecovery(int targetId) {
		try {

			String query = "UPDATE targets SET is_recovery = 1 WHERE salesman_id = 30 "
					+ " AND target_id = "
					+ " AND is_recovery=0 "
					+ " AND is_sales = 1 ";

			try (Connection con = Connect.getConnection()) {
				st = con.createStatement();
				st.executeUpdate(query);
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return 0;
	}

	public static int addTargetAndAssignUnit(int salesmanId)
			throws ParseException {
		System.out.print("SalesmanBAL.get_last_target_date(?)");

		int ID = 0;
		try (Connection connection = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = connection
					.prepareCall("{call get_last_target_date(?)}");
			prepareCall.setInt(1, salesmanId);
			ResultSet rs = prepareCall.executeQuery();
			// String query = "SELECT target_id, target_start_on , target_end_on
			// FROM targets WHERE salesman_id = "
			// + salesmanId + " ORDER BY target_id DESC ";
			// st = connection.Connect.con.createStatement();
			// rs = st.executeQuery(query);
			Date lastDate = null;
			if (rs.next()) {
				lastDate = rs.getDate(3);
			}

			// String currentDate = new
			// SimpleDateFormat("yyyy-MM-dd").format(lastDate);
			Calendar cal = Calendar.getInstance();
			if (lastDate != null) {
				cal.setTime(lastDate);
				cal.add(Calendar.DATE, 1);
				new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			}

			/*
			 * Calendar cal2 = Calendar.getInstance(); cal2.setTime(new
			 * SimpleDateFormat("yyyy-MM-dd").parse(newDate));
			 * cal2.add(cal2.DATE, 30);
			 */

			String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(cal
					.getTime());
			/*
			 * String toDate = new SimpleDateFormat("yyyy-MM-dd").format(cal2
			 * .getTime());
			 */

			// allowRecovery(rs.getInt(1));
			try {
				// PreparedStatement ps = connection.prepareStatement("INSERT
				// INTO targets (salesman_id, "
				// + "total_target, target_start_on, target_end_on, is_sales,
				// is_recovery) "
				// + "VALUES (?, '50', ?, ?, " + "'1'," +
				// "'0')",Statement.RETURN_GENERATED_KEYS);
				// ps.setInt(1,salesmanId);
				// java.sql.Date from = new java.sql.Date(new
				// SimpleDateFormat("yyyy-MM-dd").parse(fromDate).getTime());
				// java.sql.Date to = new java.sql.Date(new
				// SimpleDateFormat("yyyy-MM-dd").parse(toDate).getTime());
				// ps.setDate(2, from);
				// ps.setDate(3, to);
				// ps.executeUpdate();
				// Begin Procedure Call / Jetander
				java.sql.Date from = new java.sql.Date(new SimpleDateFormat(
						"yyyy-MM-dd").parse(fromDate).getTime());
				// java.sql.Date to = new java.sql.Date(new
				// SimpleDateFormat("yyyy-MM-dd").parse(toDate).getTime());
				prepareCall = connection.prepareCall("{call addTarget(?,?,?)}");
				prepareCall.setInt(1, salesmanId);
				prepareCall.setDate(2, from);
				// prepareCall.setDate(3, to);
				prepareCall.setInt(4, 50);
				rs = prepareCall.executeQuery();
				// ResultSet generatedKeys = prepareCall.getGeneratedKeys();
				if (rs.next()) {
					ID = rs.getInt("target_id");
					System.out.println("ID : " + ID);
				} else {
					System.out.println("Not Inserted");
				}

				// ps.close();

			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return ID;
	}

	public static int getMaxID(String phone) {

		ResultSet rs = null;

		String query = "SELECT MAX(salesman_id) FROM salesman where salesman_phone_no= ?";
		int id = 0;
		try (Connection con = Connect.getConnection()) {

			PreparedStatement st = (PreparedStatement) con.createStatement();
			st.setString(1, phone);
			rs = st.executeQuery(query);
			if (rs.next()) {
				id = rs.getInt(1);
			}
			System.out.println(id);

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return id;
	}

	public static int makerelation(int id, int idd) {
		int row = 0;

		String query = "INSERT INTO do_salesman (do_id,salesman_id) VALUES ("
				+ id + "," + idd + ")";
		try (Connection con = Connect.getConnection()) {

			st = con.createStatement();
			row = st.executeUpdate(query);
			System.out.printf(query);
			if (row > 0) {
				System.out.print("Data inserted");
			} else {
				System.out.print("Data not inserted");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static String getSalesManNO(String id) {

		ResultSet rs = null;

		String salesman_number = "";
		String query = "SELECT salesman_phone_no FROM salesman WHERE salesman_phone_no="
				+ id + "";
		Statement s = null;
		SalesManBean bean = null;
		try (Connection con = Connect.getConnection()) {

			s = con.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {

				salesman_number = rs.getString(1);
				bean = new SalesManBean();
				bean.setPhone_number(salesman_number);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return salesman_number;
	}

	public static int getCountSale(int sales) {

		ResultSet rs = null;

		int count = 0;
		String query = "Select Count(appliance_id) AS sales from sold_to WHERE salesman_id = ? AND status > 0;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, sales);
			rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<TechnicianBean> getTechnicianNock() {

		ResultSet rs = null;

		TechnicianBean bean = null;
		ArrayList<TechnicianBean> list = new ArrayList<>();
		int techId, doId;
		double salary;
		String name, address, phoneNo, cnic;
		Date joiningDate = null;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT technician_id, do_id, technican_name, technican_phone, technican_cnic, technican_address, technican_salary, created FROM technician ;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				techId = rs.getInt(1);
				doId = rs.getInt(2);
				name = rs.getString(3);
				phoneNo = rs.getString(4);
				cnic = rs.getString(5);
				address = rs.getString(6);
				salary = rs.getDouble(7);
				joiningDate = rs.getDate(8);
				bean = new TechnicianBean();
				bean.setId(techId);
				bean.setName(name);
				bean.setCnic(cnic);
				bean.setAddress(address);
				bean.setSalary(salary);
				bean.setJoiningDate(joiningDate);
				bean.setPhone(phoneNo);
				bean.setDoId(doId);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db`

	public static ArrayList<TechnicianBean> getTechnician(int id) {

		ResultSet rs = null;

		TechnicianBean bean = null;
		ArrayList<TechnicianBean> list = new ArrayList<>();
		int techId, doId;
		double salary;
		String name, address, phoneNo, cnic;
		Date joiningDate = null;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT technician_id, do_id, technican_name, technican_phone, technican_cnic, technican_address, technican_salary, created FROM technician where do_id="
					+ id + ";";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				techId = rs.getInt(1);
				doId = rs.getInt(2);
				name = rs.getString(3);
				phoneNo = rs.getString(4);
				cnic = rs.getString(5);
				address = rs.getString(6);
				salary = rs.getDouble(7);
				joiningDate = rs.getDate(8);
				bean = new TechnicianBean();
				bean.setId(techId);
				bean.setName(name);
				bean.setCnic(cnic);
				bean.setAddress(address);
				bean.setSalary(salary);
				bean.setJoiningDate(joiningDate);
				bean.setPhone(phoneNo);
				bean.setDoId(doId);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db`

	public static int getCountCustomer(int sales) {

		ResultSet rs = null;

		int count = 0;
		String query = "Select Count(customer_id) AS sales from sold_to WHERE salesman_id = ? AND status > 0;";
		try (Connection con = Connect.getConnection()) {

			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, sales);
			rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			System.out.printf(ex.getMessage());
		}
		return count;
	}

	public static ArrayList<TechnicianBean> getTechnician() {

		ResultSet rs = null;

		TechnicianBean bean = null;
		ArrayList<TechnicianBean> list = new ArrayList<>();
		int techId, doId;
		double salary;
		String name, address, phoneNo, cnic;
		Date joiningDate = null;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT technician_id, do_id, technican_name, technican_phone, technican_cnic, technican_address, technican_salary, created FROM technician;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				techId = rs.getInt(1);
				doId = rs.getInt(2);
				name = rs.getString(3);
				phoneNo = rs.getString(4);
				cnic = rs.getString(5);
				address = rs.getString(6);
				salary = rs.getDouble(7);
				joiningDate = rs.getDate(8);
				bean = new TechnicianBean();
				bean.setId(techId);
				bean.setName(name);
				bean.setCnic(cnic);
				bean.setAddress(address);
				bean.setSalary(salary);
				bean.setJoiningDate(joiningDate);
				bean.setPhone(phoneNo);
				bean.setDoId(doId);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Db`

	public static int getNumberOfSoldApplianceBySalesmanId(int salesmanId) {

		ResultSet rs = null;

		int numberOfSoldAppliances = 0;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT COUNT(DISTINCT st.appliance_id) "
					+ " FROM loan_payments lp "
					+ " INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
					+ " INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id  "
					+ " INNER JOIN customer c ON c.customer_id = lp.Customer_id"
					+ " INNER JOIN targets t ON t.salesman_id = st.salesman_id "
					+ " WHERE st.salesman_id =  ? ";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, salesmanId);
			rs = stmt.executeQuery();
			rs.next();
			numberOfSoldAppliances = rs.getInt(1);

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return numberOfSoldAppliances;
	}

	public static int getNumberOfSoldApplianceDateWiseBySalesmanId(
			int salesmanId, String startDate, String endDate) {

		ResultSet rs = null;

		int numberOfSoldAppliances = 0;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT COUNT(DISTINCT st.appliance_id) "
					+ " FROM loan_payments lp "
					+ " INNER JOIN payment_loan pl ON lp.loan_id = pl.loan_id "
					+ " INNER JOIN sold_to st ON st.sold_to_id = pl.soldto_id  "
					+ " INNER JOIN customer c ON c.customer_id = lp.Customer_id"
					+ " INNER JOIN targets t ON t.salesman_id = st.salesman_id "
					+ " AND lp.Paid_Date >= '" + startDate + "' "
					+ " AND lp.Paid_Date <= '" + endDate + "' "
					+ " WHERE st.salesman_id =  " + salesmanId;
			Statement stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			while (rs.next()) {
				numberOfSoldAppliances = rs.getInt(1);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return numberOfSoldAppliances;
	}

	public static int getTotalUnitsBySalesmanId(int salesmanId,
			String startDate, String endDate) {

		ResultSet rs = null;

		int totalUnits = 0;

		// made by Jetander
		try (Connection connection = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = connection
					.prepareCall("{call get_total_targets_for_salesman(?,?,?)}");
			prepareCall.setInt(1, salesmanId);
			prepareCall.setString(2, startDate);
			prepareCall.setString(3, endDate);
			rs = prepareCall.executeQuery();
			// try {
			//
			// connection.Connect.init();
			//
			// String query = "SELECT t.total_target FROM targets t " + " WHERE
			// "
			// + " t.target_start_on = '" + startDate + "' "
			// + " AND t.target_end_on = '" + endDate + "' "
			// + " AND t.salesman_id = " + salesmanId;
			// System.out.println(query);
			// Statement stmt = (Statement) con.createStatement();
			// rs = stmt.executeQuery(query);
			while (rs.next()) {

				totalUnits = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return totalUnits;
	}

	public static int getTotalUnitsForRecoveryBySalesmanId(int salesmanId) {

		ResultSet rs = null;

		int totalUnits = 0;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT SUM(t.total_target) FROM targets t "
					+ " WHERE " + " t.salesman_id = " + salesmanId;
			System.out.println(query);
			Statement stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				totalUnits = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return totalUnits;
	}

	public static int getTotalAmountDateWiseBySalemanId(int salesmanId,
			String startDate, String endDate) {
		System.out.print("SalesmanBAL.get_total_amount_date_wise");
		int amount = 0;
		// try {
		// connection.Connect.init();
		//
		// String query = " SELECT sum(pl.total_amount) "+
		// " FROM "+
		// " payment_loan pl INNER JOIN sold_to st ON st.sold_to_id =
		// pl.soldto_id "+
		// " INNER JOIN customer c ON c.customer_id = st.Customer_id "+
		// " WHERE pl.created_on >= '"+startDate+"' "+
		// " AND pl.created_on <= '"+endDate+"' "+
		// " and c.created_on is not null "+
		// " AND st.salesman_id = "+salesmanId;
		// Statement stmt = (Statement) con.createStatement();
		// rs = stmt.executeQuery(query);
		try (Connection connection = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = connection
					.prepareCall("{call get_total_amount_date_wise(?,?,?)}");
			prepareCall.setInt(1, salesmanId);
			prepareCall.setString(2, startDate);
			prepareCall.setString(3, endDate);
			ResultSet rs = prepareCall.executeQuery();
			rs.next();
			amount = rs.getInt(1);
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return amount;
	}

	public static ArrayList<Salesman> getSalesmanByDistrictId(int districtId) {
		ArrayList<Salesman> salesmanList = new ArrayList<>();

		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select "
					+ "s.salesman_id, " + "s.salesman_name "
					+ "from salesman s " + "where s.salesman_district = ?");
			ps.setInt(1, districtId);
			ResultSet rs = ps.executeQuery();
			System.out.println(ps.toString());
			// System.out.println(rs.next());
			while (rs.next()) {
				Salesman salesman = new Salesman();
				salesman.setSalesmanId(rs.getInt("s.salesman_id"));
				salesman.setName(rs.getString("s.salesman_name"));
				salesmanList.add(salesman);

			}

		} catch (SQLException e) {
			logger.error("", e);
			System.err.println("SalesmanBAL.getSalesmanByDistrictId()");
			e.printStackTrace();
		}
		return salesmanList;
	}

	public static SalesmanIdBean getSalesmanByCustomerId(int cusomer_id) {
		SalesmanIdBean salesman = null;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT e.salesman_id FROM eligibility e"
							+ " JOIN customer c ON c.customer_id = e.customer_id"
							+ " JOIN salesman s ON s.salesman_id = e.salesman_id WHERE s.salesman_district = ?");
			ps.setInt(1, cusomer_id);
			ResultSet rs = ps.executeQuery();
			System.out.println(ps.toString());
			// System.out.println(rs.next());
			while (rs.next()) {
				salesman = new SalesmanIdBean();
				salesman.setSalesman(rs.getInt("e.salesman_id"));
			}

		} catch (SQLException e) {
			logger.error("", e);
			System.err.println("SalesmanBAL.getSalesmanByDistrictId()");
			e.printStackTrace();
		}
		return salesman;
	}

	public static List<UpComingRevocoveries> upComingRevocoveriesBySalesmanId(
			int salesmanId) {
		System.err
				.println("SalesmanBAL.get_upcoming_recoveries_of_salesman(?)");

		List<UpComingRevocoveries> upComingRevocoveries = new ArrayList<UpComingRevocoveries>();
		// try {
		// connection.Connect.init();
		//
		// String query = "SELECT DISTINCT(ap.appliance_id),c.customer_name,
		// ap.appliance_name, DATEDIFF(lp.due_date, CURDATE()) AS dif,
		// lp.due_date "
		// + " FROM sold_to s "
		// + " INNER JOIN customer c ON s.customer_id = c.customer_id "
		// + " INNER JOIN appliance ap ON ap.appliance_id = s.appliance_id "
		// + " INNER JOIN payment_loan pl ON pl.soldto_id = s.sold_to_id "
		// + " INNER JOIN loan_payments lp ON lp.loan_id = pl.loan_id "
		// + " WHERE s.salesman_id = "
		// + salesmanId
		// + " AND ap.appliance_status = 1 AND (DATEDIFF(lp.due_date, CURDATE())
		// >= -7 AND DATEDIFF(lp.due_date, CURDATE()) <= 29) "
		// + " AND lp.Paid_Date IS NULL "
		// + " ORDER BY dif ";
		// Statement stmt = (Statement) con.createStatement();
		// rs = stmt.executeQuery(query);
		try (Connection connection = Connect.getConnection()) {
			// Begin Procedure Call // Jetander
			CallableStatement prepareCall = connection
					.prepareCall("{call get_upcoming_recoveries_of_salesman(?)}");
			prepareCall.setInt(1, salesmanId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				UpComingRevocoveries uRevocoveries = new UpComingRevocoveries();
				uRevocoveries.setCustomerName(rs.getString(2));
				uRevocoveries.setApplianceName(rs.getString(3));
				uRevocoveries.setRemainingDays("" + rs.getInt(4));
				upComingRevocoveries.add(uRevocoveries);
			}

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return upComingRevocoveries;
	}

	public static int addSalesman(String userName, String email,
			String password, String cnicNo, String gender, Double basic_salary,
			int district_id, String phone, String address, String dateOfBith,
			String dateOfJoining, String educated, String marrital_status,
			String blood_group, String licence_no, String expiry_date_of_cnic,
			String user_mobile, String degree, String institute,
			String start_date, String end_date, double percentage,
			String fatherName, String fatherDob, String fatherCnic,
			String fatherPhone, String motherName, String motherDob,
			String motherCnic, String motherMobile, String brotherName,
			String brotherCnic, String brotherDob, String brotherPhone,
			String husbandName, String husbandDob, String husbandPhone,
			String husbandCnic, String vehicle, InputStream input, int cityId,
			String beforeTime, String onTime, String afterTime) {

		ResultSet rs = null;

		String query = "INSERT INTO salesman(salesman_name,salesman_email,password,salesman_cnic,gender,salesman_basic_sallery,salesman_district,"
				+ "salesman_phone_no,salesman_address,date_of_birth,salesman_date_join,educated,marrital_status,blood_group,licence_no,expiry_date_of_cnic,"
				+ "mobile_no, degree,institute,start_date,end_date,percentage,father_name,father_dob,father_cnic,father_mobile_no,mother_name,"
				+ "mother_dob,mother_cnic,mother_mobile_no,brother_name,brother_cnic,brother_mobile_no,brother_dob,wife_name,wife_cnic,wife_dob,"
				+ "wife_mobile_no,vehicle,image,city_id,before_time_percentage,on_time_percentage,after_time_percentage)"
				+ "Values('"
				+ userName
				+ "','"
				+ email
				+ "','"
				+ password
				+ "','"
				+ cnicNo
				+ "','"
				+ gender
				+ "',"
				+ basic_salary
				+ ","
				+ district_id
				+ ",'"
				+ phone
				+ "','"
				+ address
				+ "','"
				+ dateOfBith
				+ "','"
				+ dateOfJoining
				+ "','"
				+ educated
				+ "','"
				+ marrital_status
				+ "','"
				+ blood_group
				+ "','"
				+ licence_no
				+ "','"
				+ expiry_date_of_cnic
				+ "','"
				+ user_mobile
				+ "','"
				+ degree
				+ "','"
				+ institute
				+ "','"
				+ start_date
				+ "','"
				+ end_date
				+ "',"
				+ percentage
				+ ",'"
				+ fatherName
				+ "','"
				+ fatherDob
				+ "','"
				+ fatherCnic
				+ "','"
				+ fatherPhone
				+ "','"
				+ motherName
				+ "','"
				+ motherDob
				+ "','"
				+ motherCnic
				+ "','"
				+ motherMobile
				+ "','"
				+ brotherName
				+ "','"
				+ brotherCnic
				+ "','"
				+ brotherPhone
				+ "','"
				+ brotherDob
				+ "','"
				+ husbandName
				+ "','"
				+ husbandCnic
				+ "','"
				+ husbandDob
				+ "','"
				+ husbandPhone
				+ "','"
				+ vehicle
				+ "','"
				+ input
				+ "',"
				+ cityId
				+ ",'"
				+ beforeTime
				+ "','"
				+ onTime
				+ "','"
				+ afterTime + "')";
		System.out.println("Query : " + query);
		// ,String beforeTime , String onTime , String afterTime
		// ,before_time_percentage,on_time_percentage,after_time_percentage
		int row = 0;
		int id = 0;
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			row = st.executeUpdate(query);
			String queryForSalesmanId = "SELECT salesman_id ,salesman_date_join FROM salesman  ORDER BY salesman_id DESC LIMIT 1";
			rs = st.executeQuery(queryForSalesmanId);
			String dueDate = "";
			String currentDate = "";
			if (rs.next()) {
				id = rs.getInt(1);
				Date joiningDate = rs.getDate(2);
				currentDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(joiningDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(joiningDate);
				cal.add(Calendar.DATE, 30);
				dueDate = new SimpleDateFormat("yyyy-MM-dd").format(cal
						.getTime());
				System.out.println("Due Date : " + dueDate);
			}
			System.out.println("ID: " + id);
			System.out.println("joining Date " + currentDate);
			System.out.println("Due Date " + dueDate);

			st.executeUpdate("INSERT INTO targets " + "(salesman_id, "
					+ "total_target, " + "target_start_on, "
					+ "target_end_on, " + "is_sales) " + "VALUES ('" + id
					+ "', " + "'50', " + "'" + currentDate + "', " + "'"
					+ dueDate + "', " + "'1')");

			if (row > 0) {
				System.out.println("Data is inserted12");
			} else {
				System.out.println("Data is not inserted");
			}
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return id;
	} // end of addUser

	// public static int addSalesman(String userName, String email, String
	// password, String cnicNo, String gender, Double basic_salary, int
	// district_id, String phone, String address,String dateOfBith, String
	// dateOfJoining, String educated,String marrital_status, String
	// blood_group,String licence_no, String expiry_date_of_cnic, String
	// user_mobile, String degree,String institute, String start_date, String
	// end_date, double percentage, String fatherName, String fatherDob, String
	// fatherCnic, String fatherPhone, String motherName, String motherDob,
	// String motherCnic, String motherMobile, String brotherName, String
	// brotherCnic, String brotherDob, String brotherPhone, String husbandName,
	// String husbandDob, String husbandPhone, String husbandCnic, String
	// vehicle, InputStream input,int cityId,String beforeTime,String
	// onTime,String afterTime) {
	// connection.Connect.init();
	// String query =
	// "INSERT INTO
	// salesman(salesman_name,salesman_email,password,salesman_cnic,gender,salesman_basic_sallery,salesman_district,"
	// +
	// "salesman_phone_no,salesman_address,date_of_birth,salesman_date_join,educated,marrital_status,blood_group,licence_no,expiry_date_of_cnic,"
	// +
	// "mobile_no,
	// degree,institute,start_date,end_date,percentage,father_name,father_dob,father_cnic,father_mobile_no,mother_name,"
	// +
	// "mother_dob,mother_cnic,mother_mobile_no,brother_name,brother_cnic,brother_mobile_no,brother_dob,wife_name,wife_cnic,wife_dob,"
	// +
	// "wife_mobile_no,vehicle,image,city_id,before_time_percentage,on_time_percentage,after_time_percentage)"
	// +
	// "Values('" + userName + "','" + email + "','" + password + "','" + cnicNo
	// + "','" + gender + "'," + basic_salary + "," + district_id + ",'" + phone
	// + "','" + address + "','" + dateOfBith + "','" + dateOfJoining + "','" +
	// educated + "','" + marrital_status + "','" + blood_group + "','" +
	// licence_no + "','" + expiry_date_of_cnic + "','" + user_mobile + "','" +
	// degree + "','" + institute + "','" + start_date + "','" + end_date + "',"
	// + percentage + ",'" + fatherName + "','" + fatherDob + "','" + fatherCnic
	// + "','" + fatherPhone + "','" + motherName + "','" + motherDob + "','" +
	// motherCnic + "','" + motherMobile + "','" + brotherName + "','" +
	// brotherCnic + "','" + brotherPhone + "','" + brotherDob + "','" +
	// husbandName + "','" + husbandCnic + "','" + husbandDob + "','" +
	// husbandPhone + "','" + vehicle + "','" + input + "',"+ cityId +",'" +
	// beforeTime + "','" + onTime + "','" + afterTime + "')";
	// System.out.println("Query : " + query);
	// //,String beforeTime , String onTime , String afterTime
	// //,before_time_percentage,on_time_percentage,after_time_percentage
	// int row = 0;
	// int id = 0;
	// try {
	// st = connection.Connect.con.createStatement();
	// row = st.executeUpdate(query);
	// String queryForSalesmanId =
	// "SELECT salesman_id ,salesman_date_join FROM salesman ORDER BY
	// salesman_id DESC LIMIT 1";
	// rs = st.executeQuery(queryForSalesmanId);
	// String dueDate = "";
	// String currentDate = "";
	// if (rs.next()) {
	// id = rs.getInt(1);
	// Date joiningDate = rs.getDate(2);
	// currentDate = new SimpleDateFormat("yyyy-MM-dd").format(joiningDate);
	// Calendar cal = Calendar.getInstance();
	// cal.setTime(joiningDate);
	// cal.add(Calendar.DATE, 30);
	// dueDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	// System.out.println("Due Date : "+dueDate);
	// }
	// System.out.println("ID: "+id);
	// System.out.println("joining Date "+currentDate);
	// System.out.println("Due Date "+dueDate);
	//
	// st.executeUpdate("INSERT INTO targets "
	// +"(salesman_id, "
	// +"total_target, "
	// +"target_start_on, "
	// +"target_end_on, "
	// +"is_sales) "
	// +"VALUES ('"+id+"', "
	// +"'50', "
	// +"'"+currentDate+"', "
	// +"'"+dueDate+"', "
	// +"'1')");
	//
	// if (row > 0) {
	// System.out.println("Data is inserted12");
	// } else {
	// System.out.println("Data is not inserted");
	// }
	// } catch (Exception e) {
	// System.err.println(e);
	// }
	// return id;
	// } // end of addUser

	public static int addSalesman(String userName, String email,
			String password, String cnicNo, String gender, Double basic_salary,
			int district_id, String phone, String address, String dateOfBith,
			String dateOfJoining, String educated, String marrital_status,
			String blood_group, String licence_no, String user_mobile,
			String degree, String institute, String start_date,
			String end_date, double percentage, String vehicle,
			InputStream input, int cityId, String beforeTime, String onTime,
			String afterTime) {

		ResultSet rs = null;

		String query = "INSERT INTO salesman(salesman_name,salesman_email,password,salesman_cnic,gender,salesman_basic_sallery,salesman_district,"
				+ "salesman_phone_no,salesman_address,date_of_birth,salesman_date_join,educated,marrital_status,blood_group,licence_no,"
				+ "mobile_no, degree,institute,start_date,end_date,percentage,vehicle,image,city_id,before_time_percentage,on_time_percentage,after_time_percentage)"
				+ "Values('"
				+ userName
				+ "','"
				+ email
				+ "','"
				+ password
				+ "','"
				+ cnicNo
				+ "','"
				+ gender
				+ "',"
				+ basic_salary
				+ ","
				+ district_id
				+ ",'"
				+ phone
				+ "','"
				+ address
				+ "','"
				+ dateOfBith
				+ "','"
				+ dateOfJoining
				+ "','"
				+ educated
				+ "','"
				+ marrital_status
				+ "','"
				+ blood_group
				+ "','"
				+ licence_no
				+ "','"
				+ user_mobile
				+ "','"
				+ degree
				+ "','"
				+ institute
				+ "','"
				+ start_date
				+ "','"
				+ end_date
				+ "',"
				+ percentage
				+ ",'"
				+ vehicle
				+ "','"
				+ input
				+ "',"
				+ cityId
				+ ",'"
				+ beforeTime
				+ "','" + onTime + "','" + afterTime + "')";
		System.out.println("Query : " + query);
		// ,String beforeTime , String onTime , String afterTime
		// ,before_time_percentage,on_time_percentage,after_time_percentage
		int row = 0;
		int id = 0;
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			row = st.executeUpdate(query);
			String queryForSalesmanId = "SELECT salesman_id ,salesman_date_join FROM salesman  ORDER BY salesman_id DESC LIMIT 1";
			rs = st.executeQuery(queryForSalesmanId);
			if (rs.next()) {
				id = rs.getInt(1);
			}
			/*
			 * String dueDate = ""; String currentDate = ""; if (rs.next()) { id
			 * = rs.getInt(1); Date joiningDate = rs.getDate(2); currentDate =
			 * new SimpleDateFormat("yyyy-MM-dd") .format(joiningDate); Calendar
			 * cal = Calendar.getInstance(); cal.setTime(joiningDate);
			 * cal.add(Calendar.DATE, 30); dueDate = new
			 * SimpleDateFormat("yyyy-MM-dd").format(cal .getTime());
			 * System.out.println("Due Date : " + dueDate); }
			 * System.out.println("ID: " + id); System.out.println(
			 * "joining Date " + currentDate); System.out.println("Due Date " +
			 * dueDate);
			 * 
			 * st.executeUpdate("INSERT INTO targets " + "(salesman_id, " +
			 * "total_target, " + "target_start_on, " + "target_end_on, " +
			 * "is_sales) " + "VALUES ('" + id + "', " + "'50', " + "'" +
			 * currentDate + "', " + "'" + dueDate + "', " + "'1')");
			 */

			if (row > 0) {
				System.out.println("Data is inserted12");
			} else {
				System.out.println("Data is not inserted");
			}
			con.close();
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return id;
	} // end of addUser

	public static int insertFamilyMembers(int salesmanId, String memberName,
			String relation, String cnic, String phoneNo) {

		String query = "INSERT INTO family_details (salesman_id,member_name,relation,phone_no,cnic) VALUES("
				+ salesmanId
				+ ",'"
				+ memberName
				+ "','"
				+ relation
				+ "','"
				+ phoneNo + "','" + cnic + "');";
		System.out.println("Query : " + query);
		try (Connection con = Connect.getConnection()) {
			st = con.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			logger.error("", e);
			System.err.println(e);
		}
		return 0;
	} // end of addUser

	public static ArrayList<AllSalesmansCommission> getAllSalesmansCommission(
			double beforeTime, double onTime, double afterTime, String year) {

		ArrayList<AllSalesmansCommission> list = new ArrayList<>();
		AllSalesmansCommission bean = null;
		int ot = 0;
		int bt = 0;
		int at = 0;
		Date time;

		ResultSet rs = null;

		try (Connection con = Connect.getConnection();) {
			CallableStatement call = con
					.prepareCall("{CALL salesmans_commission_bar_chart(?,?,?,?)}");
			call.setDouble(1, beforeTime);
			call.setDouble(2, onTime);
			call.setDouble(3, afterTime);
			call.setString(4, year);
			rs = call.executeQuery();

			while (rs.next()) {

				time = rs.getDate(1);
				bt = rs.getInt(2);
				ot = rs.getInt(3);
				at = rs.getInt(4);

				bean = new AllSalesmansCommission();
				bean.setBeforeTime(bt);
				bean.setOnTime(ot);
				bean.setAfterTime(at);
				bean.setTime(time + "");
				list.add(bean);

			}

			if (list.size() < 1) {
				bean = new AllSalesmansCommission();
				bean.setBeforeTime(0);
				bean.setOnTime(0);
				bean.setAfterTime(0);
				bean.setTime("2016-04-09");
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;

	}

	// Coded by Jeevan
	public ArrayList<HashMap<String, String>> getAllSalesmanBySoldTo() {
		System.out.println("SalesmanBAL.getAllSalesmanBySoldTo()");
		ArrayList<HashMap<String, String>> mapList = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_all_salesman_by_sold_to()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("salesmanId", resultSet.getInt("salesman_id") + "");
				map.put("salesmanName", resultSet.getString("salesman_name"));
				mapList.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return mapList;
	}

	public static ArrayList<HashMap<String, String>> getSalesmanListWithOrder(
			int start, int length, int order, String dir) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		ResultSet rs;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {

			// ascending order
			if (dir.equalsIgnoreCase("asc")) {

				switch (order) {
				case 0:
					call = con
							.prepareCall("{CALL get_appliance_by_salesman_name_asc(?,?)}");
					break;
				case 1:
					call = con
							.prepareCall("{CALL get_appliance_by_salesman_phone_number_asc(?,?)}");
					break;
				case 2:
					call = con
							.prepareCall("{CALL get_appliance_by_salesman_district_asc(?,?)}");
					break;
				case 3:
					call = con
							.prepareCall("{CALL get_appliance_by_district_officer_name_asc(?,?)}");
					break;
				case 4:
					call = con
							.prepareCall("{CALL get_appliance_by_salesman_field_officer_name_asc(?,?)}");
					break;
				case 5:
					call = con
							.prepareCall("{CALL get_appliance_by_sales_asc(?,?)}");
					break;

				}

				// descending order
			} else if (dir.equalsIgnoreCase("desc")) {

				switch (order) {
				case 0:
					call = con
							.prepareCall("{CALL get_appliance_by_salesman_name_desc(?,?)}");
					break;
				case 1:
					call = con
							.prepareCall("{CALL get_appliance_by_salesman_phone_number_desc(?,?)}");
					break;
				case 2:
					call = con
							.prepareCall("{CALL get_appliance_by_salesman_district_desc(?,?)}");
					break;
				case 3:
					call = con
							.prepareCall("{CALL get_appliance_by_district_officer_name_desc(?,?)}");
					break;
				case 4:
					call = con
							.prepareCall("{CALL get_appliance_by_salesman_field_officer_name_desc(?,?)}");
					break;
				case 5:
					call = con
							.prepareCall("{CALL get_appliance_by_sales_desc(?,?)}");
					break;
				}
			}
			if (call != null) {

				call.setInt(1, start);
				call.setInt(2, length);
				rs = call.executeQuery();
				while (rs.next()) {

					HashMap<String, String> map = new HashMap<>();
					map.put("salesmanId", rs.getInt("salesman_id") + "");
					map.put("salesmanName", rs.getString("salesman_name"));
					map.put("salesmanAddress", rs.getString("salesman_address"));

					String number2 = UserBAL.getFormattedPhoneNumber(rs
							.getString("salesman_phone_no"));
					map.put("salesmanNumber", number2);
					map.put("salesmanSalary",
							rs.getDouble("salesman_basic_sallery") + "");
					map.put("salesmanJoiningDate",
							rs.getDate("salesman_date_join") + "");
					map.put("salesmanDistrict", rs.getString("district_name"));
					map.put("doName", rs.getString("user_name"));
					map.put("foname", rs.getString("fo_name"));
					map.put("totalCustomers", rs.getInt("count_customer") + "");
					map.put("totalSales", rs.getInt("count_sales") + "");

					list.add(map);
				}

			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int countSalesmans() {

		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_salesmans_count()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getSalesmanListWith(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		CallableStatement call = null;
		ResultSet rs;
		PrettyTime p = new PrettyTime();
		try (Connection connection = Connect.getConnection()) {
			call = connection.prepareCall("{Call get_Nd_data(?,?,?,?,?)}");
			call.setInt(1, str);
			call.setInt(2, rng);
			call.setString(3, orde);
			call.setInt(4, col);
			call.setString(5, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanAddress", rs.getString("salesman_address"));
				String number2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("salesman_phone_no"));
				map.put("salesmanNumber", number2);
				map.put("salesmanSalary",
						rs.getDouble("salesman_basic_sallery") + "");
				map.put("salesmanJoiningDate", rs.getDate("salesman_date_join")
						+ "");
				map.put("salesmanDistrict", rs.getString("district_data"));
				map.put("foname", rs.getString("fo_name"));
				if (rs.getTimestamp("last_sale") != null) {
					Timestamp dateTime = rs.getTimestamp("last_sale");
					Date date = new Date(dateTime.getTime());
					System.out.println(p.format(date));
					map.put("lastSale", p.format(date));
				} else {
					map.put("lastSale", "No Sale");
				}
				map.put("tatalApps", rs.getString("total_apps"));
				map.put("totalInstalls", rs.getString("total_installs"));
				map.put("monthlyApps", rs.getString("monthly_apps"));
				map.put("monthlyInstalls", rs.getString("monthly_installs"));
				map.put("weeklyApps", rs.getString("weekly_apps"));
				map.put("weeklyInstalls", rs.getString("weekly_installs"));
				map.put("recovery", rs.getString("recovery"));
				int count = rs.getInt("salesman_status");
				if (count > 0) {
					map.put("status", "Active");
				} else {
					map.put("status", "Inactive");
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getSalesmanListWithSearch(
			int str, int rng, int col, String orde, String value) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs;
		try (Connection connection = Connect.getConnection()) {
			call = connection
					.prepareCall("{Call get_appliance_by_search_in_salesman_two(?,?,?,?,?)}");
			call.setInt(1, str);
			call.setInt(2, rng);
			call.setString(3, orde);
			call.setInt(4, col);
			call.setString(5, value);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanAddress", rs.getString("salesman_address"));
				String number2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("salesman_phone_no"));
				map.put("salesmanNumber", number2);
				map.put("salesmanSalary",
						rs.getDouble("salesman_basic_sallery") + "");
				map.put("salesmanJoiningDate", rs.getDate("salesman_date_join")
						+ "");
				map.put("salesmanDistrict", rs.getString("district_data"));
				map.put("foname", rs.getString("fo_name"));
				map.put("lastSale", rs.getString("last_sale"));
				map.put("tatalApps", rs.getString("total_apps"));
				map.put("totalInstalls", rs.getString("total_installs"));
				map.put("monthlyApps", rs.getString("monthly_apps"));
				map.put("monthlyInstalls", rs.getString("monthly_installs"));
				map.put("weeklyApps", rs.getString("weekly_apps"));
				map.put("weeklyInstalls", rs.getString("weekly_installs"));
				map.put("recovery", rs.getString("recovery"));
				map.put("status", rs.getString("salesman_status"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getSalesmanSearchCount(String value) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_appliance_by_search_count_in_salesman(?)}");
			call.setString(1, value);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static int getSearchCountForSalesman(String search) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_salesmans_count_search(?)}");
			call.setString(1, search);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static boolean insertSalesMan(HashMap<String, String> salesmap,
			int doId) {

		boolean err = true;

		try (Connection con = Connect.getConnection()) {

			con.setAutoCommit(false);
			// *******************Add Customer Open********************
			String query = "";
			if (salesmap.get("imageId").equals("0")) {
				query = "INSERT INTO salesman(fo_id,salesman_name,salesman_email,password,salesman_cnic,gender,salesman_basic_sallery,salesman_district,"
						+ "salesman_phone_no,salesman_address,date_of_birth,salesman_date_join,educated,marrital_status,blood_group,licence_no,"
						+ "mobile_no, degree,institute,start_date,end_date,percentage,vehicle,image,city_id,before_time_percentage,on_time_percentage,after_time_percentage,per_sale,vle_acount_no)"
						+ "Values('"
						+ salesmap.get("foid")
						+ "','"
						+ salesmap.get("firstName")
						+ "','"
						+ salesmap.get("email")
						+ "','"
						+ salesmap.get("password")
						+ "','"
						+ salesmap.get("cnic")
						+ "','"
						+ salesmap.get("gender")
						+ "','"
						+ salesmap.get("salary")
						+ "','"
						+ salesmap.get("district_id")
						+ "','"
						+ salesmap.get("phone1")
						+ "','"
						+ salesmap.get("address")
						+ "','"
						+ salesmap.get("dateOfBirth")
						+ "','"
						+ salesmap.get("joiningDate")
						+ "','"
						+ salesmap.get("educated")
						+ "','"
						+ salesmap.get("marritalStatus")
						+ "','"
						+ salesmap.get("bloodGroup")
						+ "','"
						+ salesmap.get("licence")
						+ "','"
						+ salesmap.get("phone2")
						+ "','"
						+ salesmap.get("degree")
						+ "','"
						+ salesmap.get("college")
						+ "','"
						+ salesmap.get("dateOfStart")
						+ "','"
						+ salesmap.get("dateOfEnd")
						+ "','"
						+ salesmap.get("percentage")
						+ "','"
						+ salesmap.get("vehicle")
						+ "','"
						+ salesmap.get("input")
						+ "','"
						+ salesmap.get("cityId")
						+ "','"
						+ salesmap.get("beforeTime")
						+ "','"
						+ salesmap.get("onTime")
						+ "','"
						+ salesmap.get("afterTime")
						+ "','"
						+ salesmap.get("percellcomm")
						+ "','"
						+ salesmap.get("accnumber") + "')";
			} else {
				query = "INSERT INTO salesman(fo_id,salesman_name,salesman_email,password,salesman_cnic,gender,salesman_basic_sallery,salesman_district,"
						+ "salesman_phone_no,salesman_address,date_of_birth,salesman_date_join,educated,marrital_status,blood_group,licence_no,"
						+ "mobile_no, degree,institute,start_date,end_date,percentage,vehicle,image,city_id,before_time_percentage,on_time_percentage,after_time_percentage,image_id,per_sale,vle_acount_no)"
						+ "Values('"
						+ salesmap.get("foid")
						+ "','"
						+ salesmap.get("firstName")
						+ "','"
						+ salesmap.get("email")
						+ "','"
						+ salesmap.get("password")
						+ "','"
						+ salesmap.get("cnic")
						+ "','"
						+ salesmap.get("gender")
						+ "','"
						+ salesmap.get("salary")
						+ "','"
						+ salesmap.get("district_id")
						+ "','"
						+ salesmap.get("phone1")
						+ "','"
						+ salesmap.get("address")
						+ "','"
						+ salesmap.get("dateOfBirth")
						+ "','"
						+ salesmap.get("joiningDate")
						+ "','"
						+ salesmap.get("educated")
						+ "','"
						+ salesmap.get("marritalStatus")
						+ "','"
						+ salesmap.get("bloodGroup")
						+ "','"
						+ salesmap.get("licence")
						+ "','"
						+ salesmap.get("phone2")
						+ "','"
						+ salesmap.get("degree")
						+ "','"
						+ salesmap.get("college")
						+ "','"
						+ salesmap.get("dateOfStart")
						+ "','"
						+ salesmap.get("dateOfEnd")
						+ "','"
						+ salesmap.get("percentage")
						+ "','"
						+ salesmap.get("vehicle")
						+ "','"
						+ salesmap.get("input")
						+ "','"
						+ salesmap.get("cityId")
						+ "','"
						+ salesmap.get("beforeTime")
						+ "','"
						+ salesmap.get("onTime")
						+ "','"
						+ salesmap.get("afterTime")
						+ "','"
						+ salesmap.get("imageId")
						+ "','"
						+ salesmap.get("percellcomm")
						+ "','"
						+ salesmap.get("accnumber") + "')";

			}

			System.out.println("Query : " + query);

			Statement st = con.createStatement();

			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

			int vleid = 0;
			try (ResultSet generatedKeys = st.getGeneratedKeys()) {

				if (generatedKeys.next()) {
					vleid = generatedKeys.getInt(1);
					generatedKeys.close();
				} else {

					throw new SQLException(
							"Creating vle failed, no ID obtained");
				}
			}
			PreparedStatement statement = null;
			if (vleid > 0) {

				statement = con
						.prepareStatement("INSERT INTO user_finiance(total_amount,current_amount,defaulter_amount,onhold_status,onhold_amount,user_id,user_type) VALUES(?,?,?,?,?,?,?)");
				statement.setInt(1, 0);
				statement.setInt(2, 0);
				statement.setInt(3, 0);
				statement.setInt(4, 0);
				statement.setInt(5, 0);
				statement.setInt(6, vleid);
				statement.setInt(7, 101);
				statement.executeUpdate();

			}

			// String queryForSalesmanId = "SELECT salesman_id
			// ,salesman_date_join FROM salesman ORDER BY salesman_id DESC LIMIT
			// 1";
			// ResultSet rs = st.executeQuery(queryForSalesmanId);
			// String dueDate = "";
			// String currentDate = "";
			// if(rs.next()) {
			// id = rs.getInt(1);
			//
			// }
			// System.err.println("ID: " + id);
			// System.err.println("Query: " + queryForSalesmanId);
			//
			// if(id>0){
			// for (HashMap<String, String> mapChild : schildList) {
			//
			//
			// String query1 = "INSERT INTO family_details
			// (salesman_id,member_name,relation,phone_no,cnic) "
			// + "VALUES("
			// + id
			// + ",'"
			// + mapChild.get("memberName")
			// + "','"
			// + mapChild.get("relation")
			// + "','"
			// + mapChild.get("memberPhoneNo")
			// + "','"
			// + mapChild.get("memberCnic") + "');";
			//
			// st = con.createStatement();
			// st.executeUpdate(query1);
			// }
			// }else{
			//
			// con.rollback();
			// return false;
			// }
			con.commit();
			return true;
		} catch (Exception ex) {
			logger.error("", ex);
			err = false;
			System.err.println(ex.getMessage());
			err = false;

		}
		return err;
	}

	// This method is used to update VLE Information
	public static boolean updateVLE(HashMap<String, String> salesmap) {

		boolean err = true;
		try (Connection con = Connect.getConnection();) {

			con.setAutoCommit(false);
			// *******************Add Customer Open********************
			String query = "";
			if (salesmap.get("imageId").equals("0")) {
				query = "UPDATE salesman SET salesman_name = '"
						+ salesmap.get("firstName") + "', salesman_email = '"
						+ salesmap.get("email") + "'" + ", password = '"
						+ salesmap.get("password") + "', salesman_cnic = '"
						+ salesmap.get("cnic") + "', gender = '"
						+ salesmap.get("gender") + "'"
						+ ", salesman_basic_sallery = '"
						+ salesmap.get("salary") + "', salesman_phone_no = '"
						+ salesmap.get("phone1") + "'"
						+ ", salesman_address = '" + salesmap.get("address")
						+ "', date_of_birth = '" + salesmap.get("dateOfBirth")
						+ "',salesman_date_join = '"
						+ salesmap.get("joiningDate") + "'" + ", educated = '"
						+ salesmap.get("educated") + "', marrital_status = '"
						+ salesmap.get("marritalStatus") + "', blood_group = '"
						+ salesmap.get("bloodGroup") + "'" + ", licence_no = '"
						+ salesmap.get("licence") + "', mobile_no = '"
						+ salesmap.get("phone2") + "', degree = '"
						+ salesmap.get("degree") + "'" + ", institute = '"
						+ salesmap.get("college") + "', start_date = '"
						+ salesmap.get("dateOfStart") + "', end_date = '"
						+ salesmap.get("dateOfEnd") + "'" + ", percentage = '"
						+ salesmap.get("percentage") + "', vehicle = '"
						+ salesmap.get("vehicle") + "'"
						+ ", before_time_percentage = '"
						+ salesmap.get("beforeTime")
						+ "',on_time_percentage = '" + salesmap.get("onTime")
						+ "',after_time_percentage = '"
						+ salesmap.get("afterTime") + "'" + ",per_sale = "
						+ salesmap.get("percellcomm") + ",vle_acount_no = "
						+ salesmap.get("accnumber") + " WHERE salesman_id = "
						+ salesmap.get("vleId") + "";

			} else {
				query = "UPDATE salesman SET salesman_name = '"
						+ salesmap.get("firstName") + "', salesman_email = '"
						+ salesmap.get("email") + "'" + ", password = '"
						+ salesmap.get("password") + "', salesman_cnic = '"
						+ salesmap.get("cnic") + "', gender = '"
						+ salesmap.get("gender") + "'"
						+ ", salesman_basic_sallery = '"
						+ salesmap.get("salary") + "', salesman_phone_no = '"
						+ salesmap.get("phone1") + "'"
						+ ", salesman_address = '" + salesmap.get("address")
						+ "', date_of_birth = '" + salesmap.get("dateOfBirth")
						+ "',salesman_date_join = '"
						+ salesmap.get("joiningDate") + "'" + ", educated = '"
						+ salesmap.get("educated") + "', marrital_status = '"
						+ salesmap.get("marritalStatus") + "', blood_group = '"
						+ salesmap.get("bloodGroup") + "'" + ", licence_no = '"
						+ salesmap.get("licence") + "', mobile_no = '"
						+ salesmap.get("phone2") + "', degree = '"
						+ salesmap.get("degree") + "'" + ", institute = '"
						+ salesmap.get("college") + "', start_date = '"
						+ salesmap.get("dateOfStart") + "', end_date = '"
						+ salesmap.get("dateOfEnd") + "'" + ", percentage = '"
						+ salesmap.get("percentage") + "', vehicle = '"
						+ salesmap.get("vehicle") + "'"
						+ ", before_time_percentage = '"
						+ salesmap.get("beforeTime")
						+ "',on_time_percentage = '" + salesmap.get("onTime")
						+ "',after_time_percentage = "
						+ salesmap.get("afterTime") + "" + ", image_id = "
						+ salesmap.get("imageId") + ",per_sale = '"
						+ salesmap.get("percellcomm") + "',vle_acount_no = "
						+ salesmap.get("accnumber") + " WHERE salesman_id = "
						+ salesmap.get("vleId") + "";

			}

			System.out.println("Query : " + query);

			Statement st = con.createStatement();
			st.executeUpdate(query);

			con.commit();
			return true;
		} catch (Exception ex) {
			logger.error("", ex);
			err = false;
			System.err.println(ex.getMessage());
			err = false;

		}
		return err;
	} // update VLE info end

	public static int insertFieldOfficer(HashMap<String, String> fomap, int doId) {

		Statement st = null;
		int row = 0;
		try (Connection con = Connect.getConnection();) {
			con.setAutoCommit(false);
			String query = "";

			if (fomap.get("imageId1").equals("")) {
				query = "INSERT INTO field_officer(" + " do_id," + " fo_name,"
						+ " fo_priamary_phone," + " fo_secondary_phone,"
						+ " fo_gender," + " fo_date_of_birth," + " fo_cnic,"
						+ " fo_date_of_joining," + " fo_city,"
						+ " fo_blood_group," + " fo_marital_status,"
						+ " fo_vehical," + " fo_address," + " fo_educated,"
						+ " fo_college_university," + " fo_certificate,"
						+ " fo_education_start_date,"
						+ " fo_education_end_date,"
						+ " fo_education_percentage," + " fo_base_salary,"
						+ " fo_before_time," + " fo_after_time,"
						+ " fo_on_time," + " password,per_sale,fo_acount_no)"
						+ "Values('"
						+ doId
						+ "','"
						+ fomap.get("firstName")
						+ "','"
						+ fomap.get("phone1")
						+ "','"
						+ fomap.get("phone2")
						+ "','"
						+ fomap.get("gender")
						+ "','"
						+ fomap.get("dateOfBirth")
						+ "','"
						+ fomap.get("cnic")
						+ "','"
						+ fomap.get("joiningDate")
						+ "','"
						+ Integer.parseInt(fomap.get("cityId"))
						+ "','"
						+ fomap.get("bloodGroup")
						+ "','"
						+ fomap.get("marritalStatus")
						+ "','"
						+ fomap.get("vehicle")
						+ "','"
						+ fomap.get("address")
						+ "','"
						+ Boolean.parseBoolean(fomap.get("educated"))
						+ "','"
						+ fomap.get("college")
						+ "','"
						+ fomap.get("degree")
						+ "','"
						+ fomap.get("dateOfStart")
						+ "','"
						+ fomap.get("dateOfEnd")
						+ "','"
						+ fomap.get("percentage")
						+ "','"
						+ Double.parseDouble(fomap.get("salary"))
						+ "','"
						+ Integer.parseInt(fomap.get("beforeTime"))
						+ "','"
						+ Integer.parseInt(fomap.get("afterTime"))
						+ "','"
						+ Integer.parseInt(fomap.get("onTime"))
						+ "','"
						+ fomap.get("password")
						+ "','"
						+ fomap.get("percellcomm")
						+ "','"
						+ fomap.get("accnumber") + "')";

			} else {
				query = "INSERT INTO field_officer(" + " do_id," + " fo_name,"
						+ " fo_priamary_phone," + " fo_secondary_phone,"
						+ " fo_gender," + " fo_date_of_birth," + " fo_cnic,"
						+ " fo_date_of_joining," + " fo_city,"
						+ " fo_blood_group," + " fo_marital_status,"
						+ " fo_vehical," + " fo_address," + " fo_educated,"
						+ " fo_college_university," + " fo_certificate,"
						+ " fo_education_start_date,"
						+ " fo_education_end_date,"
						+ " fo_education_percentage," + " fo_base_salary,"
						+ " fo_before_time," + " fo_after_time,"
						+ " fo_on_time," + " password,"
						+ " image_id,per_sale,fo_acount_no)" + "Values('"
						+ doId
						+ "','"
						+ fomap.get("firstName")
						+ "','"
						+ fomap.get("phone1")
						+ "','"
						+ fomap.get("phone2")
						+ "','"
						+ fomap.get("gender")
						+ "','"
						+ fomap.get("dateOfBirth")
						+ "','"
						+ fomap.get("cnic")
						+ "','"
						+ fomap.get("joiningDate")
						+ "','"
						+ Integer.parseInt(fomap.get("cityId"))
						+ "','"
						+ fomap.get("bloodGroup")
						+ "','"
						+ fomap.get("marritalStatus")
						+ "','"
						+ fomap.get("vehicle")
						+ "','"
						+ fomap.get("address")
						+ "','"
						+ Boolean.parseBoolean(fomap.get("educated"))
						+ "','"
						+ fomap.get("college")
						+ "','"
						+ fomap.get("degree")
						+ "','"
						+ fomap.get("dateOfStart")
						+ "','"
						+ fomap.get("dateOfEnd")
						+ "','"
						+ fomap.get("percentage")
						+ "','"
						+ Double.parseDouble(fomap.get("salary"))
						+ "','"
						+ Integer.parseInt(fomap.get("beforeTime"))
						+ "','"
						+ Integer.parseInt(fomap.get("afterTime"))
						+ "','"
						+ Integer.parseInt(fomap.get("onTime"))
						+ "','"
						+ fomap.get("password")
						+ "',"
						+ fomap.get("imageId1")
						+ ",'"
						+ fomap.get("percellcomm")
						+ "','"
						+ fomap.get("accnumber") + "')";

			}

			System.out.println("Query :" + query);
			st = con.createStatement();
			row = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

			int foid = 0;
			try (ResultSet generatedKeys = st.getGeneratedKeys()) {

				if (generatedKeys.next()) {
					foid = generatedKeys.getInt(1);
					generatedKeys.close();
				} else {

					throw new SQLException("Creating fo failed, no ID obtained");
				}
			}
			PreparedStatement statement = null;
			if (foid > 0) {

				statement = con
						.prepareStatement("INSERT INTO user_finiance(total_amount,current_amount,defaulter_amount,onhold_status,onhold_amount,user_id,user_type) VALUES(?,?,?,?,?,?,?)");
				statement.setInt(1, 0);
				statement.setInt(2, 0);
				statement.setInt(3, 0);
				statement.setInt(4, 0);
				statement.setInt(5, 0);
				statement.setInt(6, foid);
				statement.setInt(7, 100);
				statement.executeUpdate();

			}

			if (row > 0) {
				System.err.println("record has been added");
			} else {
				System.err.println("data not added");
			}
			// System.out.println("Query : " + query);

			con.commit();
			return row;
		} catch (Exception ex) {
			logger.error("", ex);
			row = 0;
			System.err.println(ex.getMessage());
			row = 0;

		}
		return row;
	}

	// This method is used to update FO
	public static int updateFo(HashMap<String, String> fomap) {

		Statement st = null;
		int row = 0;
		try (Connection con = Connect.getConnection();) {
			con.setAutoCommit(false);
			String query = "";
			if (fomap.get("imageId1").equals("0")) {
				query = "UPDATE field_officer SET fo_name = '"
						+ fomap.get("firstName") + "', fo_priamary_phone = '"
						+ fomap.get("phone1") + "'"
						+ ", fo_secondary_phone = '" + fomap.get("phone2")
						+ "', fo_gender = '" + fomap.get("gender")
						+ "', fo_date_of_birth = '" + fomap.get("dateOfBirth")
						+ "'" + ", fo_cnic = '" + fomap.get("cnic")
						+ "', fo_date_of_joining = '"
						+ fomap.get("joiningDate") + "'"
						+ ", fo_blood_group = '" + fomap.get("bloodGroup")
						+ "', fo_marital_status = '"
						+ fomap.get("marritalStatus") + "', fo_vehical = '"
						+ fomap.get("vehicle") + "'" + ", fo_address = '"
						+ fomap.get("address") + "', fo_educated = '"
						+ fomap.get("educated")
						+ "', fo_college_university = '" + fomap.get("college")
						+ "'" + ", fo_certificate = '" + fomap.get("degree")
						+ "', fo_education_start_date = '"
						+ fomap.get("dateOfStart")
						+ "', fo_education_end_date = '"
						+ fomap.get("dateOfEnd") + "'"
						+ ", fo_education_percentage = '"
						+ fomap.get("percentage") + "', fo_base_salary = '"
						+ Double.parseDouble(fomap.get("salary")) + "'"
						+ ", fo_before_time = '"
						+ Integer.parseInt(fomap.get("beforeTime"))
						+ "', fo_after_time = '"
						+ Integer.parseInt(fomap.get("afterTime")) + "'"
						+ ", fo_on_time = '"
						+ Integer.parseInt(fomap.get("onTime"))
						+ "', password = " + fomap.get("password")
						+ ", per_sale = '" + fomap.get("percellcomm")
						+ "', fo_acount_no = " + fomap.get("accnumber")
						+ " WHERE fo_id = " + fomap.get("foId") + "";

			} else {
				query = "UPDATE field_officer SET fo_name = '"
						+ fomap.get("firstName") + "', fo_priamary_phone = '"
						+ fomap.get("phone1") + "'"
						+ ", fo_secondary_phone = '" + fomap.get("phone2")
						+ "', fo_gender = '" + fomap.get("gender")
						+ "', fo_date_of_birth = '" + fomap.get("dateOfBirth")
						+ "'" + ", fo_cnic = '" + fomap.get("cnic")
						+ "', fo_date_of_joining = '"
						+ fomap.get("joiningDate") + "'"
						+ ", fo_blood_group = '" + fomap.get("bloodGroup")
						+ "', fo_marital_status = '"
						+ fomap.get("marritalStatus") + "', fo_vehical = '"
						+ fomap.get("vehicle") + "'" + ", fo_address = '"
						+ fomap.get("address") + "', fo_educated = '"
						+ fomap.get("educated")
						+ "', fo_college_university = '" + fomap.get("college")
						+ "'" + ", fo_certificate = '" + fomap.get("degree")
						+ "', fo_education_start_date = '"
						+ fomap.get("dateOfStart")
						+ "', fo_education_end_date = '"
						+ fomap.get("dateOfEnd") + "'"
						+ ", fo_education_percentage = '"
						+ fomap.get("percentage") + "', fo_base_salary = '"
						+ Double.parseDouble(fomap.get("salary")) + "'"
						+ ", fo_before_time = '"
						+ Integer.parseInt(fomap.get("beforeTime"))
						+ "', fo_after_time = "
						+ Integer.parseInt(fomap.get("afterTime")) + ""
						+ ", fo_on_time = '"
						+ Integer.parseInt(fomap.get("onTime"))
						+ "', password = '" + fomap.get("password")
						+ "', image_id = " + fomap.get("imageId1")
						+ ", per_sale = '" + fomap.get("percellcomm")
						+ "', fo_acount_no = " + fomap.get("accnumber")
						+ " WHERE fo_id = " + fomap.get("foId") + "";

			}

			System.out.println("Query :" + query);
			st = con.createStatement();
			row = st.executeUpdate(query);
			con.commit();
			return row;
		} catch (Exception ex) {
			logger.error("", ex);
			row = 0;
			System.err.println(ex.getMessage());
			row = 0;

		}
		return row;
	} // end update fo method

	// Ghulam Yaseen shar
	public static int updateSalemanLocation(int salemanId, double saleLat,
			double saleLng) {

		int row = 0;

		try (Connection con = Connect.getConnection();) {

			CallableStatement cst = con
					.prepareCall("CALL update_saleman_location(?, ?, ?)");
			cst.setInt(1, salemanId);
			cst.setDouble(2, saleLat);
			cst.setDouble(3, saleLng);

			row = cst.executeUpdate();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return row;
	}// Ghulam Yaseen shar

	// created by Junaid Ali
	public static ArrayList<SuperAdminSalesmanRecoveriesBean> getSuperAdminSalesmansRecoveriesTotal(
			String dateSet) {
		// HashMap<String, String> map = null;
		// HashMap<String, String> map2 = null;
		System.out.println("Date : " + dateSet);
		int paidCached;
		int remainCached;
		int paidAmountCached;
		int remainingAmountCached;
		String dateCached;
		ArrayList<SuperAdminSalesmanRecoveriesBean> list = new ArrayList<>();
		ArrayList<SuperAdminSalesmanRecoveriesBean> list2 = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {

			call = con
					.prepareCall("{CALL get_super_admin_salesmans_recoveries_dates(?)}");
			call.setString(1, dateSet);
			rs = call.executeQuery();

			while (rs.next()) {
				SuperAdminSalesmanRecoveriesBean bean = new SuperAdminSalesmanRecoveriesBean();
				bean.setRemainingRecoveries(rs.getInt(2));
				bean.setPaidRecoveries(rs.getInt(3));
				bean.setDate(rs.getDate(1) + "");
				bean.setPaidAmount(rs.getInt(4));
				bean.setRemainingAmount(rs.getInt(5));
				// System.out.println("+++++++++++++++++++++"+bean.getPaidRecoveries());
				// map2.put("remainingRecoveries",
				// rs.getInt("remaining_recovery")+"");
				// map2.put("getDate", rs.getDate("Date")+"");
				// map2.put("paidRecoveries", rs.getInt("paid_recovery")+"");

				list2.add(bean);
			}

			call = con
					.prepareCall("{CALL get_super_admin_salesmans_recoveries()}");
			rs = call.executeQuery();

			while (rs.next()) {
				// map = new HashMap<>();
				SuperAdminSalesmanRecoveriesBean bean2 = new SuperAdminSalesmanRecoveriesBean();
				bean2.setRemainingRecoveries(rs.getInt(3));
				bean2.setPaidRecoveries(rs.getInt(2));
				bean2.setDate(rs.getDate(1) + "");
				bean2.setPaidAmount(rs.getInt(4));
				bean2.setRemainingAmount(rs.getInt(5));
				// map.put("remainingRecoveries",
				// rs.getInt("remaining_recovery")+"");
				// map.put("getDate", rs.getDate("due_date")+"");
				// map.put("paidRecoveries", rs.getInt("paid_recovery")+"");

				list.add(bean2);
			}

			for (int i = 0; i < list2.size(); i++) {
				dateCached = list2.get(i).getDate();
				// System.out.println(dateCached);
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).getDate().equalsIgnoreCase(dateCached)) {
						// System.out.println(list.get(j).get("remainingRecoveries"));
						paidCached = list.get(j).getPaidRecoveries();
						remainCached = list.get(j).getRemainingRecoveries();
						paidAmountCached = list.get(j).getPaidAmount();
						remainingAmountCached = list.get(j)
								.getRemainingAmount();
						// list2.get(i).put("remainingRecoveries",
						// list.get(j).get("remainingRecoveries"));
						// list2.get(i).put("paidRecoveries",list.get(j).get("paidRecoveries"));
						list2.get(i).setPaidRecoveries(paidCached);
						list2.get(i).setRemainingRecoveries(remainCached);
						list2.get(i).setPaidAmount(paidAmountCached);
						list2.get(i).setRemainingAmount(remainingAmountCached);
						break;
					}
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list2;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getCurrentMonthlDates() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_monthly_dates_for_salesman_recovery()}");
			rs = call.executeQuery();

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("date", rs.getString("get_date"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getToltaYearsOfSalesmans() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_years_of_salesmans_dates()}");
			rs = call.executeQuery();

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("date", rs.getString("get_date"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoSalesmanList(
			int start, int length, String order, int column, int doId, int foId) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_salesman_table_list(?,?,?,?,?,?)}");
			if (call != null) {
				call.setInt(1, start);
				call.setInt(2, length);
				call.setString(3, order);
				call.setInt(4, column);
				call.setInt(5, doId);
				call.setInt(6, foId);
				rs = call.executeQuery();
				while (rs.next()) {
					map = new HashMap<>();
					map.put("salesmanId", rs.getInt("salesman_id") + "");
					map.put("salesmanName", rs.getString("salesman_name"));
					map.put("salesmanCnic", rs.getString("salesman_cnic") + "");
					map.put("salesmanAddress", rs.getString("salesman_address"));

					String number2 = UserBAL.getFormattedPhoneNumber(rs
							.getString("salesman_phone_no"));

					map.put("salesmanNumber", number2);
					map.put("salesmanSallery",
							(NumberFormat.getNumberInstance(Locale.US).format((rs
									.getDouble("salesman_basic_sallery"))))
									+ "");
					map.put("salesmanDateJoin",
							rs.getDate("salesman_date_join") + "");
					map.put("lateCustomer", rs.getInt("late_cs") + "");
					map.put("totalSales", rs.getInt("total_sales") + "");
					map.put("salesmanCommission",
							rs.getInt("salesman_commission") + "");
					map.put("commissionDate", rs.getInt("commission_date") + "");
					map.put("totalCustomers", rs.getInt("total_customers") + "");

					list.add(map);
				}

			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	// created by Junaid Ali
	public static int getDoSalesmanListCount(String order, int column,
			int doId, int foId) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {

			call = con
					.prepareCall("{CALL get_do_salesman_table_list_count(?,?,?,?)}");
			call.setString(1, order);
			call.setInt(2, column);
			call.setInt(3, doId);
			call.setInt(4, foId);
			rs = call.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getDoSalesmanListSearch(
			int start, int length, String order, int column, int doId,
			int foId, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		HashMap<String, String> map = new HashMap<>();

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_salesman_table_list_search(?,?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			call.setInt(5, doId);
			call.setInt(6, foId);
			call.setString(7, search);

			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanCnic", rs.getString("salesman_cnic") + "");
				map.put("salesmanAddress", rs.getString("salesman_address"));

				String number2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("salesman_phone_no"));

				/*
				 * String number = rs.getString("salesman_phone_no"); String
				 * concat = "+"+number; String number2 = String.format(
				 * "(%s) %s-%s", concat.substring(0, 3), concat.substring(3, 6),
				 * concat.substring(6, 13));
				 */

				map.put("salesmanNumber", number2);
				map.put("salesmanSallery",
						(NumberFormat.getNumberInstance(Locale.US).format((rs
								.getDouble("salesman_basic_sallery")))) + "");
				map.put("salesmanDateJoin", rs.getDate("salesman_date_join")
						+ "");
				map.put("lateCustomer", rs.getInt("late_cs") + "");
				map.put("totalSales", rs.getInt("total_sales") + "");
				map.put("salesmanCommission", rs.getInt("salesman_commission")
						+ "");
				map.put("commissionDate", rs.getInt("commission_date") + "");
				map.put("totalCustomers", rs.getInt("total_customers") + "");

				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	// created by Junaid Ali
	public static int getDoSalesmanListSearchCount(String order, int column,
			int doId, int foId, String search) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_salesman_table_list_search_count(?,?,?,?,?)}");
			call.setString(1, order);
			call.setInt(2, column);
			call.setInt(3, doId);
			call.setInt(4, foId);
			call.setString(5, search);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getDoCustomersWithSalesmanId(
			int start, int length, int column, String orderBy, int id) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {

			if (orderBy.equalsIgnoreCase("asc")) {

				switch (column) {
				case 0:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_name_asc(?,?,?)");
					break;
				case 1:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_father_name_asc(?,?,?)");
					break;
				case 2:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_mother_name_asc(?,?,?)");
					break;
				case 3:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_cnic_asc(?,?,?)");
					break;
				case 4:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_email_asc(?,?,?)");
					break;
				case 5:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_address_asc(?,?,?)");
					break;
				case 6:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_date_asc(?,?,?)");
					break;
				case 7:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_customer_number_asc(?,?,?)");
					break;
				}

			} else if (orderBy.equalsIgnoreCase("desc")) {

				switch (column) {
				case 0:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_name_desc(?,?,?)");
					break;
				case 1:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_father_name_desc(?,?,?)");
					break;
				case 2:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_mother_name_desc(?,?,?)");
					break;
				case 3:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_cnic_desc(?,?,?)");
					break;
				case 4:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_email_desc(?,?,?)");
					break;
				case 5:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_address_desc(?,?,?)");
					break;
				case 6:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_date_desc(?,?,?)");
					break;
				case 7:
					call = con
							.prepareCall("CALL get_do_customers_with_salesman_id_orderby_customer_number_desc(?,?,?)");
					break;
				}

			}

			call.setInt(1, start);
			call.setInt(2, length);
			call.setInt(3, id);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("customerId", rs.getInt("customer_id") + "");
				map.put("customerName", rs.getString("customer_name"));
				map.put("customerCnic", rs.getString("customer_cnic"));
				map.put("customerDateOfBirth", rs.getDate("date_of_birth") + "");
				map.put("customerAddress", rs.getString("customer_address"));
				map.put("customerCity", rs.getInt("customer_city") + "");
				map.put("customerPhone", rs.getString("customer_phone"));
				map.put("customerPhone2", rs.getString("customer_phone2"));
				map.put("customerImage", rs.getBlob("customr_image") + "");
				map.put("customerFatherName", rs.getString("father_name"));
				map.put("customerMotherName", rs.getString("mother_name"));
				/* map.put("customerEmail", rs.getString("email")); */
				map.put("customerGender", rs.getString("gender"));
				map.put("customerAge", rs.getInt("age") + "");
				map.put("customerCreated", rs.getString("created_on"));
				map.put("customerOccupation", rs.getString("occupation"));

				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getDoCustomersWithSalesmanIdSearch(
			int start, int length, String search, int id) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_customers_with_salesman_id_search(?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, search);
			call.setInt(4, id);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("customerId", rs.getInt("customer_id") + "");
				map.put("customerName", rs.getString("customer_name"));
				map.put("customerCnic", rs.getString("customer_cnic"));
				map.put("customerDateOfBirth", rs.getDate("date_of_birth") + "");
				map.put("customerAddress", rs.getString("customer_address"));
				map.put("customerCity", rs.getInt("customer_city") + "");
				map.put("customerPhone", rs.getString("customer_phone"));
				map.put("customerPhone2", rs.getString("customer_phone2"));
				map.put("customerImage", rs.getBlob("customr_image") + "");
				map.put("customerFatherName", rs.getString("father_name"));
				map.put("customerMotherName", rs.getString("mother_name"));
				/* map.put("customerEmail", rs.getString("email")); */
				map.put("customerGender", rs.getString("gender"));
				map.put("customerAge", rs.getInt("age") + "");
				map.put("customerCreated", rs.getString("created_on"));
				map.put("customerOccupation", rs.getString("occupation"));

				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getDoDeploymentList(
			int start, int length, String dir, int column, int districtId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_do_deployment_list(?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);

			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {

					String number2 = rs.getString("imei_number");

					/*
					 * String number = rs.getString("appliance_GSMno"); String
					 * concat = "+"+number; String number2 = String.format(
					 * "(%s) %s-%s", concat.substring(0, 3), concat.substring(3,
					 * 6), concat.substring(6, 13));
					 */

					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("appliancePrice", rs.getDouble("appliance_price") + "");
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				/*
				 * String customerNumber = rs.getString("customer_phone");
				 * String customerConcat = "+"+customerNumber; String
				 * customerNumber2 = String.format("(%s) %s-%s",
				 * customerConcat.substring(0, 3), customerConcat.substring(3,
				 * 6), customerConcat.substring(6, 13));
				 */

				map.put("customerNumber", customerNumber2);
				map.put("customerAddress", rs.getString("customer_address"));
				/* map.put("customerEmail", rs.getString("email")); */
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("loanStatus", rs.getString("loan_status"));
				map.put("defaulted", rs.getInt("defaulted") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	// created by Junaid Ali
	public static int getDoCustomersWithSalesmanIdSearchCount(String search,
			int sid) {
		int count = 0;
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL get_do_customers_with_salesman_id_search_count(?,?)");
			call.setString(1, search);
			call.setInt(2, sid);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	// created by Junaid Ali
	public static int getDoCustomersWithSalesmanIdCount(int sid) {
		int count = 0;
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL get_do_customers_with_salesman_id_count(?)");
			call.setInt(1, sid);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// created by Junaid Ali
	public static int getDoDeploymentListCount(String order, int column,
			int districtId) {
		int count = 0;
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_do_deployment_list_count(?,?,?)");
			call.setString(1, order);
			call.setInt(2, column);
			call.setInt(3, districtId);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	// created by Junaid Ali
	public static ArrayList<HashMap<String, String>> getDoDeploymentListSearch(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_deployment_list_search(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {

					String number2 = rs.getString("imei_number");
					/*
					 * String number = rs.getString("appliance_GSMno"); String
					 * concat = "+"+number; String number2 = String.format(
					 * "(%s) %s-%s", concat.substring(0, 3), concat.substring(3,
					 * 6), concat.substring(6, 13));
					 */

					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("appliancePrice", rs.getDouble("appliance_price") + "");
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				/*
				 * String customerNumber = rs.getString("customer_phone");
				 * String customerConcat = "+"+customerNumber; String
				 * customerNumber2 = String.format("(%s) %s-%s",
				 * customerConcat.substring(0, 3), customerConcat.substring(3,
				 * 6), customerConcat.substring(6, 13));
				 */

				map.put("customerNumber", customerNumber2);
				map.put("customerAddress", rs.getString("customer_address"));
				/* map.put("customerEmail", rs.getString("email")); */
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("loanStatus", rs.getString("loan_status"));
				map.put("defaulted", rs.getInt("defaulted") + "");
				map.put("customerid", rs.getInt("customer_id") + "");

				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	// created by Junaid Ali
	public static int getDoDeploymentListSearchCount(String order, int column,
			int districtId, String search) {
		int count = 0;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL get_do_deployment_list_search_count(?,?,?,?)");
			call.setString(1, order);
			call.setInt(2, column);
			call.setInt(3, districtId);
			call.setString(4, search);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getSalesmanAcceptedRequest(
			int salesmanId) {
		System.out.println("SalesmanBAL.getSalesmanAcceptedRequest()");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_salesman_accepted_request(?)}");
			prepareCall.setInt(1, salesmanId);
			ResultSet resultSet = prepareCall.executeQuery();

			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getSalesmanListByIdWithOrder(
			int start, int length, int doid, int order, String dir,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		ResultSet rs;
		CallableStatement call = null;
		PrettyTime p = new PrettyTime();
		try (Connection con = Connect.getConnection()) {

			// ascending order
			call = con
					.prepareCall("{Call get_all_field_officer_by_doid_in_order_search(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setInt(5, doid);
			call.setInt(4, order);
			call.setString(3, dir);
			call.setString(6, search);
			rs = call.executeQuery();
			if (call != null) {

				call.setInt(1, start);
				call.setInt(2, length);
				rs = call.executeQuery();
				while (rs.next()) {

					HashMap<String, String> map = new HashMap<>();
					map.put("salesmanId", rs.getInt("salesman_id") + "");
					map.put("salesmanName", rs.getString("salesman_name"));
					String number2 = UserBAL.getFormattedPhoneNumber(rs
							.getString("salesman_phone_no"));
					map.put("salesmanNumber", number2);
					map.put("foName", rs.getString("fo_name") + "");
					map.put("total_apps", rs.getString("total_apps") + "");
					map.put("total_installs", rs.getString("total_installs")
							+ "");
					map.put("recovery",
							(NumberFormat.getNumberInstance(Locale.US)
									.format((rs.getDouble("recovery")))) + "");
					// map.put("last_sale", rs.getString("last_sale") + "");
					map.put("monthly_installs", rs.getInt("monthly_installs")
							+ "");
					map.put("weekly_installs", rs.getInt("weekly_installs")
							+ "");
					map.put("monthly_apps", rs.getInt("monthly_apps") + "");
					map.put("weekly_apps", rs.getInt("weekly_apps") + "");
					// map.put("salesman_status",
					// rs.getString("salesman_status") + "");
					int count = rs.getInt("salesman_status");
					if (count > 0) {
						map.put("salesman_status", "Active");
					} else {
						map.put("salesman_status", "Inactive");
					}
					if (rs.getString("last_sale") != null) {
						Timestamp dateTime = rs.getTimestamp("last_sale");
						Date date = new Date(dateTime.getTime());
						map.put("last_sale", p.format(date));
					} else {
						map.put("last_sale", "No Sale");
					}
					list.add(map);
					System.out.println(list);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int countSalesmansById(int doid) {

		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_salesmans_count_by_doid(?)}");
			prepareCall.setInt(1, doid);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getSalesmanListWithSearchById(
			int str, int rng, int doid, String value) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		CallableStatement call = null;
		ResultSet rs;

		try (Connection connection = Connect.getConnection()) {
			call = connection
					.prepareCall("{Call get_appliance_by_search_in_salesman_by_doid(?,?,?,?)}");
			call.setInt(1, str);
			call.setInt(2, rng);
			call.setInt(3, doid);
			call.setString(4, value);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanAddress", rs.getString("salesman_address"));

				String number2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("salesman_phone_no"));

				/*
				 * String number = rs.getString("salesman_phone_no"); String
				 * concat = "+"+number; String number2 = String.format(
				 * "(%s) %s-%s", concat.substring(0, 3), concat.substring(3, 6),
				 * concat.substring(6, 13));
				 */

				map.put("salesmanNumber", number2);
				map.put("salesmanSalary",
						rs.getDouble("salesman_basic_sallery") + "");
				map.put("salesmanJoiningDate", rs.getDate("salesman_date_join")
						+ "");
				map.put("salesmanDistrict", rs.getString("district_name"));
				map.put("doName", rs.getString("user_name"));
				map.put("foname", rs.getString("fo_name"));
				map.put("totalCustomers", rs.getInt("count_customer") + "");
				map.put("totalSales", rs.getInt("count_sales") + "");

				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getSalesmanSearchCountById(int doid, String value) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_appliance_by_search_count_in_salesman_by_doid(?,?)}");
			call.setInt(1, doid);
			call.setString(2, value);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static HashMap<String, Integer> countDoPipelineFilters(int doId) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_do_pipeline`(?)}");
			prepareCall.setInt(1, doId);
			ResultSet rs = prepareCall.executeQuery();

			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("readyToAssign", rs.getInt(2));
				map.put("awaitingDownpayment", rs.getInt(3));
				map.put("installed", rs.getInt(4));
				map.put("returned", rs.getInt(5));
				map.put("rdp", rs.getInt(6));
				map.put("installedNoSignal", rs.getInt(7));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, Integer> countDoPipelineFiltersWithSearch(
			int doId, String search) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_do_pipeline_search`(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			ResultSet rs = prepareCall.executeQuery();

			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("readyToAssign", rs.getInt(2));
				map.put("awaitingDownpayment", rs.getInt(3));
				map.put("installed", rs.getInt(4));
				map.put("returned", rs.getInt(5));
				map.put("rdp", rs.getInt(6));
				map.put("installedNoSignal", rs.getInt(7));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListPending(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_deployment_listPending(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {

					String number2 = rs.getString("imei_number");

					/*
					 * String number = rs.getString("appliance_GSMno"); String
					 * concat = "+"+number; String number2 = String.format(
					 * "(%s) %s-%s", concat.substring(0, 3), concat.substring(3,
					 * 6), concat.substring(6, 13));
					 */

					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("appliancePrice", rs.getDouble("appliance_price") + "");
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				/*
				 * String customerNumber = rs.getString("customer_phone");
				 * String customerConcat = "+"+customerNumber; String
				 * customerNumber2 = String.format("(%s) %s-%s",
				 * customerConcat.substring(0, 3), customerConcat.substring(3,
				 * 6), customerConcat.substring(6, 13));
				 */

				map.put("customerNumber", customerNumber2);
				map.put("customerAddress", rs.getString("customer_address"));
				/* map.put("customerEmail", rs.getString("email")); */
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("loanStatus", rs.getString("loan_status"));
				map.put("defaulted", rs.getInt("defaulted") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListRtoAssign(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_deployment_list_r_to_a(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {

					String number2 = rs.getString("imei_number");

					/*
					 * String number = rs.getString("appliance_GSMno"); String
					 * concat = "+"+number; String number2 = String.format(
					 * "(%s) %s-%s", concat.substring(0, 3), concat.substring(3,
					 * 6), concat.substring(6, 13));
					 */

					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("appliancePrice", rs.getDouble("appliance_price") + "");
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				/*
				 * String customerNumber = rs.getString("customer_phone");
				 * String customerConcat = "+"+customerNumber; String
				 * customerNumber2 = String.format("(%s) %s-%s",
				 * customerConcat.substring(0, 3), customerConcat.substring(3,
				 * 6), customerConcat.substring(6, 13));
				 */

				map.put("customerNumber", customerNumber2);
				map.put("customerAddress", rs.getString("customer_address"));
				/* map.put("customerEmail", rs.getString("email")); */
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("loanStatus", rs.getString("loan_status"));
				map.put("defaulted", rs.getInt("defaulted") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListAdp(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_deployment_list_adp(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {

					String number2 = rs.getString("imei_number");

					/*
					 * String number = rs.getString("appliance_GSMno"); String
					 * concat = "+"+number; String number2 = String.format(
					 * "(%s) %s-%s", concat.substring(0, 3), concat.substring(3,
					 * 6), concat.substring(6, 13));
					 */

					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("appliancePrice", rs.getDouble("appliance_price") + "");
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				/*
				 * String customerNumber = rs.getString("customer_phone");
				 * String customerConcat = "+"+customerNumber; String
				 * customerNumber2 = String.format("(%s) %s-%s",
				 * customerConcat.substring(0, 3), customerConcat.substring(3,
				 * 6), customerConcat.substring(6, 13));
				 */

				map.put("customerNumber", customerNumber2);
				map.put("customerAddress", rs.getString("customer_address"));
				/* map.put("customerEmail", rs.getString("email")); */
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("loanStatus", rs.getString("loan_status"));
				map.put("defaulted", rs.getInt("defaulted") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListInstalled(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_deployment_list_installed(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("appliancePrice", rs.getDouble("appliance_price") + "");
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				map.put("customerNumber", customerNumber2);
				map.put("customerAddress", rs.getString("customer_address"));
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("loanStatus", rs.getString("loan_status"));
				map.put("defaulted", rs.getInt("defaulted") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getNdsSales(String from,
			String to, int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_nds_sales(?,?,?,?,?,?,?)}");
			call.setString(1, from);
			call.setString(2, to);
			call.setInt(3, str);
			call.setInt(4, rng);
			call.setInt(5, col);
			call.setString(6, orde);
			call.setString(7, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("NdName", rs.getString("salesman_name") + "");
				map.put("districtName", rs.getString("district_name"));
				map.put("sales", rs.getInt("sales") + "");
				map.put("total_sales", rs.getInt("total_sales") + "");
				map.put("recovery", rs.getInt("recovery") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDosSalesHistory(
			String from, String to, int str, int rng, int col, String orde,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_dos_sales_history(?,?,?,?,?,?,?)}");
			call.setString(1, from);
			call.setString(2, to);
			call.setInt(3, str);
			call.setInt(4, rng);
			call.setInt(5, col);
			call.setString(6, orde);
			call.setString(7, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("doName", rs.getString("user_name") + "");
				map.put("districtName", rs.getString("district_name"));
				map.put("sales", rs.getInt("sales") + "");
				map.put("total_sales", rs.getInt("total_sales") + "");
				map.put("recovery", rs.getInt("recovery") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getFosSales(String from,
			String to, int str, int rng, int col, String orde, String search) {
		System.out.println(to + " " + from);
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_fos_sales(?,?,?,?,?,?,?)}");
			call.setString(1, from);
			call.setString(2, to);
			call.setInt(3, str);
			call.setInt(4, rng);
			call.setInt(5, col);
			call.setString(6, orde);
			call.setString(7, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("foName", rs.getString("fo_name") + "");
				map.put("districtName", rs.getString("district_name"));
				map.put("sales", rs.getInt("sales") + "");
				map.put("total_sales", rs.getInt("total_sales") + "");
				map.put("recovery", rs.getInt("recovery") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int countFosSales(String search) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_fos_sales`(?)}");
			prepareCall.setString(1, search);
			// prepareCall.setString(2, from);
			// prepareCall.setString(3, to);
			ResultSet rs = prepareCall.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	} //

	public static int countNdsSales(String search) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_nds_sales`(?)}");
			prepareCall.setString(1, search);
			// prepareCall.setString(2, from);
			// prepareCall.setString(3, to);
			ResultSet rs = prepareCall.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	} //

	public static int countDosSales(String from, String to, String search) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_dos_sales`(?,?,?)}");
			prepareCall.setString(1, search);
			prepareCall.setString(2, from);
			prepareCall.setString(3, to);
			ResultSet rs = prepareCall.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	} //

	public static ArrayList<HashMap<String, String>> getTopMonth(int month) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_top_fooos(?)}");
			call.setInt(1, month);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("NdName", rs.getString("salesman_name") + "");
				map.put("districtName", rs.getString("district_name"));
				map.put("monthName", rs.getString("Month_Name") + "");
				map.put("sales", rs.getInt("sales") + "");
				System.out.println(map);
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListReturned(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_deployment_list_returned(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("appliancePrice", rs.getDouble("appliance_price") + "");
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				map.put("customerNumber", customerNumber2);
				map.put("customerAddress", rs.getString("customer_address"));
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("loanStatus", rs.getString("loan_status"));
				map.put("defaulted", rs.getInt("defaulted") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListRdp(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_deployment_list_rdp(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("appliancePrice", rs.getDouble("appliance_price") + "");
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				map.put("customerNumber", customerNumber2);
				map.put("customerAddress", rs.getString("customer_address"));
				map.put("eStatusAppliedRejected",rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("loanStatus", rs.getString("loan_status"));
				map.put("defaulted", rs.getInt("defaulted") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListInstalledNoSignal(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_deployment_list_installed_ns(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("appliancePrice", rs.getDouble("appliance_price") + "");
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = UserBAL.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				map.put("customerNumber", customerNumber2);
				map.put("customerAddress", rs.getString("customer_address"));
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("loanStatus", rs.getString("loan_status"));
				map.put("defaulted", rs.getInt("defaulted") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	

		public static void main(String arg[]) throws ParseException {
			System.out.print(countDoPipelineFiltersWithSearch(107, ""));
		}

		


}
