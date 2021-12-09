package com.connecttosubh.security.OAuth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "couponservice";
//    @Value("${publicKey}")
//    private String publicKey;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID);//.tokenStore(jwtTokenStore());-- for symmetric key
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().mvcMatchers(
                        HttpMethod.GET, "/couponapi/coupons/{code:^[A-Za-z]*$}")
                .hasAnyRole("ADMIN", "USER")
                .mvcMatchers(HttpMethod.POST, "/couponapi/coupons").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and().csrf().disable();
    }

    //FOR SYMMETRIC KEY
//    @Bean
//    public JwtTokenStore jwtTokenStore()
//    {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter()
//    {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//
//
//       // KeyStoreKeyFactory factory = new KeyStoreKeyFactory(new ClassPathResource(keyFile), password.toCharArray());
//        //KeyPair keyPair = factory.getKeyPair(alias);
//       // converter.setVerifierKey(publicKey);
//        converter.setSigningKey("hello");
//        return converter;
//    }
}
