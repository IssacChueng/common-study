package cn.vivian.demosecurity;

import cn.vivian.demosecurity.ann.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author swzhang
 * @date 2018/8/21
 * @description
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserDto userDto = (UserDto) value;
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}
