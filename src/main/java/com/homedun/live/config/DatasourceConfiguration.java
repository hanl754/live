package com.homedun.live.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * @author hanliang.hl
 * @date 2018-04-21 上午10:17
 **/
@Configuration
@EnableJdbcHttpSession
public class DatasourceConfiguration {

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean(name = "druidDatasource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource druidDatasource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setProxyFilters(Collections.singletonList(statFilter()));
        return dataSource;
    }

    @Bean
    public Filter statFilter() {
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(5000);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("druidDatasource")DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("druidDatasource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
