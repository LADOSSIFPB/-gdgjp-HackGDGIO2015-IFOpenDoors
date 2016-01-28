package br.edu.ifpb.ifopendoors.validatio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements QManagerValidator{

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param email
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String email) {
		matcher = pattern.matcher(email.trim());
		return matcher.matches();
	}
}
