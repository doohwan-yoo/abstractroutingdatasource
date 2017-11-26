package com.corp.coooldh.example.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource routeDataSource() {
        return new RoutingDataSource() {{
            setDefaultTargetDataSource(masterDataSource());
            setTargetDataSources(new HashMap<Object, Object>() {{
                put(DbType.MASTER, masterDataSource());
                put(DbType.SLAVE, slaveDataSource());
            }});
        }};
    }

    @Bean
    @Primary
    public LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy() {
        return new LazyConnectionDataSourceProxy(routeDataSource());
    }
}
