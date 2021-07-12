package c;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringOperation {
	/**
	 * ����� �� n^2 �������� ��������� �� ���������� �������:
	 * -- ��� ������� ������ ������������ � ���������� ������� ����������� n � ������� ����� �������, ������ ����
	 * -- ������� �������������� �� 90 �������� �� ������� �������
	 * -- 1-� ������ ������� �������� ������� � ���������, 2-� - � ������������� � �.�.
	 * -- 1-� ������� ������� �������� ������� � ��������� 2-� - � ������������� � �.�.
	 * -- ������������� ����� ���������� � ���������� ������ �������������� ������� �� ������� �� ������� �������
	 * 	  ������� � ������ �������� ����
	 */
	public static void task_1() {
		String text = "��, ��, �� ��. �� �� ��, �� �� �� ��, �� �� �� �� ��"; //"��, ��, �� ��. �� �� ��, �� �� �� ��, �� �� �� �� ��"
		String[] words = text.split("\\s");
		String[][] matrix = new String[(int)Math.ceil(Math.sqrt(words.length))][(int)Math.ceil(Math.sqrt(words.length))];
		int k = 0;
		for(int i = 0; i < matrix[0].length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				matrix[i][j] = words[k];
				k++;
			}
		}
		matrix = rotate(matrix);
		matrix = replaceRow(matrix);
		matrix = replaceColumn(matrix);
		show(matrix);
		System.out.println();
		encrypt(matrix);
	}
	private static String[][] rotate(String[][] matrix) {
		String[][] result = new String[matrix.length][matrix.length];
		int p = 0, q = 0;
		for(int i = 0; i < matrix[0].length; i++) {
			q = i;
			for(int j = 0; j < matrix.length; j++) {
				p = (matrix.length - 1) - j;
				result[i][j] = matrix[p][q];
			}
		}
		return result;
	}
	private static String[][] replaceRow(String[][] matrix) {
		String[][] result = new String[matrix.length][matrix.length];
		int p = 0, q = 0;
		for(int i = 0; i < matrix[0].length; i++) {
			p = (matrix[0].length - 1) - i;
			for(int j = 0; j < matrix.length; j++) {
				q = j;
				result[i][j] = matrix[p][q];
			}
		}
		return result;
	}
	private static String[][] replaceColumn(String[][] matrix) {
		String[][] result = new String[matrix.length][matrix.length];
		int p = 0, q = 0;
		for(int i = 0; i < matrix[0].length; i++) {
			p = i;
			for(int j = 0; j < matrix.length; j++) {
				q = (matrix.length - 1) - j;
				result[i][j] = matrix[p][q];
			}
		}
		return result;
	}
	private static void encrypt(String[][] matrix) {
		int freeSquares = (int) Math.ceil((double) matrix.length / 2);
		int i = 0, j = 0, floor = matrix[0].length, ceil = 0, left = 0, right = matrix.length;
		int startI = i, startJ = j;
		int countPrintedTokens = 0;
		while(freeSquares > 0) {
			if(i == ceil && j < right) {
				if(countPrintedTokens < matrix[0].length * matrix.length) {
					System.out.print(matrix[i][j] + " ");
					j++;
					countPrintedTokens++;
				} else {
					break;
				}
			} 
			if(j == right - 1 && i < floor) {
				if(countPrintedTokens < matrix[0].length * matrix.length) {
					System.out.print(matrix[i][j] + " ");
					i++;
					countPrintedTokens++;
				} else {
					break;
				}
			}
			if(i == floor - 1 && j >= left) {
				if(countPrintedTokens < matrix[0].length * matrix.length) {
					System.out.print(matrix[i][j] + " ");
					j--;
					countPrintedTokens++;
				} else {
					break;
				}
			}
			if(j == left && i > ceil) {
				if(countPrintedTokens < matrix[0].length * matrix.length) {
					System.out.print(matrix[i][j] + " ");
					i--;
					countPrintedTokens++;
				} else {
					break;
				}
			}
			if(i == startI && j == startJ) {
				freeSquares--;
				i++; j++;
				floor--; ceil++; left++; right--;
				startI++; startJ++;
			}
		}
	}
	private static void show(String[][] matrix) {
		for(int i = 0; i < matrix[0].length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
	}
	/**
	 * ��������� �� ������ ��������� ������������ �����, ������������ � ��������������� 
	 * ����� � ��� �� ��������
	 */
	public static void task_2() {
		String text = "���� ������. ����� ����, ��������� ���� ������. ����� � �����, ������ ������. ";
		Pattern pattern = Pattern.compile("\\..*\\.");
		text = text.replaceAll(pattern.pattern(), "");
		System.out.println(text);
	}
	/**
	 * ���������� �� ������ ���������� ���������� ����������� ���, ����� � ����� ���� ����������
	 * ����������� ���� ���� �� ���� ����� �����.
	 */
	public static void task_3() {}
	/**
	 * ����������� ������ ����������� ������, ������� ������ ������ �� ���� ��� �����
	 * ����� ������� �������� �� ���� ������, �� ������� ������� ���������� ��� ���������
	 * � ������
	 */
	public static String task_4() {
		String text = "HHellllllooo my friend. Hooooooow areee youuuu? Are you bbusyyyyy?";
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile("\\w+\\p{Punct}*");
		Matcher matcher = pattern.matcher(text);
		int count = 1;
		while(matcher.find()) {
			char[] chrs = matcher.group().toCharArray();
			char symb = chrs[0];
			for(int i = 1; i < chrs.length; i++) {
				if(chrs[i] == symb) {
					count++;
				} else {
					if(count == 1) {
						result.append(symb);
					} else {
						result.append(count);
						result.append(symb);
					}
					symb = chrs[i];
					count = 1;
				}
			}
			if(count > 1) {
				result.append(count);
				result.append(symb);
			} else {
				result.append(symb);
			}
			count = 1;
			result.append(" ");
		}
//		System.out.println(result);
		return result.toString();
	}
	/**
	 * ����������� �����, ������ �� ������� ����������� �������
	 */
	public static void task_5() {
		String zip = task_4();
		Pattern pattern = Pattern.compile("\\w+\\p{Punct}*");
		Matcher matcher = pattern.matcher(zip);
		StringBuilder result = new StringBuilder();
		int count = 0;
		while(matcher.find()) {
			char[] chrs = matcher.group().toCharArray();
			Pattern countPattern = Pattern.compile("[0-9]+");
			for(int i = 0; i < chrs.length; i++) {
				Matcher countMatcher = countPattern.matcher(String.valueOf(chrs[i]));
				if(countMatcher.find()) {
					count = Integer.valueOf(countMatcher.group());
					while(count > 1) {
						result.append(chrs[i + 1]);
						count--;
					}
				} else {
					result.append(chrs[i]);
				}
			}
			result.append(" ");
		}
		System.out.println(result);
	}
	/**
	 * ����������, ������������� �� ��� ����� �����. ����� ����� ���������
	 * ������� "?" (������������ ������) � "*" (������������ ���������� ������������ ��������)
	 */
	public static void task_6() {
		String file = "Secrdf"; //"Rec_et_55" "Sec_et_5" "SecUet_55"
		String mask = "?ec*"; 
		Pattern pattern = Pattern.compile("[*?]");
		char[] maskChrs = mask.toCharArray();
		char[] fileChrs = file.toCharArray();
		boolean isMatch = false;
		for(int i = 0; i < maskChrs.length; i++) {
			Matcher matcher = pattern.matcher(String.valueOf(maskChrs[i]));
			if(matcher.find()) {
				if(matcher.group().equals("?")) {
					if(i < fileChrs.length) {
						isMatch = true;
					} else {
						isMatch = false;
						break;
					}
				} else {
					//equals *
					if(i <= fileChrs.length) {
						isMatch = true;
						break;
					} else {
						isMatch = false;
						break;
					}
				}
			} else {
				if(i < fileChrs.length) {
					if(maskChrs[i] == fileChrs[i]) {
						isMatch = true;
					} else {
						isMatch = false;
						break;
					}
				} else {
					isMatch = false;
					break;
				}
			}
		}
		if(isMatch) {
			System.out.println(isMatch);
		} else {
			System.out.println(isMatch);
		}
	}
	/**
	 * ��������� ������ ���������� ������� �������� �� ���, ��� ������ ����� ������������
	 * ��������� ���������� ����: 2 - ABC, 3 - DEF, 4 - GHI, 5 - JKL, 6 - MNO, 7 - PQRS, 8 - TUV, 9 - WXYZ
	 * �������� ���������, ������� ������� � �������� ���������� ������ ��������� ������������ �����, 
	 * ��������������� ����� �� �������.
	 */
	public static void task_7() {
		int maxSubstringLength = 0;
		String number = "768681";
		String[] alphabet = new String[]{"sotu", "umvt", "mnouvotv", "wxafo", "pqnrmnos", "mnxyz", "pqotm"};//
		String[] digit = digit(alphabet);
		int[] maxLengthsSub = new int[digit.length];
		for(int i = 0; i < digit.length; i++) {
			char[] chrsDigit = digit[i].toCharArray();
			char[] chrsNumber = number.toCharArray();
			if(chrsDigit.length > chrsNumber.length) {
				continue;
			} else {
				int digitIterator = 0;
				for(int m = 0; m < chrsNumber.length; m++) {
					if(chrsNumber[m] != chrsDigit[digitIterator]) {
						maxSubstringLength = 0;
						continue;
					} else {
						digitIterator++;
						maxSubstringLength++;
						if(digitIterator >= chrsDigit.length) {
							break;
						} 
					}
				}
				maxLengthsSub[i] = maxSubstringLength;
			}
		}
		for(int i = 0; i < maxLengthsSub.length; i++) {
			if(maxLengthsSub[i] >= maxSubstringLength) {
				maxSubstringLength = maxLengthsSub[i];
			}
		}
		for(int i = 0; i < maxLengthsSub.length; i++) {
			if(maxLengthsSub[i] == maxSubstringLength) {
				System.out.print(digit[i] + " ");
			}
		}
	}
	private static String[] digit(String[] alphabet) {
		String[] digit = new String[alphabet.length];
		for(int i = 0; i < alphabet.length; i++) {
			char[] chrs = alphabet[i].toCharArray();
			char[] digitChrs = new char[chrs.length];
			for(int j = 0; j < chrs.length; j++) {
				switch (chrs[j]) {
					case 'a':
						digitChrs[j] = '2';	
					break;
					case 'b':
						digitChrs[j] = '2';	
					break;
					case 'c':
						digitChrs[j] = '2';	
					break;
					case 'd':
						digitChrs[j] = '3';
					break;
					case 'e':
						digitChrs[j] = '3';
					break;
					case 'f':
						digitChrs[j] = '3';
					break;
					case 'g':
						digitChrs[j] = '4';
					break;
					case 'h':
						digitChrs[j] = '4';
					break;
					case 'i':
						digitChrs[j] = '4';
					break;
					case 'j':
						digitChrs[j] = '5';
					break;
					case 'k':
						digitChrs[j] = '5';
					break;
					case 'l':
						digitChrs[j] = '5';
					break;
					case 'm':
						digitChrs[j] = '6';
					break;
					case 'n':
						digitChrs[j] = '6';
					break;
					case 'o':
						digitChrs[j] = '6';
					break;
					case 'p':
						digitChrs[j] = '7';
					break;
					case 'q':
						digitChrs[j] = '7';
					break;
					case 'r':
						digitChrs[j] = '7';
					break;
					case 's':
						digitChrs[j] = '7';
					break;
					case 't':
						digitChrs[j] = '8';
					break;
					case 'u':
						digitChrs[j] = '8';
					break;
					case 'v':
						digitChrs[j] = '8';
					break;
					case 'w':
						digitChrs[j] = '9';
					break;
					case 'x':
						digitChrs[j] = '9';
					break;
					case 'y':
						digitChrs[j] = '9';
					break;
					case 'z':
						digitChrs[j] = '9';
					break;
				}		
			}
			digit[i] = String.valueOf(digitChrs);
		}
//		System.out.println(Arrays.toString(digit));
		for(int i = 0; i < digit.length; i++) {
			int count = 1;
			char[] chrs = digit[i].toCharArray();
			char[] chrsDigit = new char[chrs.length];
			char symb = chrs[0];
			for(int j = 1; j < chrs.length; j++) {
				if(chrs[j] == symb) {
					if(j == chrs.length - 1) {
						chrsDigit[j - count] = symb;
					} else {
						count++;
						continue;
					}
				} else {
					if(j == chrs.length - 1) {
						chrsDigit[j - count] = symb;
						chrsDigit[j - count + 1] = chrs[j];
					} else {
						chrsDigit[j - count] = symb;
						symb = chrs[j];
					}
				}
			}
			digit[i] = String.valueOf(chrsDigit).trim();
		}
//		System.out.println(Arrays.toString(digit));
		return digit;
	}
	/**
	 * ����������� �������������� ��������� ������ � ������������� �� ������ ����. 
	 * ��������� ������ ��������� ����� �� ������ � ������, �� �������������
	 * ��������� ���������� ��������. ���� ��������� ����� �� ���������� � ������� ������, ��� ���������� 
	 * ���������� �� ���������.
	 */
	public static String task_8(int length) {
		int count = length;
		boolean isFirst = true;
		StringBuilder result = new StringBuilder();
		String text = "���� ������. ������� �������, � ��������� �������� ������. "
				+ "...������� ������ ������ �� ������ ��� � ����� ��� �������."
				+ "�����, � ���� � ��� ����� ������. "
				+ "� ������� ������� ������ - �������, ����������� � ������, "
				+ "������ ������� �� ����."
				+ "���� ���� ������������ � �������, ������ �����, ������ ������."
				+ "�� ����� ��� ����� ���������� �� �������� ����� ����.";
		Pattern pattern = Pattern.compile("\\p{Punct}*[�-��-�0-9]+\\p{Punct}*\\s*|\\p{Punct}*\\w+\\p{Punct}*\\s*|\\p{Punct}+");
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
			Pattern symbPattern = Pattern.compile("^\\p{Punct}+$");
			Matcher symbMatcher = symbPattern.matcher(matcher.group());
			if(symbMatcher.find()) {
				if(isFirst) {
					result.append(matcher.group());
					result.append(" ");
					count -= (matcher.group().length() + 1);
					isFirst = false;
				} else {
					if(count - matcher.group().length() < 0) {
						result.append("\n");
						count = length;
						result.append(matcher.group());
						count -= (matcher.group().length() + 1);
					} else {
						result.append(matcher.group());
						result.append(" ");
						count -= (matcher.group().length() + 1);
					}
				}
			} else {
				if(isFirst) {
					result.append(String.format("%s", matcher.group().trim()));
					result.append(" ");
					count -= matcher.group().length();
					isFirst = false;
				} else {
					if(count - matcher.group().length() < 0) {
						result.append("\n");
						count = length;
						result.append(String.format("%s", matcher.group().trim()));
						result.append(" ");
						count -= matcher.group().length();
					} else {
						result.append(String.format("%s", matcher.group().trim()));
						result.append(" ");
						count -= matcher.group().length();
					}
				}
			}
		}
//		System.out.println(result);
		return result.toString();
	}
	/**
	 * �������� ��������� �� ����������� ������� ���, ����� ��� ������������ ��������������
	 * � ������������� �� ����� �����. ��� �����, �������� ������������� ������� ����� �������.
	 */
	public static void task_9() {
		int length = 10;
		String text = task_8(length);
		String[] tokens = text.split("\n");
		StringBuilder tmp = new StringBuilder();
		for(int i = 0; i < tokens.length; i++) {
			if(tokens[i].length() <= length) {
				Pattern pattern = Pattern.compile("\\s");
				Matcher matcher = pattern.matcher(tokens[i]);
				if(matcher.find()) {
					int countSpace = length - tokens[i].length();
					String[] str = tokens[i].split("\\s");
					for(int j = 0; j <= countSpace; j++) {
						str[0] += " ";
					}
					for(String s : str) {
						tmp.append(String.valueOf(s));
						tmp.append(" ");
					}
					tmp.append("\n");
				} else {
					tmp.append(tokens[i]);
					tmp.append("\n");
				}
			} else {
				tmp.append(tokens[i]);
				tmp.append("\n");
			}
		}
//		System.out.println(Arrays.toString(tokens));
		System.out.println(tmp.toString());
	}
	/**
	 * �������� � ��������� �� ����������� ������� ����������� �������� ���� �� ������.
	 * ��������������, ��� ���� ������ � �������, � ������� ��� ������� ����� �������,
	 * ��� ��� ����������� �� �����
	 */
	public static void task_10(int length) {
		int count = length;
		boolean isFirst = true, isSame = false;
		StringBuilder result = new StringBuilder();
//		String text = "������� ������ ������ �� ������ ��� � ����� ��� �������.";
		String text = "���� ������. ������� �������, � ��������� �������� ������. "
				+ "...������� ������ ������ �� ������ ��� � ����� ��� �������."
				+ "�����, � ���� � ��� ����� ������. "
				+ "� ������� ������� ������ - �������, ����������� � ������, "
				+ "������ ������� �� ����."
				+ "���� ���� ������������ � �������, ������ �����, ������ ������."
				+ "�� ����� ��� ����� ���������� �� �������� ����� ����.";
		String[] library = new String[]{"������#��#����", "�������#���#��#��", "�������#��#��#���",
				"���������#����#��#���", "��������#���#���#��", "������#��#����", 
				"�������#��#��#���", "������#��#��#��", "������#���#���", "������#��#����",
				"�������#���#����", "�����#��#���", "������#�#�����", "�������#��#��#���",
				"�������#���#��#��", "������#����#��", "�������#��#��#���", "�����������#���#���#����#�",
				"������#��#����", "������#��#��#��", "�������#��#���#��", "����#��#��",
				"����#��#��", "������������#��#����#���#���", "�������#����#���", "������#���#���", 
				"�����#���#��", "������#���#���", "������#��#��#��", "�����#��#���", "�����#�#����",
				"����������#��#���#��#���", "��������#���#��#���", "�����#��#���", "����#��#��"};
		Pattern pattern = Pattern.compile("\\p{Punct}*[�-��-�0-9]+\\p{Punct}*\\s*|\\p{Punct}");
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
			String tmp = matcher.group().trim();
			Pattern symbPattern = Pattern.compile("^\\p{Punct}+$");
			Matcher symbMatcher = symbPattern.matcher(tmp);
			if(symbMatcher.find()) {
				if(isFirst) {
					result.append(tmp);
					result.append(" ");
					count -= (tmp.length() + 1);
					isFirst = false;
				} else {
					if(count - tmp.length() < 0) {
						result.append("\n");
						count = length;
						result.append(tmp);
						count -= (tmp.length() + 1);
					} else {
						result.append(tmp);
						result.append(" ");
						count -= (tmp.length() + 1);
					}
				}
			} else {
				Pattern wordPattern = Pattern.compile("[�-��-�0-9]+\\p{Punct}");
				Matcher wordMatcher = wordPattern.matcher(tmp);
				String punct = "";
				if(wordMatcher.find()) {
					String str = "";
					str = tmp.split("\\p{Punct}+")[0];
					String[] puncts = tmp.split("[�-��-�0-9]+");
					for(int i = 0; i < puncts.length; i++) {
						punct += puncts[i];
					}
					tmp = str;
				}
				if(isFirst) {
					//��������� ����� � ��� ������������� ������� �� �����
					if(tmp.length() > length) {
						for(int i = 0; i < library.length; i++) {
							String[] words = library[i].split("#");
							if(tmp.trim().toLowerCase().equals(words[0])) {
								isSame = true;
								if(isSame) {
									for(int j = 1; j < words.length; j++) {
										if(words[j].length() <= length) {
											if(words[j].length() <= count) {
												result.append(words[j]);
												if(j == words.length - 1) {
													result.append(punct);
													result.append(" ");
												}
												count -= words[j].length();
												if(count < 0) {
													result.append("-");
													result.append("\n");
													result.append("-");
													count = length;
													isFirst = false;
												} 
											} else {
												if(j == 1) {
													result.append("\n");
													count = length;
													result.append(words[j]);
													count -= words[j].length();
												} else {
													result.append("-");
													result.append("\n");
													result.append("-");
													count = length;
													result.append(words[j]);
													if(j == words.length - 1) {
														result.append(punct);
														result.append(" ");
														count -= (words[j].length() + 1);
													} else {
														count -= words[j].length();
													}
												}
											}
										} else {
											result.append(tmp);
											result.append(punct);
											result.append("\n");
											count = length;
											isFirst = false;
											break;
										}
									}
								} else {
									result.append(tmp);
									result.append(punct);
									result.append(" ");
									count -= (tmp.length() + 1);
									isFirst = false;
								}
								break;
							} 
						}
						if(!isSame) {
							if(tmp.length() > count) {
								result.append("\n");
								count = length;
								result.append(tmp);
								result.append(punct);
								result.append(" ");
								count -= (tmp.length() + 1);
							} else {
								result.append(tmp);
								result.append(punct);
								result.append(" ");
								count -= (tmp.length() + 1);
							}
							isFirst = false;
						}
					} else {
						result.append(tmp);
						result.append(punct);
						result.append(" ");
						count -= (tmp.length() + 1);
						isFirst = false;
					}
					isSame = false;
				} else {
					if(count - tmp.length() < 0) {
						//������� ����� �� �����
						for(int i = 0; i < library.length; i++) {
							String[] words = library[i].split("#");
							if(tmp.trim().toLowerCase().equals(words[0])) { 
								isSame = true;
								if(isSame) {
									for(int j = 1; j < words.length; j++) {
										if(words[j].length() <= length) {
											if(words[j].length() <= count) {
												result.append(words[j]);
												if(j == words.length - 1) {
													result.append(punct);
													result.append(" ");
												}
												count -= words[j].length();
												if(count < 0) {
													result.append("-");
													result.append("\n");
													result.append("-");
													count = length;
												} 
											} else {
												if(j == 1) {
													result.append("\n");
													count = length;
													result.append(words[j]);
													count -= words[j].length();
												} else {
													result.append("-");
													result.append("\n");
													result.append("-");
													count = length;
													result.append(words[j]);
													if(j == words.length - 1) {
														result.append(punct);
														result.append(" ");
														count -= (words[j].length() + 1);
													} else {
														count -= words[j].length();
													}
												}
											}
										} else {
											result.append(tmp);
											result.append(punct);
											result.append("\n");
											count = length;
											break;
										}
									}
								} else {
									result.append("\n");
									result.append(tmp);
									result.append(punct);
									result.append(" ");
									count -= (tmp.length() + 1);
								}
								break;
							}
						}
						if(!isSame) {
							if(tmp.length() > count) {
								result.append("\n");
								count = length;
								result.append(tmp);
								result.append(punct);
								result.append(" ");
								count -= (tmp.length() + 1);
							} else {
								result.append(tmp);
								result.append(punct);
								result.append(" ");
								count -= (tmp.length() + 1);
							}
						}
					} else {
						result.append(tmp);
						result.append(punct);
						result.append(" ");
						count -= (tmp.length() + 1);
					}
					isSame = false;
				}
			}
		}
		System.out.println(result);
	}
	/**
	 * ����� ����� �������� ������� �������� � ����������
	 * ������������ �� ��� ������ ����� ������������.
	 * ���������� ����� ������ ����. �������� �������, 
	 * ��������� ����� StringBuilder
	 */
	public static void task_11() {
		String[] words = new String[]{" abc ",  " def ", " ghi ", " jkl ", " mno ", " pqr ", " stu ", " vwy", " xz "};
		String bigStr = "";
		StringBuilder bigSB = new StringBuilder();
		long start = 0l, end = 0l, timeWork = 0;
		start = System.nanoTime();
		int i = 0, rnd = 0;
		while(i < 100000) {
			rnd = (int) (Math.random() * words.length);
			bigStr += words[rnd];
			i = i + 1;
		}
		end = System.nanoTime();
		timeWork = (end - start);
		System.out.println("Start time (String) = " + String.format("%,3d", start));
		System.out.println("End time (String) = " + String.format("%,3d", end));
		System.out.println("Time of work (String) = " + String.format("%,3d", timeWork));
		start = System.nanoTime();
		i = 0; rnd = 0;
		while(i < 100000) {
			rnd = (int) (Math.random() * words.length);
			bigSB.append(words[rnd]);
			i = i + 1;
		}
		end = System.nanoTime();
		timeWork = (end - start);
		System.out.println("Start time (StringBuilder) = " + String.format("%,3d", start));
		System.out.println("End time (StringBuilder) = " + String.format("%,3d", end));
		System.out.println("Time of work (StringBuilder) = " + String.format("%,3d", timeWork));
	}
	/**
	 * �������� ��������-������� ��� ��� ������ ������� ������������ �� ��������������
	 * ��������-�������. ��� ������������ ��������� �������: ��� ����� ���������������
	 * ��� ��� ����������� ������, ������� ����� ����������� � ���������� �������, ����� ���� 
	 * ����������� ����� �� ��������� �������� ��������������� ����������� �������.
	 * � �������, ��� ����� JAVA ����������� ������ - ��� JAVA, AVAJ, VAJA, AJAV. �����
	 * ���������� �� �������� ������� AJAV, AVAJ, JAVA, VAJA. ������, ��������� �������������� -
	 * ����� VJAA. ����������� ���������� �������������� ��������-������� ��� ������� �����.
	 */
	public static void task_12() {
		String text = "JAVA";
		StringBuilder shiftSB = cycleShift(text);
		
	}
	private static StringBuilder cycleShift(String text) {
		StringBuilder shiftSB = new StringBuilder();
		char[] chrs = text.toCharArray();
		char tmp = chrs[0];
		for(int i = 0; i < chrs.length - 1; i++) {
			 chrs[i] = chrs[i + 1];
		}
		chrs[chrs.length - 1] = tmp;
		System.out.println(Arrays.toString(chrs));
		return shiftSB;
	}
}
