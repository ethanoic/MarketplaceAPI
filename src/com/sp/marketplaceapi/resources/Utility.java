package com.sp.marketplaceapi.resources;

import com.sp.marketplaceapi.auth.jwt.JWTToken;

import io.jsonwebtoken.Claims;

public class Utility {
	public static int GetUserIdFromAuthToken(String authToken) {
		authToken = authToken.replace("Bearer ", "");
		Claims claims = JWTToken.getClaims(authToken);
		String payload = claims.get("payload").toString();
		int userId = Integer.parseInt(payload);
		return userId;
	}
}
