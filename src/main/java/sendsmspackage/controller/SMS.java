package sendsmspackage.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SMS {

	String phone;
	String message;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SMS() {
	}

	public SMS(String phone, String message) {
		this.phone = phone;
		this.message = message;
	}

	@Override
	public String toString() {
		return "SMS : {phone : " + phone + ", message : " + message + "}";
	}
}

class SendSMS {

	static String url = "https://www.way2sms.com";

	public static String sendCampaign(SMS sms) {
		System.out.println("sendCampaign => " + sms);
		try {
			String token = "apikey=" + URLEncoder.encode("UG6Q9RWPUNOAGDBX2EPYV3XLNTZ9NQD4", "UTF-8");
			String secret = "&secret=" + URLEncoder.encode("33EK25MQE792YW58", "UTF-8");
			String usetype = "&usetype=" + URLEncoder.encode("stage", "UTF-8");
			String mobile = "&phone=" + URLEncoder.encode(sms.phone, "UTF-8");
			String messageText = "&message=" + URLEncoder.encode(sms.message, "UTF-8");
			String senderId = "&senderid=" + URLEncoder.encode("CRMWEB", "UTF-8");

			URL obj = new URL(
					url + "/api/v1/sendCampaign?" + token + secret + usetype + mobile + messageText + senderId);
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);

			BufferedReader bufferedReader = null;
			if (httpConnection.getResponseCode() == 200) {
				bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			} else {
				bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
			}

			StringBuilder content = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line).append("\n");
			}
			bufferedReader.close();
			return content.toString();
		} catch (Exception ex) {
			System.out.println("Exception at:" + ex.getMessage());
			return "{\"status\":500,\"message\":\"Internal Server Error\"}";
		}
	}
}
