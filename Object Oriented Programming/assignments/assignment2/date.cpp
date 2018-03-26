//-------------------date.cpp--------------------
//Smith, Paul Cameron
//COP3330-01
//Assignment 2 - The Date Class
//Allows the usage of a Date class along with numerous useful member functions

#include <iostream>
#include <iomanip>
#include "date.h"
using namespace std;

Date::Date(int m, int d, int y)
{
   if (Set(m,d,y) == true)		//ERROR CHECKS FOR VALID DATE SETTING
   {							//IF VALID DATE, STORES SET VALUES INTO PRIVATE MEMBER DATA
      month = m;
	  day = d;
	  year = y;
   }
   else							//IF SET VALUES FAIL ERROR CHECK,
   {							//AUTO SETS DATE TO 1/1/2000
	  month = 1;
	  day = 1;
	  year = 2000;
   }
}
Date::Date()
{				//DEFAULT CONSTRUCTOR SETS DATE TO JAN 1, 2000.
   month = 1;
   day = 1;
   year = 2000;
}
void Date::Input()													//**USER INPUT MEMBER FUCTION**
{   
   cout << "\nPlease input a date (M/D/Y) : " << '\n' << '\t';		//PROMPTS USER TO INPUT A DATE
   do																//COMPLETES ONE TIME THROUGH
   {
	  char slash;													//DUMMY SLASH CHARACTER FOR INPUT
      cin >> month >> slash >> day >> slash >> year;				//COLLECTED INPUT DATA FOR DATE STORAGE
      if (Set(month,day,year) == false)
         cout << "\nInvalid date try again." << '\n' << '\t';		//ERROR STATEMENT
   }while (Set(month, day, year) == false);							//REPEATS UNTIL DATE IS VALID
}
void Date::Show()
{
   if (SetFormat(format) == true)
   {
      if (format == 'T')								//TWO DIGIT FORMAT SETTING
      {
         if (month < 10)								//CHECKS TO SEE IF THE MONTH IS TWO DIGITS OR ONE
            cout << 0 << month << '/';					//PUTS DUMMY 0 INFRONT OF 1 DIGIT MONTH VALUES
         else
            cout << month;
         if (day < 10)									//CHECKS DIGITS OF THE DAY
            cout << 0 << day << '/';					//ADDS DUMMY 0 FOR DAYS LESS THAN 2 DIGITS
         else
            cout << day;
         cout << year;									//NORMAL YEAR OUTPUT
      }
      else if (format == 'L')							//LONG FORMAT SETTING
	  {													//PRINTS OUT THE MONTH ABBREVIATIONS INSTEAD OF NUMBERS
         if (month == 1)
           cout << "Jan ";
         else if (month == 2)
           cout << "Feb ";
         else if (month == 3)
           cout << "Mar ";
         else if (month == 4)
           cout << "Apr ";
         else if (month == 5)
           cout << "May ";
         else if (month == 6)
           cout << "June ";
         else if (month == 7)
           cout << "July ";
         else if (month == 8)
           cout << "Aug ";
         else if (month == 9)
           cout << "Sept ";
         else if (month == 10)
           cout << "Oct ";
         else if (month == 11)
           cout << "Nov ";
         else
           cout << "Dec ";   
         cout << day << ", " << year;					//SHOWS DAYS FOLLOWED BY COMMAS AND YEARS
      }
      else if (format == 'J')							//JULIAN FORMAT SETTING
      {
         int JulianDay = 0;								//INIT JULIAN DAYS TO 0
         int JulianYear = (year - 2000);				//REMOVES THE FIRST TWO DIGITS OF THE YEAR
         if (JulianYear < 10)							//CHECKS THE NUMBER OF REMAINING DIGITS FOR THE YEAR
            cout << 0 << JulianYear << "-";				//ADDS DUMMY 0 IF YEAR VALUE IS LESS THAN TWO DIGITS
         else
            cout << JulianYear << "-";					//PLACES THE YEAR IF TWO DIGITS REMAINING

		 //ASSUMING THE YEAR IS NO GREATER THAN 2100 IT STOPS HERE FOR THE YEAR
		 //WITHOUT THAT ASSUMPTION THERE WOULD NEED TO BE A CHECK FOR VALUES GREATER
		 //THAN THAT AND CHOPS OFF THE FIRST TWO DIGITS
		 //IF THE YEAR IS 2184, THEN IT WOULD BE LIKE THIS:
		 //JulianYear = (year - 2100); etc. etc.

         if (month == 1)								//THE FOLLOWING CHRISTMAS TREE, HARD CODES THE NUMBER OF DAYS FOR EACH MONTH
            JulianDay = day;							//ALL THAT IS REQUIRED IS THE MONTH VALUE AND THE DAY VALUE
         else if (month == 2)							//AND ALL PRECEDING DAYS OF THAT YEAR ARE ADDED
            JulianDay = 31 + day;
         else if (month == 3)
            JulianDay = 31 + 28 + day;
         else if (month == 4)
            JulianDay = 31 + 28 + 31 + day;
         else if (month == 5)
            JulianDay = 31 + 28 + 31 + 30 + day;
         else if (month == 6)
            JulianDay = 31 + 28 + 31 + 30 + 31 + day;
         else if (month == 7)
            JulianDay = 31 + 28 + 31 + 30 + 31 + 30 + day;
         else if (month == 8)
            JulianDay = 31 + 28 + 31 + 30 + 31 + 30 + 31 + day;
         else if (month == 9)
            JulianDay = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + day;
         else if (month == 10)
            JulianDay = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + day;
         else if (month == 11)
            JulianDay = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + day;
         else if (month == 12)
            JulianDay = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + day;
         if (LeapYear(year) == true)					//ADDS AN EXTRA DAY IF IT IS A LEAP YEAR
            JulianDay++;								//VIA FEB 29
         if (JulianDay < 10)							//CHECKS THE # OF DIGITS FOR THE DAY
            cout << 00 << JulianDay;					//ADDS TWO DUMMY 0'S IF LESS THAN 10
         else if (JulianDay < 100)
            cout << 0 << JulianDay;						//ADDS ONE DUMMY 0'S IF LESS THAN 100
         else
            cout << JulianDay;							//NO DUMMY 0'S IF ALREADY HAS 3 DIGITS
      }
      else
         cout << month << '/' << day << '/' << year;	//DEFAULT 'D' FORMAT SETTING
   }
   else
      cout << month << '/' << day << '/' << year;		//PRINTS LIKE THIS DESPITE HAVING SET A FORMAT BEFORE PRINTING
}

