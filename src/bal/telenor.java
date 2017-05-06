package bal;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.xml.rpc.ServiceException;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.tempuri.ReceiveSMSResponseReceiveSMSResult;
import org.tempuri.ServiceLocator;
import org.tempuri.ServiceSoap;

public class telenor {

	final static Logger logger = Logger.getLogger(telenor.class);
	
	public static boolean telenorStatus = false;

	public static String SendSms(String Number, String Message)
			throws ServiceException, IOException, JSONException, SQLException {

		try {
		CallingXML.SendMessage(Number, Message);
		// ServiceLocator ser = new ServiceLocator();
		// ServiceSoap asService = ser.getServiceSoap();
		// System.out.println("from back end " + Number);
		// String result = asService.sendSMS("3N3rGY*8", Number, "123",
		// Message);
		// if(result.equals("0")){telenorStatus=true;}
		// else {telenorStatus=false;}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return "1";
	}

	public static void RecieveSms() throws Exception {

		ServiceLocator ser = new ServiceLocator();
		ServiceSoap asService = ser.getServiceSoap();

		// String
		// as=asService.sendSMS("3N3rGY*8","923363044208","123","1 thest");
		// String asss=asService.getLocation("3N3rGY*8", "923002023881");
		ReceiveSMSResponseReceiveSMSResult aslam = asService.receiveSMS("3N3rGY*8", "12132sdd223");

		MessageElement[] as = aslam.get_any();
		// System.out.println("Name = " + as[0].getAsString());
		// System.out.println("Recordsas :"+as[1]);

		String asass = as[1].getAsString();

		System.out.println(asass);

		String soapmessageString = asass;
		org.json.JSONObject soapDatainJsonObject = XML.toJSONObject(soapmessageString);
		System.out.println(soapDatainJsonObject);

		// JSONObject jsonObj = new JSONObject(asass);
		String message, sender, date;
		int status = 0, reciever;
		// System.out.println(jsonObj);
		JSONObject result = soapDatainJsonObject.getJSONObject("diffgr:diffgram");
		JSONObject result2 = result.getJSONObject("NewDataSet");

		JSONObject dataObject = result2.optJSONObject("Table1");

		if (!dataObject.isNull("msg")) {
			telenorStatus = true;
			message = dataObject.getString("msg");
			sender = dataObject.getString("sender").substring(1);
			reciever = dataObject.getInt("SCode");
			String msgto = new Integer(reciever).toString();
			date = dataObject.getString("aDate");

			int appliance_id = ApplianceBAL.get_appliance_id(sender);

			String arr[] = message.split(" ", 2);
			String firstWord = arr[0];

			int Option_Number = Integer.parseInt(firstWord);
			System.out.println("firstWord  " + firstWord);
			String alert_message = "{" + message.replaceFirst(firstWord, "") + "}";

			JSONObject jsonObj = new JSONObject(alert_message);
			System.out.println("Option_Number " + Option_Number);
			System.out.println("alert_message " + alert_message);
			System.out.println("message " + message);
			System.out.println("appliance_id " + appliance_id);

			switch (Option_Number) {
			case 1: {// Statements

				boolean lid = false;
				if (jsonObj.getInt("lid") == 1) {
					lid = true;
				} else {
					lid = false;
				}
				ApplianceBAL.insert_applianceStatus(appliance_id, jsonObj.getDouble("bv"), jsonObj.getDouble("ba"),
						jsonObj.getDouble("tp"), jsonObj.getDouble("sv"), jsonObj.getDouble("la"), jsonObj.getInt("db"),
						lid, jsonObj.getDouble("lat"), jsonObj.getDouble("lon"));

				break;
			} // optional
			case 3: {

				ApplianceBAL.update_appliance_lat_long(jsonObj.get("lat").toString(), jsonObj.get("lon").toString(),
						appliance_id);

				// Statements
				break;
			} // optional
			case 4: {
				System.out.println("Appliance_id OFF " + appliance_id);
				ApplianceBAL.update_appliance_on_off(false, appliance_id);
				// Statements
				break;
			} // optional
			case 5: {
				System.out.println("Appliance_id ONN " + appliance_id);
				ApplianceBAL.update_appliance_on_off(true, appliance_id);
				// Statements
				break;
			} // optional
			case 6: {
				boolean lid = false;
				if (jsonObj.getInt("lid") == 1) {
					lid = true;
				} else {
					lid = false;
				}
				HashMap<String, String> map = new HashMap<>();

				map.put("bv", "" + jsonObj.getDouble("bv"));
				map.put("ba", "" + jsonObj.getDouble("ba"));
				map.put("sv", "" + jsonObj.getDouble("sv"));
				map.put("sa", "" + jsonObj.getDouble("sa"));
				map.put("lv", "" + jsonObj.getDouble("lv"));
				map.put("la", "" + jsonObj.getDouble("la"));
				map.put("tp", "" + jsonObj.getDouble("tp"));
				map.put("db", "" + jsonObj.getInt("db"));
				map.put("lid", "" + lid);
				map.put("lat", "" + jsonObj.getDouble("lat"));
				map.put("long", "" + jsonObj.getDouble("long"));
				map.put("imei", "" + jsonObj.getDouble("imei"));

				ApplianceStatusBAL.insertStatus(map);
				// ApplianceBAL.insert_applianceStatus(appliance_id,
				// jsonObj.getDouble("bv"),
				// jsonObj.getDouble("ba"),
				// jsonObj.getDouble("tp"),
				// jsonObj.getDouble("sv"),
				// jsonObj.getDouble("la"),
				// jsonObj.getInt("db"),
				// lid,
				// jsonObj.getDouble("lat"),
				// jsonObj.getDouble("long"),
				// jsonObj.getDouble("imei"),
				// jsonObj.getDouble("sa"),
				// jsonObj.getDouble("lv")
				// );
				//

				// Statements
				break;
			} // optional
			case 7: {
				ApplianceBAL.insert_applianceStatus_alerts(appliance_id, "appliance_voltage", jsonObj.getDouble("bv"));
				// Statements
				break;
			} // optional
			case 8: {
				ApplianceBAL.insert_applianceStatus_alerts(appliance_id, "appliance_solar_voltage",
						jsonObj.getDouble("sa"));
				// Statements
				break;
			} // optional
			case 9: {
				ApplianceBAL.insert_applianceStatus_alerts(appliance_id, "appliance_load_current",
						jsonObj.getDouble("la"));
				// Statements
				break;
			} // optional
			case 10: {
				ApplianceBAL.insert_applianceStatus_alerts(appliance_id, "appliance_temperature",
						jsonObj.getDouble("tp"));
				// Statements
				break;
			} // optional
			case 11: {
				ApplianceBAL.insert_applianceStatus_lat_long(appliance_id, jsonObj.getDouble("lat"),
						jsonObj.getDouble("lon"));
				// Statements
				break;
			} // optional

			case 12: {
				boolean lid = false;
				if (jsonObj.getInt("lid") == 1) {
					lid = true;
				} else {
					lid = false;
				}
				ApplianceBAL.insert_applianceStatus_lidopen(appliance_id, lid);
				// Statements
				break;
			} // optional
			case 13: {
				ApplianceBAL.insert_applianceStatus_gsm_signal(appliance_id, jsonObj.getInt("db"));
				// Statements
				break;
			} // optional

				// You can have any number of case statements.
			default: // Optional
				// Statements

			}

			// int ap_status;
			// if (mesg.equals("OFF")) {
			// if (mesg.equals("OFF")) {
			// ap_status = 0;
			// } else {
			// ap_status = 0;
			//
			// }
			//
			// System.out.println("Appliance number: " + sender.substring(1)
			// + " app_status= " + ap_status);
			// Appliance_Message
			// .applianceOnOff(ap_status, sender.substring(1));
			// }
			//
			//
			//
			//
			//
			//
			//
			//
			//
			// Appliance_Message.insertAppliance_Message(sender, msgto, date,
			// mesg, status);

			// Do things with object.

		} else if (result2.getJSONObject("Table1").has("ErrorMsg")) {
			telenorStatus = false;
			System.out.println("No Records Found");
		} else {
			telenorStatus = true;
			JSONArray result3 = result2.getJSONArray("Table1");
			for (int i = 0; i < result3.length(); i++) {
				JSONObject p = (JSONObject) result3.get(i);
				message = p.getString("msg");
				sender = p.getString("sender").substring(1);
				reciever = p.getInt("SCode");
				String msgto = new Integer(reciever).toString();
				date = p.getString("aDate");
				int appliance_id = ApplianceBAL.get_appliance_id(sender);

				String arr[] = message.split(" ", 2);
				String firstWord = arr[0];

				int Option_Number = Integer.parseInt(firstWord);

				String alert_message = "{" + message.replaceFirst(firstWord, "") + "}";
				JSONObject jsonObj = new JSONObject(alert_message);

				switch (Option_Number) {
				case 1: {// Statements

					boolean lid = false;
					if (jsonObj.getInt("lid") == 1) {
						lid = true;
					} else {
						lid = false;
					}
					ApplianceBAL.insert_applianceStatus(appliance_id, jsonObj.getDouble("bv"), jsonObj.getDouble("ba"),
							jsonObj.getDouble("tp"), jsonObj.getDouble("sv"), jsonObj.getDouble("la"),
							jsonObj.getInt("db"), lid, jsonObj.getDouble("lat"), jsonObj.getDouble("lon"));

					break;
				} // optional
				case 3: {

					ApplianceBAL.update_appliance_lat_long(jsonObj.get("lat").toString(), jsonObj.get("lon").toString(),
							appliance_id);

					// Statements
					break;
				} // optional
				case 4: {
					ApplianceBAL.update_appliance_on_off(false, appliance_id);
					// Statements
					break;
				} // optional
				case 5: {
					ApplianceBAL.update_appliance_on_off(true, appliance_id);
					// Statements
					break;
				} // optional
				case 6: {
					boolean lid = false;
					if (jsonObj.getInt("lid") == 1) {
						lid = true;
					} else {
						lid = false;
					}
					ApplianceBAL.insert_applianceStatus(appliance_id, jsonObj.getDouble("bv"), jsonObj.getDouble("ba"),
							jsonObj.getDouble("tp"), jsonObj.getDouble("sv"), jsonObj.getDouble("la"),
							jsonObj.getInt("db"), lid, jsonObj.getDouble("lat"), jsonObj.getDouble("lon"));

					// Statements
					break;
				} // optional
				case 7: {
					ApplianceBAL.insert_applianceStatus_alerts(appliance_id, "appliance_voltage",
							jsonObj.getDouble("bv"));
					// Statements
					break;
				} // optional
				case 8: {
					ApplianceBAL.insert_applianceStatus_alerts(appliance_id, "appliance_solar_voltage",
							jsonObj.getDouble("sa"));
					// Statements
					break;
				} // optional
				case 9: {
					ApplianceBAL.insert_applianceStatus_alerts(appliance_id, "appliance_load_current",
							jsonObj.getDouble("la"));
					// Statements
					break;
				} // optional
				case 10: {
					ApplianceBAL.insert_applianceStatus_alerts(appliance_id, "appliance_temperature",
							jsonObj.getDouble("tp"));
					// Statements
					break;
				} // optional
				case 11: {
					ApplianceBAL.insert_applianceStatus_lat_long(appliance_id, jsonObj.getDouble("lat"),
							jsonObj.getDouble("lon"));
					// Statements
					break;
				} // optional

				case 12: {
					boolean lid = false;
					if (jsonObj.getInt("lid") == 1) {
						lid = true;
					} else {
						lid = false;
					}
					ApplianceBAL.insert_applianceStatus_lidopen(appliance_id, lid);
					// Statements
					break;
				} // optional
				case 13: {
					ApplianceBAL.insert_applianceStatus_gsm_signal(appliance_id, jsonObj.getInt("db"));
					// Statements
					break;
				} // optional

					// You can have any number of case statements.
				default: // Optional
					// Statements

				}

				Appliance_Message.insertAppliance_Message(sender, msgto, date, message, status);

			}
		}
		// //JSONArray jsonMainArr = new
		// JSONArray(soapDatainJsonObject.getJSONArray("Table1"));
		// JSONArray jsonMainArr = soapDatainJsonObject.getJSONArray("Table1");
		// for(int i = 0; i < jsonMainArr.length(); i++){
		//
		// org.json.JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
		// String name = childJSONObject.getString("msg");
		// String age = childJSONObject.getString("SCode");
		//
		// System.out.println(name+" "+age);
		// }

	}

	public static String getFirstString(String str) {

		String arr[] = str.split(" ", 0);

		String firstWord = arr[0];

		return firstWord;
	}

	public static boolean get_TelenorStatus() {
		return telenorStatus;
	}

	public static void main(String[] argv) throws Exception {
		// System.out.println("this is test "+SendSms("923002023881","please
		// Rerply i deleted email. "));
		// RecieveSms();
		SendSms("923313754297", "$5$");
		// System.out.println(getFirstString("12 sjhsd sdhsd sdhsd "));

	}
}
