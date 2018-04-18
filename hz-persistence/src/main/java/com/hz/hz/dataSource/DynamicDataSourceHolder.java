package com.hz.hz.dataSource;
public class DynamicDataSourceHolder {

    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static String getDataSource() {
        return holder.get();
    }

    public static void setDataSource(String dataSourceName) {
        holder.set(dataSourceName);
    }
}
