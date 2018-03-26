// CAMERON SMITH
// COP 3330
// MYSTRING CLASS

#include <iomanip>
#include <cstring>
#include <cctype>
#include <cstdio>
#include "mystring.h"
using namespace std;



ostream& operator<< (ostream& output, const MyString& s)
{
	for ( int i = 0; i < s.size; i++)
		output << s.string[i];				// stores string into output one by one in order
	return output;
}
istream& operator>> (istream& input, MyString& s)
{
	int max = s.size;			
	char ch = 0;
	while (input.peek() == ' ') //checks character after the current one
			input.get ();
	do{
		ch = input.get();
		if( s.size >= max)
		{
			max = max + 5;		// so no resets of memory are needed
			char* newString = new char[max];		// new arra for the input
			for ( int i = 0; i < s.size; i++)
				newString[i] = s.string[i];			//copies in new string
			delete [] s.string;
			s.string = newString;
		}
		if (isspace(ch) == false)					// only takes in char that aren't whitespace
		{
			s.string[s.size] = ch;				// takes in each character in array
			s.size++;							//increment of size
		}
	}while (isspace(ch) == false);		
	return input;
}
istream& getline (istream& input, MyString& s, char delim)
{
//	input.getline(s.string, strlen(s.string), delim);
	MyString instring;
//	delete [] instring.string;
//	instring.string = new char[s.size];
	char ch = 0;
	int counter = 0;
	do{
		ch = input.get();
		if( ch != delim)
		{
			instring[counter] = ch;
			counter++;
		}
	}while(ch != delim);
	s.string = instring.string;
	instring.string = NULL;
	return input;
	
}

MyString operator+ (const MyString& s1, const MyString& s2)
{
	MyString sum;
	delete [] sum.string;
	sum.size = (s1.size + s2.size);
	sum.string = new char[sum.size];
	for (int i = 0; i < s1.size; i++)
		sum.string[i] = s1.string[i];
	for (int i = 0; i < s2.size; i++)
		sum.string[i + s1.size] = s2.string[i];
	return sum.string;
}

bool operator< (const MyString& s1, const MyString& s2)
{
	if (s1.size < s2.size)
	{
		for (int i = 0; i < s1.size; i++)
		{
			if (s1.string[i] > s2.string[i])
				return true;
			else if (s1.string[i] > s2.string[i])
				return false;
		}
	}
	else if (s1.size <= s2.size)
	{
		for ( int i = 0 ; i < s1.size; i++)
		{
			if (s1.string[i] < s2.string[i])
				return true;
			else if (s1.string[i] > s2.string[i])
				return false;
		}
	}
	else
		return false;
}
bool operator> (const MyString& s1, const MyString& s2)
{
	if (s2 < s1)
		return true;
	else
		return false;
}
bool operator<=(const MyString& s1, const MyString& s2)
{
	if (s2 < s1)
		return false;
	else
		return true;
}
bool operator>=(const MyString& s1, const MyString& s2)
{
	if (s1 < s2)
		return false;
	else
		return true;
}
bool operator==(const MyString& s1, const MyString& s2)
{
	if ((s1 < s2) || (s2 < s1))
		return false;
	else
		return true;
}
bool operator!=(const MyString& s1, const MyString& s2)
{
	if ((s1 < s2) || (s2 < s1))
		return true;
	else
		return false;
}
//CONSTRUCTORS
MyString::MyString()
{
	size = 0;
	string = new char[size];				// sets up an array of chars with a size of 0
}
MyString::MyString(const char* userString)
{
	size = strlen(userString);			// stores the parameter's size into our size
	string = new char[size];			// dynamic array off the same size of the parameter
	//strcpy(string, userString);			// copies the parameter string into our string
	for ( int i = 0; i < size; i++)
		string[i] = userString[i];
}
MyString::MyString(int nums)
{
	int digits= 0;
	int numbercopy = nums;
	while(numbercopy > 0){
		numbercopy = numbercopy / 10;		// increments digits for every time
		digits++;							// the numbers are divisible by 10
	}
	size = digits + 1;							// array size to exact number of digits
	string = new char[size];				// initialization of array of digit size
	sprintf(string, "%d", nums);			// c function to copy numbers to character array
	// found the above c function online 
}
MyString::~MyString()
{
	delete [] string;		// deallocation of memory
}
MyString::MyString(const MyString& s)
{
	delete [] string;		// deallocates current memory in string
	size = s.size;			// stores the same size
	for (int i =0; i < size; i++)
		string[i] = s.string[i];			// copies each character in a for loop
}

