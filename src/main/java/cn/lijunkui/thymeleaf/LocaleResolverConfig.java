package cn.lijunkui.thymeleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LocaleResolverConfig {
	@Bean(name="localeResolver")
	public LocaleResolver localeResolverBean() {
	    return new SessionLocaleResolver();
	}
}
