#include <string>
using namespace std;

template <typename T>
 class BET{

 	private:

		struct BinaryNode{
			//nested structure node
			T data;
			BinaryNode *childL;
			BinaryNode *childR;

			
		};

		void printInfixExpression(BinaryNode *n);
		void makeEmpty(BinaryNode* &t);

		BinaryNode * clone(BinaryNode *t);
		void printPostfixExpression(BinaryNode *n);
		
		size_t size(BinaryNode* t);
		size_t leaf_nodes(BinaryNode *t);


	public:

		BET();
		BET(const string postfix);
		BET(const BET&);
		~BET();

		bool buildFromPostfix(const string postfix);
		const BET & operator=(const BET &);
		
		void printInfixExpression();
		void printPostfixExpression();

		size_t size();
		size_t leaf_nodes();

		bool empty();

}