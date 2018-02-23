package com.zyy.springcloud.cache.core.load;


import com.zyy.springcloud.cache.core.Cache;

import javax.sql.DataSource;

public class SqlLoadHandler implements LoadHandler {
    public DataSource datasource;
    public String sql;

    public DataSource getDatasource() {
        return datasource;
    }

    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void load(Cache cache) {
        //exe sql
    }

}