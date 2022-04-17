package com.ou.configs;


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableWebSecurity
//@EnableTransactionManagement
//@ComponentScan(basePackages = {
//        "com.ou.admin.repositories",
//        "com.ou.admin.services",
//        "com.ou.customer.repositories",
//        "com.ou.customer.services",
//})
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService ;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin().loginPage("/sign-in").usernameParameter("username")
//                .passwordParameter("password");
//        http.formLogin().defaultSuccessUrl("/homepage")
//                .failureUrl("/sign-in/?error");
//        http.logout().logoutUrl("/sign-out").logoutSuccessUrl("/sign-in");
//        http.exceptionHandling().accessDeniedPage("/sign-in?accessDenied");
//        http.authorizeRequests().antMatchers("/admin")
//                .hasAuthority("admin");
//        http.csrf().disable();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//}

public class SpringSecurityConfig {
}