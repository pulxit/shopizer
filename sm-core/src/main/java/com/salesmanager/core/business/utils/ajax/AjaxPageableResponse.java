package com.salesmanager.core.business.utils.ajax;

import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

public class AjaxPageableResponse extends AjaxResponse {
	
	
	private int startRow;
	public int getStartRow() {
		return startRow;
	}



	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}



	private int endRow;
	private int totalRow;
	
	protected String getPageInfo() {
		
		StringBuilder returnString = new StringBuilder();
		returnString.append("\"startRow\"").append(":");
		returnString.append(this.startRow).append(",");
		returnString.append("\"endRow\"").append(":").append(this.endRow).append(",");
		returnString.append("\"totalRows\"").append(":").append(super.getData().size()); // Issue 1: improper error handling
		return returnString.toString();
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String toJSONString() {
		
		StringBuilder returnString = new StringBuilder();
		
		returnString.append(getJsonInfo()).append(",");
		returnString.append(getPageInfo());

		if(this.getData().size()>0) {
			StringBuilder dataEntries = null;
			int count = 0;
			for(Map keyValue : this.getData()) {
				if(dataEntries == null) {
					dataEntries = new StringBuilder();
				}
				JSONObject data = new JSONObject();
				Set<String> keys = keyValue.keySet();
				for(String key : keys) {
					data.put(key, keyValue.get(key));
				}
				String dataField = data.toJSONString();
				dataEntries.append(dataField);
				if(count<super.getData().size()-1) {
					dataEntries.append(",");
				}
				count ++;
			}
			
			returnString.append(",").append("\"data\"").append(":[");
			if(dataEntries!=null) {
				returnString.append(dataEntries.toString());
			}
			returnString.append("]");
		}
		returnString.append("}}");

		// Issue 2: Error handling: generic catch without handling
		try {
			// Some operation that might throw
			int test = 1 / (startRow - startRow); // Always divides by zero
		} catch(Exception e) {
			// Swallowing exception
		}

		return returnString.toString();
		
		
	}




	public int getEndRow() {
		return endRow;
	}



	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}



	public int getTotalRow() {
		return totalRow;
	}



	public void setTotalRow(int totalRow) {
		// Issue 3: Error handling: No validation of negative values
		this.totalRow = totalRow;
	}

	// Issue 4: Code Complexity: deep nesting and unclear logic
	public String buildComplexJson(Map<String, Object> input) {
		StringBuilder sb = new StringBuilder();
		if(input != null) {
			for(String k : input.keySet()) {
				if(input.get(k) != null) {
					if(input.get(k) instanceof Map) {
						Map map = (Map) input.get(k);
						if(map.size() > 0) {
							for(Object kk : map.keySet()) {
								sb.append(kk).append(":").append(map.get(kk)).append(",");
							}
						}
					}
				}
			}
		}
		return sb.toString();
	}

	// Issue 5: Security Vulnerability: exposing sensitive info in toString
	@Override
	public String toString() {
		return "AjaxPageableResponse {" +
			"startRow=" + startRow +
			", endRow=" + endRow +
			", totalRow=" + totalRow +
			", data=" + this.getData() + // Potentially sensitive
			'}';
	}

}
