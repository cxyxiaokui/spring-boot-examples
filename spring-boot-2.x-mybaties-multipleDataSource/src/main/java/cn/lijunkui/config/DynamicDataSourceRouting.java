package cn.lijunkui.config;

import cn.lijunkui.enums.DataSourceKeyEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
public class DynamicDataSourceRouting extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceKeyEnum dataSourceKey = DynamicDataSourceRoutingKeyState.getDataSourceKey();
        return dataSourceKey;
    }
}
