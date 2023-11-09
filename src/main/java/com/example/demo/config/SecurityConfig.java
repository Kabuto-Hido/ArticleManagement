package com.example.demo.config;

import com.example.demo.Util.ApplicationUserService;
import com.example.demo.filter.CORSFilter;
import com.example.demo.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.demo.config.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/", "/api/v1/register", "/api/v1/login",
                        "/api/v1/category/**", "/api/v1/post/**", "/api/v1/test/**",
                        "/api/v1/comment/get-all", "/api/v1/comment/getBy", "/api/v1/user/search",
                        "/v2/api-docs",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/api/v1/vnpay/payment-detail", "/api/v1/confirm/**", "/api/v1/sendEmail/**",
                        "/api/v1/recoveryPassword/**", "/api/v1/paypal/**",
                        "/api/v1/kafka/**").permitAll()
                .antMatchers("/api/v1/user/profile", "/api/v1/user/changeProfile",
                        "/api/v1/user/avatar", "/api/v1/post/createNew",
                        "/api/v1/post/update/**", "/api/v1/vnpay/**",
                        "/api/v1/paypal/create-payment").hasAnyRole(ADMIN.name(), USER.name())
                .antMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
                .antMatchers("/api/v1/user/**", "/api/v1/feeling/**",
                        "/api/v1/comment/**").hasRole(USER.name())
                .anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class);
    }
}
