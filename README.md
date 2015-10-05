Creating an OAuth2 Authorization Server
=======================================

Spring Boot Initializr Setup
---------------------------

 * mkdir template-spring-boot-oauth-server && cd template-spring-boot-oauth-server
 * curl https://start.spring.io/starter.tgz -d style=web -d style=security -d name=authserver | tar -xzvf - 

Spring Security OAuth2
----------------------

<dependency>
  <groupId>org.springframework.security.oauth</groupId>
  <artifactId>spring-security-oauth2</artifactId>
  <version>2.0.7.RELEASE</version>
</dependency>
