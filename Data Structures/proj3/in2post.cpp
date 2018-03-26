// PAUL CAMERON SMITH
// ASSIGNMENT/PROJECT 3

#include <iostream>
#include <cstdlib>
#include <stdio.h> 
#include "stack.h"
#include <string>

using namespace std;
using namespace cop4530;

// MEMBER FUNCTIONS
string convert(const string& in);
string evaluate(const string& post);

bool isNumber(const char& c);
bool lowPrecedence(const char& c);
bool highPrecedence(const char& c);
bool isParentheses(const char& c);
bool isHigherPrecedence(const char& c1, const char& c2);

void testgetline(const string& in);

int main(){


	string infix;
	
	do{
		cout << "\nEnter infix expression (\"exit\" to quit) :";
		getline(std::cin, infix);
		//testgetline(infix);
		if (infix != "exit"){
			string postfix = convert(infix);
			cout << "\nPostfix expression: " << postfix;
			cout << "\nPostfix evaluation: " << postfix + " = " << evaluate(postfix);
		}
	}while (infix != "exit"); // || infix != "\n");

	cout << endl;

	return 0;
}


string convert(const string& in){
	string post = "";
	string operand;
	string temp;
	Stack<char> stk;

	for (unsigned int i = 0; i < in.length(); i ++){
		if (in[i] != ' '){	//not a space
			if (lowPrecedence(in[i]) || highPrecedence(in[i])){	
				//operator check
				while (!stk.empty() && stk.top() != '(' && !isHigherPrecedence(stk.top(), in[i])){	
				//meaning the new operator is lower/equal precedence
					temp = stk.top();
					post += temp + " ";	//send to output
					temp.clear();
					stk.pop();		//remove top element
				}
				stk.push(in[i]);	//add to stack
			}
			else if (!isParentheses(in[i]))	//variables and numbers
				operand += in[i];		//add to the operand string for later
			else{	//parentheses
				if (in[i] == '(')
					stk.push(in[i]);	//being highest precedence, just add to stack
				else{
					while (!stk.empty() && stk.top() != '('){	//until stk.top() is the open parantheses
						temp = stk.top();
						post += temp + " ";	//send to output
						temp.clear();	//empty stack to postfix
						stk.pop();	//move on to next on the stack
					}
				}
			}
		}
		else{	//if finished with an operand/operator
			if (!operand.empty()){
				post += operand + " ";		//push it to postfix
				operand.clear();			//reset operand string				
			}
		}
	}
	// INFIX STRING FINISHED

	post += operand; 	//add last operand
	while (!stk.empty()){
		if (stk.top() != '('){	//never add ( to the postfix
			temp = stk.top();	//just ignore it and pop it
			post += " " + temp;
			temp.clear();
		}
		stk.pop();
	}
	return post;
}

string evaluate(const string& post){
	
	string result = "";	//empty string for our result;
	string operand = "";
	stringstream conversion();
	Stack<double> stk;	//stack for our numbers
	double op1 = 0, op2 = 0, op3 = 0;


	for (unsigned int i = 0; i < post.length(); i++){
		//cout << post[i] << endl;
		if (post[i] == ' '){
			stk.push(strtod(operand.c_str(), nullptr));		//typecast to int and push to stack
			operand.clear();	//and reset it
			continue;		//skip spaces, not important
		}
		else if (isNumber(post[i])){
			// if a number, add to stack (check for multiple digits)
			if (post[i+1] != ' '){		//space after
				stk.push(strtod(operand.c_str(), nullptr));		//typecast to int and push to stack
				operand.clear();		//empty operand string 
			}
			else{	//multiple digits, damnit
				operand += post[i];
			}

		}
		else if (lowPrecedence(post[i]) || highPrecedence(post[i])){
			// if an operand calculate based on stack
			if (stk.size() < 2){	//if stack size has less than two operands in it
				stk.clear();
				result = "Error: Operator addressed with too few operands.";
			}
			op1 = stk.top();
			stk.pop();
			op2 = stk.top();
			stk.pop();
				//grab top two operands  
			if (post[i] == '+')
				op3 = op1 + op2;
			else if (post[i] == '-')
				op3 = op2 - op1;
			else if (post[i] == '*')
				op3 = op1 * op2;
			else if (post[i] == '/')
				op3 = op2 / op1;
			result = op3;
			stk.push(strtod(result.c_str(), nullptr));	//push result of operation to stack
			result.clear();
		}
		else{
			// if neither an operator or an operand
			// then the postfix holds a variable, 
			// and no evaluation can be done
			result = post;
			stk.clear();
			break; 
		}
	}
//	if (stk.size() > 1)	//more than one operand with no operators left
//		result = "Error: Too many operands left on stack.";
	if(stk.size() == 1)
		result = stk.top();
	cout << result << stk.size() << endl;
	return result;
}

bool isNumber(const char& c){
	if(c >= '0' && c <= '9' || c == '.')	//also account for decimals 
		return true;
	return false;
}
bool lowPrecedence(const char& c){
	if (c == '+' || c == '-')
		return true;
	return false;
}
bool highPrecedence(const char& c){
	if (c == '*' || c == '/')
		return true;
	return false;
}
bool isParentheses(const char& c){
	if (c == '(' || c == ')')
		return true;
	return false;
}

bool isHigherPrecedence(const char& c1, const char& c2){
	if (isParentheses(c2) && highPrecedence(c1))
		return true;
	else if (isParentheses(c2) && lowPrecedence(c1))
		return true;
	else if (highPrecedence(c2) && lowPrecedence(c1))
		return true;
	// if any of the above, just pass true and c2 will be pushed to stack
	else
		return false;	//meaning stack.top() is higher precedence and output will be made
}

void testgetline(const string& in){
	for (unsigned int i = 0; i < in.length(); i++){
		cout << in[i] << ' ';
	}
}