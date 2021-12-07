package com.sda.online_store_final_project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@DependsOn("passwordEncoder")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/home").permitAll()

                .antMatchers("/order/finish/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/admin/product/new").access("hasAnyRole('ADMIN')")
                .antMatchers("/admin/delete/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/admin/**").access("hasAnyRole('ADMIN')")
                // Customer
                .antMatchers(HttpMethod.POST,"/cart/checkout/**").access("hasAnyRole('CUSTOMER')")
                .antMatchers("/cart/**").access("!hasAnyRole('ADMIN')")

                .antMatchers("/order/**").authenticated()
                .antMatchers("/profiles/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/login") // Submit URL
                .failureUrl("/login?error")//
                .usernameParameter("username")//
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403");

    }



    // FOR TESTING
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("{bcrypt}$2a$12$8LaArtd39wA49kaVYFM7k.s5YJo3WfhNrMo6arfSR6wFwlH4oMl82").roles("CUSTOMER");
//        auth.inMemoryAuthentication().withUser("admin").password("{bcrypt}$2a$12$FtK2yWqrj/xAf983cdVDMORGPtsixqMvBC5bagafzu2AoEsjdSvy6").roles("ADMIN");
//    }

}
