package com.example.photoapp.api.gateway.zuul.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    Environment environment;

    @Autowired
    public WebSecurity(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Instructor doesn't have this in his versions!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //super.configure(http);

        http.csrf().disable();
        http.headers().frameOptions().disable(); //H2 screens don't work without this
        http.authorizeRequests()
                .antMatchers(environment.getProperty("api.h2console.url.path")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.register.url.path")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.login.url.path")).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthorizationFilter(authenticationManager(), environment));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
