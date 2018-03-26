// -------------------FLIST.CPP--------------------
// PAUL C SMITH
// COP3330 - 01
// ASSIGNMENT 4
// definitions for fraction array class

#include <iostream>
#include "flist.h"
using namespace std;
ostream& operator<<(ostream& output, const FList& f1)
{
	if (f1.currentSize == 0)
		output << "The Fraction List is empty.\n";
	else
	{
		for ( int i = 0; i < f1.currentSize; i++)
		{
			output << f1.fracList[i];
			if ( i != (f1.currentSize - 1))
				output << ", ";
			else
				output << '\n';
		}
	}
	return output;
}
FList::FList()		// const maxsize of 20
{
	cout << "Creating Fraction List...\n";
	currentSize = 0;												// creates an array with 0 entries
}
// array addtions/deletion booleans
bool FList::Insert(const Fraction& f1)
{
	if (currentSize < maxSize)
	{
		currentSize++;
		fracList[currentSize-1] = f1;
		return true;
	}
	else
	{
		cout << "*** Invalid list position\n";
		return false;
	}
}
bool FList::Insert(const Fraction& f1, int insertion)
{
	if (insertion <= currentSize && insertion > 0 && currentSize < maxSize)
	{
		currentSize++;						// bumps up size for one more fraction
		fracList[currentSize] = 0;			// fills in new fraction with 0
		for (int i = currentSize; i >= insertion ; i--)
			//work backwards starting from the end
			fracList[i] = fracList[i-1];
		fracList[insertion-1] = f1;			//replaces the term you want to replace with the desired value
		return true;
	}
	else if (currentSize != maxSize && (insertion <= 0 || insertion > maxSize))
	{
		cout << "*** Invalid list position\n";
		return false;
	}
	else
	{
		cout << "*** FList full.\n";
		return false;
	}
}
bool FList::Delete(int deletionTerm)
{
	if (deletionTerm == currentSize)
	{
		currentSize--;							// decrease array size by one
		return true;							// cutting off last term
	}
	else if (deletionTerm < currentSize && deletionTerm > 0)
	{
		for (int i = deletionTerm-1; i < currentSize; i++)
			fracList[i] = fracList[i+1];					// copies values over from the right
															// starting at the deletion term
		currentSize--;										// cuts off last term
		return true;
	}
	else
	{
		cout << "*** Invalid position\n";
		return false;
	}
}
// array accessors
int FList::Size() const
{
	return currentSize;		//size of the array
}
Fraction FList::Largest() const
{
	Fraction largestFrac;
	for (int i = 0; i < currentSize; i++)	// whole array
	{
		if (fracList[i] > largestFrac)		
			largestFrac = fracList[i];		// replaces largest frac value
	}
	return largestFrac;
}
int FList::HowMany(Fraction f1)
{
	int equalFracs = 0;						// counter	
	for (int i = 0; i < currentSize; i++)		
	{
		if (fracList[i] == f1)		
			equalFracs++;
	}
	return equalFracs;
}
// array calculators
Fraction FList::Sum() const
{
	Fraction fracSum = 0;
	for (int i = 0; i < currentSize; i++)
		fracSum = fracSum + fracList[i];
	fracSum.Simplify();
	return fracSum;
}
double FList::Average() const
{
	double fracAvg = 0;
	Fraction fracSum = Sum();
	fracAvg = (fracSum.GetNumerator() / static_cast<double>(fracSum.GetDenominator())) / (currentSize);
	// change numerator to double and divide by denominator and then again by current size
	return fracAvg;
}
Fraction FList::Product(int a, int b)
{
	Fraction fracProduct = 1;
	for (int i = (a-1); i < b; i++)
	{
		fracProduct = fracProduct * fracList[i];
		fracProduct.Simplify();
	}
	fracProduct.Simplify();
	return fracProduct;
}
void FList::SimplifyAll()
{
	for (int i = 0; i < currentSize; i ++)
		fracList[i].Simplify();
}
void FList::Clear()
{
	for (int i = 0; i < currentSize; i++)
		fracList[i] = 0;
	currentSize = 0;
}
