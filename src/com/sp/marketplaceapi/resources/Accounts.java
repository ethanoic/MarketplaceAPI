package com.sp.marketplaceapi.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.sp.marketplaceapi.managers.AccountsManager;
import com.sp.marketplaceapi.models.CreateAccount;

@Path("accounts")
public class Accounts {
	
	private AccountsManager manager = new AccountsManager();
	
	@POST
	@Consumes("application/json")
	public Response Create(CreateAccount newAccount) throws Exception {
		
		try {
			manager.CreateAccount(newAccount.Email, 
					newAccount.FirstName, 
					newAccount.LastName, 
					newAccount.CountryCode, 
					newAccount.Mobile);
		} catch (Exception ex ){
			// supposed to log this somewhere...
			//System.out.println(ex.getMessage());
			throw ex;
		}
		
		return Response.ok().build();
	}
}
