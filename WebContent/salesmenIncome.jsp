public static String RecieveSMSList() throws IOException, JSONException,
			ParseException, SQLException {
		String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1";
		String url = "http://221.132.117.58:7700/receivesms_xml.html";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date today = (Date) Calendar.getInstance().getTime();

		String reportDate = df.format(today);
		// System.out.println(reportDate);
		// System.out.println(previousdate);
		StringBuffer sb = new StringBuffer();
		/* sb.append("<?xml version=\"1.0\" ?>"); */
		sb.append("<SMSRequest>");
		sb.append("<Username>03028501626</Username>");
		sb.append("<Password>Pakistan123</Password> ");
		sb.append("<Shortcode>7005148</Shortcode> ");
		// sb.append("<FromDate>"+ previousdate +"</FromDate>");
		// sb.append("<ToDate>"+reportDate+"</ToDate>");
		sb.append("</SMSRequest>");

		// add header
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
					System.err.println(message);
					ApplianceBAL.update_appliance_on_off(true, appliance_id);
				}
				if (Option_Number == 4) {
					System.err.println(message);
					ApplianceBAL.update_appliance_on_off(false, appliance_id);
				}
				if (message.startsWith("IMEI")) {
					System.err.println(message);
					updateApplianceStatus(message);
				}
			}
			else {
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
						System.err.println(message);
						ApplianceBAL
								.update_appliance_on_off(true, appliance_id);
					}
					if (Option_Number == 4) {
						System.err.println(message);
						ApplianceBAL.update_appliance_on_off(false,
								appliance_id);
					}
					if (message.startsWith("IMEI")) {
						System.err.println(message);
						updateApplianceStatus(message);
					}

					if (i == 0) {

						previousdate = date;

					}
				}
			}

			
		}
				return result.toString();
	}
