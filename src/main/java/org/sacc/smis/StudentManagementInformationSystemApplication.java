package org.sacc.smis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
//@ComponentScan(basePackages = "org.sacc.smis.mapper")
//@EnableGlobalMethodSecurity(securedEnabled=true)
public class StudentManagementInformationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementInformationSystemApplication.class, args);
    }

}
