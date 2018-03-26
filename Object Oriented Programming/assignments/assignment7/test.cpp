#include <iostream>
#include "bitarray.h"

using namespace std;

int main()
{
   int input, count = 0;
   
   cout << "Please input a desired number of bits: ";
   cin >> input;
   
   BitArray b(input);		// starts with all 0 flags

   cout << "Original bitarray: \n";
   cout << b << endl;
   cout << "Array Length: " << b.Length() << endl;

   b.Set(7);
   b.Set(8);
   b.Set(9);
   b.Unset(8);
   b.Flip(2);
   

   cout << "\nChanged bitarray:\n";
   cout << b << '\n';
   cout << "Array Length: " << b.Length() << endl;

   
   for (int i = 0; i < b.Length(); i++)
		if(b.Query(i))
			count++;

   cout << "\nNumber of changed bits: " << count << endl;


}