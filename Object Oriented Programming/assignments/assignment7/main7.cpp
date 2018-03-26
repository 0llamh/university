#include <iostream>
#include "sieve.h"
using namespace std;

int main()
{
   unsigned int i, max, counter = 0;

   cout << "\nEnter a positive integer for the maximum value: ";
   cin >> max;
   
   BitArray ba(max);
   cout << ba.Length() << endl;
   cout << sizeof(unsigned char ) << endl;
   
   Sieve(ba);                    // find the primes (marking the bits)

   cout << "The bit array looks like this: \n"
        << ba
        << '\n';  

   cout << "\nPrimes less than " << max << ':' << '\n';
   for (i = 0; i < max; i++)
   {    
       if (ba.Query(i))
       {
	    counter++;
            cout << i;
            if (counter % 8 == 0)
            {
                cout << '\n';
                counter = 0;
            }
            else
                cout << '\t';
       }
   }


   cout << "\nGoodbye!\n";
   return 0;
}

