// Cameron Smith
// COP 3330 - 01
// sieve.h

#ifndef _SIEVE_H
#define _SIEVE_H

#include <iostream>
#include "bitarray.h"
using namespace std;

void Sieve(BitArray& ba)
{
	int multiple;
	for (int i = 1; i <= ba.Length(); i++)	// turn all bits to 1
		ba.Set(i);
		
	for (int i = 0; i < ba.Length(); i++)
	{
		if ( i == 0 || i == 1)			// 0 and 1 are not prime
			ba.Unset(i);				// so unset them
		else if (ba.Query(i))			// for all other values after...
			for ( int q = 2; q < ((ba.Length() / i) + 1); q++)
			{							// start at its next multiple (2)
				multiple = i * q;		// and go for as long as its multiples
				ba.Unset(multiple);		// are within the array
			}							// and unset them
	}	
}

#endif