package org.sacc.smis.conf;

import org.sacc.smis.secure.process.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    SecureAuthenticationFailureHandler secureAuthenticationFailureHandler;

    @Autowired
    SecureAuthenticationSuccessHandler secureAuthenticationSuccessHandler;

    @Autowired
    SecureAccessDeniedHandler secureAccessDeniedHandler;

    @Autowired
    SecureSessionExpiredHandler secureSessionExpiredHandler;

    @Autowired
    SecureLogoutSuccessHandler secureLogoutSuccessHandler;

    @Autowired
    SecureLogoutHandler secureLogoutHandler;

    @Autowired
    SecureAuthenticationEntryPoint secureAuthenticationEntryPoint;

    private static final String[] NO_AUTH_LIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/register/**",
            "/application/**",
            "/validate/sendValidationEmail",
            "/validate/resetPassword"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and().formLogin()
                //登录失败处理，返回json
                .permitAll()
                .failureHandler(secureAuthenticationFailureHandler)
                //登录成功处理，返回json
                .successHandler(secureAuthenticationSuccessHandler)
                .and()
                .logout().logoutUrl("/logout").permitAll()
                // 配置用户登出自定义处理类
                .addLogoutHandler(secureLogoutHandler)
                // 配置用户登出成功自定义处理类
                .logoutSuccessHandler(secureLogoutSuccessHandler)
                .and().exceptionHandling()
                //请求登录处理，改变默认跳转登录页
                .authenticationEntryPoint(secureAuthenticationEntryPoint)
                // 配置没有权限自定义处理类
                .accessDeniedHandler(secureAccessDeniedHandler)
                // 同时登陆多个只保留一个,多余踢出用户
                .and().sessionManagement().maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                // 踢出用户操作
                .expiredSessionStrategy(secureSessionExpiredHandler)
                .and()
                .and().authorizeRequests().antMatchers(NO_AUTH_LIST).permitAll()
                .and().authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
