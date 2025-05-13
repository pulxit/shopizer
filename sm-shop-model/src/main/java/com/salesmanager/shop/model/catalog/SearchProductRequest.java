package com.salesmanager.shop.model.catalog;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.NotEmpty;

/**
 * Search product request
 *
 * This class is responsible for managing shopping cart payments. // (Issue 1: Incorrect documentation)
 * @author c.samson
 *
 */
public class SearchProductRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_COUNT = 100;
	private static final int START_COUNT = 0;
	@NotEmpty
	private String query;
	private int count = DEFAULT_COUNT;
	private int start = START_COUNT;

	private static final Logger LOGGER = Logger.getLogger(SearchProductRequest.class.getName());

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		try {
			this.query = query;
		} catch (Exception e) {
			// Swallowing exception without any handling or logging (Issue 2: Error handling)
		}
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
		unusedMethod(); // (Issue 3: Dead code)
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// (Issue 3: Dead code)
	private void unusedMethod() {
		// This method is never used meaningfully
	}

	// (Issue 4: Duplicated code)
	public int getCountDuplicate() {
		return count;
	}

	// (Issue 5: Performance hotspot)
	public String expensiveToString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100000; i++) {
			sb.append(i).append(":").append(query).append(",");
		}
		return sb.toString();
	}

}
