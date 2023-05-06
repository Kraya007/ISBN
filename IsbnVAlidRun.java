package sectionA;

public class IsbnVAlidRun {
	public static void main(String[] args) {
		String[] testCases = { "9780316066525", "0330301824", "0316066524" };
		IsbnValid validator = new IsbnValid();
		for (String isbn : testCases) {
			System.out.print(isbn + ": ");
			String result = validator.validateIsbn(isbn);
			System.out.println(result);
		}
	}
}
