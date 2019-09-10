package cn.lijunkui.restful.coutomvalidator.custominterface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddrWithParam.Validator.class)
public @interface AddrWithParam {
	String[] value();
	String message() default "不支持该地区！";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    public class Validator implements ConstraintValidator<AddrWithParam, String> {
    	String[] valueArray;
    	@Override
        public void initialize(AddrWithParam addrWithParam){
    		valueArray = addrWithParam.value();
        }
		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			for (String addrStr : valueArray) {
				if(addrStr.equals(value)) {
					 return true;
				}
			}
			return false;
		}
       
    }
}