MyString& MyString::operator=(const MyString& s)
{
	delete [] string;			// reset of data
	size = s.size;				// new size to match parameter
	string = new char[size];	// new array of same name of same size as parameter
	for ( int i = 0; i < size; i++)
		string[i] = s.string[i];		// copies each character through a loop
	return *this;
}
MyString& MyString::operator+=(const MyString& s)
{
	int original_size = size;		// the size of the calling object before change
	char* temp = new char[size + s.size];			// creates a temp string to store original string
	strncpy(temp, string, size);
	delete [] string;				// deletes old string
	string = temp;	// adds more memory based on parameter
	strcat(string, s.string);
	return *this;					// returns the changed calling object
}

//Bracket Operators
char& MyString::operator[] (unsigned int index)
{
	if (strlen(string) > index)
		return string[index];
	else
	{
		int oldsize = size;
		char* temp = new char[size + (index - size)];
		strncpy(temp,string,size);
		size = strlen(temp) + 1;
		delete [] string;
		string = new char[size];
		strcpy(string, temp);
        	temp = NULL;
		for (int i = oldsize; i < index; i++)
			string[i] = ' ';
		return string[index];
	}
}
const char& MyString::operator[] (unsigned int index) const
{
	static char null = '\0';
	if ( strlen(string) > index)
		return string[index];
	else
		return null;
}

MyString& MyString::insert (unsigned int index, const MyString& s)
{
	int oldsize = size;
	MyString str1 = substring(0,index);	//first half of string
	MyString str2 = substring(index);	// second half of string
	delete [] string;		
	string = new char[oldsize + s.size];	//new size using oldsize and parameter 	
	size = oldsize + s.size;
	int marker = 0;				//string placeholder
	for (int i = 0; i < index; i++)
	{
		string[marker] = str1.string[i];
		marker++;
	}
	for (int i = 0; i < s.size; i++)
	{
		string[marker] = s.string[i];
		marker++;
	}
	for (int i = 0; i < str2.size + 1; i++)
	{
		string[marker] = str2.string[i];
		marker++;
	}
	str1.string = NULL;		// nullifies substrings
	str2.string = NULL;
	return *this;
}

int MyString::indexOf(const MyString& s) const
{
	char* indexstring = strstr(string, s.string);		// strstr as recommended by TA
	int i = (indexstring - string);
	if (i < 0)
		return -1;					
	return i;
}

int MyString::getLength() const
{
	return strlen(string);
}
const char* MyString::getCString() const
{
	return string;
}	

MyString MyString::substring( unsigned int start, unsigned int length) const
{
	MyString sub;
	delete [] sub.string;
	sub.string = new char[length + start];
	sub.size = strlen(sub.string);
	if ((start + length) >= strlen(string))
	{
		int count = 0;
		for ( unsigned int i = start; i < strlen(string); i++)
		{
			sub.string[count] = string[i];
			count++;
		}
	}
	else
	{
		int count = 0;
		for ( unsigned  int i = start; i < (start + length); i++)
		{
			sub.string[count] = string[i];
			count++;
		}
	}
	return sub.string;
}
MyString MyString::substring (unsigned int start) const
{
	MyString sub;
	delete [] sub.string;
	sub.string = new char[strlen(string) - start];
	sub.size = strlen(sub.string);
	int count = 0;
	for (unsigned int i = start; i < strlen(string); i++)
	{
		sub.string[count] = string[i];
		count++;
	}
	return sub.string;
}
