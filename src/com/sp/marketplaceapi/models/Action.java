package com.sp.marketplaceapi.models;

public class Action {
	public Action() {
		this.Name = "";
		this.Href = "";
		this.Method = "";
	}
	public Action(String name, String href, String method) {
		this.Name = name;
		this.Href = href;
		this.Method = method;
	}
	public String Name;
	public String Href;
	public String Method;
}
