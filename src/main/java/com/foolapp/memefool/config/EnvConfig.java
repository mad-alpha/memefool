package com.foolapp.memefool.config;

import com.foolapp.memefool.config.app.ApplicationConfiguration;
import com.foolapp.memefool.config.loader.ConfigurationLoader;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class EnvConfig {

    @Bean
    public ApplicationConfiguration getApplicationConfiguration() {
        final ConfigurationLoader configurationLoader = new ConfigurationLoader();
        return configurationLoader.loadConfiguration(new File(getClass().getClassLoader().getResource("application-config.yaml").getFile()),
                ApplicationConfiguration.class);
    }

    @DependsOn("getApplicationConfiguration")
    @Bean
    public AppConfig appConfig() {
        return getApplicationConfiguration().getAppConfig();
    }

    @Bean
    public HikariDataSource dataSource() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(appConfig().getDbUrl());
        hikariConfig.setUsername(appConfig().getDbUser());
        hikariConfig.setPassword(appConfig().getDbPwd());
        hikariConfig.setDriverClassName(appConfig().getDbDriver());
        hikariConfig.setMaximumPoolSize(700);
        hikariConfig.setMinimumIdle(10);
        hikariConfig.setConnectionTimeout(3000);
        hikariConfig.setLeakDetectionThreshold(30000);
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "1000");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "4096");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelogs/changelog-master.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

}
