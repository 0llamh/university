// Cameron Smith
// COP3330 - 01
// -------------- main.cpp -----------

#include <string>
#include <fstream>
#include<cstdlib>
#include <iomanip>
#include "student.h"
using namespace std;

void PrintHeader( ostream& o);

int main()
{
	string ifile, ofile, temp_fn, temp_ln, temp_c;
	int report_size;

	cout << "Enter input file name: ";
	cin >> ifile;

	ifstream inputFile;
	inputFile.open(ifile.c_str());		// connects the input file to input stream
        if(!inputFile) {
           exit(0);
      }
	cout << "Enter output file name: ";
	cin >> ofile;
	ofstream outputFile;
	outputFile.open(ofile.c_str());		// connects the output file

	inputFile >> report_size;		// collects number of students from the top
	inputFile.get();        // collects the \n after the number of students
	cout << report_size << endl;
	Student** stud = new Student*[report_size];
	// array of pointers to type student

	cout << "\nReading input file...";
	
	for (int i = 0; i < report_size; i ++)
	{
		getline(inputFile, temp_ln, ',');
		inputFile.get();
		getline(inputFile, temp_fn);
		getline(inputFile, temp_c, ' ');

//		cout << temp_ln;
		if (temp_c == "Computer")
			temp_c += " Science";
		// catenates Science onto Computer to create a single course string
		
		/*char* fn = new char[temp_fn.length()];
		strcpy(fn, temp_fn.c_str());		
		char* ln = new char[temp_ln.length() + 1];
		strcpy(ln, temp_ln.c_str());		
		char* c = new char[temp_c.length() + 1];
		strcpy(c, temp_c.c_str());*/			

		// copies each string (fn, ln, c) into a char* to use as parameters later

		if (temp_c == "Biology")
		{
			int* grades = new int[4];		// 5 grade scores for bio students
			for( int s = 0; s <= 4;s++)
				inputFile >> grades[s];
//			cout << "\ntesting bio\n";
			int lab_grade = grades[0];
			double test_grade = ((grades[1] + grades[2] + grades[3]) / 3.0); 
			int final_exam = grades[4];
			inputFile.get();					// collects \n char
			stud[i] = new BioStudent( temp_fn, temp_ln, temp_c, lab_grade, test_grade, final_exam);
			grades = NULL;
		}
		else if (temp_c == "Theater")
		{
			int* grades = new int[2];		// 3 grades for theatre students
			for ( int s = 0; s <= 2; s++)
				inputFile >> grades[s];
			inputFile.get();					// collects \n char
			int part = grades[0];
			int mid_term = grades [1];
			int final_exam = grades [2];

			stud[i] = new ThStudent( temp_fn, temp_ln, temp_c, part, mid_term, final_exam);
			grades = NULL;
		}
		else if (temp_c == "Computer Science")
		{
			string science_fill;
			inputFile >> science_fill;			// collects the "Science" after computer
			int* grades = new int[8];		// 9 grades for cs students
			for ( int s = 0; s <= 8; s++)
				inputFile >> grades[s];
			inputFile.get();					// collects \n char
//			cout << "\ntesting grade string\n";
			double program_avg = ( (grades[0] + grades[1] + grades[2] + grades[3] + 
				grades[4] + grades[5]) / 6.0);
			int test1 = grades[6];
			int test2 = grades[7];
			int final_exam = grades[8];

			stud[i] = new CSStudent( temp_fn, temp_ln , temp_c, program_avg, test1, test2, final_exam);
			grades = NULL;
		}



	}

	cout << "\nThe input file has been read and stored!";
	cout << "\nPrinting data to output file...";

	outputFile.fill(' ');
	outputFile	<< "Student Grade Summary\n"
				<<  "---------------------\n"
				<< '\n'
				<< "BIOLOGY CLASS\n"
				<< '\n';
	PrintHeader(outputFile);
	for ( int q = 0; q < report_size; q++)
		if( stud[q]->GetCourse() == "Biology")
			stud[q]->PrintReport(outputFile);
	outputFile	<< '\n'
				<< "THEATER CLASS\n"
				<< '\n';
	PrintHeader(outputFile);
	for ( int q = 0; q < report_size; q++)
		if( stud[q]->GetCourse() == "Theater")
			stud[q]->PrintReport(outputFile);
	outputFile	<< '\n'
				<< "COMPUTER SCIENCE CLASS\n"
				<< '\n';
	PrintHeader(outputFile);
	for ( int q = 0; q < report_size; q++)
		if( stud[q]->GetCourse() == "Computer Science")
			stud[q]->PrintReport(outputFile);

	outputFile << '\n';
	outputFile << "OVERALL GRADE DISTRIBUTION";
	outputFile << '\n';

	int a = 0, b = 0, c = 0, d = 0, f = 0;

	for (int g = 0; g < report_size; g++)
	{
		if (stud[g]->LetterGrade() == 'A')
			a++;
		else if (stud[g]->LetterGrade() == 'B')
			b++;
		else if (stud[g]->LetterGrade() == 'C')
			c++;
		else if (stud[g]->LetterGrade() == 'D')
			d++;
		else
			f++;
	}
	
	outputFile	<< "A: " << a << endl
				<< "B: " << b << endl
				<< "C: " << c << endl
				<< "D: " << d << endl
				<< "F: " << f << endl;


	stud = NULL;

	cout << "\nPrinting to output file has completed! Enjoy!";
	
	outputFile.close();		
	inputFile.close();		// closes files when finished
	
	cout << endl;
	return 0;
}


void PrintHeader( ostream& o)
{
	o	<< setw(42) << left << "Student" << setw(8) << "Final" << setw(8) << "Final"	<< setw(8) << "Letter\n"
		<< setw(42) << left << "Name"	 << setw(8) << "Exam"  << setw(8) << "Avg"	<< setw(8) << "Grade\n"
	<< left << "-----------------------------------------------------------------------\n";
}
