package ll.mrli.lixf.common.validator;

import ll.mrli.lixf.common.annotation.IsMobile;
import ll.mrli.lixf.common.entity.RegexpConstant;
import ll.mrli.lixf.common.utils.LixfUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<IsMobile, String> {
    @Override
    public void initialize(IsMobile constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if(StringUtils.isBlank(value)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE_REG;
                return LixfUtil.match(regex, value);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
