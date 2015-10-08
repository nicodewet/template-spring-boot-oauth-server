package com.nicodewet.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@SpringBootApplication
public class AuthserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthserverApplication.class, args);
    }
    
    @Configuration
    @EnableAuthorizationServer
    protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

      @Autowired
      private AuthenticationManager authenticationManager;
      
      @Override
      public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
      }

      @Override
      public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("acme")
            .secret("acmesecret")
            .authorizedGrantTypes("authorization_code", "refresh_token",
                "password").scopes("openid");
      }
    }
    
    // Global authentication configuration ordered *after* the one in Spring
 	// Boot (so the settings here overwrite the ones in Boot). The explicit
 	// order is not needed in Spring Boot 1.2.3 or greater. (Actually with Boot
 	// 1.2.3 you don't need this inner class at all and you can just @Autowired
 	// the AuthenticationManagerBuilder).
 	@Configuration
 	@Order(Ordered.LOWEST_PRECEDENCE - 20)
 	protected static class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

 		@Override
 		public void init(AuthenticationManagerBuilder auth) throws Exception {
 			// @formatter:off
 			auth.inMemoryAuthentication().withUser("min").password("min").roles("USER");
 			// @formatter:on
 		}

 	}
}
