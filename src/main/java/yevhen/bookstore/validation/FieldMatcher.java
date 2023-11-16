package yevhen.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatcher implements ConstraintValidator<FieldMatch, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatch fields) {
        this.field = fields.field();
        this.fieldMatch = fields.fieldMatch();
    }

    @Override
    public boolean isValid(Object object,
                           ConstraintValidatorContext constraintValidatorContext) {
        Object firstValue = new BeanWrapperImpl(object).getPropertyValue(field);
        Object secondValue = new BeanWrapperImpl(object).getPropertyValue(fieldMatch);
        return firstValue != null
                && firstValue.equals(secondValue);
    }
}
