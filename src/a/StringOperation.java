package a;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringOperation {
	/***
	 * � ������ ����� ������ �-� ����� �������� �������� ��������. 
	 * ���� � ������ ����� �����, ������������� �� ���������.
	 */
	public static final int FLAG_1 = 1;
	public static final int FLAG_0 = 0;
	public static final int ALPHABETIC_ORDER = 0;
	public static final int LENGTH_WORD_ORDER = 1;
	public static void task_1(String text, char symbol, int index) {
		text = text.trim();
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile("\\s");
		String[] words = text.split(pattern.pattern());
		for(String word : words) {
			if(word.matches("\\p{Punct}+")) {
				continue;
			} else {
				if(index >= word.length()) {
					result.append(word + " ");
				} else {
					char[] arr = word.toCharArray();
					arr[index] = symbol;
					word = String.valueOf(arr);
					result.append(word + " ");
					//������������� �������
//					StringBuilder sb = new StringBuilder(word);
//					sb.setCharAt(index, symbol);
//					result.append(sb.toString() + " ");
				}
			}
		}
		System.out.println(result.toString());
	}
	/***
	 * � ������ ������ ����� �������� �� ���������� ������� � ��������. 
	 * ��� ������ � ����� ������ �������� ����� � ����� ��������� ����� �������,
	 * � ��������� ������ ����� ��� ������ ������ �������� �� �����. 
	 */
	public static void task_2(String text) {
		StringBuilder textBuilder = new StringBuilder("Text: ");
		StringBuilder alphabetBuilder = new StringBuilder("Numb: ");
		for(char symb : text.toCharArray()) {
			if(symb != ' '){
				textBuilder.append(String.format("%-5s", symb));
				if(Character.isAlphabetic(symb)) {
					alphabetBuilder.append((String.format("%-5d", (int) symb - 'a' + 1)));
				} else {
					alphabetBuilder.append(String.format("%-5s", " "));
				}
			} 
		}
		System.out.println(textBuilder.toString());
		System.out.println(alphabetBuilder.toString());
	}
	/***
	 * � ������ ����� ����� �, ���� ��� �� ��������� � �����, �������� ���������� ����� � ������ �.
	 * ������ ����������� � �����
	 */
	public static void task_3(String text) {
		StringBuilder result = new StringBuilder();
		String[] words = text.split(" ");
		for(String word : words) {
			/////////
			char[] chs = word.toCharArray();
			for(int i = 0; i < chs.length; i++) {
				if(chs[i] == '�' || chs[i] == '�') {
					if(i < chs.length - 1) {
						if(chs[i + 1] == '�') {
							chs[i + 1] = '�';
						} else if(chs[i + 1] == '�') {
							chs[i + 1] = '�';
						}
					}
				}
			}
			result.append(String.valueOf(chs) + " ");
			//////////
		}
		System.out.println(result.toString());
	}
	/***
	 * � ������, ����� �-�� ������� �������� �������� ���������
	 */
	public static void task_4(String text, String substring, int index) {
		StringBuilder result = new StringBuilder();
		if(index < text.length()) {
			String before = text.substring(0, index);
			String after = text.substring(index, text.length());
			result.append(before);
			result.append(substring);
			result.append(after);
		} else {
			result.append(text);
		}
		System.out.println(result.toString());
	}
	/***
	 * ����� ������� ����� ������, ���������������� �������� ����������, �������� ��������� �����
	 */
	public static void task_5(String text, String substring, String word) {
		StringBuilder result = new StringBuilder();
		String[] words = text.split("\\s");
		for(String str : words) {
//			System.out.println(str.substring(str.length() - substring.length()));
			if(str.length() > substring.length()) {
				if(str.substring(str.length() - substring.length()).equals(substring)) {
					result.append(str + " ");
					result.append(word + " ");
				} else {
					result.append(str + " ");
				}
			} 
		}
		System.out.println(result.toString());
	}
	/***
	 * � ����������� �� �����a�� (0 ��� 1) � ������ ������ ������ ������� ��������� ������ ����e, 
	 * ��� �� �����������, ��� �������� ��� ����� �-�� �������.
	 */
	public static void task_6(int parameter, String text, char symb, int index) {
		StringBuilder result = new StringBuilder();
		if(parameter >= 1) {
			String before = text.substring(0, index);
			String after = text.substring(index, text.length());
			result.append(before);
			result.append(symb);
			result.append(after);
		} else {
			for(char ch : text.toCharArray()) {
				if(ch != symb) {
					result.append(ch);
				}
			}
		}
		System.out.println(result.toString());
	}
	/***
	 * �� ������ ������� ��� �������, ����� ��������, �� ���������� �������. 
	 * ����� ������������������� ������ ������ ����, �������� ���� �� ���� ������.
	 */
	public static void task_7(String text) {
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile("\\p{Punct}");
		String[] words = text.split(pattern.pattern());
		for(String word : words) {
			if(word != "\\s" && word.length() > 1) {
				char[] chrs = word.toCharArray();
				for(char ch : chrs) {
					if(ch != chrs.length - 1) {
						result.append(ch + " ");
					} else {
						result.append(ch);
					}
				}
			} else {
				result.append(word);
			}
		}
		System.out.println(result);
	}
	/***
	 * ������� ����� ����� ����������
	 */
	public static void task_8(String text, char symbol) {
		StringBuilder result = new StringBuilder();
		char[] chars = text.toCharArray();
		boolean[] bools = new boolean[chars.length];
		int[] startIndexs = new int[chars.length];
		int[] endIndexs = new int[chars.length];
		int idS = 0, idE = 0;
		boolean isFirst = true;
		for(int i = 0 ; i < chars.length; i++) {
			if(chars[i] == symbol) {
				if(isFirst == true) {
					startIndexs[idS] = i;
					idS++;
					isFirst = false;
				} else {
					endIndexs[idE] = i;
					idE++;
					isFirst = true;
				}
			}
		}
		for(int i = 0; i < endIndexs.length; i++) {
			int flag = endIndexs[i] - startIndexs[i];
			if(flag > 0) {
				for(int j = startIndexs[i]; j < endIndexs[i] + 1; j++) {
					bools[j] = true;
				}
			} else if(flag < 0) {
				for(int j = endIndexs[i]; j < startIndexs[i] + 1; j++) {
					bools[j] = true;
				}
			} else {
				break;
			}
		}
		for(int i = 0; i < chars.length; i++) {
			if(bools[i] == false) {
				result.append(chars[i]);
			}
		}
		System.out.println(result.toString());
	}
	/***
	 * ������� ����� ����� ����������
	 */
	public static void task8_2(String text)
	{
		String newStr = text.replaceAll("\\..*\\.", "");
		System.out.println(newStr);
	}
	/***
	 * ����������, ������� ��� ����������� � ������ ������ �����, ������� ����������� � ���.
	 */
	public static void task_9(String text) {
		int count = 1;
		Pattern pattern = Pattern.compile("\\p{Punct}");
		String str = text.replaceAll(pattern.pattern(), "");
		String[] words = str.split("\\s");
		String[] sorted = sort(words, 0, words.length - 1, ALPHABETIC_ORDER);
		for(int i = 0; i < sorted.length - 1; i++) {
			if(sorted[i].equals(sorted[i + 1])) {
				count++;
			} else {
				System.out.println(sorted[i] + ": " + count);
				count = 1;
			}
		}
	}
	private static String[] sort(String[] words, int left, int right, int flag) {
		int i = left, j = right;
		if(flag == ALPHABETIC_ORDER) {
			String mid = words[(i + j) / 2];
			while(words[i].compareTo(mid) < 0) {
				i++;
			}
			while(words[j].compareTo(mid) > 0) {
				j--;
			}
			if(i <= j) {
				String tmp = words[i];
				words[i] = words[j];
				words[j] = tmp;
				i++;
				j--;
			}
			if(i < right) {
				sort(words, i, right, flag);
			}
			if(j > left) {
				sort(words, left, j, flag);
			}
		} else if(flag == LENGTH_WORD_ORDER) {
			int mid = words[(i + j) / 2].length();
			while(words[i].length() > mid) {
				i++;
			}
			while(words[j].length() < mid) {
				j--;
			}
			if(i <= j) {
				String tmp = words[i];
				words[i] = words[j];
				words[j] = tmp;
				i++;
				j--;
			}
			if(i < right) {
				sort(words, i, right, flag);
			}
			if(j > left) {
				sort(words, left, j, flag);
			}
		}
//		int i = left, j = right;
//		String mid = words[(i + j) / 2];
//		while(words[i].compareTo(mid) < 0) {
//			i++;
//		}
//		while(words[j].compareTo(mid) > 0) {
//			j--;
//		}
//		if(i <= j) {
//			String tmp = words[i];
//			words[i] = words[j];
//			words[j] = tmp;
//			i++;
//			j--;
//		}
//		if(i < right) {
//			sort(words, i, right, flag);
//		}
//		if(j > left) {
//			sort(words, left, j, flag);
//		}
		if(i == right && j == left) {
			return words;
		} else {
			return words;
		}
	}
	/***
	 * � ������ ����� � ���������� n ���� (� �� ����������), ������������� �������� �����.
	 */
	public static void task_10(String text, int n) {
		int count = 1, j = 0;
		Pattern pattern = Pattern.compile("\\p{Punct}");
		String str = text.replaceAll(pattern.pattern(), "");
		String[] words = str.split("\\s");
		String[] sorted = sort(words, 0, words.length - 1, ALPHABETIC_ORDER);
		String[] wordsByCount = new String[sorted.length];
		int[] counts = new int[sorted.length];
		for(int i = 0; i < sorted.length - 1; i++) {
			if(sorted[i].equals(sorted[i + 1])) {
				count++;
			} else {
				wordsByCount[j] = sorted[i];
				counts[j] = count;
				count = 1;
				j++;
			}
		}
		for(int i = 1; i < counts.length; i++) {
			for(int k = counts.length - 1; k >= i; k--) {
				if(counts[k] != 0) {
					if(counts[k - 1] < counts[k]) {
						int tmp = counts[k];
						counts[k] = counts[k - 1];
						counts[k - 1] = tmp;
						
						String string = wordsByCount[k];
						wordsByCount[k] = wordsByCount[k - 1];
						wordsByCount[k - 1] = string;
					}
				} 
			}
		}
		for(int i = 0; i < n; i++) {
			if(counts[i] == 0 && i < n) {
				System.out.println("��� �������� �������� n");
				break;
			} else {
				System.out.println(wordsByCount[i] + ": " + counts[i]);
			}
		}
	}
	/**
	 * �����, ����� ����, ������� ��� ���������, ������ � ������ ����������� ������.
	 */
	public static void task_11(String text) {
		int countVowels = 0, countConsonent = 0;
		Pattern splitText = Pattern.compile("([\\.!?;])([\\s]+)");
		Pattern vowels = Pattern.compile("[�����������Ũ��������]");
		Pattern consonent = Pattern.compile("[��������������������������������������������]");
		String[] offers = (text + " ").split(splitText.pattern());
		for(int i = 0; i < offers.length; i++) {
			Matcher matcherV = vowels.matcher(offers[i]);
			Matcher matcherC = consonent.matcher(offers[i]);
			while(matcherV.find()) {
				countVowels++;
			} 
			while(matcherC.find()) {
				countConsonent++;
			}
			if(countConsonent == countVowels) {
				System.out.println("In offer " + i + " count consonent = vowels" + countConsonent);
			} else if(countConsonent > countVowels) {
				System.out.println("In offer " + i + " count consonent > vowels"  + countConsonent);
			} else {
				System.out.println("In offer " + i + " count consonent < vowels"  + countConsonent);
			}
		}
	}
	/**
	 * � ����������� ����� ����������� ����, ������������ � ��������������� ������� ������
	 */
	public static void task_12(String text) {
		int count = 0;
		String[] words = text.split("[\\s\\p{Punct}]+");
		Pattern pattern = Pattern.compile("^[�����������Ũ��������][�-��-�]*[�����������Ũ��������]$",
				Pattern.MULTILINE);
		for(String word : words) {
			Matcher matcher = pattern.matcher(word);
			if(matcher.find()) {
				count++;
			}
		}
		System.out.println(count);
	}
	/**
	 * ���������� ��� ���������� ����� ������, � ������� ������ � ��������� ����� �������
	 */
	public static void task_13(String text) {
		int k = 0;
		System.out.println(text + "\n");
		boolean isAdd = false;
		String[] noSames = new String[text.length()];
		String[] words = text.split(" ");
		Pattern pattern = Pattern.compile("[�-��-�]+\\p{Punct}+");
		for(int i = 0; i < words.length; i++) {
			Matcher matcher = pattern.matcher(words[i]);
			if(matcher.find()) {
				String[] tmp = words[i].split("\\p{Punct}");
				words[i] = tmp[0];
				Pattern p = Pattern.compile("^[�����������Ũ��������][�-��-�]*[�����������Ũ��������]$");
				Matcher m = p.matcher(words[i]);
				if(m.find()) {
					if(noSames[0] == null) {
						noSames[0] = words[i];
						
					} else {
						for(int j = 0; j < noSames.length; j++) {
							if(noSames[j] != null) {
								if(noSames[j].equals(words[i])) {
									isAdd = false;
									break;
								} else {
									isAdd = true;
								}
							}
						}
						if(isAdd == true) {
							k++;
							noSames[k] = words[i];
						}
					}
				}
			} else {
				Pattern p = Pattern.compile("^[�����������Ũ��������][�-��-�]*[�����������Ũ��������]$");
				Matcher m = p.matcher(words[i]);
				if(m.find()) {
					if(noSames[0] == null) {
						noSames[0] = words[i];
						
					} else {
						for(int j = 0; j < noSames.length; j++) {
							if(noSames[j] != null) {
								if(noSames[j].equals(words[i])) {
									isAdd = false;
									break;
								} else {
									isAdd = true;
								}
							}
						}
						if(isAdd == true) {
							k++;
							noSames[k] = words[i];
						}
					}
				}
			}
		}
		for(int i = 0; i < noSames.length; i++) {
			if(noSames[i] != null) {
				System.out.println(noSames[i]);
			} else {
				break;
			}
		}
	}
	/**
	 * � ������ ����� � ���������� ��� ����� ������������ � ����������� �����
	 */
	public static void task_14(String text) {
		int min = Integer.MAX_VALUE, max = 0;
		String[] words = text.split("\\s+");
		Pattern pattern = Pattern.compile("[�-��-�]*\\d*\\p{Punct}+");
		for(int i = 0; i < words.length; i++) {
			Matcher matcher = pattern.matcher(words[i]);
			if(matcher.find()) {
				String[] tmp = words[i].split("\\p{Punct}");
				words[i] = tmp[0];
			}
		}
		for(String word : words) {
			if(word.length() > max) {
				max = word.length();
			}
			if(word.length() < min) {
				min = word.length();
			}
		}
		for(String word : words) {
			if(word.length() == max || word.length() == min) {
				System.out.println(word);
			}
		}
	}
	/**
	 * ���������� ��������� �� ������ ����������, ���� ��������� ������ ����� ������.
	 */
	public static void task_15(String text, double priceWord) {
		double totalPrice = 0;
		int count = 0;
		String[] words = text.split("\\s");
		Pattern pattern = Pattern.compile("[�-��-�]*[a-zA-Z]*\\s*\\p{Punct}+");
		Pattern p = Pattern.compile("\\p{Punct}+");
		for(int i = 0; i < words.length; i++) {
			Matcher matcher = pattern.matcher(words[i]);
			if(matcher.find()) {
				String[] tmp = words[i].split("\\p{Punct}");
				if(tmp.length >= 1) {
					words[i] = tmp[0];
				}
			}
			Matcher m = p.matcher(words[i]);
			if(m.find()) {
				words[i] = "";
			}
		}
		for(String word : words) {
			if(word != null && word != "") {
				totalPrice += priceWord;
				count++;
			}	
		}
		System.out.printf("%-10s%-15s%-15s%n", "Count", "Price word", "Total price");
		System.out.printf("%-10.5s%-15.10s%-15.11s%n", "-----", "----------", "-----------");
		System.out.printf("%3d%14.2f$%15.2f$%n", count, priceWord, totalPrice);
	}
	/**
	 * � ������ ����� ���������� �����, ������� ����������� �� ���� ������
	 * 
	 */
	public static void task_16(String text) {
		int countSame = 1;
		String[] words = text.split("\\s");
		String str = "";
		Pattern pattern = Pattern.compile("[�-��-�]*\\p{Punct}+");
		Pattern symbP = Pattern.compile("\\p{Punct}+");
		for(int i = 0; i < words.length; i++) {
			Matcher m1 = pattern.matcher(words[i]);
			if(m1.find()) {
				String[] tmp = m1.group().split("\\p{Punct}");
				if(tmp.length >= 1) {
					words[i] = tmp[0];
				}	
			}
			Matcher m2 = symbP.matcher(words[i]);
			if(m2.find()) {
				words[i] = null;
			}
		}
		for(int i = 0; i < words.length; i++) {
			char[] chars = sort(words[i].toCharArray(), 0, words[i].toCharArray().length - 1);
			words[i] = String.valueOf(chars);
		}
		words = sort(words, 0, words.length - 1, LENGTH_WORD_ORDER);
		for(int i = 0; i < words.length; i++) {
			char[] chrs = words[i].toCharArray();
			boolean[] bool = new boolean[chrs.length];
			for(int j = 0; j < chrs.length - 1; j++) {
				if(chrs[j] == chrs[j + 1]) {
					bool[j + 1] = true;
				}
			}
			for(int j = 0; j < bool.length; j++) {
				if(!bool[j]) {
					str += chrs[j];
				}
			}
			words[i] = str;
			str = "";
		}
		char[] chrs1 = words[0].toCharArray();
		for(int i = 1; i < words.length; i++) {
			for(int j = 0; j < chrs1.length; j++) {
				for(int k = 0; k < words[i].toCharArray().length; k++) {
					if(chrs1[j] == words[i].toCharArray()[k]) {
						str += chrs1[j] + " ";
						break;
					}
				}
			}
		}
		String[] result = str.split("\\s");
		result = sort(result, 0, result.length - 1, ALPHABETIC_ORDER);
		for(int i = 0; i < result.length - 1; i++) {
			if(result[i].equals(result[i + 1])) {
				countSame++;
				if(countSame == words.length - 1) {
					System.out.print(result[i] + " ");
					countSame = 1;
				}
			}
		}
	}
	private static char[] sort(char[] words, int left, int right) {
		int i = left, j = right;
		char mid = words[(i + j) / 2];
		while(words[i] < mid) {
			i++;
		}
		while(words[j] > mid) {
			j--;
		}
		if(i <= j) {
			char tmp = words[i];
			words[i] = words[j];
			words[j] = tmp;
			i++;
			j--;
		}
		if(i < right) {
			sort(words, i, right);
		}
		if(j > left) {
			sort(words, left, j);
		}
		if(i == right && j == left) {
			return words;
		} else {
			return words;
		}
	}
	/**
	 * � ������ ����� ������ ��������� ������������ �����, �� ���������� ����.
	 */
	public static void task_17(String text) {
		StringBuilder tmp = new StringBuilder();
		Pattern pattern = Pattern.compile("\\d*\\p{Punct}*\\d.*");
		String result = "";
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
			tmp.append(matcher.group());
		}
		for(int i = 0; i < tmp.toString().toCharArray().length; i++) {
			char ch = tmp.toString().toCharArray()[i];
			if(!Character.isAlphabetic(ch)) {
				result += ch;
			} else {
				System.out.println(result);
				break;
			}
		}
	}
	/**
	 * � ������ ���������� ��� ��������� �����, ������������� �� ����� ��� � ���� ������
	 */
	public static void task_18(String text) {
		int count = 2;
		String[] words = text.split("\\s");
		Pattern pattern = Pattern.compile("[��������������������������������������������]+");
		for(int i = 0; i < count; i++) {
			char[] wordCh = words[i].toCharArray();
			for(char ch : wordCh) {
				Matcher matcher = pattern.matcher(String.valueOf(ch));
				if(matcher.find()) {
					System.out.print(matcher.group() + " ");
				}
			}
		}
	}
	/**
	 * ������������� ����� ���, ����� ������ �����, ������� �� �������� ������������ �������
	 * , ���������� � ��������� �����
	 */
	public static void task_19(String text) {
		StringBuilder result = new StringBuilder();
		String[] words = text.split("\\s");
		Pattern pattern = Pattern.compile("^[�-��-�]+$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		for(int i = 0; i < words.length; i++) {
			Matcher matcher = pattern.matcher(words[i]);
			if(matcher.find()) {
				char symb = words[i].charAt(0);
				String first = String.valueOf(symb).toUpperCase();
				symb = first.charAt(0);
				StringBuilder tmp = new StringBuilder(words[i]);
				tmp.setCharAt(0, symb);
				result.append(tmp);
				result.append(" ");
			} else {
				result.append(words[i]);
				result.append(" ");
			}
		}
		System.out.println(result);
	}
	/**
	 * ���������� ���������� ������ ���������� � ������
	 */
	public static void task_20(String text) {
		int count = 0;
		Pattern pattern = Pattern.compile("\\p{Punct}");
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
			System.out.println(matcher.group());
			count++;
		}
		System.out.println("The text include: " + count + " symbols");
	}
	/**
	 * � �������� ������ ����� ����� ���� ������������� ����
	 */
	public static void task_21(String text) {
		int result = 0;
		Pattern pattern = Pattern.compile("\\d");
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
			String tmp = matcher.group();
			result += Integer.valueOf(tmp);
		}
		System.out.println(result);
	}
	/**
	 * �� ���� Java, ������� ��� �����������.(// /*//**)
	 */
	public static void task_22(String text) {
		System.out.println(text);
		Pattern pattern = Pattern.compile("//.*\\s*", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
			text = text.replace(matcher.group(), "");
		}
		pattern = Pattern.compile("/*\\*.*", Pattern.MULTILINE); 
		matcher = pattern.matcher(text);
		while(matcher.find()) {
			text = text.replace(matcher.group(), "");
		}
		pattern = Pattern.compile("/\\w*", Pattern.MULTILINE);
		matcher = pattern.matcher(text);
		while(matcher.find()) {
			text = text.replace(matcher.group(), "");
		}
		System.out.println();
		String[] result = text.split("\n");
		for(String str : result) {
			if(!str.equals("")) {
				System.out.println(str);
			}
		}
	}
	/**
	 * �������� ��� ����� ������� ������� ����� ��������
	 */
	public static void task_29(String text) {
		char[] chrs = text.toCharArray();
		for(int i = 0; i < chrs.length - 1; i++) {
			if(chrs[i] == chrs[i + 1]) {
				System.out.print(chrs[i]);
				while(chrs[i] == chrs[i + 1]) {
					i++;
				}
			} else {
				System.out.print(chrs[i]);
			}
		}
	}
	
}