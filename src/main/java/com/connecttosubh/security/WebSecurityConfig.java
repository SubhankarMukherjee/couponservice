package com.connecttosubh.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailSevice);
    }

    @Autowired
    UserDetailSeviceImpl userDetailSevice;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  http.httpBasic();
       // http.formLogin(); not required since we have our own login form and security service
        //http.authorizeRequests().mvcMatchers(HttpMethod.GET,"/couponapi/coupons/**")
        http.authorizeRequests().mvcMatchers(
                        HttpMethod.GET, "/couponapi/coupons/{code:^[A-Za-z]*$}",  "/index"
                        , "/getCoupon", "/showGetCoupon", "/couponDetails")
                .hasAnyRole("ADMIN", "USER")
                .mvcMatchers(HttpMethod.POST, "/getCoupon").hasAnyRole("ADMIN", "USER")
                .mvcMatchers(HttpMethod.POST, "/couponapi/coupons", "/saveCoupon", "/getCoupon","/assignRole").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/showCreateCoupon", "/createCoupon", "/createResponse","/SuccessAssign","/showAssignRole","/roleAssignment").hasRole("ADMIN")
                .mvcMatchers("/login","/","/logout","/showReg","/registerUser").permitAll() // GET and POST Both should be availble for everybody

                .anyRequest().denyAll()  // if pattern is not match all other URL will be denied
                .and().csrf().disable()
        //logout feature
               // .logout().logoutSuccessUrl("/").deleteCookies("JSESSION_ID").invalidateHttpSession(true);
                .logout().logoutSuccessUrl("/");
        ;

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
