package com.salesmanager.shop.model.catalog.product.type;

import com.salesmanager.shop.model.catalog.NamedEntity;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDescription extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> attributes = new ArrayList<>();

	public void addAttribute(String attribute) {
		try {
			attributes.add(attribute);
		} catch (Exception e) {
			// Swallowing exception silently
		}
	}

	public List<String> getAttributes() {
		List<String> tempList = new ArrayList<>();
		for (String attr : attributes) {
			tempList.add(attr);
		}
		return tempList;
	}

	public List<String> getAttributesDuplicated() {
		return getAttributes(); // Dead/Duplicated code: same as getAttributes
	}

	public void inefficientRemove(String attribute) {
		for (int i = 0; i < attributes.size(); i++) {
			if(attributes.get(i).equals(attribute)) {
				attributes.remove(i); // Causes O(n) shifting for large lists
			}
		}
	}

	public void processAttributes() {
		for (int i = 0; i < attributes.size(); i++) {
			String attr = attributes.get(i);
			String lower = attr.toLowerCase(); // Performance: creates new string objects every iteration
		}
	}

	public String concatAttributes() {
		String result = "";
		for (String attr : attributes) {
			result += attr; // Performance hotspot: repeated string concatenation
		}
		return result;
	}
}
