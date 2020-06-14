package cn.lijunkui.config;


import cn.lijunkui.enums.DataSourceTypeEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 通过AOP 动态切换数据源切面类
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
@Aspect
@Component
public class DataSourceAop {

    Logger log = LoggerFactory.getLogger(DataSourceAop.class);

    @Pointcut("execution( * cn.lijunkui.dao.*.*(..))")
    public void daoAspect() {
    }
    @Before(value="daoAspect()")
    public void switchDataSource(JoinPoint joinPoint) throws NoSuchMethodException {
        log.info("开始切换数据源");

        //获取HotelMapper 获取 ProductMapper 类上声明的TargetDataSource的数据源注解的值
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Class<?> declaringClass  = methodSignature.getMethod().getDeclaringClass();
        TargetDataSource annotation = declaringClass.getAnnotation(TargetDataSource.class);
        DataSourceTypeEnum value = annotation.value();
        log.info("数据源为：{}",value);

        //根据TargetDataSource的value设置要切换的数据源
        DynamicDataSource.setDataBaseType(value);
    }
}
