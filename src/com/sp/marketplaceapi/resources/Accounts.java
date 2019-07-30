package com.sp.marketplaceapi.resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sp.marketplaceapi.auth.jwt.JWTToken;
import com.sp.marketplaceapi.auth.jwt.jwtIssuer;
import com.sp.marketplaceapi.managers.AccountsManager;
import com.sp.marketplaceapi.models.AuthenticateModel;
import com.sp.marketplaceapi.models.CreateAccount;
import com.sp.marketplaceapi.models.User;

@Path("accounts")
public class Accounts {
	
	private AccountsManager manager = new AccountsManager();

	@PermitAll
	@POST
	@Consumes("application/json")
	public Response Create(CreateAccount newAccount) throws Exception {
		boolean result = true;
		try {
			result = manager.CreateAccount(newAccount.Email, 
					newAccount.Username,
					newAccount.Password,
					newAccount.FirstName, 
					newAccount.LastName, 
					newAccount.CountryCode, 
					newAccount.Mobile,
					"MEMBER");
		} catch (Exception ex ){
			// supposed to log this somewhere...
			//System.out.println(ex.getMessage());
			//throw ex;-
			result = false;
		}
		
		return Response.ok(result).build();
	}
	
	@PermitAll
	@Path("auth")
	@POST
	@Consumes("application/json")
	public Response Authenticate(AuthenticateModel model) {
		// sign and return a jwt
		// TODO check if username and password exists
		if (manager.CheckUsernamePassword(model.Username, model.Password)) {
			// GET account or userid
			User getUser = manager.GetUser(model.Username);
			String userId = getUser.Id + "";
			String roleName = getUser.Role;
			String accessToken = JWTToken.createJWT(jwtIssuer.getId(), jwtIssuer.getIssuer(), "user", 
					userId, roleName, "", 3000000);
			return Response.ok(accessToken).build();	
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
	
}
