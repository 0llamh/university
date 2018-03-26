// CAMERON SMITH
// COP 3330
// MYSTRING CLASS
// Original file by Bob Myers

#include <iostream>
using namespace std;

class MyString
{
  friend ostream& operator<< (ostream& output, const MyString& s);
  friend istream& operator>> (istream& input, MyString& s);
  friend istream& getline (istream& input, MyString& s, char delim = '\n');

  friend MyString operator+ (const MyString& , const MyString& );

  friend bool operator< (const MyString& s1, const MyString& s2);
  friend bool operator> (const MyString& s1, const MyString& s2);
  friend bool operator<=(const MyString& s1, const MyString& s2);
  friend bool operator>=(const MyString& s1, const MyString& s2);
  friend bool operator==(const MyString& s1, const MyString& s2);
  friend bool operator!=(const MyString& s1, const MyString& s2);

public:
  MyString();									// DEFAULT CONSTRUCTOR FOR empty string
  MyString(const char* userString);						// conversion from c-string
  MyString(int nums);								// conversion from int
  ~MyString();									// destructor
  MyString(const MyString& s);					// copy constructor
  MyString& operator=(const MyString& s);		// assignment operator

  MyString& operator+=(const MyString& s);		// concatenation/assignment

  // BRACKET OPERATORS
  char& operator[] (unsigned int index);
  const char& operator[] (unsigned int index) const;

  // insert s into the string at position "index"	
  MyString& insert(unsigned int index, const MyString& s);

  // find index of the first occurrence of s inside the string
  //  return the index, or -1 if not found
  int indexOf(const MyString& s) const;
  
  int getLength() const;		// return string length
  const char* getCString() const;	// return c-string equiv

  MyString substring(unsigned int , unsigned int ) const;
  MyString substring(unsigned int ) const;

private:

  // declare your member data here
	int size;		// size of the character string
	char* string;			// pointer to array address for constructors

};
