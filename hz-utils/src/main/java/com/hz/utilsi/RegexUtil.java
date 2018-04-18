
package com.hz.utilsi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexUtil {
	
	
	public static boolean isCard(String cardNum){
		if(null == cardNum || "".equals(cardNum)){
			return false;
		}
		String regex = "(\\d{17}[0-9a-zA-Z])|(\\d{14}[0-9a-zA-Z])";
		return  cardNum.matches(regex);
	}
	

	public static boolean isIp(String ip) {
		if (null == ip || "".equals(ip))
			return false;
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		return ip.matches(regex);
	}

	
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return email.matches(regex);
	}

	
	public static boolean isChinese(String text) {
		if (null == text || "".equals(text))
			return false;
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(text);
		return m.find();
	}


	public static boolean isNumber(String number) {
		if (null == number || "".equals(number))
			return false;
		String regex = "[0-9]*";
		return number.matches(regex);
	}

	
	public static boolean isDecimal(String decimal, int count) {
		if (null == decimal || "".equals(decimal))
			return false;
		String regex = "^(-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){" + count + "})?$";
		return decimal.matches(regex);
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		if (null == phoneNumber || "".equals(phoneNumber))
			return false;
		String regex = "^1\\d{10}$";
		return phoneNumber.matches(regex);
	}

	
	public static boolean hasSpecialChar(String text) {
		if (null == text || "".equals(text))
			return false;
		if (text.replaceAll("[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() == 0) {
			
			return true;
		}
		return false;
	}

	
	public static boolean isChinese2(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}
	
	
}
