package schedule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.json.JSONException;

import connection.Connect;
import bal.ApplianceBAL;
import bal.ApplianceStatusBAL;
import bal.CallingXML;
import bal.telenor;

public class TriggerModule extends Thread {

	private final static Logger logger = Logger.getLogger(TriggerModule.class);

	public void run() {
		try {
			while (true) {
				DateFormat df = new SimpleDateFormat("HH:mm");
				Date dateobj = new Date();
				if (df.format(dateobj).equals("07:01")) {
//					sendMessageNotices();
//					sendWellWishes();
//					checkNotIntrustedCustomerToVarCust();
//					checkNotIntrustedCustomerToAcceptedCust();
//					isLive();
				}
				Thread.sleep(60000);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public static void checkNotIntrustedCustomerToVarCust() {
		System.err.println("checkNotIntrustedCustomerToAcceptedCust");
		ArrayList<HashMap<String, String>> list = ScheduleBAL
				.getVerifiedCustomers();
		for (HashMap<String, String> hashMap : list) {
			int remaining = Integer.parseInt(hashMap.get("remaining"));
			System.err.println("in method VERIFY and remainig= " + remaining);
			if (remaining == 0) {
				try {
					int appid = Integer.parseInt(hashMap.get("appliance_id"));
					int csId = Integer.parseInt(hashMap.get("customer_id"));
					ApplianceBAL.revokeByThread(appid, csId);
					ScheduleBAL.updateStatus(hashMap.get("appliance_id"), 7);
					ScheduleBAL.deleteSoldToByApplianceId(hashMap
							.get("appliance_id"));
					ScheduleBAL.deleteApplianceByApplianceId(hashMap
							.get("appliance_id"));
				} catch (SQLException e) {
					logger.info(e);
					e.printStackTrace();
				}
				System.err.println(hashMap.get("customer_name")
						+ "  now Not Interested");
			}
		}
	}

	public static void checkNotIntrustedCustomerToAcceptedCust() {
		System.err.println("checkNotIntrustedCustomerToAcceptedCust");
		ArrayList<HashMap<String, String>> list = ScheduleBAL
				.getAcceptedCustomers();
		for (HashMap<String, String> hashMap : list) {
			int remaining = Integer.parseInt(hashMap.get("remaining"));
			if (remaining == 0) {
				int appid = Integer.parseInt(hashMap.get("appliance_id"));
				int csId = Integer.parseInt(hashMap.get("customer_id"));
				try {
					ScheduleBAL.updateStatus(hashMap.get("appliance_id"), 1);
					ApplianceBAL.revokeByThread(appid, csId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.err.println(hashMap.get("customer_name")
						+ "  now Not Interested");
			}
		}
	}

	public static void sendMessageNotices() {
		ArrayList<HashMap<String, String>> list = null;
		try {
			list = ReminderUtility.sendDueDateReminders();
		} catch (Exception e) {
			logger.error(e);
		}
		for (int i = 0; i < list.size(); i++) {
			int days = Integer.parseInt(list.get(i).get("days"));
			String phoneNo = list.get(i).get("customerPhone");
			String appName = list.get(i).get("applianceName");
			if (days == 10) {
				String str = "Moaziz Saarif: aap ko Rs."
						+ list.get(i).get("monthlyInstallment")
						+ " ki agli mahaana adaaigi "
						+ days
						+ " dinoon ma karni hai. Aap ka Consumer number "
						+ list.get(i).get("imei_number")
						+ ", brahay meharbani is pe "
						+ "apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya.";
				try {
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}

			} else if (days == 7) {
				String str = "Moaziz Saarif: aap ko Rs."
						+ list.get(i).get("monthlyInstallment")
						+ " ki agli mahaana adaaigi "
						+ days
						+ " dinoon ma karni hai. Aap  ka Consumer number "
						+ list.get(i).get("imei_number")
						+ ", brahay meharbani is pe "
						+ "apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya.";
				try {
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}

			} else if (days == 4) {
				String str = "Moaziz Saarif: aap ko Rs."
						+ list.get(i).get("monthlyInstallment")
						+ " ki agli mahaana adaaigi "
						+ days
						+ " dinoon ma karni hai. Aap ka Consumer number "
						+ list.get(i).get("imei_number")
						+ ", brahay meharbani is pe "
						+ "apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya.";
				try {
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}

			} else if (days >= 0 && days <= 2) {
				String str = "Moaziz Saarif aap ko Rs."
						+ list.get(i).get("monthlyInstallment")
						+ " ki agli mahaana adaaigi"
						+ days
						+ " dinoon ma karni hai. Aap ka Consumer number "
						+ list.get(i).get("imei_number")
						+ ", brahay meharbani is pe "
						+ "apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya.";
				try {
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}

			} else if (days == -1) {
				String str = "Aap ki adaaigi ki tareekh guzar chuki hai. Brahay meharbaani Rs."
						+ list.get(i).get("monthlyInstallment")
						+ " ki "
						+ "foran adaaigi kar dein. Aap ka Consumer number hai "
						+ list.get(i).get("imei_number")
						+ ", brahay meharbani is pe "
						+ "apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya";
				try {
					if (appName != "60 W") {
						CallingXML.SendMessage(list.get(i).get("gsmNumber"),
								"$4$");
					}
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}

			} else if (days == -3) {
				String str = "Aap ki "
						+ list.get(i).get("duedate")
						+ " ki tareekh ki mahaana adaaigi abhi tak rehti hai. "
						+ "Brahay meharbani "
						+ list.get(i).get("afterTenDays")
						+ " tareekh tak apni adaaigi laazmi kar dein. Adaaigi na honay "
						+ "ki sourat main Nizam Bijli apna system wapis le le ge. Aap ka Consumer number hai "
						+ list.get(i).get("imei_number")
						+ ", brahay "
						+ "meharbani is pe apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya";
				try {
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}
			} else if (days == -5) {
				String str = "Aap ki "
						+ list.get(i).get("duedate")
						+ " ki tareekh ki mahaana adaaigi abhi tak rehti hai. "
						+ "Brahay meharbani "
						+ list.get(i).get("afterTenDays")
						+ " tareekh tak apni adaaigi laazmi kar dein. Adaaigi na honay "
						+ "ki sourat main Nizam Bijli apna system wapis le le ge. Aap ka Consumer number hai "
						+ list.get(i).get("imei_number")
						+ ", brahay "
						+ "meharbani is pe apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya";
				try {
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}
			} else if (days == -8) {
				String str = "Aap ki "
						+ list.get(i).get("duedate")
						+ " ki tareekh ki mahaana adaaigi abhi tak rehti hai. "
						+ "Brahay meharbani "
						+ list.get(i).get("afterTenDays")
						+ " tareekh tak apni adaaigi laazmi kar dein. Adaaigi na honay "
						+ "ki sourat main Nizam Bijli apna system wapis le le ge. Aap ka Consumer number hai "
						+ list.get(i).get("imei_number")
						+ ", brahay "
						+ "meharbani is pe apni adaaigi kar dijiyay. Nizam Bijli istimaal karnay k lia shukriya";
				try {
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}
			} else if (days < -10) {
				String str = "Brahay meharbani apnay wajib ul adaa ki fouran adaaigi kijiyay. Agar aap yeh "
						+ " adaaigi naheen kar saktay ya Nizam Bijli ka system istamaal naheen karna chahtay toh "
						+ "kal tak Nizam Bijli k office apna system wapis kar k advance ki di "
						+ "howi raqam hasil kar lein. Xyz tareekh tak system wapis na karnay ki sourat main "
						+ "Nizam Bijli system wapis lenay k baad advance ki raqam wapis naheen ki jae ge. Aap "
						+ "ka Consumer number hai "
						+ list.get(i).get("imei_number")
						+ ", brahay meharbani is pe apni adaaigi kar dijiyay. Nizam Bijli "
						+ "istimaal karnay k lia shukriya ";
				try {
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
	}

	public static void sendWellWishes() {
		ArrayList<HashMap<String, String>> list = ReminderUtility
				.sendDueDateReminders();
		for (int i = 0; i < list.size(); i++) {
			int days = Integer.parseInt(list.get(i).get("downpayment_days"));
			if (days == 21) {
				String str = "Hope you are happy with Nizam Bijli, if you are having any problems "
						+ "please do not hestiate to call our call centre 03-111-741-741 we will make "
						+ "sure to resolve any problem you are facing as we aim to give you an amazing customer experience";
				try {
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}
			} else if (days == 101) {
				String str = "Hope you are happy with Nizam Bijli, if you are having any problems "
						+ "please do not hestiate to call our call centre 03-111-741-741 we will make "
						+ "sure to resolve any problem you are facing as we aim to give you an amazing customer experience";
				try {
					CallingXML.SendMessage(list.get(i).get("customerPhone"),
							str);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
	}

	public static void sendEmailToRO() {
		ArrayList<HashMap<String, String>> list = ReminderUtility
				.sendDueDateReminders();
		String str = "Due Date       DO Name          FO Name         ND Name         Customer Name         Payment\n";
		for (int i = 0; i < list.size(); i++) {
			str = str + "" + list.get(i).get("duedate") + "     "
					+ list.get(i).get("doName") + "     "
					+ list.get(i).get("foName") + "     "
					+ list.get(i).get("NdName") + "     "
					+ list.get(i).get("customerName") + "     "
					+ list.get(i).get("monthlyInstallment") + "\n";
			str = str
					+ ".............................................................................................\n";
		}
		SendMailSSL.sendEmail("kumarjetander92@gmail.com",
				"Due Date Reminders", str);
	}

	public static void sendEmailToDo() {
		String str = "Due Date       DO Name          FO Name         ND Name         Customer Name         Payment\n\n";
		ArrayList<HashMap<String, String>> doList = ReminderUtility
				.getUserIdList();
		for (int j = 0; j < doList.size(); j++) {
			int doId = Integer.valueOf(doList.get(j).get("userId"));
			ArrayList<HashMap<String, String>> list = ReminderUtility
					.sendDueDateRemindersDoWise(doId);
			for (int i = 0; i < list.size(); i++) {
				str = str + "" + list.get(i).get("duedate") + "     "
						+ list.get(i).get("doName") + "     "
						+ list.get(i).get("foName") + "     "
						+ list.get(i).get("ndName") + "     "
						+ list.get(i).get("customerName") + "     "
						+ list.get(i).get("monthlyInstallment") + "\n";
				str = str
						+ ".............................................................................................\n";
			}
			SendMailSSL.sendEmail("kumarjetander92@gmail.com",
					"Due Date Reminders", str);
		}
	}

	public static void isLive() {
		System.out.println("isLive()!!!!!!!!!");
		ArrayList<HashMap<String, String>> list = null;
		int previousStatus = 0;
		int flag = 0;
		int status = 0;
		double solarPower = 0.0;
		double loadPower = 0.0;

		try {
			list = ReminderUtility.getHealth();
			for (HashMap<String, String> hashMap : list) {
				int health = Integer.parseInt(hashMap.get("number"));
				int appid = Integer.parseInt(hashMap.get("appId"));

				if (health > 15) {

					solarPower = ApplianceStatusBAL.ApplianceSummary(appid)[0];
					loadPower = ApplianceStatusBAL.ApplianceLoadSummary(appid);
					try (Connection con = Connect.getConnection();
							Statement statement = con.createStatement()) {
						statement
								.executeUpdate("UPDATE appliance SET appliance.is_alive=1, appliance.status = 6 ,"
										+ " appliance.health_flag=0 ,appliance.health_status=3 , appliance.load_consumed="
										+ loadPower
										+ ",appliance.power_produced="
										+ solarPower
										+ " WHERE appliance.`appliance_id`="
										+ appid);

					} catch (SQLException e) {
						logger.error(e);
						e.printStackTrace();
					}
				} else {
					try (Connection con = Connect.getConnection();) {
						ResultSet rs = null;
						Statement s = null;
						s = con.createStatement();
						rs = s.executeQuery("SELECT a.is_alive,a.health_flag ,a.status FROM appliance a WHERE a.`appliance_id`="
								+ appid);
						while (rs.next()) {
							previousStatus = rs.getInt("is_alive");
							flag = rs.getInt("health_flag");
							status = rs.getInt("status");
						}
					} catch (Exception ex) {
						ex.getStackTrace();
					}
					if ((previousStatus == 1) && (flag == 0)) {

						try (Connection con = Connect.getConnection();
								Statement statement = con.createStatement()) {
							System.out.println("isLive()!!!!!!!!!<15");
							statement
									.executeUpdate("UPDATE appliance a SET a.`is_alive`=0 , a.`dead_since`=CURDATE(),a.health_flag=1 "
											+ " ,a.health_status=2 ,a.`load_consumed`=NULL , a.`power_produced`=NULL  "
											+ " WHERE `appliance_id`=" + appid);

						} catch (SQLException e) {
							logger.error(e);
							e.printStackTrace();
						}
					} else if ((previousStatus == 0) && (flag == 0)) {

						if (status == 6) {
							try (Connection con = Connect.getConnection();
									Statement statement = con.createStatement()) {
								statement
										.executeUpdate("UPDATE appliance a SET a.is_alive=0 , a.health_flag=1 ,a.dead_since=CURDATE() ,a.health_status=2 ,"
												+ " a.`load_consumed`=NULL , a.`power_produced`=NULL  "
												+ " WHERE a.`appliance_id`="
												+ appid);

							} catch (SQLException e) {
								logger.error(e);
								e.printStackTrace();
							}
						} else if (status == 7) {

							try (Connection con = Connect.getConnection();
									Statement statement = con.createStatement()) {
								statement
										.executeUpdate("UPDATE appliance a SET a.is_alive=0 , a.health_flag=0 ,a.dead_since=CURDATE() ,a.health_status=1 ,"
												+ " a.`load_consumed`=NULL , a.`power_produced`=NULL  "
												+ " WHERE a.`appliance_id`="
												+ appid);

							} catch (SQLException e) {
								logger.error(e);
								e.printStackTrace();
							}

						}
					} else if ((previousStatus == 0) && (flag == 1)) {
						try (Connection con = Connect.getConnection();
								Statement statement = con.createStatement()) {
							statement
									.executeUpdate("UPDATE appliance a SET a.is_alive=0 , a.health_flag=1 ,a.health_status=2 , "
											+ " a.`load_consumed`=NULL , a.`power_produced`=NULL  "
											+ " WHERE a.`appliance_id`="
											+ appid);

						} catch (SQLException e) {
							logger.error(e);
							e.printStackTrace();
						}
					}

				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

	}

	public static void sendEmailToFo() {
		String str = "Due Date       DO Name          FO Name         ND Name         Customer Name         Payment\n";
		ArrayList<HashMap<String, String>> foList = ReminderUtility.getFoList();
		for (int j = 0; j < foList.size(); j++) {
			int foId = Integer.valueOf(foList.get(j).get("foId"));
			ArrayList<HashMap<String, String>> list = ReminderUtility
					.sendDueDateRemindersFoWise(foId);
			for (int i = 0; i < list.size(); i++) {
				str = str + "" + list.get(i).get("duedate") + "     " + "     "
						+ list.get(i).get("NdName") + "     "
						+ list.get(i).get("customerName") + "     "
						+ list.get(i).get("monthlyInstallment") + "\n";
				str = str
						+ ".............................................................................................\n";
			}
		}
	}

}
