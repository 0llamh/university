//-------------- main.cpp --------------

#include <iostream>    // for cout, cin
#include "digitime.h"    // for our Timer and Display classes

using namespace std;

void main()
{
   Timer t;              // Create and initialize a Timer object named "t."

   cout << "Here's the initial value of the timer: "
   t.Show();
   cout << "\n\n";

   t.Set();              // Allow the user to set the timer's value.
   cout << "Here are the new settings: ";
   Show();
   cout << "\n\n";

   cout << "Now we run it for ten minutes . . .\n";

   for (int i = 0, i < 10, i++)
   {
      t.Increment(1);
      t.Show();
      cout << '\n';
   }

   // Freeze the screen until the user presses a key.
   cout << \nPress any key and ENTER to conclude processing: ";
   char any;
   cin >> any;
   cout << "\nPROCESSING COMPLETED ... GOOD BYE\n";
}











void Timer::Show()
// Show the current timer's settings.
{
        cout << "Howdy, Y'all!   I'm a timer!!\n";
}

