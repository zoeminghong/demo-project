package com.example.mybatis.config;

import com.example.mybatis.util.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 说明：
 *
 * @author 周靖捷
 * Created by 周靖捷 on 2017/8/17.
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    @Autowired
    public SqlSessionFactoryBean sqlSessionFactoryBean(PageInterceptor pageInterceptor, DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.setProperty("mapUnderscoreToCamelCase", "true");
        properties.setProperty("jdbcTypeForNull", "NULL");
        sqlSessionFactoryBean.setTypeHandlersPackage("com.example.mybatis.typehandler");
        sqlSessionFactoryBean.setConfigurationProperties(properties);
        Interceptor[] plugins = new PageInterceptor[]{pageInterceptor};
        sqlSessionFactoryBean.setPlugins(plugins);
        return sqlSessionFactoryBean;
    }


    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.example.mybatis.dao");
        mapperScannerConfigurer.setBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }
}
