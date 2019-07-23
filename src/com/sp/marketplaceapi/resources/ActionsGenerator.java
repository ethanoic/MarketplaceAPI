package com.sp.marketplaceapi.resources;

import java.util.ArrayList;
import java.util.List;

import com.sp.marketplaceapi.models.Action;
import com.sp.marketplaceapi.models.Product;

/*
 * will create the actions necessary based on the state of the object
 */
public class ActionsGenerator {
	
	public List<Action> CreateProductDetailActions(Product product, int userId) {
		List<Action> actions = new ArrayList();
		
		// insert logic to return different type of actions based on the state of the product
		/*
		 * If product belongs to the current context user
		 * 	show update, remove links
		 * else
		 * 	show post offer link
		 */
		
		Action updateAction = new Action();
		updateAction.Method = "PUT";
		updateAction.Href = "/products/" + product.Id;
		updateAction.Name = "UPDATE PRODUCT";
		
		Action deleteAction = new Action();
		deleteAction.Method = "DELETE";
		deleteAction.Href = "/products/" + product.Id;
		deleteAction.Name = "DELETE PRODUCT";
		
		Action postBuyOfferAction = new Action();
		postBuyOfferAction.Method = "POST";
		postBuyOfferAction.Href = "/products/" + product.Id + "/offers";
		postBuyOfferAction.Name = "POST BUY OFFER";
		
		if (product.OwnedByUser == userId) {
			actions.add(updateAction);
			actions.add(deleteAction);	
		} else {
			actions.add(postBuyOfferAction);	
		}
		
		return actions;
	}
	
}
