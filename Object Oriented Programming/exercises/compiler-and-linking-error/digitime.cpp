//-------------- digitime.cpp --------------

#include <iostream>       // for cout, cin
#include "digitime.h"       // for Display and Timer declarations

using namespace std;

//-------------- Definition of member functions for class Display

Display::Display(int lim)
// Initialize a new Display object.
{
   value = 0;
   limit = lim;
}

void Display::Increment()
// Add 1 to value.  If incrementing makes value
// equal to limit, reset value to zero.
{
   value++;
   if (value == limit)
      value = 0;
}

int Display::SetValue(int val)
// Set the value.  If the argument is negative, we make it positive.
// To make sure value is within the right range, we set the value to
// its remainder upon division by limit.
{
   if (val < 0)
      val = -val;
   value = (val % limit);
}

int GetValue()
// Return the current value.
{
   return value;
}

void Display::Show()
// Show the value of a Display.
{
   if (value < 10)          / Pad with a leading zero, if needed,
      cout << '0';

   cout << value;           // and in any case, display the value.
}

int Display::GetLimit()
// Return the limit for this display
{
   return limit;
}

//-------------- Definition of member functions for class Timer

Timer::Timer() : hours(24), minutes(60)
// Initialize a new Timer object,
// setting hours limit to 24 and minutes limit to 60.
{
   // All the work is done by the two constructor calls in the header.
}

void Timer::Increment()
// Add 1 minute to timer.
{
   minutes.Increment();

   if (minutes.GetValue() == 0) // We've turned the minute counter over,
      hours.Increment();        // so we have to increment the hours counter.
}

void Timer::Set()
// Set hours and minutes from the keyboard.
{
   int setting;                 // user-input values for hours, minutes

   cout << "Set hours to what value? ;
   cin >> setting;
   hours.SetValue(setting);     // Set hours.

   cout << "Set minutes to what value? ";
   cin >> setting;
   minutes.SetValue(setting);   // Set minutes.
}

void Timer::Show()
// Show the current timer's settings.
{
   hours.Show();
   cout << ':';
   minutes.Show();
}

