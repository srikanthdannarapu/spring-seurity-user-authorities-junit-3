package com.srikanth.brewery.config;

import com.srikanth.brewery.security.MyPasswordEncoderFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return MyPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager){
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(restHeaderAuthFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();;
        http
                .authorizeRequests(authorize -> {
                    authorize
                            .antMatchers("/h2-console/**").permitAll() //do not use in production
                            .antMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll();
                })
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic() .and().csrf().disable();
        //h2 console config
        http.headers().frameOptions().sameOrigin();
    }

    //{noop} is not required if PasswordEncoder bean is placed in spring context
   /* @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin_user")
                .password("pass")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("pass")
                .roles("USER");
    }*/
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin_user")
                .password("{noop}pass")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{noop}pass")
                .roles("USER");
    }*/
   /* @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin_user")
                .password("pass")
                .roles("ADMIN")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("pass")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }*/


   /* @Bean
    PasswordEncoder passwordEncoder(){
        return new LdapShaPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin_user")
                .password("{SSHA}DKAaPFDlP+6zbAtnMeZtYRVboqaEA7oIKsf+Dw==")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{SSHA}DKAaPFDlP+6zbAtnMeZtYRVboqaEA7oIKsf+Dw==")
                .roles("USER");

        auth.inMemoryAuthentication().withUser("scott").password("tiger").roles("CUSTOMER");
    }*/


    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // valid key secrets are : user/pass , spring/guru, scott/tiger
        auth.inMemoryAuthentication()
                .withUser("spring")
                .password("{bcrypt}$2a$10$7tYAvVL2/KwcQTcQywHIleKueg4ZK7y7d44hKyngjTwHCDlesxdla")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{sha256}65e7f1297bd3f4019abe7bea07b06060d99d603324f89a11e0686f7f018a2d083d8460ab0d7c29ab")
                .roles("USER");

        auth.inMemoryAuthentication().withUser("scott").password("{ldap}{SSHA}A10yuLOEGbSTbHl7csQHk7X0X3rwrqdmBomRsA==").roles("CUSTOMER");
    }*/

}