package d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sort 
{
	private static String text = "сапог сарай арбуз болт бокс биржа сапог"; //сапог сарай арбуз болт бокс биржа
	private ArrayList<String> arrListWords = new ArrayList<String>();
	private Set<String> setFirstLetters = new HashSet<String>();
	private ArrayList<String> arrListFirstLetters = new ArrayList<String>();
	private String[] arrWords;
	String str = "";
	
	public String[] takeWords(String s)
	{
		arrWords = s.split(" ");
		return arrWords;
	}
	public void removeSingleWord(ArrayList<String> wordList, ArrayList<String> firstLetters)
	{
		int pointToRead = 0;
		int countSigleWord = 0;
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		ArrayList<String> listSingleWords = new ArrayList<String>();
		for(String word : wordList)
		{
			if(word.substring(0, 1).equals(firstLetters.get(pointToRead).substring(0, 1)))
			{
				countSigleWord++;
			}
			else
			{
				countSigleWord = 0;
				countSigleWord++;
				pointToRead++;
			}
			wordMap.put(word.substring(0, 1), countSigleWord);
		}
		for(Map.Entry<String, Integer> map : wordMap.entrySet())
		{
			if(map.getValue().equals(1))
			{
				listSingleWords.add(map.getKey());
			}
		}
		for(int i = 0; i < wordList.size(); i++)
		{
			for(int j = 0; j < listSingleWords.size(); j++)
			{
				for(int z = 0; z < firstLetters.size(); z++)
				{
					if(wordList.get(i).substring(0, 1).equals(listSingleWords.get(j).substring(0, 1)) &&
							firstLetters.get(z).substring(0, 1).equals(listSingleWords.get(j).substring(0, 1)))
					{
						wordList.remove(wordList.get(i));
						firstLetters.remove(z);
					}
				}
			}
		}
	}
	public void sortText()
	{
		takeWords(text);
		int pointToRead = 0;
		int countReadWords = 0;
		ArrayList<String> buffer = new ArrayList<String>();
		AlphaComparator alphaComparator = new AlphaComparator();
		LenghtComparator lenghtComparator = new LenghtComparator();
		for(String word : arrWords)
		{
			arrListWords.add(word);
			setFirstLetters.add(word.substring(0, 1));
		}
		arrListWords.sort(alphaComparator);
		arrListFirstLetters.addAll(setFirstLetters);
		arrListFirstLetters.sort(alphaComparator);	
		
		removeSingleWord(arrListWords, arrListFirstLetters);
		for(String word : arrListWords)
		{
			if(word.substring(0, 1).equals(arrListFirstLetters.get(pointToRead).substring(0, 1)))
			{
				str += word + " ";
			}
			else
			{
				pointToRead++;
				str += "/ ";
				str += word + " ";
			}
		}
		takeWords(str);
		arrListWords.removeAll(arrListWords);
		for(String word : arrWords)
		{
			arrListWords.add(word);
		}
		str = "";
		for(int i = countReadWords; i < arrListWords.size(); i++)
		{
			buffer.add(arrListWords.get(i));
			countReadWords++;
			if(arrListWords.get(i).equals("/"))
			{
				buffer.sort(lenghtComparator);
				buffer.remove("/");
				if(str == "")
				{
					str += arrListWords.get(i - 1).substring(0, 1) + " = " + buffer;
				}
				else
				{
					str += ", " + arrListWords.get(i - 1).substring(0, 1) + " = " + buffer;
				}
				buffer.removeAll(buffer);
				pointToRead = countReadWords;
			}
		}
		for(int i = pointToRead - 1; i < arrListWords.size(); i++)
		{
			buffer.add(arrListWords.get(i));
			buffer.sort(lenghtComparator);
			buffer.remove("/");
			str += ", " + arrListWords.get(i + 1).substring(0, 1) + " = " +  buffer;
			buffer.removeAll(buffer);
			break;
		}
		System.out.println("[" + str + "]");
	}
	public static void main(String[] args) 
	{
		Sort s = new Sort();
		s.sortText();
	}
	
	class AlphaComparator implements Comparator<String>
	{
		@Override
		public int compare(String o1, String o2) 
		{
			return o1.compareTo(o2);
		}
	}
	
	class LenghtComparator implements Comparator<String>
	{
		@Override
		public int compare(String o1, String o2)
		{
			return o2.length() - o1.length();
		}
	}
}