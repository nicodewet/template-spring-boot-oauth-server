package com.nicodewet.oauth2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nicodewet.oauth2.domain.Credentials;

@Controller
/**
 * @see <a href="http://projects.spring.io/spring-security-oauth/docs/oauth2.html">Spring Security OAuth</a> and navigate to "Customizing the UI"
 */
public class AuthorizationServerUIController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
    public String showLoginForm(final Credentials credentials) {
        return "credentials";
    }
}
