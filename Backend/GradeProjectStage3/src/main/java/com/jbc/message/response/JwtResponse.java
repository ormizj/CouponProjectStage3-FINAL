package com.jbc.message.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * {@code class} used for Jwt responses.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 */
@Setter
@Getter
@ApiModel(description = "Jwt response for the Jwt token.")
public class JwtResponse {

	/* attributes */
	@ApiModelProperty(value = "Jwt token of the user.")
	private String token;
	@ApiModelProperty(value = "Will always be \"Bearer\".", example = "Bearer")
	private String type = "Bearer";
	@ApiModelProperty(value = "Email of the user.")
	private String username;
	@ApiModelProperty(value = "Authorities of the user.\n\n" + "Authority List:\r\n\n" + "CUSTOMER\r\n" + "COMPANY\r\n"
			+ "ADMIN")
	private Collection<? extends GrantedAuthority> authorities;

	/* constructor */
	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
		this.token = accessToken;
		this.username = username;
		this.authorities = authorities;
	}

}
