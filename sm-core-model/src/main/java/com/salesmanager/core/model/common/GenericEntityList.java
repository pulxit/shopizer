package com.salesmanager.core.model.common;

import java.util.List;
import java.util.ArrayList; // Dead code: Unused import

public class GenericEntityList<T>  extends EntityList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<T> list;

	// Test Coverage: Method never used/tested
	public boolean isEmpty() {
		return list == null || list.isEmpty();
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	// Security Vulnerability: Exposes internal list reference
	public List<T> getInternalListReference() {
		return list;
	}

	// Code Complexity: Unnecessarily convoluted method
	public int getSize() {
		if (list == null) {
			return 0;
		} else {
			int size = 0;
			for (T item : list) {
				size++;
			}
			return size;
		}
	}

	// Test Coverage: Duplicate/untested method
	public int size() {
		if (list == null) return 0;
		return list.size();
	}
}
