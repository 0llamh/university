// Cameron Smith
// COP3330 - 01
// bitarray.cpp


#include <iostream>
#include <iomanip>
#include "bitarray.h"
using namespace std;

ostream& operator<<(ostream& os, const BitArray& ba)
{
	os << setw(10) << " The Array looks like this: \n";
	//displaying bits
	os << '(';
	for ( int i = 0; i < ba.Length();i++)
		os << ba.Query(i);					// returns bits as 
	os << ')';								// either a 0 or 1
	os << endl;
}
bool operator== (const BitArray& ba1, const BitArray& ba2)
{
	int check = 0;
	if ( ba1.arraySize == ba2.arraySize)
	{
		for (int i = 0; i < ba1.arraySize;i++)
		{
			if (ba1.barray[i] == ba2.barray[i])
				check = 1;
			else
				check = 0;
		}
		if (check == 1)
			return true;
		else
			return false;
	}
	else
		return false;
}
bool operator!= (const BitArray& ba1, const BitArray& ba2)
{
	if (ba1 == ba2)
		return false;
	else
		return true;
}

// CONSTRUCTORS AND DESTRUCTORS
BitArray::BitArray(unsigned int n)
{
	int mini = (n / 8) ;
	while (mini < (n / 8.0))
		mini++;

	if ( mini % charSize == 0)
		arraySize = (( mini / charSize) );
	else
		arraySize = (( mini / charSize) + 1);
	
	barray = new unsigned char[arraySize];
	
	for (int i = 0; i < arraySize;i++)
		barray[i] = 0;
		
}
BitArray::BitArray(const BitArray& ba)
{
	delete [] barray;
	arraySize = ba.arraySize;
	barray = new unsigned char[arraySize];
	
	for (int i = 0; i < arraySize; i++)
		barray[i] = ba.barray[i];
}
BitArray::~BitArray()
{
	/*for (int i = 0; i < Length();i++)		
		Unset(i);*/							// nullify data stored
	delete [] barray;
}

BitArray& BitArray::operator= (const BitArray& ba)
{
	delete [] barray;						// erase old array
	arraySize = ba. arraySize;				// new size
	barray = new unsigned char[arraySize];	// new array with new size
	
	for (int i = 0; i < arraySize; i++)		// copies it all over
		barray[i] = ba.barray[i];
}

unsigned int BitArray::Length() const
{
	return (arraySize * charSize * 8);		// returns the number of bits
}											// in the array

void BitArray::Set(unsigned int index)
{
	if (index <= Length())
	{
		int address = (index / 8);
		if (index % 8 == 0)							// if index is a multiple of 8
			barray[address - 1] |= Mask(8);			// then change the last bit 
		else										// of the previous byte
			barray[address] |= Mask(index % 8);		// otherwise mask it normally
	}
	//	cout << "\nBit #" << index << " has been Set.";

}		
void BitArray::Unset(unsigned int index)
{
	if (index <= Length())
	{
		int address = (index / 8);
		if (index % 8 == 0)							// 
			barray[address - 1] &= ~Mask(8);		//  AND operator with the
		else										//  complement makes sure
			barray[address] &= ~Mask(index % 8);	//  any value changes to 0
	}
	//	cout << "\nBit #" << index << " has been Unset.";

}
void BitArray::Flip(unsigned int index)
{
	if ( index <= Length())
	{
	
		int address = (index / 8);
		if (index % 8 == 0)							// 
			barray[address - 1] ^= Mask(8);			// XOR changes any 1 to 0
		else										// and any 0 to 1
			barray[address] ^= Mask(index % 8);		// WITH LOGIC!! :D 
	}
	//	cout << "\nBit #" << index << " has been Flipped.";
	
}
bool BitArray::Query(unsigned int index) const
{
	int address = (index / 8);					
	if (barray[address] & Mask(index % 8))		// checks the address 
		return true;							// with the mask of index
    else
		return false;
	
}
int BitArray::Mask(unsigned int n) const
{
	return (1 << (8 - n));			// shifts 1 left how ever many times 
									// depending on the remainder of the 
									// index subtracted with the size of a byte 

}


