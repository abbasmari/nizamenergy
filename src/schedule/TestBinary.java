package schedule;

public class TestBinary {

	public static int xoring(int x, int y) {
		return (x ^ y);
	}

	public static String keyChan(int serial,int dueDate) {

//		int serial = 960017;
//		int dueDate = 857383;

		//////////////////////////////////////// First Phase XOR
		//////////////////////////////////////// ////////////////////////////////////////////////////////////////

		int xora = xoring(serial, dueDate);
		System.err.println("Decimal of XOR Phase 1 (Serial, Duedate): " + xora);

		///////////////////////////////////////// Maintaining XOR result to 6
		///////////////////////////////////////// digit

		String number1 = Integer.toString(xora);
		if (number1.length() != 6) {
			number1 = "0" + number1;
			System.err.println("Conversion: " + number1);
		}

		else {
			System.err.println("No conversion ");
		}
		System.err.println("Conversion: " + number1);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////// String to Integer back
		////////////////////////////////////////////// ///////////////////////////////////////////////////////////////////////////

		///////////////////////// Checking if XOR result is Even/Odd
		///////////////////////// ////////////////////////////////////////////////////////////

		if (xora % 2 == 0) {
			number1 = number1.charAt(0) + "" + number1.charAt(3) + "" + number1.charAt(1) + "" + number1.charAt(4) + ""
					+ number1.charAt(2) + "" + number1.charAt(5);
			System.err.println("Even Shifting: " + number1);
			// System.out.println("******************************");
		}

		else {
			number1 = number1.charAt(4) + "" + number1.charAt(3) + "" + number1.charAt(2) + "" + number1.charAt(1) + ""
					+ number1.charAt(0) + "" + number1.charAt(5);
			System.err.println("Odd Shifting: " + number1);
			// System.out.println("******************************");
		}

		//////////////////////////////////////// Second Phase XOR
		//////////////////////////////////////// ////////////////////////////////////////////////////////////////

		int xorb = xoring(Integer.valueOf(number1), dueDate);
		System.err.println("Decimal of XOR Phase 2 (XOR1, Duedate): " + xorb);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		///////////////////////////////////////// Maintaining XOR result to 6
		///////////////////////////////////////// digit
		///////////////////////////////////////// /////////////////////////////////////////////////////////////

		String number3 = Integer.toString(xorb);
		if (number3.length() != 6) {
			number3 = "0" + number3;
			System.err.println("Conversion: " + number3);
		}

		else {
			System.err.println("No conversion ");
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		///////////////////////// Checking if XOR result is Even/Odd
		///////////////////////// ////////////////////////////////////////////////////////////

		if (xorb % 2 == 0) {
			number3 = number3.charAt(0) + "" + number3.charAt(3) + "" + number3.charAt(1) + "" + number3.charAt(4) + ""
					+ number3.charAt(2) + "" + number3.charAt(5);
			System.err.println("Even Shifting: " + number3);
			// System.out.println("******************************");
		}

		else {
			number3 = number3.charAt(4) + "" + number3.charAt(3) + "" + number3.charAt(2) + "" + number3.charAt(1) + ""
					+ number3.charAt(0) + "" + number3.charAt(5);
			System.err.println("Odd Shifting: " + number3);
			// System.out.println("******************************");
		}

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		String CODE = number1 + "" + number3;
		System.err.println("Your monthly code " + CODE);

		//////////////////////////////////////// Decryptor////////////////////////

		String code1 = CODE.substring(0, 6);
		String code2 = CODE.substring(6, 12);

		System.out.println(code1 + " " + code2);

		int code2Integer = Integer.valueOf(code2);

		if (code2Integer % 2 == 0) {
			code2 = code2.charAt(0) + "" + code2.charAt(2) + "" + code2.charAt(4) + "" + code2.charAt(1) + ""
					+ code2.charAt(3) + "" + code2.charAt(5);
			System.err.println("Even Shifting: " + code2);
		} else {
			code2 = code2.charAt(4) + "" + code2.charAt(3) + "" + code2.charAt(2) + "" + code2.charAt(1) + ""
					+ code2.charAt(0) + "" + code2.charAt(5);
			System.err.println("Odd Shifting: " + code2);
		}

		int xorc = xoring(Integer.valueOf(code1), Integer.valueOf(code2));
		System.err.println("Decimal of XOR Phase 3 (XOR1, Duedate): " + xorc);

		//////////////////////////////////////// Decryptor2////////////////////////
		int code1Integer = Integer.valueOf(code1);
		if (code1Integer % 2 == 0) {
			code1 = code1.charAt(0) + "" + code1.charAt(2) + "" + code1.charAt(4) + "" + code1.charAt(1) + ""
					+ code1.charAt(3) + "" + code1.charAt(5);
			System.err.println("Even Shifting: " + code1);
		} else {
			code1 = code1.charAt(4) + "" + code1.charAt(3) + "" + code1.charAt(2) + "" + code1.charAt(1) + ""
					+ code1.charAt(0) + "" + code1.charAt(5);
			System.err.println("Odd Shifting: " + code1);
		}

		int xord = xoring(Integer.valueOf(code1), Integer.valueOf(xorc));
		System.err.println("Decimal of XOR Phase 4 (XOR1, Duedate): " + xord);
		
//		long lastKey = Long.parseLong(CODE);
		return CODE;
	}

	public static void main(String arg[]) {
		long fraction = (long)(6 * Math.random());
		System.out.println(fraction);
	}
}
