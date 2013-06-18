package com.dmitrynikol.dynamic.client;

/**
 * How to extract all emails from string and replace them. 
 * Search and replacement e-mails.
 * 
 * @author Dmitry Nikolaenko
 *
 */
public final class StringUtils {
	public static final String EMAIL_REGEXP = 
			"([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})";
	
	/**
     * Only constructed by ourselves. Class contains only static methods.
     */
	private StringUtils() {}

	/**
	 * Replaces each email that matches the regular expression.
	 * 
	 * @param text the <code>String</code> that is contained required text to handle
	 * @return the resulting <tt>String</tt>
	 */
	public static String replaceEmail(final String text) {
		// array without any emails
		String[] words = text.split(EMAIL_REGEXP);

		String emails = text;
		String result = text;

		if (words.length > 1) {
			// gather only emails in one string
			for (String word : words) {
				if (!word.trim().isEmpty()) {
					emails = emails.replace(word, " ").trim();
				}
			}

			if (!emails.isEmpty()) {
				// execute replacement for pattern
				for (String word : emails.split(" ")) {
					if (text.contains(word)) {
						result = result.replaceAll(word, constructReplacement(word));
					}
				}
			}
		}

		return result;
	}

	private static String constructReplacement(final String email) {
		// String#format() doesn't work in GWT
		//return String.format("<a href=\"mailto:%s\">%s</a>", email, email);
		return new StringBuilder("<a href=mailto:").append(email).
				   append(">").append("Send email").append("</a>").toString();
	}
}