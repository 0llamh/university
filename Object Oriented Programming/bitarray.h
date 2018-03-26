//   bitarray.h
//
//   BitArray class declaration

#ifndef _BITARRAY_H
#define _BITARRAY_H

#include <iostream>
using namespace std;

class BitArray
{
   friend ostream& operator<< (ostream& os, const BitArray& ba);
   friend bool operator== (const BitArray& ba1, const BitArray& ba2);
   friend bool operator!= (const BitArray& ba1, const BitArray& ba2);

public:
   BitArray(unsigned int n);    // Construct an array that can handle n bits
   BitArray(const BitArray& ba);   // copy constructor
   ~BitArray();                 // destructor

   BitArray& operator= (const BitArray& ba);  // assignment operator

   unsigned int Length() const;            // return number of bits in bitarray

   void Set   (unsigned int index);        // set bit with given index to 1
   void Unset (unsigned int index);        // set bit with given index to 0
   void Flip  (unsigned int index);        // change bit (with given index)
   bool Query (unsigned int index) const;  // return true if the given bit
					   //  is set to 1, false otherwise
					   
private:
   unsigned char* barray;		   // pointer to the bit array
   int arraySize;
   static const int size = sizeof(unsigned char);
   
   int Mask(unsigned int n) const;
};

#endif

