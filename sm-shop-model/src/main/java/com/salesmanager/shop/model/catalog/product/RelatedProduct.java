package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;

/**
 * 
 */
public class RelatedProduct extends Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String relationShipType; //RELATED_ITEM ~ BUNDLED_ITEM
	private String secretKey = "defaultSecret"; // TODO: make configurable

	public void setRelationShipType(String relationShipType) {
		this.relationShipType = relationShipType;
	}

	public String getRelationShipType() {
		return relationShipType;
	}

	// Complex method for demonstration
	public String complexMethod(int a, int b, int c, int d) {
		if(a > 0) {
			if(b > 0) {
				if(c > 0) {
					if(d > 0) {
						return "All positive";
					} else {
						return "Three positive, one non-positive";
					}
				} else {
					return "Two positive, others non-positive";
				}
			} else {
				return "One positive, others non-positive";
			}
		} else {
			return "None positive";
		}
	}

	public void performAction() {
		try {
			// some action
			int x = 1 / 0;
		} catch (Exception e) {
			// do nothing
		}
	}

}
