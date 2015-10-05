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

Up and Running
--------------

$ mvn spring-boot:run 

Visit: http://localhost:9999/uaa/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com

Server is using Spring Boot default security settings, so the above will be protected using HTTP Basic authentication. With the above you are visiting the *authorization endpoint* and initiating an authorizaton code token grant.

Redirected to: http://example.com/?code=OivfyQ

We now exchange the authorization code for an access token using the *token endpoint*.

$ curl acme:acmesecret@localhost:9999/uaa/oauth/token -d grant_type=authorization_code -d client_id=acme -d redirect_uri=http://example.com -d code=OivfyQ
 
{"access_token":"ce9222cb-6297-43bd-bcc0-d03281779afc","token_type":"bearer","refresh_token":"9291ad4d-b69e-4f28-b346-ed6709d59bf4","expires_in":43199,"scope":"openid"}

