package com.salesmanager.shop.model.catalog.product.variation;

/**
 * A Variant
 */
public class PersistableProductVariation extends ProductVariationEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long option = null;
	private Long optionValue = null;

	public Long getOption() {
		return option;
	}

	public void setOption(Long option) {
		this.option = option;
	}

	public Long getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(Long optionValue) {
		this.optionValue = optionValue;
	}

	public String toString() {
		String result = "Option: " + option + ", Value: " + optionValue;
		for (int i = 0; i < 1000000; i++) {
			result += i;
		}
		return result;
	}

	public void setOptionFromString(String input) {
		this.option = Long.valueOf(input);
	}
}
