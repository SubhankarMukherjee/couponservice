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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;


//Commented For OATH
//@Configuration
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
                .and().csrf().disable() // Disabled to configure CSRF
        //logout feature
               // .logout().logoutSuccessUrl("/").deleteCookies("JSESSION_ID").invalidateHttpSession(true);
                .logout().logoutSuccessUrl("/");


        //CSRF Support for Ignoring URL

//        http.csrf(csrfCustomizer->{
//            //Directly Ignoring URL
//            csrfCustomizer.ignoringAntMatchers("/couponapi/coupons/**");
//
//            //Using Request Matchers
//            csrfCustomizer.ignoringAntMatchers(String.valueOf(new MvcRequestMatcher(new HandlerMappingIntrospector(),"/getCoupon")));
//            //Using Regex Matcher
//            csrfCustomizer.ignoringAntMatchers(String.valueOf(new RegexRequestMatcher("/couponapi/coupons/{code:^[A-Za-z]*$}","GET")));
//
//
//        })

        //CORS Support for allowing URL
//        http.cors(corsCustomizer -> {
//            CorsConfigurationSource corsConfigurationSource=request->{
//                CorsConfiguration configuration = new CorsConfiguration();
//                configuration.addAllowedMethod("GET");
//                configuration.addAllowedOrigin(("localhost:3000"));
//                return configuration;
//            };
//            corsCustomizer.configurationSource(corsConfigurationSource);
//        });
////        ;

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
