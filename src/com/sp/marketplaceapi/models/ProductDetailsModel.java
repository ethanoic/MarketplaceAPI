package com.sp.marketplaceapi.models;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsModel extends Product {
	
	public ProductDetailsModel(Product product) {
		this.Id = product.Id;
		this.Name = product.Name;
		this.FullDescription = product.FullDescription;
		this.CategoryId = product.CategoryId;
		this.DealTypes = product.DealTypes;
		this.HeroImage = product.HeroImage;
		this.Images = product.Images;
		this.Likes = product.Likes;
		this.Price = product.Price;
		this.ShortDescription = product.ShortDescription;
		this.OwnedByUser = product.OwnedByUser;
		this.Actions = new ArrayList<Action>();
	}
	
	public List<Action> Actions;
}
