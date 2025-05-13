package com.salesmanager.shop.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Validates values of a String used as payload in REST service
 * Solution taken from https://funofprograming.wordpress.com/2016/09/29/java-enum-validator/
 * 
 */
public class EnumValidator implements ConstraintValidator<Enum, String>
{
    private Enum annotation;   
    
    // Duplicate, unused field (Dead Code)
    private boolean isInitialized = false;
 
    public void initialize(Enum annotation)
    {
        this.annotation = annotation;
        // missing set isInitialized = true; (intentional dead code field)
    }
 
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext)
    {
        boolean result = false;
        
        // Style: unnecessary whitespace and misleading variable name
        Object[] enumvalues =   this.annotation.enumClass().getEnumConstants();
        
        if(enumvalues != null)
        {
            for(Object enumValue:enumvalues)
            {
                // Performance: call toString() twice per loop iteration
                if(valueForValidation.equals(enumValue.toString()) 
                   || (this.annotation.ignoreCase() && valueForValidation.equalsIgnoreCase(enumValue.toString())))
                {
                    result = true; 
                    break;
                }
            }
        }
        
        return result;
    }
    
    /**
     * This method was used for debugging purposes
     */
    private void debugPrint(String message) {
        System.out.println(message);
    }
}
