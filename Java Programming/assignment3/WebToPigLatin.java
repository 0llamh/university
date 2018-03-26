// Cameron Smith
// COP 3252
// WebToPigLatin.java
// takes in a .html file and converts all valid text
// to piglatin and creates an identical .html file

import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.text.html.HTML;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WebToPigLatin {
	
	public static boolean validWord(String w){
		char c;
		w = w.toLowerCase();
		for(int i = 0; i < w.length();i++){
			c = w.charAt(i);
			if (c=='<' || c=='>' || c=='&' || c=='$' || c=='\"' || c=='.' || 
				c==',' || c==':' || c==';' || c==' ' || c=='=' || c=='-')
				return false;	// invalid word
			else if (c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || 
					 c=='7' || c=='8' || c=='9')
				return false;	// contains numbers
			else
				return true;
		}
		return false;

	}
	public static boolean isVowel(char c){
		if (c=='a' || c=='e' || c=='i' || c=='o' || c=='u' || c=='y')
			return true;
		else
			return false;
	}
	public static String tobeTranslated(String s){
		StringTokenizer old_line;	//will be initialized with text substring
		//allows the splitting of tokens(words) to be translated
		//individually and concat to a new string
		//splits up html tags from 
		String translated_line = new String();	//string holding the translated line
		String token;			//temp holding a single token in a loop
		char token_punc;		//holds a token's punctuation if applicable
		String html_tag;		//holds the first html tag
		String second_html_tag = new String();	//holds the second html tag
		int html_tag_start = 0;
		int html_tag_end = 0;
		int index = 0;
//		System.out.println("entering do-while loop");
		s = s.trim();
		
		
		
		do{
			for (int i = index; i < s.length();i++){
				if(s.charAt(i) == '<')
					html_tag_start = i;
				else if (s.charAt(i) == '>'){
					html_tag_end = i;
					i = s.length(); 	//end the loop to snag html tag
					index = html_tag_end;	//save end tag index
				}
			}
			html_tag = s.substring(html_tag_start, html_tag_end+1);
			if (translated_line.length() == 0)
				translated_line = html_tag;
			else
				translated_line += html_tag;
			if ( index <= s.length()){
				for (int i = html_tag_end; i < s.length();i++){
					//resumes the tokenizer
					if (s.charAt(i) == '\\')
						i++;	// skips the string literal character
					else if (s.charAt(i) == '<'){
						html_tag_start = i;	//starts a new html tag string
						i = s.length();		//ends the loop
						index = html_tag_start;
					}
				}
				if ((html_tag_start - html_tag_end) >= 1){
					System.out.println("test");
					// only translates line if the gap between tags actually exists
					old_line = new StringTokenizer(s.substring(html_tag_end+1, html_tag_start), " \")(");
					while (old_line.hasMoreTokens()){
						token = old_line.nextToken();
						if (token.charAt(token.length()-1) == '!' || token.charAt(token.length()-1) == '.' ||
							token.charAt(token.length()-1) == ',' || token.charAt(token.length()-1) == ';' ||
							token.charAt(token.length()-1) == '?'){
							token_punc = token.charAt(token.length()-1);
							token = token.substring(0,token.length()-1);
							if (validWord(token))
								translated_line += toPigLatin(token) + token_punc + " "; 
							else
								translated_line += token + token_punc + " ";
						}
						else{
							if (validWord(token))
								translated_line += toPigLatin(token) + " ";
							else
								translated_line += token + " ";
						}
					}
					/*if(second_html_tag.length() > 0){
						translated_line += "</" + second_html_tag.substring(1);
					//	translated_line += "</" + html_tag.substring(1);
						html_tag_end = second_html_tag_end;
					}
					//translated_line += "</" + html_tag.substring(1);*/
					index = html_tag_start;
				}
				else
					index = s.length();
/*				if ((second_html_tag_start - html_tag_end) < 1){
					for (int i = second_html_tag_start; i < s.length();i++){
						if (s.charAt(i) == '>'){
//							System.out.println("test");
							second_html_tag_end = i;
							i = s.length();	//ends the loop
							index = second_html_tag_end; 	//resets the index
						}	
					}
					second_html_tag = s.substring(second_html_tag_start, second_html_tag_end+1);
					translated_line += second_html_tag;
				}*/
			}
			else
				index = s.length();
		}while ( index < s.length()-1);
		// normally runs with only two html tag deliminators, but can run for more
//		html_tag = html_tag.trim();
//		if ((html_tag.compareTo(second_html_tag) != 0) && (html_tag.compareTo(translated_line) != 0))
//			translated_line += "</" + html_tag.substring(1);
//		else if (translated_line.compareTo(html_tag) == 0)
//			translated_line = html_tag;
		return translated_line;
	}
	public static String toPigLatin(String s){
		// takes in a single valid word to be translated
		String pig = new String();
		char first_char = s.charAt(0);
		char punctuation;
		int start_index = 0;			// index where to split the string for piglatin
		s = s.toLowerCase(); 			// converts all of s to lower case for easier vowel-checking
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' ||
				s.charAt(i) == 'u' || s.charAt(i) == 'y'){
				start_index = i;
				i = s.length();		//ends loop at first vowel
			}
		if (start_index == 0)
			pig = (s + "way");	//if the input word starts with a vowel, just add "way" to the end
		else{
			String first_half = s.substring(0,start_index);	// need to split from the beginning up to to first vowel
			String second_half = s.substring(start_index, s.length()); //grabs the second half (remainder) of the word
			pig = second_half + first_half + "ay";
		}
		pig = pig.toLowerCase(); //converts all the characters to lowercase
		//checking for uppercase/lowercase in original word via
		//ascii value with default compareTo() for characters
		if (first_char >= 'A' && first_char <= 'Z')
			//uppercase
			pig = pig.substring(0,1).toUpperCase() + pig.substring(1);
			// pig will substring the first char, capitalize it, and concat the remainder of the word
			// returns a new obj of pig
		return pig;
	}	
	public static void main (String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		FileReader old_html = new FileReader(args[0]);
		FileWriter new_html = new FileWriter(args[1]);
		PrintWriter toFile = new PrintWriter(new_html);
		String old_line;
		String new_line;
		Scanner fromFile = new Scanner(old_html);
		System.out.println("Writing to File...");
		while (fromFile.hasNextLine()){
			old_line = fromFile.nextLine();
			new_line = tobeTranslated(old_line);
//			System.out.println(new_line);
			new_html.write(new_line);
			new_html.write("\n");
		}
		System.out.println("...Done!");
		/*do{
			System.out.println("Enter input to be translated: ");
			s4 = input.nextLine();
			System.out.println(toPigLatin(s4));
		}while(s4 != null);*/
		input.close();
		toFile.close();
		fromFile.close();
		new_html.close();
	}
}
