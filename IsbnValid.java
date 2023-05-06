package sectionA;

public class IsbnValid {

	private static final int ISBN13_LENGTH = 13;

	public static String validateIsbn(String isbn) {
		isbn = removeHyphensAndSpaces(isbn);
		try {
			return convertToIsbn13(isbn);
		} catch (IllegalArgumentException e) {
			return isbn13(isbn);
		}
	}

	public static String convertToIsbn13(String isbn) {
		isbn = removeHyphensAndSpaces(isbn);
		if (isbn.length() != 10 || !isbn.matches("\\d{9}[\\d|X]")) {
			throw new IllegalArgumentException("Invalid ISBN-10: " + isbn);
		}
		String isbnWithoutCheckDigit = "978" + isbn.substring(0, 9);
		int checkDigit = calculateCheckDigit(isbnWithoutCheckDigit);
		return isbnWithoutCheckDigit + checkDigit;
	}

	private static int calculateCheckDigit(String isbn) {
		int sum = 0;
		for (int i = 0; i < isbn.length(); i++) {
			int digit = Character.getNumericValue(isbn.charAt(i));
			sum += (i % 2 == 0) ? digit : digit * 3;
		}
		int checkDigit = (10 - sum % 10) % 10;
		return checkDigit;
	}

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
