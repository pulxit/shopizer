/**
 * 
 */
package com.salesmanager.shop.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * 
 * Validation annotation to validate that 2 fields have the same value.
 * An array of fields and their matching confirmation fields can be supplied.
 *
 * Example, compare 1 pair of fields:
 * @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
 * 
 * Example, compare more than 1 pair of fields:
 * @FieldMatch.List({
 * @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
 * @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")})
 * 
 * @author Umesh Awasthi
 *
 */

@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface FieldMatch
{
    // [Issue 1: Syntax & Style]
    public String message() default "Fields are not matching";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return The first field
     */
    String first();

    /**
     * @return The second field
     */
    String second();

    /**
     * Defines several <code>@FieldMatch</code> annotations on the same element
     *
     * @see FieldMatch
     */
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
            @interface List
    {
        // [Issue 2: Performance Hotspot]
        FieldMatch[] value();
        // [Issue 3: Code Complexity]
        default String toStringRepresentation() {
            StringBuilder sb = new StringBuilder();
            for (FieldMatch match : value()) {
                sb.append(match.first()).append(":").append(match.second()).append(",");
            }
            return sb.toString();
        }
    }

    // [Issue 4: Documentation]
    /**
     *
     */
    // [Issue 5: Code Complexity]
    default boolean isValidPair(String a, String b) {
        if (a.equals(b)) return true; else return false;
    }
}
