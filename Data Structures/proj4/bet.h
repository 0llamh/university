#ifndef BET_CLASS_H
#define BET_CLASS_H
#include <string>
#include <stack>
using namespace std;

class BET{

 	public:

		struct BinaryNode{	//nested structure node
			string data;	//Element node's data
			BinaryNode *childL;	//Left child node
			BinaryNode *childR;	//Right child node

			BinaryNode(const string & s = string(), BinaryNode *L = nullptr, BinaryNode *R = nullptr) 
            : data{s}, childL{L}, childR{R} {}
		};

	private:

		BinaryNode *root;

	public:

		BET();	//default zero param constructor. Builds empty tree.
		BET(const std::string postfix); //A new tree built from the a "postfix" expression.
		BET(const BET& b);	//Deep copy constructor.
		~BET();		//Typical destructor
		bool buildFromPostfix(const std::string postfix); //"postfix" express param is the building block of a new tree.
			//Tokens seperated by spaces. Delete any previous existing nodes if any exist on creation.
			//Returns true if created successfully; false otherwise.
		const BET & operator=(const BET& b);	//Deep Copy assignment operator.
		void printInfixExpression();	//Print the infix expression using private recursive function.
		void printPostfixExpression();	//Print the postfix expression using private recursive function.
		size_t size();	//Return the number of nodes in a tree. Uses private recursive function.
		size_t leaf_nodes();	//Return the number of leaf nodes in a tree. Uses private recursive function.
		bool empty();	//Returns true on empty tree, otherwise false.
		//BinaryNode*& getRoot();	//Returns the root of the BET.

	private:

		BinaryNode * clone(BinaryNode *t);	//Clone all nodes of subtree pointed by t. Used in deep copy.
		void printPostfixExpression(BinaryNode *n);	//Print to standard output postfix notation recursively.
		void printInfixExpression(BinaryNode *n);	//Print to standard output infix notation recursively.
		size_t size(BinaryNode* t);	//Returns number of nodes in a tree recursively.
		size_t leaf_nodes(BinaryNode *t);	//Return number of leaf nodes in a tree recursively.
		void makeEmpty(BinaryNode * & t);	//Empties the tree starting at t.

	

};	//end of class init

bool isOperand(const string& s);
bool isOperator(const string& s);
bool isLeaf(typename BET::BinaryNode * b);

#include "bet.hpp"

#endif