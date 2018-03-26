//------------------------date.h------------------------
//Smith, Paul Cameron
//COP3330-01
//Assignment 2 - The Date Class
//Allows the usage of a Date class along with numerous useful member functions

class Date
{
   private:
       int month;
       int day;
       int year;
	   char format;
   public:
       Date();						//DEFAULT CONSTRUCTOR
       Date(int m, int d, int y);		//CONSTRUCTOR USING FUNCTION CALL VARIABLES

       void Input();					//PROMPTS THE USER TO ENTER A DATE 
       void Show();				//PRINTS THE STORED DATE
       int GetMonth() const;				//RETURNS MONTH VARIABLE
       int GetDay() const;				//RETURNS DAY VARIABLE
       int GetYear() const;				//RETURNS YEAR VARIABLE
       bool Set( int m, int d, int y);	         	//ERROR CHECKING
       bool SetFormat(char f);		        //ALLOWS CHANGING OF DATE FORMAT WHEN PRINTING
       void Increment(int numDays = 1);			//ALLOWS THE INCREMENT OF DAYS. DEFAULT OF 1.
       int Compare(const Date& d);			//COMPARES TWO DATES TO SEE WHICH ONE COMES FIRST
       bool LeapYear(int year) const;                   //CHECKS LEAP YEAR ELIGIBILITY
};
