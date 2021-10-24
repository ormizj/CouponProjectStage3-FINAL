package com.jbc.service.user.ifc;

/**
 * {@code interface} that contains the {@code login} method to the client
 * {@code Entity}s of the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Customer
 * @see user#Company
 */
public interface ClientLogin {

	/**
	 * 
	 * @param email
	 * @param password
	 * @return {@code true} if there is an existing client {@code Entity} with the
	 *         {@code email} and {@code password} in the system. {@code false} if
	 *         there isn't.
	 */
	public boolean login(String email, String password);

}
