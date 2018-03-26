// PAUL CAMERON SMITH
// COP 4530 - DSAGP
// PROJECT 1: WORD, NUMBER, AND CHARACTER USAGE STATISTICS

#include <iostream>
#include <vector>
#include <string>
#include <sstream>	//string stream
#include <algorithm>	//sort function
using namespace std;

// STRUCTURES TO STORE VALUES AND NUMBER OF OCCURRENCES
struct CHARS{
	string c;
	int count;
};
struct WORDS{
	string word;
	int count;
};
struct NUMBS{
	int numb;
	int count;
};

// MEMBER FUNCTIONS
vector<string> getWords(vector<char> filecontents);
vector<int> getNumbs(vector<char> filecontents);
template<typename T> 
int countElements(vector<T> list);
bool charCompare(CHARS c1, CHARS c2);	//bool function for sort
bool wordCompare(WORDS w1, WORDS w2);	//bool function for sort
bool numbCompare(NUMBS n1, NUMBS n2);	//bool function for sort
	//couldnt get templates to work in time. That's something i need to work on

int main(){

	char filec; 	//creates a character variable to use 
		//and store each individual character
	string line;	//string containing a singular line of the file
	vector<char> filev;		//vector holding whole file data

	while (getline(cin, line)){ 	//for every line in the file, store into string line
		for (unsigned int i = 0; i < line.length(); i++)	//store each char in string line to vector containing whole file
			filev.push_back(line.at(i));
		filev.push_back('\n');		//after each line, add a \n to the vector
	}

 	// GET ALL THE WORDS OF A FILE
 	// AND STORE THEM INTO A NEW VECTOR OF STRINGS
 	vector<string> wordlist;
 	wordlist = getWords(filev);

	// GET ALL THE NUMBERS OF A FILE
	// AND STORE THEM INTO A NEW VECTOR OF STRINGS
	vector<int> numblist;
	numblist = getNumbs(filev);

	// SORT ALL CHARACTERS INDIVIDUALLY BY ASCII VALUES
	sort(filev.begin(), filev.end(), less<char>());

	// SORT ALL STRINGS ALPHABETICALLY AND PRINT
	// sorting algorithm is by ascii values, so
	// everything needs to be converted to lowercase
	string uppercasestring;	//create dummy string
	for ( unsigned int i = 0; i < wordlist.size(); i ++){
		uppercasestring = wordlist[i];	//copy string into dummy
		for (unsigned j = 0; j < uppercasestring.length(); j++){
				//for every char in dummy string
			uppercasestring[j] = (tolower(uppercasestring[j]));
				//convert each char to lowercase
		}
		wordlist[i] = uppercasestring;	//copy converted string back into vector
	}
	sort(wordlist.begin(), wordlist.end(), less<string>());
		//sort strings alphabetically by ascii values

	// SORT ALL INT VALUES IN DESCENDING ORDER AND PRINT
	sort(numblist.begin(), numblist.end(), greater<int>());

	////TIME TO CREATE FINAL STRUCTURES///

	// CHARACTER STRUCTURE
	int char_elements;	
	char_elements = countElements(filev);
	CHARS* charlist_final = new CHARS[char_elements];
	int charlist_index = 0;
	for (unsigned int i = 0; i < filev.size(); i++){
		if (charlist_index >= char_elements)
			break;
		else if (i == 0){
			charlist_final[charlist_index].c = string(1,filev[i]);
			charlist_final[charlist_index].count = 1;
		}
		else{
			string dummystr = string(1,filev[i]);
			if (charlist_final[charlist_index].c == dummystr){
				charlist_final[charlist_index].count++;
			} 
			else{
				charlist_index++;
				charlist_final[charlist_index].c = string(1,filev[i]);
				charlist_final[charlist_index].count = 1;
			}
		}
	}
	for (unsigned int i = 0; i < char_elements; i++){
			// goes through the char struct and replaces '\n' with "\n"
		if (charlist_final[i].c == "\n")
			charlist_final[i].c = "\\n";
	}
	sort(charlist_final, charlist_final+char_elements, charCompare);
		//uses sort to go through CHARS struct array and sorts using my defined bool function	
	// PRINT THE TOP 10 CHARACTERS AND THEIR COUNTS
	cout << "Total " << char_elements << " different characters, ";
	if (char_elements >= 10){
		cout << "10 most used characters:\n";
		for (unsigned int i = 0; i < 10; i++){
			cout << "No. " << i << ": " 
				<< charlist_final[i].c << "\t\t" << charlist_final[i].count << endl;
		}
	}
	else{
		cout << char_elements << " most used characters:\n";
		for (unsigned int i = 0; i < char_elements; i++){
			cout << "No. " << i << ": " 
				<< charlist_final[i].c << "\t\t" << charlist_final[i].count << endl;
		}
	}// WORKS

	// WORD STRUCTURE
	int word_elements;
	word_elements = countElements(wordlist);
	WORDS* wordlist_final = new WORDS[word_elements];
	int wordlist_index = 0;
	for (unsigned int i = 0; i < wordlist.size(); i++){
		if (wordlist_index >= word_elements)
			break;
		else if (i == 0){
			wordlist_final[wordlist_index].word = wordlist[i];
			wordlist_final[wordlist_index].count = 1;
		}
		else{
			if (wordlist_final[wordlist_index].word.compare(wordlist[i]) == 0){
				wordlist_final[wordlist_index].count++;
			} 
			else{
				wordlist_index++;
				wordlist_final[wordlist_index].word = wordlist[i];
				wordlist_final[wordlist_index].count = 1;
			}
		}
	}
	sort(wordlist_final, wordlist_final+word_elements, wordCompare);
		//uses sort to go through CHARS struct array and sorts using my defined bool function	
	// PRINT THE TOP 10 WORDS AND ITS COUNT
	cout << "\nTotal " << word_elements << " different words, ";
	if (word_elements >= 10){
		cout << "10 most used words:\n";
		for (unsigned int i = 0; i < 10; i++){
			cout << "No. " << i << ": " 
				<< wordlist_final[i].word << "\t\t" << wordlist_final[i].count << endl;
		}
	}
	else{
		cout << word_elements << " most used words:\n";
		for (unsigned int i = 0; i < word_elements; i++){
			cout << "No. " << i << ": " 
				<< wordlist_final[i].word << "\t\t" << wordlist_final[i].count << endl;
		}
	}// WORKS

	// NUMB STRUCTURE
	int numb_elements;
	numb_elements = countElements(numblist);
	NUMBS* numblist_final = new NUMBS[numb_elements];
	int numblist_index = 0;
	for (unsigned int i = 0; i < numblist.size(); i++){
		if (numblist_index >= numb_elements)
			break;
		else if (i == 0){
			numblist_final[numblist_index].numb = numblist[i];
			numblist_final[numblist_index].count = 1;
		}
		else{
			if (numblist_final[numblist_index].numb == numblist[i]){
				numblist_final[numblist_index].count++;
			} 
			else{
				numblist_index++;
				numblist_final[numblist_index].numb = numblist[i];
				numblist_final[numblist_index].count = 1;
			}
		}
	}
	sort(numblist_final, numblist_final+numb_elements, numbCompare);
		//uses sort to go through CHARS struct array and sorts using my defined bool function	
	// PRINT THE TOP 10 numbS AND ITS COUNT
	cout << "\nTotal " << numb_elements << " different numbers, ";
	if (numb_elements >= 10){
		cout << "10 most used numbers:\n";
		for (unsigned int i = 0; i < 10; i++){
			cout << "No. " << i << ": " 
				<< numblist_final[i].numb << "\t\t" << numblist_final[i].count << endl;
		}
	}
	else{
		cout << numb_elements << " most used numbers:\n";
		for (unsigned int i = 0; i < numb_elements; i++){
			cout << "No. " << i << ": " 
				<< numblist_final[i].numb << "\t\t" << numblist_final[i].count << endl;
		}
	}// WORKS

	return 0;
}

