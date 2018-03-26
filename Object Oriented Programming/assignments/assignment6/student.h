// Cameron Smith
// COP3330 - 01
// -------------- student.h ---------------

#include <iostream>
using namespace std;

class Student
{
	protected:
		int MAX;
		string first_name;
		string last_name;	
		string course;
		Student();
		Student( string fn, string ln, string c);
		
	public:
		virtual void PrintReport( ostream& o)=0;
		virtual char LetterGrade()=0;
		string GetCourse();
		string Get_fn();
		string Get_ln();
};
class BioStudent : public Student
{
	private:
		int lab;
		double tests;
		int finalexam;
		double final_avg;
	public:
		BioStudent();		// default
		BioStudent( string fn, string ln, string c, int l, double t, int f); 
		void PrintReport( ostream& o);
		char LetterGrade();
};
class ThStudent : public Student
{
	private:
		int participation;
		int midterm;
		int finalexam;
		double final_avg;
	public:
		ThStudent();		
		ThStudent( string fn, string ln, string c, int p, int m, int f);
		void PrintReport( ostream& o);
		char LetterGrade();
};
class CSStudent : public Student
{
	private:
		double programs;
		int test1;
		int test2;
		int finalexam;
		double final_avg;
	public:
		CSStudent();
		CSStudent( string fn, string ln, string c, double p, int t1, int t2, int f);
		void PrintReport( ostream& o);
		char LetterGrade();
};
