package yevhen.bookstore.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldMatcher.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {
    String message() default "Password and repeat password aren't equal";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
