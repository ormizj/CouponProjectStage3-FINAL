package com.jbc.util.serviceUtil.validationUtil;

import java.util.regex.Pattern;

/**
 * {@code interface} containing {@code default} methods related to
 * {@code String}s.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 */
public interface StringModifier {

	/**
	 * 
	 * @return Trimmed {@code String} and removes extra spaces, or {@code null}
	 *         {@code if} ({@code string} == {@code null})
	 */
	public default String trim(String string) {
		if (string == null)
			return null;
		return string.trim().replaceAll(" +", " ");
	}

	/**
	 * 
	 * @return {@code String} with its spaces removed, or {@code null} {@code if}
	 *         ({@code string} == {@code null})
	 */
	public default String removeSpace(String string) {
		if (string == null)
			return null;
		return string.replace(" ", "");
	}

	/**
	 * 
	 * @return True if the {@code String} is in email format.False if it's not in
	 *         email format or {@code if} ({@code string} == {@code null})
	 */
	public default boolean isEmail(String email) {
		if (email == null)
			return false;
		return Pattern.compile("^(.+)@(.+)$").matcher(email).matches();
	}

}
