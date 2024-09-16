package cn.fo9c.educationaltourism;

import cn.fo9c.educationaltourism.utils.UUIDGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class EducationalTourismApplication {

    public static void main(String[] args) {
        // 新启动项追加路径显示

        // URL的环境变量
        ConfigurableApplicationContext application = SpringApplication.run(EducationalTourismApplication.class, args);
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        String property = env.getProperty("server.servlet.context-path");
        String path = property == null ? "" :  property;

        // SpringBoot的环境变量
        String springBootVersion = SpringBootVersion.getVersion();

        // Mybatis的环境变量
        String mybatisVersion = "3.0.3";
        System.out.println(
                "———————————————————————————————————————————————————————————————————————————————————————————————————————————\n\t" +
                "         Access URLs:   http://localhost:" + port + path + "              SpringBoot Version: " + springBootVersion + "\n\t" +
                "         DataBase:      MySQL                              Mybatis    Version: " + mybatisVersion + "\n" +
                "———————————————————————————————————————————————————————————————————————————————————————————————————————————");
    }

}
