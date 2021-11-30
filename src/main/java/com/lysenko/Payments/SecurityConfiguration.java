package com.lysenko.Payments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthSuccessHandler authSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.debug("onConfigure set userDetailsService");
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("onConfigure set httpSecurity");
        http.authorizeRequests()
                .antMatchers("admin", "customer", "customer-account/block", "customer-account/unblock",
                        "customer/block", "customer/unblock", "unblock_account", "request_unblock_account").hasRole("ADMIN")
                .antMatchers("user", "account", "top_up", "payment/create", "payment/new", "block",
                        "sent-request", "add_account").hasRole("USER")
                .antMatchers("/","registration").permitAll()
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .successHandler(authSuccessHandler)
                .and()
                .logout().logoutUrl("/sign_out")
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        log.debug("getPasswordEncoder");
        return NoOpPasswordEncoder.getInstance();
    }
}
