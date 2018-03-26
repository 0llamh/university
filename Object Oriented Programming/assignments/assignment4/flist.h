// -------------------FLIST.H--------------------
// PAUL C SMITH
// COP3330 - 01
// ASSIGNMENT 4
// header for fraction array class

#include "frac.h"
using namespace std;
const int maxSize = 20;			// hard coded max size for easier testing
class FList
{
	// friend functions
	friend ostream& operator<<(ostream& output, const FList& f1);
public:
	FList();
	// array addtions/deletion booleans
	bool Insert(const Fraction& f1);
	bool Insert(const Fraction& f1, int insertion);
	bool Delete(int deletionTerm);
	// array accessors
	int Size() const;
	Fraction Largest() const;
	int HowMany(Fraction f1);
	// array calculators
	Fraction Sum() const;
	double Average() const;
	Fraction Product(int a, int b);
	void SimplifyAll();
	void Clear();
private:
	int currentSize;
	Fraction fracList[20];		// const array size of 20
};	