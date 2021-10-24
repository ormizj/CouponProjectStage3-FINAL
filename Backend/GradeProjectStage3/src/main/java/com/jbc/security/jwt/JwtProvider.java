package com.jbc.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.jbc.security.services.UserPrinciple;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * {@code Component} for the Jwt Security system to create Jwt tokens.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 */
@Component
public class JwtProvider {

	/* attributes */
	@Value("${com.jwt.secret}")
	private String jwtSecret;
	@Value("${com.jwt.expiration.hours}")
	private int jwtExpiration;

	/**
	 * 
	 * @param authentication
	 * @return Jwt token.
	 */
	public String generateJwtToken(Authentication authentication) {
		UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000 * 60 * 60))
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
	}

	/**
	 * 
	 * @param authToken
	 * @return {@code true} if the Jwt token is valid. {@code false} if the Jwt
	 *         token is not valid.
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid JWT signature -> Message: " + e);
		} catch (MalformedJwtException e) {
			System.out.println("Invalid JWT token -> Message: " + e);
		} catch (ExpiredJwtException e) {
			System.out.println("Expired JWT token -> Message: " + e);
		} catch (UnsupportedJwtException e) {
			System.out.println("Unsupported JWT token -> Message: " + e);
		} catch (IllegalArgumentException e) {
			System.out.println("JWT claims string is empty -> Message: " + e);
		}
		return false;
	}

	/**
	 * 
	 * @param token
	 * @return {@code Username} from the Jwt token.
	 */
	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

}
