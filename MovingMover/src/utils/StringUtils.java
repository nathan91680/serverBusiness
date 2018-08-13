package utils;

public class StringUtils {
	
	public static boolean isEmptyOrNull(String stringToTest) {
		boolean isValid = false;
		if(stringToTest != null && !stringToTest.trim().isEmpty()) {
			isValid = true;
		}
		return !isValid;
	}

}
