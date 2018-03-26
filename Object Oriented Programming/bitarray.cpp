// Cameron Smith
// COP3330 - 01
// bitarray.cpp


#include <iostream>
#include <iomanip>
#include "bitarray.h"
using namespace std;

ostream& operator<<(ostream& os, const BitArray& ba)
{
	const int SHIFT = 8 * ba.size - 1;
	const unsigned MASK = 1 << SHIFT;
	
	os << setw(10) << " The Array looks like this: \n";
	
	//displaying bits
	for (int i = 0; i <= ba.arraySize; i++)
	{
		for ( unsigned ii = 1; ii <= SHIFT + 1; ii++)
		{
			os << (ba.barray[i] & MASK ? '1' : '0' );
			ba.barray[i] <<= 1; 			//shifts value to the left by 1
			//cout << '2';
		}
	}
	//for (int i = 0; i < ba.arraySize;i++)
		//os << ba.barray[i];
	
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

	if ( mini % size == 0)
		arraySize = (( mini / size) );
	else
		arraySize = (( mini / size) + 1);
	
	barray = new unsigned char[arraySize];
	
	for (int i = 0; i < arraySize;i++)
		barray[i] = 1;
		
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
	delete [] barray;
}

BitArray& BitArray::operator= (const BitArray& ba)
{
	delete [] barray;
	arraySize = ba. arraySize;
	barray = new unsigned char[arraySize];
	
	for (int i = 0; i < arraySize; i++)
		barray[i] = ba.barray[i];
}

unsigned int BitArray::Length() const
{
	return (arraySize * size);
}

void BitArray::Set(unsigned int index)
{
	int address = index / size;
	switch (index % size)
	{
		case 0 :
			barray[address] |= (00000001);
			break;
		case 1 :
			barray[address] |= (10000000);
			break;
		case 2 :
			barray[address] |= (01000000);
			break;
		case 3 : 
			barray[address] |= (00100000);
			break;
		case 4 :
			barray[address] |= (00010000);
			break;
		case 5 :
			barray[address] |= (00001000);
			break;
		case 6 :
			barray[address] |= (00000100);
			break;
		case 7 :
			barray[address] |= (00000010);
			break;
	}
	
	//barray[address] |= Mask(index % size);
}		
void BitArray::Unset(unsigned int index)
{
	int address = index / size;
	// switch (index % size)
	// {
		// case 0 :
			// barray[address] &= ~(00000001);
			// break;
		// case 1 :
			// barray[address] &= ~(10000000);
			// break;
		// case 2 :
			// barray[address] &= ~(01000000);
			// break;
		// case 3 : 
			// barray[address] &= ~(00100000);
			// break;
		// case 4 :
			// barray[address] &= ~(00010000);
			// break;
		// case 5 :
			// barray[address] &= ~(00001000);
			// break;
		// case 6 :
			// barray[address] &= ~(00000100);
			// break;
		// case 7 :
			// barray[address] &= ~(00000010);
			// break;
	// }
	
	barray[address] &= ~Mask(index);

}
void BitArray::Flip(unsigned int index)
{
	int address = index / size;
	// switch (index % size)
	// {
		// case 0 :
			// barray[address] ^= 00000001;
			// break;
		// case 1 :
			// barray[address] ^= 10000000;
			// break;
		// case 2 :
			// barray[address] ^= 01000000;
			// break;
		// case 3 : 
			// barray[address] ^= 00100000;
			// break;
		// case 4 :
			// barray[address] ^= 00010000;
			// break;
		// case 5 :
			// barray[address] ^= 00001000;
			// break;
		// case 6 :
			// barray[address] ^= 00000100;
			// break;
		// case 7 :
			// barray[address] ^= 00000010;
			// break;
	// }
	
	barray[address] ^= Mask(index);

}
bool BitArray::Query(unsigned int index) const
{
	int address = index / size;
	if (barray[address] & Mask(index))
		return true;
	else
		return false;
}
int BitArray::Mask(unsigned int n) const
{
	return (1 << n);
}


