package com.jbc.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Home redirection to Swagger Api documentation.
 * <p>
 * <li>The exact site is: "http://URI/swagger-ui/"</li>
 * <li><b>Example:</b> "http://localhost:8080/swagger-ui/"
 */
@CrossOrigin(origins = "*")
@ApiIgnore
@Controller
public class HomeController {

	/**
	 * 
	 * @return Redirection to swagger api documentation.
	 */
    @RequestMapping(value = "/swagger")
    public String index() {
        return "redirect:swagger-ui/";
    }
    
}

