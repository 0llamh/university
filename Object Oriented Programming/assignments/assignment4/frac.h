//--------------- FRAC.H ---------------
// PAUL C SMITH
// COP3330 - 01
// ASSIGNMENT 4
// the header file for fractions
// ORIGINAL AUTHOR :: Bob Myers
#include <iostream>
using namespace std;
class Fraction
{ 
   friend Fraction operator+(const Fraction& f1, const Fraction& f2);
   friend Fraction operator*(const Fraction& f1, const Fraction& f2);	// unchanged parameters
	// COMPARISON OPERATORS
   friend bool operator<(const Fraction& f1, const Fraction& f2);
   friend bool operator>(const Fraction& f1, const Fraction& f2);
   friend bool operator==(const Fraction& f1, const Fraction& f2);
	// STREAM OPERATORS
   friend ostream& operator<<(ostream& s, const Fraction& f);
public:
   Fraction();						// Set numerator = 0, denominator = 1.
   Fraction(int n, int d=1);		// constructor with parameter and acts as conversion constructor
   void Input();					// input a fraction from keyboard.
   // accessors
   int GetNumerator() const;
   int GetDenominator() const;
   // mutators
   bool SetValue(int n, int d=1);	// set the fraction's value through parameters
   void Simplify();					// simplifier function
   double Evaluate() const;			// Return the decimal value of a fraction
private:
   int numerator;					// may be any integer
   int denominator;					// should always be positive
};