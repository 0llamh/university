#include <iostream>
using namespace std;
class Time
{
	//FRIEND FUNCTIONS

		//COMPARISON OPERATORS
		friend bool operator<(const Time& t1, const Time& t2);
		friend bool operator>(const Time& t1, const Time& t2);
		friend bool operator==(const Time& t1, const Time& t2);
		friend bool operator!=(const Time& t1, const Time& t2);
		friend bool operator<=(const Time& t1, const Time& t2);
		friend bool operator>=(const Time& t1, const Time& t2);

		//ARITHMETIC OPERATORS
		friend Time operator+(const Time& t1, const Time& t2);				//ADDS TWO TIME VALUES TOGETHER
		friend Time operator-(const Time& t1, const Time& t2);				//SUBTRACTS TWO TIME VALUES FROM EACH OTHER
		friend Time operator*(const Time& t1, int x);						//ALLOWS A TIME VALUE TO BE MULTIPLIED BY AN INTEGER

		//INPUT AND OUTPUT OPERATORS	
		friend ostream& operator<<(ostream& x, const Time& t1);
		friend istream& operator>>(istream& x, Time& t2);

private:
	int day;
	int hour;
	int minute;
	int second;

public:

	//CONSTRUCTORS
	Time();								//DEFAULT 0~00:00:00
	Time(int s);						//CONVERSION CONSTRUCTOR
	Time(int d, int h, int m, int s);	//SIMPLE INPUT CONSTRUCTOR (INCLUDES ERROR CHECKING)

	//INCREMENT/DECREMENT OPERATORS
	Time& operator++();
	Time& operator--();
	Time operator++(int);
	Time operator--(int);

	//ERROR CHECKING FUNCTION
	void TimeCheck(int d, int h, int m, int s);
};