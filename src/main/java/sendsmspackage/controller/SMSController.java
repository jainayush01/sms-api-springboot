package sendsmspackage.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/sms")
public class SMSController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String testServer() {
		return "SMS API IS LISTENING ...";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String sendSMS(@RequestBody SMS sms) {
		System.out.println("sendSMS => " + sms);
		return SendSMS.sendCampaign(sms);
		// return "{\"message\":\"sms sent\"}";
	}

}
