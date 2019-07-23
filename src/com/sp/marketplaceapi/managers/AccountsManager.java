package com.sp.marketplaceapi.managers;

import com.sendgrid.*;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sp.marketplaceapi.models.User;
import com.sp.marketplaceapi.database.ConnectionManager;
/*
 * Handles all accounts creation, forget password logic
 */
public class AccountsManager {
	
	public User GetUser(String username) {
		User getUser = null;
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				getUser = new User();
				getUser.Username = result.getString("username");
				getUser.Role = result.getString("role");
				getUser.Id = result.getInt("id");
				// TODO get the others, but no password
			}
		} catch (Exception ex) {
		
		}
		return getUser;
	}
	public boolean CheckUsernamePassword(String username, String password) {
		boolean isValid = false;
		try {
			
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM users "
					+ "WHERE username = ? AND password = ?");
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			isValid = result.next();
			
		} catch (Exception ex) {
			isValid = false;
		}
		return isValid;
	}
	
	public boolean CreateAccount(String email, String username ,String password, String firstName, 
			String lastName, String countryCode, String mobile, String role) throws Exception {
		
		// TODO
		/*
		 * save to db, get account id
		 * generate code using account id and code aka OTP generator
		 * send email to user with code 
		 */
		
		boolean result = true;
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement statement = conn.prepareStatement("INSERT INTO users "
					+ "(email, username, password, first_name, last_name, country_code, mobile, role) "
					+ "VALUES (?, ? , ?, ?, ?, ?, ?, ?)");
			
			statement.setString(1, email);
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, firstName);
			statement.setString(5, lastName);
			statement.setString(6, countryCode);
			statement.setString(7, mobile);
			statement.setString(8, role);
			
			statement.execute();
			
		} catch (Exception ex) {
			result = false;
		}
		
		return result;
		
		/*
		Email from = new Email("accounts@the-marketplace.com.sg");
	    String subject = "Sending with SendGrid is Fun";
	    Email to = new Email(email);
	    String message = "Hi " + firstName + ", Welcome";
	    Content content = new Content("text/plain", message);
	    Mail mail = new Mail(from, subject, to, content);

	    //SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    SendGrid sg = new SendGrid("<insert your sendgrid api key>");
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
	    */
	}
}
