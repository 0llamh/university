//class Fraction
//Author:  Bob Myers
//
//For COP3252, Java Programming

public class Fraction
{
private int numerator = 0;		// numerator (and keeps sign)
private int denominator = 1;		// always stores positive value

public Fraction()
{
}

public Fraction(int n, int d)
{
 if (set(n,d)==false)
	set(0,1);
}

public boolean set(int n, int d)
{
 if (d > 0)
 {
	numerator = n;
	denominator = d;
	return true;
 }
 else
	return false;
}

public String toString()
{
 return (numerator + "/" + denominator);
}

public int getNumerator()
{
 return numerator;
}

public int getDenominator()
{
 return denominator;
}

public double decimal()
{
 return (double)numerator / denominator;
}

public Fraction simplify(){
	Fraction sim;	// 
	int gcd = -1;	// greatest common denom variable
	int smallerVal;	// smallest of n&d to initialize the gcd
	if ((this.numerator > this.denominator) && (this.numerator > 0))
		smallerVal = this.denominator;
	else if ((this.numerator < 0) && (-this.numerator > this.denominator))
		smallerVal = this.denominator;
	else if ((this.numerator < 0) && (-this.numerator < this.denominator))
		smallerVal = -this.numerator;
	else
		smallerVal = this.numerator;
	for (int i = smallerVal; i > 0; i--)
		if ((this.numerator % i == 0) && (this.denominator % i == 0)){
			gcd = i;		// grabs the greatest value
			i = 0;			// ends the loop
		}
	if (gcd > 1){			// gcd needs to be atleast 2 for simplification
		int newNum = (this.numerator / gcd);	
		int newDen = (this.denominator / gcd);
		sim = new Fraction(newNum, newDen);		// new obj with simplified values
	}
	else
		sim = new Fraction(this.numerator, this.denominator);	//spits out copy of original
	return sim;
}

public Fraction add(Fraction f){
	Fraction result;
	int result_n = ((this.numerator * f.denominator) + (this.denominator * f.numerator));
	int result_d = (this.denominator * f.denominator);
	result = new Fraction(result_n, result_d);
	return result.simplify();		// returns a simplified result
}

public Fraction subtract(Fraction f){
	Fraction result;
	int result_n = ((this.numerator * f.denominator) - (this.denominator * f.numerator));
	int result_d = (this.denominator * f.denominator);
	result = new Fraction(result_n, result_d);
	return result.simplify();		// returns a simplified result
}

public Fraction multiply(Fraction f){
	Fraction result;
	int result_n = ((this.numerator * f.numerator));
	int result_d = (this.denominator * f.denominator);
	result = new Fraction(result_n, result_d);
	return result.simplify();		// returns a simplified result
}

public Fraction divide(Fraction f){
	Fraction result;
	if (f.numerator < 0){
		int result_n = (this.numerator * -(f.denominator));
		int result_d = (this.denominator * -(f.numerator));
		result = new Fraction(result_n, result_d);
	}
	else{
		int result_n = ((this.numerator * f.denominator));
		int result_d = (this.denominator * f.numerator);
		result = new Fraction(result_n, result_d);
	}
	return result.simplify();		// returns a simplified result
	
}




}
