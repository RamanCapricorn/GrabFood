package Com.base;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CustomRandom {

	static StringBuilder builder;
	private static final String UPPER_ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER_ALPHA_STRING = "abcdefghijklmnopqrstuvwxyz";
	private static final String SPECIAL_CHARACTER_STRING = "@#$%&*!";
	private static final String NUMERIC_STRING = "0123456789";
	private static final String FIRST_FIVE_NUMBERS = "56789";

	public static String randomAlphaNumeric(int count) {
		builder = new StringBuilder();
		int Uflag = 0, Sflag = 0;

		while (count-- != 0) {

			if (Uflag != 1) {
				int upperChar = (int) (Math.random() * UPPER_ALPHA_STRING.length());
				builder.append(UPPER_ALPHA_STRING.charAt(upperChar));
				Uflag = 1;
				count--;
			}

			int lowerChar = (int) (Math.random() * LOWER_ALPHA_STRING.length());
			builder.append(LOWER_ALPHA_STRING.charAt(lowerChar));

			if (count == 5 && Sflag != 1) {
				int SpecialChar = (int) (Math.random() * SPECIAL_CHARACTER_STRING.length());
				builder.append(SPECIAL_CHARACTER_STRING.charAt(SpecialChar));
				Sflag = 1;
				count--;
			}

			if (count <= 1) {
				int NumChar = (int) (Math.random() * NUMERIC_STRING.length());
				builder.append(NUMERIC_STRING.charAt(NumChar));
				count--;
			}
		}
		return builder.toString();
	}

	public static String mobileNumber(int count) {
		builder = new StringBuilder();

		int FNumChar = (int) (Math.random() * FIRST_FIVE_NUMBERS.length());
		System.out.println();
		builder.append(FIRST_FIVE_NUMBERS.charAt(FNumChar));

		return baseMethod(count-1);
	}

	public static String licenseNumber(int count) {

		builder = new StringBuilder();
		return baseMethod(count);
	}

	public static String accountNumber(int count) {
		builder = new StringBuilder();

		int FNumChar = (int) (Math.random() * FIRST_FIVE_NUMBERS.length());
		System.out.println();
		builder.append(FIRST_FIVE_NUMBERS.charAt(FNumChar));

		return baseMethod(count-1);
	}

	public static String branchCode(int count) {

		builder = new StringBuilder();
		return baseMethod(count);
	}

	public static String chequeNumber(int count) {

		builder = new StringBuilder();
		return baseMethod(count);
	}

	public static String baseMethod(int count){

		while (count-- != 0) {

			int LNumChar = (int) (Math.random() * NUMERIC_STRING.length());
			builder.append(NUMERIC_STRING.charAt(LNumChar));
		}
		return builder.toString();
	}

	public static Hashtable<Integer,String> randomAmount() {

		Hashtable<Integer,String> amount = new Hashtable<Integer, String>();
		amount.put(5000, "Five Thousand");
		amount.put(10000, "Ten Thousand");
		amount.put(15000, "Fifteen Thousand");
		amount.put(20000, "Twenty Thousand");
		amount.put(25000, "Twenty five Thousand");
		amount.put(30000, "Thirty Thousand");
		amount.put(35000, "Thirty five Thousand");
		amount.put(40000, "Fourty Thousand");
		amount.put(45000, "Fourty five Thousand");
		amount.put(50000, "Fifty Thousand");
		amount.put(55000, "Fifty five Thousand");
		amount.put(60000, "Sixty Thousand");
		amount.put(65000, "Sixty five Thousand");
		amount.put(70000, "Seventy Thousand");
		amount.put(75000, "Seventy five Thousand");
		amount.put(80000, "Eighty Thousand");
		amount.put(85000, "Eighty five Thousand");
		amount.put(90000, "Ninty Thousand");
		amount.put(95000, "Ninty five Thousand");
		amount.put(100000, "One hundred Thousand");

		List<Integer> K = new ArrayList<>(amount.keySet());
		int rv = ThreadLocalRandom.current().nextInt(0, K.size());
		int Amount = K.get(rv);
		String AmountWords = amount.get(K.get(rv));
		
		Hashtable<Integer,String> RanAmount = new Hashtable<Integer, String>();
		RanAmount.put(Amount, AmountWords);
		
		return RanAmount;
	}
}