vector<string> getWords(vector<char> filecontents){
	vector<string> tempwords;
	string tempstring;
	for (unsigned int i = 0; i < filecontents.size(); i++){
			//if char in vector is a letter, loop char until its not
		if (isalpha(filecontents[i]))	//if letter
			tempstring += filecontents[i];	//append it to string
		else{	
			if (tempstring.length() > 0){	//if the temp string isn't empty,
				tempwords.push_back(tempstring);	//store the string into the vector
				tempstring.clear();	//empties the word string
			}
		}
	}
	return tempwords;
}	// COMPLETED
vector<int> getNumbs(vector<char> filecontents){
	vector<int> tempnumbs;
	string tempstring;
	int number;
	for (unsigned int i = 0; i < filecontents.size(); i++){
			//if char in vector is a number, loop char until its not
		if (isdigit(filecontents[i]))	//if digit
			tempstring += filecontents[i];	//append it to string
		else{	
			if (tempstring.length() > 0){	//if the temp string isn't empty,
				stringstream value(tempstring);	//uses sstream lib to create int value
				value >> number;	//and stores the string's numberic value into number
				tempnumbs.push_back(number);	//store the int into the vector
				number = 0;			//resets number value
				tempstring.clear();	//empties the number string
			}
		}
	}
	return tempnumbs;
}	// COMPLETED

template<typename T> 
int countElements(vector<T> list){
	// TAKES IN A VECTOR CONTAINING ANY TYPE
	// AND RETURNS THE NUMBER OF UNIQUE ELEMENTS IN IT

	int elements = 0;
	T temp; 	// temp value to store whats in the vector (strings, char, ints)

	for (unsigned int i = 0; i < list.size(); i++){
		if (i == 0){	//initial store first element into temp
			temp = list[i];
			elements++; //atleast one element
		}
		else{		// and every iteration after that
			if (temp != list[i]){
				// if not the same
				elements++;	// increment element count
				temp = list[i];	// store different value into temp for comparison
			}
		}
	}
	return elements;
}	// COMPLETED

bool charCompare(CHARS c1, CHARS c2){
	return (c1.count > c2.count);
}	// COMPLETED
bool wordCompare(WORDS w1, WORDS w2){
	return (w1.count > w2.count);
}	// COMPLETED
bool numbCompare(NUMBS n1, NUMBS n2){
	return (n1.count > n2.count);
}	//COMPLETED