//ACCESSOR FUNCTIONS. NOTHING SPECIAL
int Date::GetMonth() const
{
   return month;
}
int Date::GetDay() const
{
   return day;
}
int Date::GetYear() const
{
   return year;
}

bool Date::Set(int m, int d, int y)
{                                                            //IF STATEMENT FOR EACH MONTH AND ITS RESPECTIVE DAYS
   if ( y >= 2000)											 //DEFAULT YEAR BOUNDARY. NO DATE SHOULD BE EARLIER THAN 1/1/2000
   {
       if ( m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12)	//THESE MONTHS HAVE 31 DAYS IN IT
       {
          if (day <= 31 && day >= 1)												//DAY BOUNDARIES
             return true;
          else
             return false;
       }
       else if ( m == 4 || m == 6 || m == 9 || m == 11)								//THESE MONTHS HAVE 30 DAYS IN IT
       {
          if (d <= 30 && d >= 1)													//DAY BOUNDARIES
             return true;
          else
             return false;
       }
       else if ( m == 2)															//FEBRUARY IS A SPECIAL EXCEPTIONS
	   {																			//DIFFERENT DAY BOUNDARIES DEPENDING ON YEAR FOR LEAP YEAR
          if (LeapYear(y) == true)													//IF LEAP YEAR, THERE ARE 29 DAYS
          {
             if (d <= 29 && d >= 1)
                return true;
             else
                return false;
          }
          else if (LeapYear(y) == false)											//IF NOT LEAP YEAR, THERE ARE 28 DAYS
          {
             if (d <= 28 && d >= 1)
                return true;
             else
                return false;
          }
       }
       else
          return false;
   }
   else
	   return false;
}   
bool Date::SetFormat(char f)
{
   if (f == 'D' || f == 'T' || f == 'L' || f == 'J')
   {
      format = f;									//changes the set format for  how the date will be printed in Show()
      return true;
   }
   else												//if no correct format input, still prints out to 
     return false;	
}
void Date::Increment(int numDays)
{
       if ( month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10)
       {
		  for (int i = 0; i < numDays; i++)
			  day++;
		  if (day > 31)
		  {
			  day = day - 31;
			  month++;
		  }
       }
       else if ( month == 4 || month == 6 || month == 9 || month == 11)
       {
          for (int i = 0; i < numDays; i++)
		  if (day > 30)
		  {
			  day = day - 30;
			  month++;
		  }
       }
       else if ( month == 2)
       {
		   if (LeapYear(year) == true)
          {
            for (int i = 0; i < numDays; i++)
			  day++;
		    if (day > 29)
		    {
			  day = day - 29;
			  month++;
		    }
          }
          else if (LeapYear(year) == false)
          {
            for (int i = 0; i < numDays; i++)
			  day++;
		    if (day > 28)
		    {
			  day = day - 28;
		  	  month++;
		    }
          }
       }
	   else if ( month == 12 )
	   {
		   for (int i = 0; i < numDays; i++)
			  day++;
		  if (day > 31)
		  {
			  day = day - 31;
			  month = 1;
			  year++;
		  }
       }
}
int Date::Compare(const Date& d)
{
   if (year > d.year)
     return 1;
   else
   {
      if (month > d.month)
         return 1;
      else
      {
         if (day > d.day)
            return 1;
         else if ( day == d.day)
            return 0;
         else
            return -1;
      }
   }
}
bool Date::LeapYear(int y) const		//LEAP YEAR BOOLEAN
{
   if (y % 100 == 0)		//CHECKS CENTURY YEARS
   {
      if (y % 400 == 0)		//IF CENTURY YEAR IS DIVISABLE BY 400
         return true;		//THEN ITS A LEAP YEAR
      else
         return false;
   }
   else if (y % 4 == 0)		//IF NOT CENTURY YEAR BUT STILL
      return true;			//DIVISIBLE BY 4, THEN YES
   else
      return false;
}
