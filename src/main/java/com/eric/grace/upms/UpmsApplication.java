package com.eric.grace.upms;

import com.eric.grace.dao.dynamicDataSource.DynamicDataSourceRegister;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * UpmsApplication: 权限服务启动类
 *
 * @author: MrServer
 * @since: 2018/4/18 下午3:11
 */

@SpringBootApplication
@EnableTransactionManagement
@Import({DynamicDataSourceRegister.class})
@ComponentScan(basePackages = {"com.eric.grace.*"})
public class UpmsApplication {


    // jar启动
    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(UpmsApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}