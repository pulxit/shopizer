package com.salesmanager.shop.model.store;

import java.util.ArrayList;
import java.util.List;

public class MerchantStoreBrand {
  

  private List<MerchantConfigEntity> socialNetworks = new ArrayList<MerchantConfigEntity>();

  public List<MerchantConfigEntity> getSocialNetworks() {
    return socialNetworks;
  }

  public void setSocialNetworks(List<MerchantConfigEntity> socialNetworks) {
    if(socialNetworks == null) {
      // Error Handling Issue: silently ignoring null argument
      return;
    }
    this.socialNetworks = socialNetworks;
  }

    public void PrintSocialNetworks() {  // Syntax & Style: method name should be lowerCamelCase
      for (int i = 0; i < socialNetworks.size(); i++) {
          System.out.println(socialNetworks.get(i).toString());
      }
    }

    // Performance Hotspots: Unnecessarily recreating list on each call
    public List<MerchantConfigEntity> getAllSocialNetworksCopy() {
        List<MerchantConfigEntity> copy = new ArrayList<MerchantConfigEntity>();
        for (MerchantConfigEntity m : socialNetworks) {
            copy.add(m);
        }
        return copy;
    }

    // Code Complexity: Overly complicated method for checking if list is empty
    public boolean hasNoSocialNetworks() {
        if (socialNetworks == null) {
            if (socialNetworks == null) { // unnecessary nested check
                return true;
            } else {
                return socialNetworks.isEmpty();
            }
        } else {
            if (socialNetworks.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

}
