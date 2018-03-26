#include <stack>
#include <string>
#include <sstream>
using namespace std;

/** Public */

//
BET::BET(){
 	root = nullptr;
 }

//
BET::BET(const string postfix){
	root = nullptr;
	if (buildFromPostfix(postfix))
		std::cout << "\nBET successfully created using postfix expression.";
	else
		std::cout << "\nBET could not be created using postfix expression.";
}

//
BET::BET(const BET& b){
 	//deep copy constructor
 	root = clone(b.root);
 }

//
BET::~BET(){
 	makeEmpty(root);
 		//starts deletion of BET starting at its root
}

//
bool BET::buildFromPostfix(const string postfix){

	stack <BinaryNode*> st;	//use a stack of BinaryNode pointers to keep track of operators/operands
	istringstream inputstring(postfix);	//create an input string from postfix for easier tokenizing
		//allows for cleaner seperation of operators/operands
	string slice;	//slice of the postfix expression. either an operator or operand. will check later
	makeEmpty(root);	//delete anything already stored or connected to root. start fresh.

	while (inputstring >> slice){	//stores each token into slice
  		if (isOperand(slice)){
  			BinaryNode* tmp = new BinaryNode();
  			tmp->data = slice;
  			tmp->childR = NULL;
  			tmp->childL = NULL;
  			st.push(tmp);
  		}
  		else if (isOperator(slice)){
  			if(slice.size() != 1){	//operator has a length larger than one
  				cout << "Invalid Postfix Expression\n";	//prints error message
  				while (st.size() > 0){	//empties the stack
  					makeEmpty(st.top());	//as a cleanup
  					st.pop();
  				}
  				return false;
  			}
  			else{
  				BinaryNode* tmp = new BinaryNode();
  				tmp->data = slice;
  				tmp->childR = st.top();
  				st.pop();
  				tmp->childL = st.top();
  				st.pop();

  				st.push(tmp);	//adds binary node to stack
  			}
  		}
	}

	if(st.size() != 1){
		cout << "Invalid Postfix Expression\n";
		while (st.size() > 0){	//empties the stack
  			makeEmpty(st.top());	//as a cleanup
  			st.pop();
  		}
  		return false;
	}
	else{
		root = st.top();
		st.pop();
	}
 	return true;		
 }

//
const BET & BET::operator=(const BET& b){
	makeEmpty(root);	//clear the lhs BET tree
	root = clone(b.root);
	return *this;
 }	

//
void BET::printInfixExpression(){
 	printInfixExpression(root);
 		//Public accessor to an already defined private function.
 }

//
void BET::printPostfixExpression(){
 	printPostfixExpression(root);
 		//Public accessor to an already defined private function.
 }

//
size_t BET::size(){
	size(root);
		//Accessor to an already defined private function.
 }

//
size_t BET::leaf_nodes(){
 	leaf_nodes(root);
 		//Accessor to an already defined private function.
 }

//
bool BET::empty(){
		//checks to see if no data is stored in a BET.
	if (root->childL == nullptr && root->childR == nullptr && root == nullptr)
		return true;
	else
		return false;
 }

/** Private */

//
void BET::printInfixExpression(BinaryNode *n){

	if (empty()){
		cout << "Binary Expression Tree is Empty" << endl;
	}
	else if (isLeaf(n)){
		cout << n->data << ' ';
	}
	else{
		if (!isLeaf(n->childL)){
			cout << "( ";
			printInfixExpression(n->childL);
			cout << ") ";
		}
		else
			printInfixExpression(n->childL);

		cout << n->data << ' ';
	
		if (!isLeaf(n->childR)){
			cout << "( ";
			printInfixExpression(n->childR);
			cout << ") ";
		}
		else
			printInfixExpression(n->childR);
	}

 }
		
//
void BET::makeEmpty(BinaryNode* &t){
 	if (t != nullptr){
 		makeEmpty(t->childL);
 		makeEmpty(t->childR);
 		delete t;
 	}
 	//else
 		//do nothing. already empty
 	t = nullptr;
 }

//
typename BET::BinaryNode* BET::clone(BinaryNode *t) {

	if (t == nullptr){
		return nullptr;
	}
	else{
		return new BinaryNode{ t->data, clone(t->childL), clone(t->childR) };		
		// if (t->childL != nullptr)
		// 	clone(t->childL);
		// if (t->childR != nullptr)
		// 	clone(t->childR);
		// return new BinaryNode(t->data, t->childL, t->childR);		
	}

}

//
void BET::printPostfixExpression(BinaryNode *n){
	if (n != nullptr){
		printPostfixExpression(n->childL);
		printPostfixExpression(n->childR);
		if (n->data != string())	//default data input, meaning empty node
			std::cout << n->data << ' ';
	}

}
		
//
size_t BET::size(BinaryNode* t){
	size_t count = 0;
	if (t != nullptr){
		count += size(t->childL);
		count += size(t->childR);
		if (t->data != string())	//default data input, meaning empty node
			count++;
	}
	return count;
}

//
size_t BET::leaf_nodes(BinaryNode *t){
	size_t count = 0;
	if (t != nullptr){
		count += leaf_nodes(t->childL);
		count += leaf_nodes(t->childR);
		if (isLeaf(t))	//default data input, meaning empty node
			count++;
	}
	return count;
}

/** Non-class functions */

bool isOperand(const string& s){
	if (!isOperator(s))
		return true;
}

bool isOperator(const string& s){
	if (s.compare("+") == 0 || s.compare("-") == 0 || s.compare("*") == 0 || s.compare("/") == 0)
		return true;
	else
		return false;
}

//
bool isLeaf(typename BET::BinaryNode * b){
	if (b->childL == nullptr && b->childR == nullptr)
		return true;
	else
		return false;
}

int checkPrecedence(const string& s){
	if (s == "*" || s == "/")
		return 2;
	else if (s == "+" || s == "-")
		return 1;
	else
		return 0;
}


// //
// string convertInfixToPostfix(const string postfix){
// 	stack st;

// }

// template <tyename T>
// string convertPostfixToInfix(const string infix){

// }
