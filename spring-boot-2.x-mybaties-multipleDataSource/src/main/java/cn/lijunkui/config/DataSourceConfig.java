package cn.lijunkui.config;

import cn.lijunkui.enums.DataSourceKeyEnum;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 多数据库源配置类
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
@Configuration
public class DataSourceConfig {

    /**
     * 商品库的数据源
     * @return
     */
    @Bean(name = "dataSourceForProduct")
    @ConfigurationProperties(prefix="spring.datasource.druid.product")
    public DruidDataSource dataSourceForProduct() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 旅馆库的数据源
     * @return
     */
    @Bean(name = "dataSourceForHotel")
    @ConfigurationProperties(prefix="spring.datasource.druid.hotel")
    public DruidDataSource dataSourceForHotel() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态切换的数据源
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {

        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceKeyEnum.PRODUCT, dataSourceForProduct());
        targetDataSource.put(DataSourceKeyEnum.HOTEL, dataSourceForHotel());
        //设置默认的数据源和以及多数据源的Map信息
        DynamicDataSourceRouting dataSource = new DynamicDataSourceRouting();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(dataSourceForProduct());
        return dataSource;
    }

    /**
     * 多数据源的 SqlSessionFactory
     * @param dynamicDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        //设置数据数据源的Mapper.xml路径
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        //设置Mybaties查询数据自动以驼峰式命名进行设值
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session
                .Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);

        return bean.getObject();
    }

    /**
     * 注入 DataSourceTransactionManager 用于事务管理
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dynamicDataSource);
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
