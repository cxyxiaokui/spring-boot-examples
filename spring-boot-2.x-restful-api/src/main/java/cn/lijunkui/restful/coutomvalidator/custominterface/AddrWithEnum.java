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
@Constraint(validatedBy = AddrWithEnum.Validator.class)
public @interface AddrWithEnum {
	public enum AddrEnum{ HEBEI,BEIJING,TIANJING};
    AddrEnum addr() default AddrEnum.BEIJING;
    String message() default "不支持该地区！";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    public class Validator implements ConstraintValidator<AddrWithEnum, String> {
    	
    	AddrEnum addrEnum;
    	@Override
        public void initialize(AddrWithEnum addrWithEnum){
    		addrEnum = addrWithEnum.addr();
        }
		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if(value.equals(addrEnum.toString())) {
				return true;
			}
			return false;
		}
       
    }
}
