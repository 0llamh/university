// Cameron Smith
// 9-8-2015
// Reverse.java
// takes in an integer and returns the reverse of it
// Assumptions: user inputs non-negative integers

package assignment1;
import java.util.Scanner;

public class Reverse {
	
	public static long reverseDigits(long i){
		String stringVal = String.valueOf(i); 
		//creates a new string of the value to be reversed
		String newStringVal = new String();
		//creates new string for the reversed string
		for (int x = stringVal.length(); x > 0; x--){
			// loop counting down based upon the length of the original value
			newStringVal += (stringVal.substring(x-1,x));
			// concats the next digit to the new string starting at the end of the original
		}
		long newVal = Long.parseLong(newStringVal);
		//creates a reference to a integer value equal to the string value		
		return newVal;
	}
	
	public static void main(String[] args){
		long value = 0;
		// needs to be long for ridiculously long (haha) integers
		Scanner input = new Scanner(System.in);
		
		do{
			System.out.print("Please enter a long integer (0 to quit): ");
			value = input.nextLong();		
//			System.out.print(value);
			System.out.println("The number reversed is: " +
			reverseDigits(value));
		}while (value != 0.0);
		System.out.print("Goodbye!");
	}
	
	
}
