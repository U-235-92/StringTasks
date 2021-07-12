package b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringOperation {
	private static String load() {
		String text = "";
		String tmp = null;
		try {
			FileReader reader = new FileReader(new File("tasks/chapter_7/b/text.txt"));
			BufferedReader br = new BufferedReader(reader);
			while((tmp = br.readLine()) != null) {
				text += tmp;
			}
			br.close();
			reader.close();
		} catch(IOException exc) {
			exc.printStackTrace();
		}
		return text;
	}
	/**
	 * ����� ���������� ���������� ����������� ������, � ������� ���� ���������� �����
	 */
	public static void task_1() {
		int count = 0, index = 0, max = 0;
		String text = load();
		Pattern splitter = Pattern.compile("[.!?;]+\\s*", Pattern.MULTILINE);
		String[] offers = text.split(splitter.pattern());
		int[] counts = new int[offers.length];
		for(String offer : offers) {
			String[] words = offer.toString().split("\\p{Punct}*\\s+");
			for(int i = 0; i < words.length; i++) {
				words[i] = words[i].toLowerCase();
			}
			Arrays.sort(words);
			for(int i = 0; i < words.length - 1; i++) {
				if(words[i].equals(words[i + 1])) {
					count++;
				}
			}
			counts[index] = count;
			index++;
			count = 0;
		}
		for(int i = 0; i < counts.length; i++) {
			if(counts[i] > max) {
				max = counts[i];
				index = i;
			}
		}
		for(int i = 0; i < offers.length; i++) {
			if(counts[i] == max) {
				System.out.println(offers[i]);
			}
		}
	}
	/**
	 * ������� ��� ����������� ��������� ������ � ������� ����������� ���������� ���� � ������ �� ���. 
	 */
	public static void task_2() {
		String text = load();
		Pattern splitter = Pattern.compile("[.|?|;|!]+\\s+");
		String[] offers = text.split(splitter.pattern());
		System.out.println("Before: ");
		System.out.println(Arrays.toString(offers));
		for(int i = 1; i < offers.length; i++) {
			for(int j = offers.length - 1; j >= i; j--) {
				if(offers[j].length() < offers[j - 1].length()) {
					String tmp = offers[j - 1];
					offers[j - 1] = offers[j];
					offers[j] = tmp;
				}
			}
		}
		System.out.println("After: ");
		System.out.println(Arrays.toString(offers));
	}
	/**
	 * ����� ����� ����� � ������ �����������, �������� ��� �� � ����� �� ��������� �����������.
	 */
	public static void task_3() {
		boolean isSame = false;
		String text = load();
		Pattern splitter = Pattern.compile("[.!?;]+\\s*", Pattern.MULTILINE);
		String[] offers = text.split(splitter.pattern());
		String[] wordsFirstOffer = offers[0].split("\\p{Punct}*\\s+");
		for(int i = 0; i < wordsFirstOffer.length; i++) {
			for(int j = 1; j < offers.length; j++) {
				String[] wordsOther = offers[j].split("\\p{Punct}*\\s+");
				for(int k = 0; k < wordsOther.length; k++) {
					if(wordsFirstOffer[i].equals(wordsOther[k])) {
						isSame = true;
						break;
					}
				}
			}
			if(isSame) {
				System.out.println("Word "+ wordsFirstOffer[i] +" was searched.");
				isSame = false;
			} else {
				System.out.println("Word " + wordsFirstOffer[i]  + " is alone in text.");
			}
		}
	}
	/**
	 * �� ���� ������������� ������������ ������ ����� � ���������� ��� ���������� �����,
	 * �������� �����
	 */
	public static void task_4(int length) {
		int count = 0;
		String text = load(), data = "";
		Pattern pattern = Pattern.compile("[^.!;?]+[?]+", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
			data += matcher.group();
		}
		String[] words = data.split("\\s+|\\p{Punct}+\\s*");
		String[] found = words;
		for(int i = 0; i < words.length; i++) {
			for(int j = 0; j < found.length; j++) {
				if(words[i].equals(found[j])) {
					count++;
				}
			}
			if(count == 1) {
				if(words[i].length() == length) {
					System.out.print(words[i] + " ");
				}
			}
			count = 0;
		}
	}
	/**
	 * � ������ ����������� �������� ������� ������ � ��������� ����� �������
	 */
	public static void task_5() {
		StringBuilder result = new StringBuilder();
		String text = load();
		Pattern pattern = Pattern.compile("[^.!;?]+", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
			String[] words = matcher.group().trim().split("\\s");
			String first = words[0];
			String last = words[words.length - 1];
			words[0] = last;
			words[words.length - 1] = first;
			for(String word : words) {
				result.append(word);
				result.append(" ");
			}
			result.append("\n");
		}
		System.out.println(result);
	}
	/**
	 * ���������� ����� ������ � ���������� ������� �� ������ �����. 
	 * �����, ������������ � ����� �����, �������� � ������� ������ (���������)
	 */
	public static void task_6() {
		String text = load();
		String[] words = text.split("\\s+|\\p{Punct}+\\s*");
//		System.out.println(Arrays.toString(words));
		for(int i = 1; i < words.length; i++) {
			for(int j = words.length - 1; j >= i; j--) {
				if(words[j].toLowerCase().substring(0, 1).compareTo(words[j - 1].toLowerCase().substring(0, 1)) < 0) {
					String tmp = words[j];
					words[j] = words[j - 1];
					words[j - 1] = tmp;
				}
			}
		}
//		System.out.println(Arrays.toString(words));
		for(int i = 0; i < words.length - 1; i++) {
			if(i == 0) {
				System.out.println("\t" + words[i]);
			} else {
				if(!words[i].toLowerCase().substring(0, 1).equals(words[i + 1].toLowerCase().substring(0, 1))) {
					System.out.println(words[i]);
					System.out.println("\t" + words[i + 1]);
				} else {
					System.out.println(words[i]);
				}
			}
		}
	}
	/**
	 * �������������� ����� ������ �� ������������ ���� ������� ���� 
	 * (��������� ���������� ������� ���� � ������ ���������� ���� �����)
	 */
	public static void task_7() {
		String text = load();//"���������� ������ ������ ��������";
		Pattern pattern = Pattern.compile("[�����������Ũ��������]", Pattern.MULTILINE);
		String[] words = text.split("\\s+|\\p{Punct}+\\s*");
		for(int i = 1; i < words.length; i++) {
			for(int j = words.length - 1; j >= i; j--) {
				char[] chrs1 = words[j].toCharArray();
				char[] chrs2 = words[j - 1].toCharArray();
				int countVowels1 = 0, countVowels2 = 0;
				double ratio1 = 0, ratio2 = 0;
				for(int k = 0; k < chrs1.length; k++) {
					if(String.valueOf(chrs1[k]).matches(pattern.pattern())) {
						countVowels1++;
					}
				}
				ratio1 = (double) countVowels1 / chrs1.length;
//				System.out.println(words[j] + " " + ratio1);
				for(int k = 0; k < chrs2.length; k++) {
					if(String.valueOf(chrs2[k]).matches(pattern.pattern())) {
						countVowels2++;
					}
				}
				ratio2 = (double) countVowels2 / chrs2.length;
//				System.out.println(words[j] + " " + ratio2);
				if(ratio1 > ratio2) {
					String tmp = words[j - 1];
					words[j - 1] = words[j];
					words[j] = tmp;
				}
				countVowels1 = countVowels2 = 0;
				ratio1 = ratio2 = 0;
			}
		}
		System.out.println(Arrays.toString(words));
	}
	/**
	 * ����� ������, ������������ � ������� ����, �������������� � ���������� ������� 
	 * �� ������ ��������� ����� �����
	 */
	public static void task_8() {
		String text = load();//"��� �� �� ����� ��� �� ����� �� � ����";
		text = text.trim();
		String[] words = text.split("\\s+|\\p{Punct}+\\s*");
		Pattern patternVowel = Pattern.compile("^[�����������Ũ��������][�-��-�]*", Pattern.MULTILINE);
		Pattern patternConsonent = Pattern.compile("[��������������������������������������������]", Pattern.MULTILINE);
		StringBuilder otherBuilder = new StringBuilder();
		StringBuilder sortBuilder = new StringBuilder();
		for(int i = 0; i < words.length; i++) {
			if(words[i].matches(patternVowel.pattern())) {
				boolean isCorrect = false;
				char[] chrs = words[i].toCharArray();
				for(int j = 0; j < chrs.length; j++) {
					if(String.valueOf(chrs[j]).matches(patternConsonent.pattern())) {
						isCorrect = true;
					} else {
						isCorrect = false;
					}
				}
				if(isCorrect) {
					sortBuilder.append(words[i]);
					sortBuilder.append(" ");
				} else {
					otherBuilder.append(words[i]);
					otherBuilder.append(" ");
				}
			} else {
				otherBuilder.append(words[i]);
				otherBuilder.append(" ");
			}
		}
		String[] sortWords = sortBuilder.toString().split("\\s");
		for(int i = 1; i < sortWords.length; i++) {
			for(int j = sortWords.length - 1; j >= i; j--) {
				int indexFirst_1 = 0, indexFirst_2 = 0;
				char[] chrs_1 = sortWords[j].toCharArray(), chrs_2 = sortWords[j - 1].toCharArray();
				for(int k = 0; k < chrs_1.length; k++) {
					if(String.valueOf(chrs_1[k]).matches(patternConsonent.pattern())) {
						indexFirst_1 = k;
						break;
					}
				}
				for(int k = 0; k < chrs_2.length; k++) {
					if(String.valueOf(chrs_2[k]).matches(patternConsonent.pattern())) {
						indexFirst_2 = k;
						break;
					}
				} 
				if(sortWords[j].toLowerCase().substring(indexFirst_1, indexFirst_1 + 1).compareTo(sortWords[j - 1].toLowerCase().substring(indexFirst_2, indexFirst_2 + 1)) < 0) {
					String tmp = sortWords[j];
					sortWords[j] = sortWords[j - 1];
					sortWords[j - 1] = tmp;
				}
			}
		}
		String result = "";
		for(String s : sortWords) {
			result += s + " ";
		}
		result += otherBuilder.toString();
		System.out.println(result);
	}
	/**
	 * ��� ����� ������ �������������� �� ������������ ���������� �������� ����� � �����. 
	 * ����� � ���������� ����������� ����������� � ���������� �������.
	 */
	public static void task_9(char letter) {
		String text = load(); //"�� ����� ��� ��� ��� �";
		String[] words = text.split("\\s+|\\p{Punct}+\\s*");
		int[] freqs = new int[words.length];
		int freq = 0;
		for(int i = 0; i < words.length; i++) {
			char[] chrs = words[i].toCharArray();
			for(char ch : chrs) {
				if(ch == letter) {
					freq++;
				}
			}
			freqs[i] = freq;
			freq = 0;
		}
		for(int i = 1; i < freqs.length; i++) {
			for(int j = freqs.length - 1; j >= i; j--) {
				if(freqs[j] < freqs[j - 1]) {
					int p = freqs[j];
					freqs[j] = freqs[j - 1];
					freqs[j - 1] = p;
					
					String tmp = words[j];
					words[j] = words[j - 1];
					words[j - 1] = tmp;
				} 
				else if(freqs[j] == freqs[j - 1]) {
					if(words[j].toLowerCase().compareTo(words[j - 1].toLowerCase()) < 0) {
						int p = freqs[j];
						freqs[j] = freqs[j - 1];
						freqs[j - 1] = p;
				
						String tmp = words[j];
						words[j] = words[j - 1];
						words[j - 1] = tmp;
					}
				}
			}
		}
		System.out.println(Arrays.toString(words));
	}
	/**
	 * ���������� ����� � ������ ����. ��� ������� ����� �� ��������� ������
	 * �����, ������� ��� ��� ����������� � ������ �����������, � 
	 * �������������� ����� �� �������� ������ ���������� ���������.
	 */
	public static void task_10() {
		String words = "";
		String tmp = null;
		try {
			FileReader reader = new FileReader(new File("tasks/chapter_7/b/words.txt"));
			BufferedReader br = new BufferedReader(reader);
			while((tmp = br.readLine()) != null) {
				words += tmp;
			}
			br.close();
			reader.close();
		} catch(IOException exc) {
			exc.printStackTrace();
		}
		String text = load();
		String[] wordsArr = words.split("\\s");
		String[] offers = text.split("[.!?;]+\\s+");
		int freq = 0, info = 0;
		int[] freqs = new int[wordsArr.length];
		System.out.println("����� �������� " + offers.length + " �����������");
		for(int i = 0; i < wordsArr.length; i++) {
			String wordL = wordsArr[i].trim().toLowerCase();
			for(int j = 0; j < offers.length; j++) {
				String[] wordsOffer = offers[j].toString().split("\\s+|\\p{Punct}+\\s*");
				for(int k = 0; k < wordsOffer.length; k++) {
					String wordO = wordsOffer[k].trim().toLowerCase();
					if(wordO.equals(wordL)) {
						freq++;
					}
				}	
				if(freq > 0) {
					int count = j + 1;
					info += freq;
					System.out.println("����� " + wordsArr[i].toUpperCase() + " � ����������� "
							+ count + " ����������� " + freq + " ���");
					freq = 0;
				}
			}
			freqs[i] = info;
			info = 0;
		}
		for(int i = 1; i < freqs.length; i++) {
			for(int j = freqs.length - 1; j >= i; j--) {
				if(freqs[j] > freqs[j - 1]) {
					int p = freqs[j];
					freqs[j] = freqs[j - 1];
					freqs[j - 1] = p;
					
					String str = wordsArr[j];
					wordsArr[j] = wordsArr[j - 1];
					wordsArr[j - 1] = str;
				}
			}
		}
		System.out.println(Arrays.toString(wordsArr));
	}
	/**
	 * � ������ ����������� ������ ��������� ��������� ������������ �����,
	 * ������������ � ��������������� ��������� �������
	 */
	public static void task_11(String beginWord, String endWord) {
		String text = "F drova k d test g s drova r e test. S drova d d d d test TT drova d d test.";//load();
		String[] offers = text.split("[.!?;]+\\s*");
		Pattern pattern = Pattern.compile("\\w+");
		int beginIndex = 0, endIndex = 0, maxSubstring = 0;
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < offers.length; i++) {
			StringBuilder tmp = new StringBuilder(offers[i]);
			Matcher matcher = pattern.matcher(offers[i]);
			while(matcher.find()) {
				if(matcher.group().equals(beginWord)) {
					beginIndex = matcher.start();
				}
				if(matcher.group().equals(endWord)) {
					endIndex = matcher.end();
				}
				if(endIndex > 0 && endIndex > beginIndex) {
					if(endIndex - beginIndex >= maxSubstring) {
						tmp.delete(beginIndex - maxSubstring, endIndex - maxSubstring);
						maxSubstring = endIndex - beginIndex;
					}
					beginIndex = endIndex = 0;
				}
			}
			maxSubstring = 0;
			result.append(tmp);
		}
		System.out.println(result);
	}
	/**
	 * �� ������ ������� ��� ����� �������� �����,
	 * ������������ �� ��������� �����
	 */
	public static void task_12(int length) {
		String text = load(); //"����� ��� ��� ���� ����� ���� �����"
		StringBuilder tmp = new StringBuilder(text);
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile("[�-��-�0-9]+|\\w");
		Matcher matcher = pattern.matcher(text);
		int previousWordLength = 0;
		int start = 0, end = 0;
		while(matcher.find()) {
			if(matcher.group().length() == length) {
				Pattern pConsonant = Pattern.compile("^[��������������������������������������������"
						+ "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ][�-��-�0-9\\w]*");
				Matcher mConsonant = pConsonant.matcher(matcher.group());
				if(mConsonant.find()) {
					start = matcher.start();
					end = matcher.end();
					tmp.delete(start - previousWordLength, end - previousWordLength);
					previousWordLength += length;// matcher.end() - matcher.start();
				}
			}
		}
		result.append(tmp);
		System.out.println(result);
	}
	/**
	 * ������������� ����� � ������ �� �������� ���������� ���������
	 * ��������� �������, � � ������ ��������� - �� ��������
	 */
	public static void task_13(char symbol) {
		String text = "��� ������ ��� ����� ���� ���� �� �� ����";
		String[] words = text.split("\\s+|\\p{Punct}+\\s*");
		sort(words, 0, words.length - 1, symbol);
		System.out.println(Arrays.toString(words));
	}
	private static void sort(String[] words, int left, int right, char symbol) {
		int[] freqs = new int[words.length];
		int freq = 0, q = 0;
		for(String word : words) {
			char[] chrs = word.toCharArray();
			for(char ch : chrs) {
				if(ch == symbol) {
					freq++;
				}
			}
			freqs[q] = freq;
			freq = 0; q++;
		}
		int i = left, j = right;
		int mid = freqs[(i + j) / 2];
		while(i <= j) {
			while(freqs[i] > mid) {
				i++;
			}
			while(freqs[j] < mid) {
				j--;
			}
			if(i <= j) {
				String tmpS = words[i];
				words[i] = words[j];
				words[j] = tmpS;
				
				int tmpI = freqs[i];
				freqs[i] = freqs[j];
				freqs[j] = tmpI;
				i++; j--;
			}
		}
		if(i < right) {
			sort(words, i, right, symbol);
		}
		if(j > left) {
			sort(words, left, j, symbol);
		}
		for(int m = freqs.length - 1; m > 0; m--) {
			if(freqs[m] == freqs[m - 1]) {
				if(words[m].toLowerCase().compareTo(words[m - 1].toLowerCase()) < 0) {
					String tmp = words[m];
					words[m] = words[m - 1];
					words[m - 1] = tmp;
				}
			}
		}
	}
	/**
	 * � �������� ������ ����� ��������� ������������ �����,
	 * ���������� �����������
	 */
	public static void task_14() {
		String text = "��� ����� ��� ����, �����, �����, ���, ��� ��� ��� ���";
		String[] words = text.split("\\s|\\p{Punct}+\\s*");
		StringBuilder substrings = new StringBuilder();
		int maxLength = 0;
		for(int i = 0; i < words.length; i++) {
			StringBuilder tmp = new StringBuilder(words[i]);
			if(tmp.toString().toLowerCase().equals(tmp.reverse().toString().toLowerCase())) {
				substrings.append(tmp.reverse().toString());
				substrings.append(" ");
			} else {
				substrings.append("\n");
			}
		}
		String[] arrSubstrings = substrings.toString().split("\n");
		for(String sub : arrSubstrings) {
			if(sub.length() > maxLength) {
				maxLength = sub.length();
			}
		}
		for(String sub : arrSubstrings) {
			if(sub.length() == maxLength) {
				System.out.println(sub);
			}
		}
	}
	/**
	 * ������������� ������ ����� � ������, ������ �� ���� ��� ��������� ��������� ������ 
	 * ����� ����� �����
	 */
	public static void task_15() {
		String text = "������� ��� ����� ���� ���������, �������, ���� �";
		String[] words = text.split("\\s+");
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < words.length; i++) {
			char[] chrs = words[i].toCharArray();
			if(chrs.length > 1) {
				for(int j = 1; j < chrs.length; j++) {
					if(chrs[j] == chrs[0]) {
						words[i] = words[i].replace(String.valueOf(chrs[j]), "");
					}
				}
				result.append(words[i]);
				result.append(" ");
			} else {
				result.append(words[i]);
				result.append(" ");
			}
		}
		System.out.println(result);
	}
	/**
	 * � ��������� ����������� ������ ����� �������� �����, �������� ��������� ����������,
	 * ����� ������� ����� �� ��������� � ������ �����.
	 */
	public static void task_16(int length, String substring) {
		String text = "��� ����� ��� ����, �����, �����, ���, ��� ��� ��� ���";
		String[] words = text.split("\\s");
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile("[�-��-�0-9]+|\\w");
		for(int i = 0; i < words.length; i++) {
			Matcher matcher = pattern.matcher(words[i]);
			if(matcher.find()) {
				if(matcher.group().length() == length) {
					words[i] = substring;
					result.append(words[i]);
					result.append(" ");
				} else {
					result.append(words[i]);
					result.append(" ");
				}
			}
		}
		System.out.println(result);
//		Matcher matcher = pattern.matcher(text);
//		while(matcher.find()) {
//			if(matcher.group().length() == length) {
//				text = text.replace(text.substring(matcher.start(), matcher.end()), substring);
//			}
//		}
//		System.out.println(text);
	}
}