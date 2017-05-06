package bal;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import bean.AlarmsBean;
import bean.ApplianceStatusBean;
import bean.PendingMessages;
import bean.lat_long_bean;

public class CallingXML {
	final static Logger logger = Logger.getLogger(CallingXML.class);

	static public boolean mobilink_status = false;
	static SimpleDateFormat formatter = new SimpleDateFormat(
			"dd-mmmm-yyyy HH:mm:ss");
	Date old = new Date(0);
	static String previousdate = "2015-10-21 00:00:00";

	public static int SendMessage(String phone, String message)
			throws IOException, JSONException, SQLException {

		String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1";
		String url = "http://221.132.117.58:7700/sendsms_xml.html";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		StringBuffer sb = new StringBuffer();
		sb.append("<SMSRequest>");
		sb.append("<Username>03028501626</Username>");
		sb.append("<Password>Pakistan123</Password> ");
		sb.append("<From>7005148</From> ");
		sb.append("<To>" + phone + "</To> ");
		sb.append("<Message>" + message + "</Message> ");
		sb.append("</SMSRequest>");
		post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("xmldoc", sb.toString()));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(60000).setConnectTimeout(90000)
				.setConnectionRequestTimeout(120000).build();
		post.setConfig(requestConfig);
		HttpResponse response = client.execute(post);
		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		String soapmessageString = result.toString();
		org.json.JSONObject soapDatainJsonObject = XML
				.toJSONObject(soapmessageString);
		if (soapDatainJsonObject.getJSONObject("response").has("messageid")) {
			CustomerBAL.insertTotalMessage("Mobilink", phone, message);
			return 0;
		} else {
			// SendmailBAL.Nosms_mail("saad.hamid@mobilink.net");
			PendingMessages pm = new PendingMessages();
			pm.setRecieverNumber(phone);
			pm.setMessage(message);
			System.out.println("no sms");
			return 1;
		}

	}

	public static boolean get_mobilink_status() {
		return mobilink_status;
	}

	public static String RecieveSMSList() throws IOException, JSONException,
			ParseException, SQLException {
		String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1";
		String url = "http://221.132.117.58:7700/receivesms_xml.html";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date today = (Date) Calendar.getInstance().getTime();

		String reportDate = df.format(today);
		StringBuffer sb = new StringBuffer();
		sb.append("<SMSRequest>");
		sb.append("<Username>03028501626</Username>");
		sb.append("<Password>Pakistan123</Password> ");
		sb.append("<Shortcode>7005148</Shortcode> ");
		sb.append("</SMSRequest>");

		post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("xmldoc", sb.toString()));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		StringBuffer sb1 = new StringBuffer(
				"<SMSRsponse><Error>No Record Found.</Error></SMSRsponse>");
		mobilink_status = false;
		if (sb1.equals(result)) {
			return "No records foundasass";
		}
		String soapmessageString = result.toString();
		org.json.JSONObject soapDatainJsonObject = XML
				.toJSONObject(soapmessageString);

		org.json.JSONObject newJSON = soapDatainJsonObject
				.getJSONObject("SMSRsponse");
		if (!newJSON.has("SMSInfo")) {
			mobilink_status = false;
			return "No Record found";
		} else {
			Object item = newJSON.get("SMSInfo");
			boolean json = item.toString().startsWith("{");
			if (json) {
				mobilink_status = true;
				org.json.JSONObject obj = newJSON.getJSONObject("SMSInfo");
				String smsFrom = obj.getString("smsFrom").substring(1);
				double smsTo = obj.getDouble("smsTo");
				String message = obj.getString("smsMessage");
				String date = obj.getString("smsDate");

				String msgto = new Double(smsTo).toString();
				System.out.println(message);
				int appliance_id = ApplianceBAL.get_appliance_id(smsFrom);
				String arr[] = message.split(" ", 2);
				String firstWord = "";
				int Option_Number = 0;
				if (message.startsWith("5") || message.startsWith("4")) {
					firstWord = arr[0];
					Option_Number = Integer.parseInt(firstWord);
				}
				if (Option_Number == 5) {
					logger.info(message);
					ApplianceBAL.update_appliance_on_off(true, appliance_id);
				}
				if (Option_Number == 4) {
					logger.info(message);
					ApplianceBAL.update_appliance_on_off(false, appliance_id);
				}
				if (message.startsWith("IMEI")) {
					logger.info(message);
					message = message.replace("+", "");
					updateApplianceStatus(message);
				}
			} else {
				org.json.JSONArray ARRAY = newJSON.getJSONArray("SMSInfo");
				if (ARRAY.length() < 1) {
					return "NO";
				}
				for (int i = 0; i < ARRAY.length(); i++) {
					mobilink_status = true;
					org.json.JSONObject obj = ARRAY.getJSONObject(i);
					String smsFrom = obj.getString("smsFrom").substring(1);
					double smsTo = obj.getDouble("smsTo");
					String message = obj.getString("smsMessage");
					String date = obj.getString("smsDate");
					String msgto = new Double(smsTo).toString();
					int appliance_id = ApplianceBAL.get_appliance_id(smsFrom);
					String arr[] = message.split(" ", 2);
					String firstWord = "";
					int Option_Number = 0;
					if (message.startsWith("5") || message.startsWith("4")) {
						firstWord = arr[0];
						Option_Number = Integer.parseInt(firstWord);
					}
					if (Option_Number == 5) {
						logger.info(message);
						ApplianceBAL
								.update_appliance_on_off(true, appliance_id);
					}
					if (Option_Number == 4) {
						logger.info(message);
						ApplianceBAL.update_appliance_on_off(false,
								appliance_id);
					}
					if (message.startsWith("IMEI")) {
						logger.info(message);
						message = message.replace("+", "");
						updateApplianceStatus(message);
					}
				}
			}
		}
		return result.toString();
	}

	private static void updateApplianceStatus(String message) {
		Map<String, String> map = null;
		try {
			map = getQueryMap(message);
		} catch (Exception e) {
			logger.error(e);
		}
		ApplianceStatusBean statusBean = new ApplianceStatusBean();
		statusBean.setBa(Double.parseDouble(map.get("ba")));
		statusBean.setBv(Double.parseDouble(map.get("bv")));
		statusBean.setLa(Double.parseDouble(map.get("la")));
		statusBean.setLv(Double.parseDouble(map.get("lv")));
		statusBean.setSa(Double.parseDouble(map.get("sa")));
		statusBean.setSv(Double.parseDouble(map.get("sv")));
		statusBean.setTemperature(Double.parseDouble(map.get("tp")));
		statusBean.setSignalStrength(Integer.parseInt(map.get("db")));
		statusBean.setLid(Boolean.parseBoolean("0"));
		statusBean.setIMEI(map.get("IMEI"));
		statusBean.setLatitude(Double.parseDouble(map.get("lat")));
		statusBean.setLongitude(Double.parseDouble(map.get("lon")));
		String alarmBits = map.get("st");
		int id = 0;
		try {
			id = ApplianceStatusBAL.insertStatus(statusBean);
		} catch (Exception e) {
			logger.error(e);
		}

		int idd = 0;
		HashMap<String, String> mapAlarms = new HashMap<>();
		int alarm = 0;
		if (alarmBits != null) {
			alarm = Integer.parseInt(alarmBits);
			alarmBits = binaryFill(alarm);
		}
		char nums[] = alarmBits.toCharArray();

		mapAlarms.put("betteryLevel", nums[8] + "");
		mapAlarms.put("solarPower", nums[7] + ""); // Over Current or Voltage
		mapAlarms.put("currentLoad", nums[2] + "");
		mapAlarms.put("temperature", nums[1] + "");
		mapAlarms.put("lidOpen", nums[4] + "");
		mapAlarms.put("signalStrength", nums[3] + "");
		mapAlarms.put("commonFault", nums[5] + "");
		mapAlarms.put("gsm_suicide", nums[0] + "");
		mapAlarms.put("battery_overcharge", nums[6] + "");

		mapAlarms.put("latitude", 0 + "");
		mapAlarms.put("longitude", 0 + "");
		mapAlarms.put("imeiNumber", map.get("IMEI"));
		try {
			idd = ApplianceStatusBAL.insertAlarms(mapAlarms);
		} catch (Exception e) {
			logger.error(e);
		}
		logger.info("Status id " + id + "Alarm id " + idd);
	}

	public static Map<String, String> getQueryMap(String query) {
		String[] params = query.split(" ");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}

	public static String binaryFill(int number) {
		String numberAsString = Integer.toBinaryString(number);
		StringBuilder sb = new StringBuilder();
		while (sb.length() + numberAsString.length() < 9) {
			sb.append('0');
		}
		sb.append(numberAsString);
		String paddedNumberAsString = sb.toString();
		return paddedNumberAsString.toString();
	}

	public static void main(String[] argv) throws IOException, JSONException,
			SQLException {
		updateApplianceStatus("IMEI=867967029621952 bv=13.9 ba=+0.3 sv=22.6 sa=0.3 lv=13.9 la=0.0 tp=18 lon= lat= db=97 st=32");
//		updateApplianceStatus("IMEI=747766666666666 bv=13.0 ba=-0.2 sv=0.0 sa=0.0 lv=13.0 la=0.2 tp=28 lon=71.932724 lat=32.413582 db=83 st=0");
//		updateApplianceStatus("IMEI=747766666666666 bv=13.0 ba=-0.2 sv=0.0 sa=0.0 lv=13.0 la=0.2 tp=28 lon=71.932724 lat=32.413582 db=83 st=0");
//		updateApplianceStatus("IMEI=747766666666666 bv=13.0 ba=-0.2 sv=0.0 sa=0.0 lv=13.0 la=0.2 tp=28 lon=71.932724 lat=32.413582 db=83 st=0");
//		updateApplianceStatus("IMEI=747766666666666 bv=13.0 ba=-0.2 sv=0.0 sa=0.0 lv=13.0 la=0.2 tp=28 lon=71.932724 lat=32.413582 db=83 st=0");
//		updateApplianceStatus("IMEI=747766666666666 bv=13.0 ba=-0.2 sv=0.0 sa=0.0 lv=13.0 la=0.2 tp=28 lon=71.932724 lat=32.413582 db=83 st=0");

	}
}
