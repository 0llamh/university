// Sample main program to test the Time class
// Author:  Bob Myers

#include <iostream>
#include "time.h"

using namespace std;

int main()
{
   Time t1(86430), t2(86100), t3(117456), t4(987654321);
   cout << "t1 = " << t1 << '\n';
   cout << "t2 = " << t2 << '\n';
   cout << "t3 = " << t3 << '\n';
   cout << "t4 = " << t4 << '\n';

   cout << "Enter first Time object (DAYS~HH:MM:SS):\t";
   cin >> t1;

   cout << "Enter second Time object (DAYS~HH:MM:SS):\t";
   cin >> t2;

   cout << "\n\n"; 
   cout << t1 << " + " << t2 << " = " << t1 + t2 << '\n';
   cout << t1 << " - " << t2 << " = " << t1 - t2 << "\n\n";

   if (t1 < t2) 	cout << t1 << " <  " << t2 << " is TRUE\n";
   else				cout << t1 << " <  " << t2 << " is FALSE\n";

   if (t1 > t2) 	cout << t1 << " >  " << t2 << " is TRUE\n";
   else				cout << t1 << " >  " << t2 << " is FALSE\n";

   if (t1 <= t2) 	cout << t1 << " <= " << t2 << " is TRUE\n";
   else				cout << t1 << " <= " << t2 << " is FALSE\n";

   if (t1 >= t2) 	cout << t1 << " >= " << t2 << " is TRUE\n";
   else				cout << t1 << " >= " << t2 << " is FALSE\n";

   if (t1 == t2) 	cout << t1 << " == " << t2 << " is TRUE\n";
   else				cout << t1 << " == " << t2 << " is FALSE\n";

   if (t1 != t2) 	cout << t1 << " != " << t2 << " is TRUE\n\n";
   else				cout << t1 << " != " << t2 << " is FALSE\n";

   cout << t1 << " + 3600 = " << t1 + 3600 << '\n';
   cout << t2 << " + 1 = " << t2 + 1 << '\n';


   cout << "\n\nThe 1st time objust is " << t1 << '\n';
   cout << "Counting up by ones... \n";
   for (int i = 0; i < 62; i++)
   {
	   cout << t1++ << '\n';
   }

   cout << "\n\nThe 2nd time object is " << t2 << '\n';
   cout << "Counting down by ones...\n";
   for (int i = 0; i < 62; i++)
   {
	   cout << --t2 << '\n';
   }
//test
}
