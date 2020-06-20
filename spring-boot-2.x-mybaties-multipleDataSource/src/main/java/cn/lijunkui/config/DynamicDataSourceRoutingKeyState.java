package cn.lijunkui.config;


import cn.lijunkui.enums.DataSourceKeyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
public class DynamicDataSourceRoutingKeyState {

    private static Logger log = LoggerFactory.getLogger(DynamicDataSourceRoutingKeyState.class);
    // 使用ThreadLocal保证线程安全
    private static final ThreadLocal<DataSourceKeyEnum> TYPE = new ThreadLocal<DataSourceKeyEnum>();

    // 往当前线程里设置数据源类型
    public static void setDataSourceKey(DataSourceKeyEnum dataSourceType) {
        if (dataSourceType == null) {
            throw new NullPointerException();
        }
        log.info("[将当前数据源改为]：{}",dataSourceType);
        TYPE.set(dataSourceType);
    }

    // 获取数据源类型
    public static DataSourceKeyEnum getDataSourceKey() {
        DataSourceKeyEnum dataSourceKey = TYPE.get();
        log.info("[获取当前数据源的类型为]：{}",dataSourceKey);
        System.err.println("[获取当前数据源的类型为]：" + dataSourceKey);
        return dataSourceKey;
    }

    // 清空数据类型
    public static void clearDataSourceKey() {
        TYPE.remove();
    }
}
