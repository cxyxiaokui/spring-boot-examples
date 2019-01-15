package cn.lijunkui.restful.custominterface;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyAddrValidator.class)
public @interface Addr {
	String message() default "不支持该地区！";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
