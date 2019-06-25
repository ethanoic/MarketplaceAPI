package com.sp.marketplaceapi.auth.jwt;

public class jwtIssuer {
	public static String getId() {
		return "usertoken";
	}
	public static String getIssuer() {
		return "marketplace.sg"; // change to your own domain name
	}
}
