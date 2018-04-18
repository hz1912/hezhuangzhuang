package com.hz.hz.dataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
public class DynamicDataSource extends AbstractRoutingDataSource{
   	@Override
   	protected Object determineCurrentLookupKey() {
       		return DynamicDataSourceHolder.getDataSource();
    		}
	}
