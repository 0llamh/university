// Cameron Smith
// 9-8-2015
// DiceStats.java

import java.util.Scanner;
import java.util.Random;

public class DiceStats {

	private int diceAmt;
	private int rolls;
	private int[] sums;
	// array of all possible rolls
	private int[] freq;
	// array for the frequency of all possible rolls
	private double[] percent;
	// array for the percentage of all possible rolls
	
	// SETTERS
	public void setDiceAmt(int d){
		//allows the change of the dice amt
		this.diceAmt = d;
	}
	public void setRolls(int r){
		// allows the change of the number of rolls
		this.rolls = r;
	}
	public void setArraySizes(){
		// allows the change of array sizes based on dice amt
		this.sums = new int[this.possibleRolls(this.getDiceAmt())];
		this.freq = new int[this.possibleRolls(this.getDiceAmt())];
		this.percent = new double[this.possibleRolls(this.getDiceAmt())];
	}

	// GETTERS
	public int getDiceAmt(){
		return this.diceAmt;
	}
	public int getRolls(){
		return this.rolls;
	}
	public int getSums(int a){
		return this.sums[a];
	}
	public int getFreq(int a){
		return this.freq[a];
	}
	public double getPercent(int a){
		return this.percent[a];
	}
	
	public void inputData(){
		// allows the user to input/change private data to a specified DS obj
		Scanner input = new Scanner(System.in);
		System.out.print("How many dice will constitute one roll? ");
		int d = input.nextInt();
		this.setDiceAmt(d);
		System.out.print("How many rolls? ");
		int r = input.nextInt();
		this.setRolls(r);
		this.setArraySizes();
	}
	public int possibleRolls(int d){
		// calculates the number of all possible sums based upon the dice amt
		return ((6 * this.getDiceAmt()) - this.getDiceAmt()) + 1;
	}
	
	public DiceStats(){
		// default constructor sets the dice and rolls to 0
		this.setDiceAmt(0);
		this.setRolls(0);
	}
	public DiceStats(int d, int r){
		// constructor that sets private data based on user's call
		this.setDiceAmt(d);
		this.setRolls(r);
		this.sums = new int[this.possibleRolls(this.getDiceAmt())];
		this.freq = new int[this.possibleRolls(this.getDiceAmt())];
		this.percent = new double[this.possibleRolls(this.getDiceAmt())];
	}
	
	public int getDiceSum(int d){
		// gets the sum of dice based upon number of dice rolled
		int sum = 0;
		int temp = 0;
		Random roll = new Random(); //rng obj
		for (int i = 0; i < d; i++){
			temp = roll.nextInt(6)+1;
			sum += temp;
			// returns number btw 0-6 (five values) + 1
		}
		return sum;
	}
	
	public void rollDice(){
		int possibleSums = this.getDiceAmt();
		// counter starting where if every dice rolled a 1
		for(int i = 0; i < this.possibleRolls(this.getDiceAmt()); i++){
			this.sums[i] = possibleSums;
			possibleSums++;		
		}
		// possibleSums should now be equal if every dice rolled a 6
//		System.out.println("max sum = " + possibleSums);	//correct
		int randomBound = possibleSums - this.getDiceAmt();
		// creates a bound variable for the RNG
		// this needs to be the difference btw the maximum sum and the number of dice plus 1
		// bc the possible Sum incremented on the last loop, no need to subtract 1
//		System.out.println("bound = " + randomBound);	//correct
		int rollValue = 0; // address that needs to the be incremented
		for(int r = 0; r < this.getRolls(); r++){
			rollValue = getDiceSum(this.getDiceAmt());
			//calls the dice sum function that calculates each
			//individual dice value and adds them up
//			System.out.print("value= " +(4+rollAddress));	//correct
//			System.out.println("\taddress= " +rollAddress);	//correct
			this.freq[rollValue-this.getDiceAmt()]++;
			// this will increment a value in the frequency
			// array at a passed in address
			//address = value - number of dice
		}
		System.out.println("totalRolls= " + this.getRolls());
		for (int a = 0; a < randomBound; a++){
			// randomBound should reflective of the addresses
			double temp = this.getFreq(a) * 100.0;
			temp = temp / this.getRolls();
			this.percent[a] = temp;
		}
		
	}
	public void printStats(){
		System.out.printf("%-7s %-15s %s\n\n", "Sum", "# of times",
				"Percentage");
		for (int i = 0; i < (this.getDiceAmt()*5)+1; i++){
			System.out.printf("%-7d %-15d %f%%", sums[i], freq[i],
					percent[i]);
			System.out.println(" "); // starts new line
		}
	}
	
	public static void main(String[] args){
		DiceStats ds = new DiceStats();
		ds.inputData();
//		System.out.println(ds.getDiceAmt());
//		System.out.println(ds.getRolls());
		ds.rollDice();
		ds.printStats();	
	}
		
}
	
