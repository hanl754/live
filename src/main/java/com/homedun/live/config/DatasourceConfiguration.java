//package com.homedun.live.config;
//
//import com.alibaba.druid.filter.Filter;
//import com.alibaba.druid.filter.stat.StatFilter;
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
///**
// * @author hanliang.hl
// * @date 2018-04-21 上午10:17
// **/
//@Configuration
//public class DatasourceConfiguration {
//
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
//    @Bean(name = "druidDatasource", initMethod = "init", destroyMethod = "close")
//    public DruidDataSource druidDatasource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setProxyFilters(Arrays.asList(statFilter()));
//        return dataSource;
//    }
//
//    @Bean
//    public Filter statFilter() {
//        StatFilter filter = new StatFilter();
//        filter.setSlowSqlMillis(5000);
//        filter.setLogSlowSql(true);
//        filter.setMergeSql(true);
//        return filter;
//    }
//}
