package cn.lijunkui.config;

import cn.lijunkui.enums.DataSourceTypeEnum;

/**
 * @Author jkli
 * @Date 2020/6/14 2:57 下午
 **/
public class DynamicDataSource {


    // 使用ThreadLocal保证线程安全
    private static final ThreadLocal<DataSourceTypeEnum> TYPE = new ThreadLocal<DataSourceTypeEnum>();

    // 往当前线程里设置数据源类型
    public static void setDataBaseType(DataSourceTypeEnum dataBaseType) {
        if (dataBaseType == null) {
            throw new NullPointerException();
        }
        System.err.println("[将当前数据源改为]：" + dataBaseType);
        TYPE.set(dataBaseType);
    }

    // 获取数据源类型
    public static DataSourceTypeEnum getDataBaseType() {
        DataSourceTypeEnum dataBaseType = TYPE.get() == null ? DataSourceTypeEnum.PRODUCT : TYPE.get();
        System.err.println("[获取当前数据源的类型为]：" + dataBaseType);
        return dataBaseType;
    }

    // 清空数据类型
    public static void clearDataBaseType() {
        TYPE.remove();
    }
}
