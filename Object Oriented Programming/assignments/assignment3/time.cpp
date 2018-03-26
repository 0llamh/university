#include <iostream>
#include <iomanip>
#include "time.h"
using namespace std;

//FRIEND FUNCTIONS
bool operator<(const Time& t1, const Time& t2)
{
	//ALL BOOL FUNCTIONS ARE DEFINED USING THIS ONE. ALL OTHER
	//BOOLS HAVE REFERENCE BACK TO HERE JUST TO MAKE BUG FIXING EASIER

	if (t1.day < t2.day)
		return true;
	else if (t1.day == t2.day)
	{
		if (t1.hour < t2.hour)
			return true;
		else if (t1.hour == t2.hour)
		{
			if (t1.minute < t2.minute)
				return true;
			else if (t1.minute == t2.minute)
			{
				if (t1. second < t2.second)
					return true;
				else
					return false;
			}
			else
				return false;
		}
		else
			return false;
	}
	else
		return false;
}
bool operator>(const Time& t1, const Time& t2)
{
	if (t2 < t1)
		return true;
	else
		return false;
}
bool operator==(const Time& t1, const Time& t2)
{
	if (t1 < t2 || t2 < t1)
		return false;
	else
		return true;
}
bool operator!=(const Time& t1, const Time& t2)
{
	if (t1 < t2 || t2 < t1)
		return true;
	else
		return false;
}
bool operator<=(const Time& t1, const Time& t2)
{
	if (t2 < t1)
		return false;
	else
		return true;
}
bool operator>=(const Time& t1, const Time& t2)
{
	if (t1 < t2)			//USES THE ORIGINAL BOOL OPERATOR (FOR EASIER BUG FIXES)
		return false;
	else
		return true;
}
Time operator+(const Time& t1, const Time& t2)
{
	//CONVERTS ALL TO SECONDS AND ADDS UP BOTH TIME VALUES
	int seconds = ((t1.second + t2.second) + (60 * (t1.minute + t2.minute)) + (3600 * (t1.hour + t2.hour)) + (86400  * (t1.day + t2.day)));
	Time t3(seconds);									//INIT RESULT TIME WITH TOTAL # OF SECONDS
	t3.TimeCheck(t3.day, t3.hour, t3.minute, t3.second);		//ERROR CHECKING
	return t3;											//RETURNS THE RESULT
}
Time operator-(const Time& t1, const Time& t2)
{
	//CONVERTS ALL TO THEIR INDIVIDUAL TOTAL SECOND VALUES
	int t3_seconds, t2_seconds, t1_seconds;
	t1_seconds = (t1.second + ( 60 * (t1.minute)) + ( 3600 * ( t1.hour)) + ( 86400 * ( t1.day)));
	t2_seconds = (t2.second + ( 60 * (t1.minute)) + ( 3600 * ( t1.hour)) + ( 86400 * ( t1.day)));
	if (t1_seconds > t2_seconds)					//DETERMINES IF T1 IS GREATER
		t3_seconds = t1_seconds - t2_seconds;
	else											//IF NOT, T3 = 0 SECONDS
		t3_seconds = 0;
	Time t3(t3_seconds);							//INITALIZES RESULT TIME WITH TOTAL REMAINING SECONDS
	t3.TimeCheck(t3.day, t3.hour, t3.minute, t3.second);		//ERROR CHECKING
	return t3;										//RETURNS RESULT
}
Time operator*(const Time& t1, int x)
{
	int seconds = ((t1.second) + (60 * (t1.minute)) + (3600 * (t1.hour)) + (86400  * (t1.day)));
	seconds = seconds * x;						//TOTAL # OF SECONDS MULTIPLIED BY AN INTEGER
	Time t2(seconds);							//INIT RESULT TIME WITH TOTAL SECONDS
	t2.TimeCheck(t2.day, t2.hour, t2.minute, t2.second);		//ERROR CHECK AND OVERLAP FUNCTION
	return t2;									//RETURNS RESULT TIME
}
ostream& operator<<(ostream& x, const Time& t1)
{
	if (t1.day == 0)                                                                //DAY COUNTER
		x << "0" << '~';
	else
		x << t1.day << '~';
	if ( t1.hour == 0)                                                              //HOUR COUNTER
		x << "00:";
	else if (t1.hour < 10)
		x << '0' << t1.hour << ':';
	else
		x << t1.hour << ':';
	if (t1.minute == 0)                                                             //MINUTE COUNTER
		x << "00:";
	else if (t1.minute < 10)
		x << '0' << t1.minute << ':';
	else
		x << t1.minute << ':';
	if (t1.second == 0)                                                             //SECOND COUNTER
		x << "00";
	else if (t1.second < 10)
		x << '0' << t1.second;
	else
		x << t1.second;
    return x;							//RETURN THE OUTPUT
}
istream& operator>>(istream& x, Time& t1)					
{
	char colon;					//DUMMY CHAR TO ABSORB THE :
	char tilda;					//ABSORBS THE ~
	int d, h, m, s;				//INPUT VALUES
	x >> d >> tilda >> h >> colon >> m >> colon >> s;		//INSERTION OPERATOR
	t1.Time::TimeCheck(d, h, m, s);							//CONVERSION/OVERLAP/ERROR CHECK FUNCTION
	return x;					//RETURNS THE INSERTION
}

