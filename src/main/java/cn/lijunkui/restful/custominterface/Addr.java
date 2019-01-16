package cn.lijunkui.restful.custominterface;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD})//修饰的范围是成员变量
@Retention(RetentionPolicy.RUNTIME)//该注解被保留的时间长短 RUNTIME:在运行时有效
@Constraint(validatedBy = MyAddrValidator.class)//指定校验逻辑处理的Class
public @interface Addr {
	String message() default "不支持该地区！";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
