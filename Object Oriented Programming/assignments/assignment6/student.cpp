// Cameron Smith
// COP3330 - 01
// -------------- student.cpp -------------

#include <string>
#include <cstring>
#include <iomanip>
#include "student.h"
using namespace std;

//Student
	Student::Student()
	{
		// empty on purpose
	}
	Student::Student( string fn, string ln, string c)
	{
		// intitalizes the three string and copies their respective parameters
		first_name = fn;
		last_name = ln;
		course = c;
	}
	void Student::PrintReport(ostream& o)
	{
		//deliberately empty
		//abstract function
	}
	char Student::LetterGrade()
	{
		// empty
		return 'a';
	}
	string Student::GetCourse()
	{
		return course;
	}
	string Student::Get_fn()
	{
		return first_name;
	}
	string Student::Get_ln()
	{
		return last_name;
	}


//BioStudent
	BioStudent::BioStudent()
	{
		//deliberately empty
	}
	BioStudent::BioStudent( string fn, string ln, string c, int l, double t, int f) : 
			Student( fn, ln, c)
		// passes in all but grades into the normal student constructor
	{
		lab = l;
		tests = t;
		finalexam = f;
	}
	void BioStudent::PrintReport( ostream& o)
	{
		final_avg = ((0.3*lab) + (0.45*tests) + (0.25*finalexam));

                o << setw(42) << left << Get_fn() + ' ' + Get_ln();
                o << setw(8) << left << finalexam;
                o << setw(8) << left << fixed << setprecision(2) << final_avg;
		o << setw(8) << left << LetterGrade();
                o << setprecision(0) << '\n';
	}
	char BioStudent::LetterGrade()
	{
		if (final_avg >= 90)
			return 'A';
		else if (final_avg >= 80)
			return 'B';
		else if (final_avg >= 70)
			return 'C';
		else if (final_avg >= 60)
			return 'D';
		else
			return 'F';

	}

//ThStudent
	ThStudent::ThStudent()
	{
		//empty
	}
	ThStudent::ThStudent( string fn, string ln, string c, int p, int m, int f) :
		Student ( fn, ln, c)
		//sends name and course to normal student constructor
	{
		participation = p;
		midterm = m;
		finalexam = f;
	}
	void ThStudent::PrintReport( ostream& o)
	{	
		final_avg = ((participation * 0.4) + (0.25*midterm) + (0.35*finalexam)); 
		
                o << setw(42) << left << Get_fn() + ' ' + Get_ln();
                o << setw(8) << left << finalexam;
                o << setw(8) << left << fixed << setprecision(2) << final_avg;
		o << setw(8) << left << LetterGrade();
                o << setprecision(0) << '\n';
	}
	char ThStudent::LetterGrade()
	{
		if (final_avg >= 90)
			return 'A';
		else if (final_avg >= 80)
			return 'B';
		else if (final_avg >= 70)
			return 'C';
		else if (final_avg >= 60)
			return 'D';
		else
			return 'F';

	}

//CSStudent
	CSStudent::CSStudent()
	{
		//empty
	}
	CSStudent::CSStudent( string fn, string ln, string c, double p, int t1, int t2, int f) :
		Student( fn, ln, c)
		//sends name and course to normal student constructor
	{
		programs = p;
		test1 = t1;
		test2 = t2;
		finalexam = f;
	}
	void CSStudent::PrintReport( ostream& o)
	{
		final_avg = ((0.3*programs) + (0.2*test1) + (0.2*test2) + (0.3*finalexam));
	
		o << setw(42) << left << Get_fn() + ' ' + Get_ln();
		o << setw(8) << left << finalexam; 
		o << setw(8) << left << fixed << setprecision(2) << final_avg;
		o << setw(8) << left << LetterGrade();
                o << setprecision(0) << '\n';
	}
	char CSStudent::LetterGrade()
	{
		if (final_avg >= 90)
			return 'A';
		else if (final_avg >= 80)
			return 'B';
		else if (final_avg >= 70)
			return 'C';
		else if (final_avg >= 60)
			return 'D';
		else
			return 'F';

	}