//CONSTRUCTORS
Time::Time()			//DEFAULT CONSTRUCTOR
{
	day = 0;
	hour = 0;
	minute = 0;
	second = 0;
}
Time::Time(int s)
{
	day = 0;						//VARIABLE INITIATION
	hour = 0;
	minute = 0;
	second = s;
	int sec_overlap = second/60;				//ALLOWS FOR THE THE NUMBER OF OVERLAPS IN THE SECOND
	for (int i1 = 0; i1 < sec_overlap; i1++)
	{
		second = second - 60;					//DECREMENTS THE NUMBER OF SECONDS PER MINUTE
		minute++;								//MINUTE INCREMENT
	}
	if (minute >= 60)							//ALLOWS FOR MINUTE OVERLAP FOR HOURS
	{
		int min_overlap = minute/60;			//NUMBER OF MINUTE OVERLAPS
		for (int i2 = 0; i2 < min_overlap; i2++)
		{
			minute = minute - 60;				//MINUTE DECCREMENT IN HOURS
			hour++;								//HOUR INCREMENT
		}
	}
	if (hour >= 24)								//HOUR OVERLAP
	{
		int hour_overlap = hour/24;				
		for (int i3 = 0; i3 < hour_overlap; i3++)
		{
			hour = hour - 24;					//HOUR DECREMENT
			day++;								//DAY INCREMENT
		}
	}
}
Time::Time(int d, int h, int m, int s)
{
	TimeCheck(d, h, m, s);		//ERROR CHECKING FUNCTION
}

//MEMBER FUNCTIONS
Time& Time::operator++()
{
	second = second + 1;					//CHANGES THE TIME BY +1 SECOND
	TimeCheck(day, hour, minute, second);	//ERROR CHECK
	return *this;							//RETURNS THE NEW TIME
}
Time& Time::operator--()
{
	second = second - 1;
	if (second < 0 && (minute > 0 || hour > 0 || day > 0))
	{
		second = 59;						//RESETS SECONDS
		minute = minute - 1;				//DOCKS ONE MINUTE
		if (minute < 0 && (hour > 0 || day > 0))		
		{
			minute = 59;					//RESETS MINUTES
			hour = hour - 1;				//DOCKS ONE HOUR
			if (hour < 0 && day > 0)
			{
				hour = 23;					//RESETS HOURS
				day = day - 1;				//DOCKS ONE DAY
			}
		}
		else if (minute < 0 && (hour <= 0 || day <= 0))
			minute = 0;
	}
	else if (second < 0)
		second = 0;
	TimeCheck(day, hour, minute, second);		//ERROR CHECKING FOR NEGATIVES
	return *this;
}
Time Time::operator++(int)
{
	Time temp = *this;							//INITIALIZES A COPY OF THE CALLING OBJECT
	second = second + 1;						//CHANGES THE TIME BY +1 SECOND
	TimeCheck(day, hour, minute, second);
	return temp;								//RETURNS THE OLD TIME
}
Time Time::operator--(int)
{
	Time temp = *this;
	second = second - 1;
	if (second < 0 && (minute > 0 || hour > 0 || day > 0))
	{
		second = 59;						//RESETS SECONDS
		minute = minute - 1;				//DOCKS ONE MINUTE
		if (minute < 0 && (hour > 0 || day > 0))		
		{
			minute = 59;					//RESETS MINUTES
			hour = hour - 1;				//DOCKS ONE HOUR
			if (hour < 0 && day > 0)
			{
				hour = 23;					//RESETS HOURS
				day = day - 1;				//DOCKS ONE DAY
			}
		}
		else if (minute < 0 && (hour <= 0 || day <= 0))
			minute = 0;
	}
	else if (second < 0)
		second = 0;
	TimeCheck(day, hour, minute, second);		//ERROR CHECKING FOR NEGATIVES
	return temp;
}

//ERROR CHECKING FUNCTION
void Time::TimeCheck(int d, int h, int m, int s)
{
	day = d;									//INITIATION VALUES OF TIME
	hour = h;									//ERROR CHECKING IN ORDER
	minute = m;									//TO CHECK FOR OVERFLOW
	second =s;
	if (second >= 60)							//ERROR CHECKING FOR SECOND OVERLAPS
	{
		int sec_overlap = second/60;
		for (int i4 = 0; i4 < sec_overlap; i4++)
		{
			second = second - 60;
			minute++;
		}
	}
	else if (second <= 0)						//NEGATIVE CHECK
		second = 0;
	if (minute >= 60)							//ERROR CHECKING FOR MINUTE OVERLAPS
	{
		int min_overlap = minute/60;
		for (int i5 = 0; i5 < min_overlap; i5++)
		{
			minute = minute - 60;
			hour++;
		}
	}
	else if (minute <= 0)						//NEGATIVE CHECK
		minute = 0;
	if (hour >= 24)								//ERROR CHECKING FOR HOUR OVERLAPS
	{
		int hour_overlap = hour/24;
		for (int i6 = 0; i6 < hour_overlap; i6++)
		{
			hour = hour - 24;
			day++;
		}
	}
	else if (hour <= 0)							//NEGATIVE CHECK
		hour = 0;
	if (day <= 0)								//NEGATIVE CHECK
		day = 0;
}
