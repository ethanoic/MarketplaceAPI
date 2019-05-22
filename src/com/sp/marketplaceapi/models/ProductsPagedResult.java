package com.sp.marketplaceapi.models;

import java.util.ArrayList;

public class ProductsPagedResult {
	public ArrayList<ProductSummary> List;
	public String Next;
	public String Prev;
	public int TotalCount;
}
