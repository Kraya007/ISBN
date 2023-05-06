package sectionA;

/**
 * @author kndub The International Standard Book Number (ISBN) is a unique
 *         identifying number given to each published book. ISBNs assigned after
 *         January 2007 are 13 digits long (ISBN-13), however books with
 *         10-digit ISBNs are still in wide use program complexity is O(n) for
 *         each charater a constant operation is being executed
 */
public class IsbnValid {

	private static final int ISBN13_LENGTH = 13; // isbn length of 13

	/**
	 * This method takes an ISBN and removes any hyphens or spaces,rhen convert it
	 * to ISBN13 format. If it is in ISBN13 format, it validates it as is. If an
	 * invalid ISBN10 is entered, it throws an exception plause.
	 *
	 * @param isbn The ISBN to be validated.
	 * @return Either a validated ISBN13 or an error message.
	 */
	public static String validateIsbn(String isbn) {
		isbn = removeHyphensAndSpaces(isbn);
		try {
			return convertToIsbn13(isbn);
		} catch (IllegalArgumentException e) {
			return isbn13(isbn);
		}
	}

	/**
	 * @param isbn
	 * @return the converted 1sbn13
	 */
	public static String convertToIsbn13(String isbn) {
		isbn = removeHyphensAndSpaces(isbn);
		if (isbn.length() != 10 || !isbn.matches("\\d{9}[\\d|X]")) {
			throw new IllegalArgumentException("Invalid ISBN-10: " + isbn);
		}
		String isbnWithoutCheckDigit = "978" + isbn.substring(0, 9);
		int checkDigit = calculateCheckDigit(isbnWithoutCheckDigit);
		return isbnWithoutCheckDigit + checkDigit;
	}

	/**
	 * @param isbn
	 * @return check num
	 */
	private static int calculateCheckDigit(String isbn) {
		int sum = 0;
		for (int i = 0; i < isbn.length(); i++) {
			int digit = Character.getNumericValue(isbn.charAt(i));
			sum += (i % 2 == 0) ? digit : digit * 3; // sum = sum+.....
		}
		int checkDigit = (10 - sum % 10) % 10;
		return checkDigit;
	}

	/**
	 * validates ispn
	 * 
	 * @param isbn
	 * @return either valid or invalid
	 */
	public static String isbn13(String isbn) {
		isbn = removeHyphensAndSpaces(isbn);
		if (isbn.length() != ISBN13_LENGTH || !isbn.matches("\\d{12}[\\d|X]")) {
			throw new IllegalArgumentException("Invalid ISBN-13: " + isbn);
		}
		int sum = 0;
		for (int i = 0; i < isbn.length(); i++) {
			int digit = Character.getNumericValue(isbn.charAt(i));
			sum += (i % 2 == 0) ? digit * 1 : digit * 3;
		}
		if (sum % 10 == 0) {
			return "Valid";
		} else {
			return "Invalid";
		}
	}

	private static String removeHyphensAndSpaces(String isbn) {
		return isbn.replaceAll("[\\-\\s]", "");
	}
}
