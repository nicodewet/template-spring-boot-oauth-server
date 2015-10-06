Creating an OAuth2 Authorization Server
=======================================

Spring Boot Initializr Setup
---------------------------

 * mkdir template-spring-boot-oauth-server && cd template-spring-boot-oauth-server
 * curl https://start.spring.io/starter.tgz -d style=web -d style=security -d name=authserver | tar -xzvf - 

Spring Security OAuth2
----------------------

Add the following dependency to your pom.xml:

	<dependency>
		<groupId>org.springframework.security.oauth</groupId>
		<artifactId>spring-security-oauth2</artifactId>
		<version>2.0.7.RELEASE</version>
	</dependency>

Up and Running
--------------

Naturally, since we are using Spring Boot, run the application via the main class or using Maven as follows:

	$ mvn spring-boot:run 

In your browser, visit the whitelabel UI provided by Spring OAuth: 

	http://localhost:9999/uaa/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com

The server is using Spring Boot default security settings, so the above will be protected using HTTP Basic authentication (username=user, password=password). With the above you are visiting the **authorization endpoint** and initiating an *authorizaton code token grant*.

If your grant access, you will be redirected to: 

	http://example.com/?code=OivfyQ

We now exchange the *authorization code* (**OivfyQ**) for an *access token* using the **token endpoint**.

	$ curl acme:acmesecret@localhost:9999/uaa/oauth/token -d grant_type=authorization_code -d client_id=acme -d redirect_uri=http://example.com -d code=OivfyQ

You will get a response similar to this: 
 
	{"access_token":"ce9222cb-6297-43bd-bcc0-d03281779afc","token_type":"bearer","refresh_token":"9291ad4d-b69e-4f28-b346-ed6709d59bf4","expires_in":43199,"scope":"openid"}
	
The UUID **ce9222cb-...** is our access token with an associated in-memory token store on our server. The refresh token **9291ad4d-...*** can be used to get a new access token when the current one expires.

