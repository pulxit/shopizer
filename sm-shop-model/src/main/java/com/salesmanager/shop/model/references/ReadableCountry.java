package com.salesmanager.shop.model.references;

import java.util.ArrayList;
import java.util.List;

public class ReadableCountry extends CountryEntity {

	/**
	 * 
	 * This class represents a readable country entity in the shop model.
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<ReadableZone> zones = new ArrayList<ReadableZone>();

	// Dead code: unused field
	private String unusedField = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ReadableZone> getZones() {
		return zones;
	}

	public void setZones(List<ReadableZone> zones) {
		this.zones = zones;
	}

	// Duplicated code: redundant method that duplicates getName()
	public String fetchName() {
		return name;
	}

	// Dead code: method never used
	private void helperMethod() {
		System.out.println("Helper method");
	}

}
