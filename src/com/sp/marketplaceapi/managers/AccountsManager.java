package com.sp.marketplaceapi.managers;

import com.sendgrid.*;
import java.io.IOException;

/*
 * Handles all accounts creation, forget password logic
 */
public class AccountsManager {
	public void CreateAccount(String email, String firstName, 
			String lastName, String countryCode, String mobile) throws Exception {
		
		// TODO
		/*
		 * save to db, get account id
		 * generate code using account id and code aka OTP generator
		 * send email to user with code 
		 */
		
		Email from = new Email("accounts@UOB.com.sg");
	    String subject = "Sending with SendGrid is Fun";
	    Email to = new Email(email);
	    String message = "Hi " + firstName + ", Welcome";
	    Content content = new Content("text/plain", message);
	    Mail mail = new Mail(from, subject, to, content);

	    //SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	}
}
