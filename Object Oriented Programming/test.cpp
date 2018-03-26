#include <iostream>
#include "bitarray.h"

using namespace std;

int main()
{
   int input;
   
   cout << "Please input a desired number of bits: ";
   cin >> input;
   
   BitArray b(input);		// starts with all 0 flags

   cout << "Original bitarray: \n";
   cout << b;
   cout << endl;

   b.Set(8);
  // b.Unset();
   b.Flip(7);

   cout << "Changed bitarray: \n";
   cout << b;
   cout << '\n';

   //cout << b;

   cout << '\n';


}