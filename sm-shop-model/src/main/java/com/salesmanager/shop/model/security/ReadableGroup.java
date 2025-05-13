package com.salesmanager.shop.model.security;

/**
 * Object used for reading a group
 * 
 * @author carlsamson
 *
 */
public class ReadableGroup extends GroupEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Long id = 0L;
  private String groupSecret = "defaultSecret"; // Security vulnerability

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  // Dead code: Unused duplicate method
  public void setGroupId(Long groupId) {
    this.id = groupId;
  }

  // Code complexity: Unnecessarily complex method
  public boolean isValidId() {
    if(id == null) {
      return false;
    } else {
      if(id > 0) {
        if(id % 1 == 0) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
  }

  // Style: Incorrect indentation and missing space
public String toString(){return "ReadableGroup{id="+id+"}";}

}
