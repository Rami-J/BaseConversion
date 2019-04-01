package com.example.android.baseconversion;


public class BaseConverter
{
	public static final char[] HEX_CHARS = {'A','B','C','D','E','F'};
	public static final int[] HEX_VALS = {10, 11, 12, 13, 14, 15};

	/**
	 * Precondition: Parameter num is a number in base 2
	 * Postcondition: result is a string representing a number in the base the
	 * 	 *            user chooses (binary, octal, hex)
	 * @param num
	 * @return result
	 */
	// TODO: IMPLEMENT OTHER CASES
	public static String convertBinary(int num, String choice)
	{
		String s = num + "";
		int result = 0;
		int digitCounter = 0;

		if (choice.equalsIgnoreCase("Decimal"))
		{
			for (int i = s.length() - 1; i >= 0; i--)
			{
				result += Math.pow(2, digitCounter) * (num % 10);
				digitCounter++;
				num /= 10;
			}
		}

		else if (choice.equalsIgnoreCase("Octal"))
		{

		}

		else if (choice.equalsIgnoreCase("Hexadecimal"))
		{

		}

		else
		{
			return String.valueOf(num);
		}

		return String.valueOf(result);
	}

	/**
	 * Precondition: Parameter num is a number in base 8
	 * Postcondition: result is a string representing a number in the base the
	 * 	 *            user chooses (binary, octal, hex)
	 * @param num
	 * @return result
	 */
	// TODO: IMPLEMENT OTHER CASES
	public static String convertOctal(int num, String choice)
	{
		String s = num + "";
		int result = 0;
		int digitCounter = 0;

		if (choice.equalsIgnoreCase("Decimal"))
		{
			for (int i = s.length() - 1; i >= 0; i--)
			{
				result += Math.pow(8, digitCounter) * (num % 10);
				digitCounter++;
				num /= 10;
			}
		}

		else if (choice.equalsIgnoreCase("Binary"))
		{

		}

		else if (choice.equalsIgnoreCase("Hexadecimal"))
		{

		}

		else
		{
			return String.valueOf(num);
		}

		return String.valueOf(result);
	}

	/**
	 * Precondition: Parameter num is a string representing a number in base 16
	 * Postcondition: result is a string representing a number in the base the
	 * 	 *            user chooses (binary, octal, hex)
	 * @param num
	 * @return result
	 */
	// TODO: IMPLEMENT OTHER CASES
	public static String convertHexadecimal(String num, String choice)
	{
		int result = 0;
		int digitCounter = 0;
		boolean isChar;

		if (choice.equalsIgnoreCase("Decimal"))
		{
			for (int i = num.length() - 1; i >= 0; i--)
			{
				isChar = false;
				//for loop to check if char at index i is part of HEX_CHARS array
				for (int j = 0; j < HEX_CHARS.length; j++)
				{
					if (Character.toUpperCase(num.charAt(i)) == HEX_CHARS[j])
					{
						result += Math.pow(16, digitCounter) * HEX_VALS[j];
						isChar = true;
						break;
					}
				}

				if (!isChar)
				{
					result += Math.pow(16, digitCounter) * (Integer.parseInt(num.charAt(i) + "") % 10);
				}

				digitCounter++;
				num = num.substring(0, i);
			}
		}

		else if (choice.equalsIgnoreCase("Binary"))
		{

		}

		else if (choice.equalsIgnoreCase("Octal"))
		{

		}

		else
		{
			return num;
		}

		return String.valueOf(result);
	}

	/**
	 * Precondition:  num is a number in base 10
	 * Postcondition: result is a string representing a number in the base the
	 *                user chooses (binary, octal, hex)
	 * @param num, choice
	 * @return result
	 */
	// TODO: FIX DECIMAL TO HEX CONVERTER
	public static String convertDecimal(int num, String choice)
	{
		String s = num + "";
		String temp = "";
		String result = "";
		if ( choice.equalsIgnoreCase("Binary") )
		{
			for ( int i = 0; i <= s.length() + 1; i++ )
			{
				temp += num % 2 + "";
				num /= 2;
			}
		}

		else if ( choice.equalsIgnoreCase("Octal") )
		{
			for ( int i = 0; i <= s.length() + 1; i++ )
			{
				temp += num % 8 + "";
				num /= 8;
			}
		}

		else if ( choice.equalsIgnoreCase("Hexadecimal") )
		{
			for ( int i = 0; i <= s.length() + 1; i++ )
			{
				if ( num % 16 > 9 )
				{
					for (int j = 0; j < HEX_CHARS.length; j++)
					{
						if (num % 16 == HEX_VALS[j])
						{
							temp += HEX_CHARS[j];
							break;
						}
					}
				}
				else
				{
					temp += num % 16 + "";
				}
				num /= 16;
			}
		}

		else
		{
			return String.valueOf(num);
		}

		for (int i = temp.length() - 1; i >= 0; i--)
		{
			result += temp.charAt(i);
		}

		return result;
	}

	/**
	 * This function removes trailing zeroes that appear in the front of a given string.
	 * @param str the string to trim
	 * @return result
	 */
	public static String trimZeroes(String str)
	{
		String result = "";
		int len;

		if (str == null)
			return null;

		len = str.length();

		for (int i = 0; i < len; i++)
		{
			if (str.charAt(i) == '0')
			{
				result = str.substring(i + 1);
			}
			else
			{
				result = str.substring(i);
				break;
			}
		}

		// if the given string was a string of all zeroes return a single zero string
		if (result.equals(""))
			return "0";

		return result;
	}


}
