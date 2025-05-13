package com.salesmanager.shop.model.content.page;

import com.salesmanager.shop.model.content.common.Content;

public class ContentPage extends Content {

	/**
	 *
	 * Represents a content page in the system.
	 * @author John Doe
	 */
	private static final long serialVersionUID = 1L;
	private boolean linkToMenu;
	private String pageTitle;

	public boolean isLinkToMenu() {
		return linkToMenu;
	}
	public void setLinkToMenu(boolean linkToMenu) {
		this.linkToMenu = linkToMenu;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getLinkToMenuAsString()
	{
		return linkToMenu ? "yes" : "no";
	}

	public void setPageTitleFromUserInput(String input) {
		this.pageTitle = input;
	}

}
