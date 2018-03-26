#include <iostream>
#include "date.h"
using namespace std;

int main()
{
   Date d1;		// should default to 1/1/2000
   Date d2(4,10,1992);  // should init to 1/1/2000
   Date d3(9,8,2010);  // should init to 9/8/2010
   Date d4(2,29,2000);  //should init to 2/29/2000
   Date d5(2,29,2001);   //should init to 1/1/2000
   Date d6(9,7,2010);    //should init to 9/7/2010


   // display dates to the screen
   cout << "\nDate d1 is: (1/1/2000) ";
   d1.Show();			
   cout << "\nDate d2 is: (4/10/1992) ";
   d2.Show();
   cout << "\nDate d3 is: (9/8/2010) ";
   d3.Show();
   cout << "\nDate d4 is: (2/29/2000) ";
   d4.Show();
   cout << "\nDate d5 is: (2/29/2001) ";
   d5.Show();
   cout << "\nDate d6 is: (9/7/2010) ";
   d6.Show();

   d1.Input();			// Allow user to enter a date for d1
   cout << "\nThe New Date d1 is: ";
   d1.Show();			
   
   d1.SetFormat('L');		// change format of d1 to "Long" format
   cout << "\nLong Date d1 is: ";
   d1.Show();			// print d1 -- should show now in long format

   d1.SetFormat('J');		//changes format to Julian date
   cout << "\nJulian Date d1 is: ";
   d1.Show();				//prints the JUlian format for d1

   d3.SetFormat('L');		//long d3
   cout << "\nLong Date d3 is: ";
   d3.Show();

   d3.SetFormat('J');		//julian d3
   cout << "\nJulian Date d3 is: ";
   d3.Show();

   cout << "\nDate d2's month is: " << d2.GetMonth();		//month accessor
   cout << "\nDate d3's day is: " << d3.GetDay();			//day accessor
   cout << "\nDate d6's year is: " << d6.GetYear();			//year accessor

   d2.Increment(5);											//tests increment with a value of 5
   cout << "\nDate d2 is now: (+5) ";
   d2.Show();

   d1.Increment();											//tests default increment
   cout << "\nDate d1 is now: (+1) ";
   d1.Show();

   cout << "\nComparing d3 (9/8/2010) to d6 (9/7/2010) returns: " << d3.Compare(d6);		//tests compares, should return 1
   cout << "\nComparing d6 (9/7/2010) to d3 (9/8/2010) returns: " << d6.Compare(d3);		//returns -1
   cout << "\nComparing d6 to d6 returns: " << d6.Compare(d6);								//returns 0
}

