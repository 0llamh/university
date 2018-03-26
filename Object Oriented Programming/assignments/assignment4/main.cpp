#include <iostream>
#include "flist.h"

using namespace std;

int main()
{
   int start;
   Fraction entry;
   FList a;
   cout << "Welcome!\n";
   cout << a;
   cout << "How many numbers to start with (1 - 20)? ";
   cin >> start;
   cout << "Please input the " << start << " starting fractions\n";
   for (int i = 0; i < start; i++)
   {
        cout << "Fraction #" << (i+1) << ":  ";
	entry.Input();
	a.Insert(entry);
   }
   cout << "\nHere's the list:\n" << a;

   cout << "Size of list = " << a.Size() << '\n';
   cout << "Sum of items in list = " << a.Sum();
   cout << "\nProduct of first 5 fractions in list = " << a.Product(1,5);
   cout << "\nAverage of list = " << a.Average() << "\n\n";

   if (a.Insert(Fraction(1,10),0) == false)	cout << "* Insert 1 failed *\n";
   if (a.Insert(Fraction(1,20),12) == false)	cout << "* Insert 2 failed *\n";
   if (a.Insert(Fraction(1,30)) == false)	cout << "* Insert 3 failed *\n";

   cout << "The list after insert attempts 1-3:\n" << a << '\n';

   if (a.Insert(Fraction(-9,10),3) == false)	cout << "* Insert 4 failed *\n";
   if (a.Insert(Fraction(-19,20),7) == false)	cout << "* Insert 5 failed *\n";

   cout << "The list after insert attempts 4-5:\n" << a;
   cout << "Size of list = " << a.Size() << "\n\n";

   if (a.Delete(0) == false)		cout << "* Delete 1 failed *\n";
   if (a.Delete(16) == false)		cout << "* Delete 2 failed *\n";
   cout << "The list after delete attempts 1-2:\n" << a << '\n';

   if (a.Delete(4) == false)		cout << "* Delete 3 failed *\n";

   cout << "The list after delete attempt 3:\n" << a;
   
   cout << "\nSize of list = " << a.Size() << '\n';
   cout << "Sum of items in list = " << a.Sum();
   cout << "\nProduct of last 5 fractions in list = "
        << a.Product(a.Size()-4, a.Size());
   cout << "\nAverage of list = " << a.Average() << "\n\n";

   cout << "The value 1/2 appears " << a.HowMany(Fraction(1,2)) << " times\n";
   cout << "The value 1/3 appears " << a.HowMany(Fraction(1,3)) << " times\n";
   cout << "The value 0 appears " << a.HowMany(0) << " times\n\n";

   cout << "Largest value in the list is " << a.Largest() << "\n\n";

   cout << "The list (unsimplified):\n" << a;
   a.SimplifyAll();
   cout << "\nThe list (simplified):\n" << a;

   // Extra credit only.  Uncomment these lines to do the extra credit
/*
   cout << "\nTesting Rotate()\n";
   a.Rotate(-4);
   cout << "New list:\n" << a;

   a.Rotate(0);
   cout << "New list:\n" << a;

   a.Rotate(15);
   cout << "New list:\n" << a << '\n';
*/

   a.Clear();
   cout << "Final list:\n" << a;

   return 0;
}