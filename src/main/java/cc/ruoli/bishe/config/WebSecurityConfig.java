package cc.ruoli.bishe.config;

import cc.ruoli.bishe.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @Author: 若离
 * @Date: 2021/9/7 17:03
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    LoginFailureHandler loginFailureHandler;

    @Resource
    LoginSuccessHandler loginSuccessHandler;

    @Resource
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Resource
    JwtLogoutSuccessHandler jwtLogoutSuccessHandler;


    private static final String[] URL_WHITELIST = {
            "/login",
            "/logout",
            "/captcha",
            "/favicon.ico",
    };

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }

//    密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()

                .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)


                .and()
                .logout()
                .logoutSuccessHandler(jwtLogoutSuccessHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated()


                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 配置自定义的过滤器
                .and()
                .addFilter(jwtAuthenticationFilter());
                //.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)

    }
}
