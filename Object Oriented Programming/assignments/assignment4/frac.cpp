//--------------- FRAC.CPP ---------------
// PAUL C SMITH
// COP3330 - 01
// ASSIGNMENT 4
// The class definition for fractions.
// ORIGINAL AUTHOR :: Bob Myers
#include "frac.h"									// iostream is already included in frac.h
using namespace std;
// FRIEND FUNCTIONS

	// OPERATOR OVERLOADS
Fraction operator+(const Fraction& f1, const Fraction& f2)
{
   Fraction r;										// declare a Fraction to hold the result
   r.numerator = (f1.numerator*f2.denominator)		// load result Fraction with sum
                  + (f2.numerator*f1.denominator);	// of adjusted numerators
   r.denominator = f1.denominator*f2.denominator;	// load result with the coommon denominator
   r.Simplify();									// simplify result
   return r;										// return the result Fraction
}
Fraction operator*(const Fraction& f1, const Fraction& f2)
{
	Fraction r;										// initialize a result fraction
	r.numerator = (f1.numerator*f2.numerator);		// numerator result
	r.denominator = (f1.denominator*f2.denominator);// denominator result
	return r;										// return the result fraction
}
bool operator< (const Fraction& f1, const Fraction& f2)
{
   return (f1.numerator*f2.denominator < f2.numerator*f1.denominator);
}
bool operator> (const Fraction& f1, const Fraction& f2)
{
   return (f2 < f1);
}
bool operator== (const Fraction& f1, const Fraction& f2)
{
   return !(f1 < f2 || f2 < f1);
}
ostream& operator<< (ostream& os, const Fraction& f)
{  
  os << f.numerator << '/' << f.denominator;
  return os;
}

// MEMBER FUNCTIONS
Fraction::Fraction()					// Default constructor.  Initializes fraction to 0/1
{
   numerator = 0; 
   denominator = 1;
}
Fraction::Fraction(int n, int d)	// initializes fraction to n/d (defaults to 0/1 if invalid data)
{
   if (SetValue(n, d) == false)
	SetValue(0, 1);
}
void Fraction::Input()
// Get a fraction from standard input, in the form "numerator/denominator."
// what kind of error checking should be added?
{
   char divSign;								// used to consume the '/' character during input
   do
   {
      cin >> numerator >> divSign >> denominator;
      if (denominator <= 0)
	cout << "*** Denominator must be positive.  Try again: ";

   } while (denominator <= 0);
}
/* Commented out, because we're using operator<< now
 void Fraction::Show() const
 // Display a fraction, in the form "numerator/denominator."
 {
    cout << numerator << '/' << denominator;
 }
*/
	// ACCESSOR FUNCTIONS
int Fraction::GetNumerator() const
{
   return numerator;
}
int Fraction::GetDenominator() const
{
   return denominator;
}
	// MUTATOR FUNCTION
bool Fraction::SetValue(int n, int d)
// sets fraction to n/d and returns true for success (good data)
// returns false and leaves fraction alone if bad data
{
   if (d <= 0)
	return false;
   numerator = n;
   denominator = d;
   return true;
}
void Fraction::Simplify()
{
	if (numerator == 0)				// sets any 0/N fraction to 0/1
		denominator = 1;
	else 
	{
		int GCD = 1;
		if (numerator > 0)
		{
			for (int i = 1; i <= numerator && i <= denominator; i ++)	// if numerator is positive GCD
			{ 
				if ((numerator) % i == 0 && denominator % i == 0)
					GCD = i;
			}
		}
		else
		{
			int temp = numerator * -1;					// multiplies numerator by -1 and stores it as temp for GCD
			for (int i = 1; i <= temp && i <= denominator; i++)
			{
				if (temp % i == 0 && denominator % i == 0)
					GCD = i;
			}
		} 
		denominator = denominator / GCD;				// reduces denominator by GCD
		numerator = numerator / GCD;					// numerator divided by GCD
	}
}
	// CONVERSION TO INTEGER FUNCTION
double Fraction::Evaluate() const
// Calculates and returns the decimal value of a fraction
{
   return static_cast<double>(numerator) / denominator;
}

