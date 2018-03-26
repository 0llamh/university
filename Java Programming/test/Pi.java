// Cameron Smith
// 9-8-2015
// Pi.java
// computes the value of pi based on a user input
// Assumptions: User input will be a non-negative integer

import java.util.Scanner;

public class Pi {

//	private static double piValue;
//	private double int denom;
	
/*	public static double calculate(int t){
		int d = denominator; 
		
		if (t % 2 == 0){
			// if the number of terms is even
			piValue = piValue + (1 / denom);
			// we add the last value in the sequence
		}
		else{
			// if the number of terms is odd
			piValue = piValue - (1 / denom);
			// we sub the last value in the sequence
		}
		denominator += 2; // denominator changes by 2 for each additional term
		// this is because the next term is halved.
		double piFinal = piValue;
		return piValue*4;
	}
*/	

	public static void main(String[] args){
		double piValue = 0; // initialize value of pi
		double denom = 1; // init the denom to 1 for the first term
		int terms = 0; // init number of terms
		double nextTerm; // init next term variable
		Scanner input = new Scanner(System.in);	// for user input
		
		System.out.print("Compute to how many terms of the series? ");
		terms = input.nextInt();			// asks for how many terms
		
		System.out.printf("%-10s PI approximation", "terms");
		
		for(int i = 0; i < terms; i++){
			nextTerm = (1 / denom);			
			
			if (i % 2 == 0){
				// if the number of terms is even
				piValue += nextTerm;
				// we add the next value in the sequence
			}
			else{
				// if the number of terms is odd
				piValue -= nextTerm;
				// we sub the next value in the sequence
			}
//			System.out.print("\ndenominator = " + denom);
//			System.out.print("\nnewTerm = " + nextTerm);
			
			denom += 2; // denominator changes by 2 for each additional term
						// this is because the next term is halved.
//			piValue *= 4;
			
			System.out.printf("\n%-10d", i+1); // for the terms
			System.out.print(piValue*4); // default decimal precision
		}
	}
	
	
}
