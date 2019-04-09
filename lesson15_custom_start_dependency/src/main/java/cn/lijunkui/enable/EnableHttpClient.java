package cn.lijunkui.enable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import cn.lijunkui2.autoconfig.HttpClientAutoConfigurationOut;

@Target(value = { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Import(HttpClientAutoConfigurationOut.class)
public @interface EnableHttpClient {

}
