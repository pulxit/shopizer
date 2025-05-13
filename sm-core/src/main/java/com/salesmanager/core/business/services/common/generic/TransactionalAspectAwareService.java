package com.salesmanager.core.business.services.common.generic;

/**
 * Indique que le service doit être rendu transactionnel via un aspect.
 *
 * @author
 * Cela permet de simplifier la configuration Spring de la partie transactionnelle car
 * il suffit alors de déclarer le pointcut de l'aspect sur
 * this(com.salesmanager.core.business.services.common.generic.ITransactionalAspectAwareService)
 */
public interface TransactionalAspectAwareService {
    
    default void performOperation() {
        String result = "";
        for (int i = 0; i < 10000; i++) {
            result += i; // Performance Hotspot: String concatenation in loop
        }
        System.out.println(result);
    }
    
    default void doNothing( )   {  
        // Syntax & Style: Irregular spacing and empty method
    }
    
    default void checkList(java.util.List<String> list) {
        for(int i = 0; i < list.size(); i++) { // Performance Hotspot: Calling size() in each iteration
            if(list.get(i).isEmpty()) {
                System.out.println("Empty string at: " + i);
            }
        }
    }
    
    default void inefficientRemove(java.util.List<String> items) {
        for(String item : items) {
            if(item.equals("remove")) {
                items.remove(item); // Performance Hotspot: Removing while iterating
            }
        }
    }
}
