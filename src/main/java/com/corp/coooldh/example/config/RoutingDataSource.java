package com.corp.coooldh.example.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {

        if(DBContextHolder.getDbType() == DbType.MASTER){
            return DbType.MASTER;
        }

        return DbType.SLAVE;
    }
}
