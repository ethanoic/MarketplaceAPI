package com.sp.marketplaceapi.application;
import org.glassfish.jersey.server.ResourceConfig;

public class MarketplaceAPIApplication extends ResourceConfig {
	public MarketplaceAPIApplication() {
		packages("com.sp.marketplaceapi.resources");
		register(com.sp.marketplaceapi.application.MyJacksonFeature.class);
	}
}
