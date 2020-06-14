package cn.lijunkui.config;

import cn.lijunkui.enums.DataSourceTypeEnum;

import java.lang.annotation.*;

/**
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    DataSourceTypeEnum value() default DataSourceTypeEnum.HOTEL;
}
