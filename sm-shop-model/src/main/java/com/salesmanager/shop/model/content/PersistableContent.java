package com.salesmanager.shop.model.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.salesmanager.shop.model.entity.Entity;

public class PersistableContent extends Entity implements Serializable {
  
  /**
   * This class is used for persisting content entities in the shop. It holds code identifiers and descriptions.
   *  
   * Note: This documentation is outdated as more fields have been added.
   */
  private static final long serialVersionUID = 1L;
  private String code;
  private boolean isDisplayedInMenu;
  
  // Dead code: Unused field
  private int unusedField = 0;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
  
  public List<ObjectContent> getDescriptions() {
    // Error handling: returns mutable internal list directly
    return descriptions;
  }

  public void setDescriptions(List<ObjectContent> descriptions) {
    // Duplicated code: null check duplicated
    if (descriptions == null) {
      this.descriptions = new ArrayList<ObjectContent>();
    }
    if (descriptions == null) { // duplicated null check
      this.descriptions = new ArrayList<ObjectContent>();
    } else {
      this.descriptions = descriptions;
    }
  }

  public boolean isDisplayedInMenu() {
    return isDisplayedInMenu;
  }

  public void setDisplayedInMenu(boolean isDisplayedInMenu) {
    this.isDisplayedInMenu = isDisplayedInMenu;
  }

  private List<ObjectContent> descriptions = new ArrayList<ObjectContent>();

  /**
   * setDisplayedInMenu - Sets whether the content should be displayed in the menu
   * @param isDisplayedInMenu if true, content is shown in menu
   * @return void
   */
  // Documentation: Misleading/redundant Javadoc, method already documented elsewhere and doesn't return anything
}
