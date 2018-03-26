// PAUL CAMERON SMITH
// COP4530 - DATA STRUCTURES
// PROJECT 2 - DOUBLE LINKED LIST
// LIST.HPP 
//		CONTAINS DEFINITIONS OF FUNCTIONS
//		PREVIOUSLY DECLARED IN LIST.H
#include <iostream>
using namespace std;

template <typename T>
 List<T>::const_iterator::const_iterator(){
	// default zero parameter constructor
	*current = NULL;		// sets pointer current in Node to Null
}	//done
template <typename T>
const T& List<T>::const_iterator::operator*() const{
	// returns a reference to the corresponding element in the list
	// by calling retrieve() member function
	return List<T>::const_iterator::retrieve();
}	//done

//* CONST ITERATOR INCREMENT AND DECREMENT OPERATOR OVERLOADS */

//prefix increment operator
template <typename T>
 typename List<T>::const_iterator& List<T>::const_iterator::operator++(){
	current = current->next;	//moves the current node to the next node
	return *this;			//returns the now current node
}	//done
//postfix increment operator
template <typename T>
 typename List<T>::const_iterator List<T>::const_iterator::operator++(int){
	const_iterator oldnode = *this;	//saves current node as oldnode
	++( *this );	//uses the previously defined prefix increment overload
	return oldnode;		//returns the old node
}	//done
//prefix decrement operator
template <typename T>
 typename List<T>::const_iterator& List<T>::const_iterator::operator--(){
	current = current->prev;	//moves current node to the prev node
	return *this;		//returns the new current node
}	//done
//postfix decrement operator
template <typename T>
 typename List<T>::const_iterator List<T>::const_iterator::operator--(int){
	const_iterator oldnode = *this;	//saves current node as oldnode
	--( *this );		//uses previously defined prefix decrement overload
	return oldnode;		//returns the oldnode
}	//done

//* CONST ITERATOR COMPARISON OPERATOR OVERLOADS */

//Returns true on equal iterators
template <typename T>
 bool List<T>::const_iterator::operator==(const const_iterator &rhs) const{
	return current == rhs.current;		//if their pointers are the same, true
}	//done
//Returns true on unequal iterators
template <typename T>
 bool List<T>::const_iterator::operator!=(const const_iterator &rhs) const{
	return !(*this == rhs);		//uses previously defined operator== overload
}	//done

//* CONST ITERATOR RETRIEVE AND PROTECTED CONSTRUCTOR */

//Returns reference to current node data
template <typename T>
 T & List<T>::const_iterator::retrieve() const {
	return current->data;
} 	// done
template <typename T>
 List<T>::const_iterator::const_iterator(Node *p): current(p){
	//protected constructor to set pointer current to the passed in Node pointer
}	//done

//////////////////////////////////////////////

//* ITERATOR NESTED CLASS DEFINITIONS */

template <typename T>
 List<T>::iterator::iterator(){
	//default zero-parameter constructor
	//empty
}	//done
template <typename T>
 T & List<T>::iterator::operator*(){
	//returns a reference to the corresponding in the list
	// by calling retrieve() member function from const_iterator
	return const_iterator::retrieve();	//uses const_iterator's retrieve function
}	//done
//Returns reference to Node
template <typename T>
 const T & List<T>::iterator::operator*() const{
	// returns a const referenceto a node in the list via retrieve()
	return const_iterator::operator*();	//uses const_iterator's overload function
}	//done

//Prefix increment operator
template <typename T>
 typename List<T>::iterator& List<T>::iterator::operator++(){
	this->current = this->current->next;
	return *this;
}	//done
//Postfix increment Operator
template <typename T>
 typename List<T>::iterator List<T>::iterator::operator++(int){
	typename List<T>::iterator oldnode = *this;
	++( *this );
	return oldnode;
}	//done
//Prefix decrement operator
template <typename T>
 typename List<T>::iterator& List<T>::iterator::operator--(){
	this->current = this->current->prev;	//current now points to previous node
	return *this;		//returns now current node
}	//done
//Postfix decrement operator
template <typename T>
 typename List<T>::iterator List<T>::iterator::operator--(int){
	iterator oldnode = *this;	//saves current node in oldnode
	--( *this );	//uses previously defined prefix decrement
	return oldnode;	//return saved old Node
}	//done

//Protected 1-param Constructor for set Node
template <typename T>
 List<T>::iterator::iterator(Node *p)
 	: const_iterator(p) {
}	//done


//////////////////////////////////////////////

//* LIST CLASS FUNCTIONS AND OVERLOADS */

//Default Constructor. Calls List<T>::init()
template <typename T>
 List<T>::List(){
	//default zero param constructor. call init() to initialize list member variables
	init();
}	//done
//Copy Constructor with passed in List<T>
template <typename T>
 List<T>::List(const List &rhs){
	//create new list using elements in existing list rhs
	init();		//create new list
	for(auto & val : rhs){	//loop through as many times rhs has elements
		push_back(val);	//add each subsequent element in rhs to the back of the current list
	}	//did research on 'auto' after looking at the text book. I LOVE IT.
}	//done
//Move Constructor
template <typename T>
 List<T>::List(List && rhs){
	theSize = rhs.size();	//sets new size to param's size
	head = rhs.head;
	tail = rhs.tail;
	rhs.theSize = 0;
	rhs.head = NULL;
	rhs.tail = NULL;
}	//done
// construct a list with num elements, all initialized with value val
template <typename T>
 List<T>::List(int num, const T& val){
	init();	//create new list
	for(int i = 0; i < num; i++)
		push_back(val);
}	//done
// construct a list with elements froma nother lsit bewteen start and end
template <typename T>
 List<T>::List(const_iterator start, const_iterator end){
	// [start, end)	
	init();
	for(auto itr = start; itr != end; itr++)
		push_back(*itr);
}	//done
// construct a list with elements from the initializer list passed in
template <typename T>
 List<T>::List(std::initializer_list<T> iList){
	// allows declarations like: List<int> myList {2, 4, 6, 8, 10, 12 , 14};
	init();	//create new list
	//did some reading on std::initializer_list documentation and found some neato function calls
 	for (auto itr = iList.begin(); itr != iList.end(); ++itr)
 		push_back(*itr);	//add item in iList to back of list
}	//TODO ?
//Deconstructor
template <typename T>
 List<T>::~List(){
	//reclaim memory from head and tails
	clear();	//empties list of all nodes but head and tail
	delete head;
	delete tail;
}	//done
//Copy Assignment Operator
template <typename T>
 const List<T>& List<T>::operator=(const List &rhs){
	List copy = rhs;
	std::swap( *this, copy);
	return *this;
}	//done
//Move Assignmente T>
template <typename T>
 List<T> & List<T>::operator=(List && rhs){
	std::swap(theSize, rhs.theSize);
	std::swap(head, rhs.head);
	std::swap(tail, rhs.tail);
	return *this;
}	//done
//Assignment Operator Initializer list
template <typename T>
 List<T>& List<T>::operator=(std::initializer_list<T> iList){
	//assigne the initializer list data to be the calling objects new data
	// ex: list2 = {1, 3, 5, 7, 9, 11, 13, 15};
	//	same as the List(std::initializer_list<T> iList) constructor
	init(); 	//create new list
	for (auto itr = iList.begin(); itr != iList.end(); ++itr)
		push_back(*itr);
}	//TODO ?


// LIST MEMBER FUNCTIONS
//Return Size of list
template <typename T>
 int List<T>::size() const{
	//return the number of elements in the list
	return theSize;
}	//done
//Boolean check on empty list
template <typename T>
 bool List<T>::empty() const{
	//returns true if the list is empty (no elements)
	if (theSize == 0)
		return true;
	else
		return false;
}	//done
//Clear & empty List
template <typename T>
 void List<T>::clear(){
	//delete all the elemtns in the list
	while ( !empty())	//loop until the list is not empty
		pop_front();	//delete the first element in the list
}	//done
//Reverse the List
template <typename T>
 void List<T>::reverse(){
 	// reverse the order of the elements in the list
	Node *tmpcurr, *tmpnext, *tmpprev;	
		//create temp nodes shuffling next values
	tmpcurr = head;		//point temp current to head (first node)
	tmpprev = NULL;	//tmp prev to null
	tail = head;	//point head to tail
	while (tmpcurr != NULL){	//loop until done
		tmpnext = tmpcurr->next;
		tmpcurr->next = tmpprev;
		tmpcurr->prev = tmpnext;
		tmpprev = tmpcurr;
		tmpcurr = tmpnext;
	}
	head = tmpprev;	//readjust head to previous at end of list
}	//TODO ?


//////////////	front & back
template <typename T>
 T& List<T>::front(){
	//return reference to the first element in the list
	return *begin();
}	//done
template <typename T>
 const T& List<T>::front() const{
	//return a const reference of the first element
	return *begin();
}	//done
template <typename T>
 T& List<T>::back(){
	//return reference to the last element in the list
	return *--end();
}	//done
template <typename T>
 const T& List<T>::back() const{
	//return a const reference of the last element
	return *--end();
}	//done


//////////////	push & pop
template <typename T>
 void List<T>::push_front(const T & val){
	//insert T into the begining of the list
	List<T>::insert(begin(), val);
}	//done
template <typename T>
 void List<T>::push_front(T && val){
	//move version of the front insert function
	List<T>::insert(begin(), std::move(val));
}	//done
template <typename T>
 void List<T>::push_back(const T & val){
	//insert at the end of the list
	List<T>::insert(end(), val);
}	//done
template <typename T>
 void List<T>::push_back(T && val){
	//move version of back insert
	insert(end(), std::move(val));
} 	//done
template <typename T>
 void List<T>::pop_front(){
	//delete first element
	erase(begin());	//
}	//done
template <typename T>
 void List<T>::pop_back(){
	//delete last element
	erase(--end());
}	//done


//////////////
template <typename T>
 void List<T>::remove(const T & val){
	// remove all elements of a passed in value
 	typename List<T>::iterator itr1 = begin();	//first element in the list
	while(itr1 != end()){		//loop until no more elements
		if(*itr1 == val){	//if node's data is the same
			erase(itr1);		//remove current iterator
		}
		++itr1;	//move to next iterator
	}
}	//done
template <typename T>
 template <typename PREDICATE>
  void List<T>::remove_if(PREDICATE pred){
	//remove all elements if pred returns true.
	// TODO
	auto itr1 = begin();
	while (itr1 != end()){
		if(pred(*itr1))
			erase(itr1);
		++itr1;
	}
	
}


////////////// print
template <typename T>
 void List<T>::print(std::ostream& os, char ofc) const{
	// print all element int he list, using char ofc as the delim
 	auto itr1 = begin();
 	while(itr1 != end()){	//loop until no more elements
 		os << *itr1 << ofc;	//use outputstream to output iterator's node's data and delim
 		++itr1;		//move to next element
 	} 	
}	//done


//////////////	Begin & end
template <typename T>
 typename List<T>::iterator List<T>::begin(){
	//iterator to the first element
	return {head->next};
}	//done
template <typename T>
 typename List<T>::const_iterator List<T>::begin() const{
	//iterator to the first element as a const
	return {head->next};
}	//done
template <typename T>
 typename List<T>::iterator List<T>::end(){
	//end iterator element
	return {tail};
}	//done
template <typename T>
 typename List<T>::const_iterator List<T>::end() const{
	//const end iterator element
	return {tail};
}	//done


////////////// insert & erase
template <typename T>
 typename List<T>::iterator List<T>::insert(iterator itr, const T& val){
	//insert value ahead of the itr node
	Node* p = itr.current;	//new Node equal to the itr
	theSize++;		//increase the List size
	return{ 
		p->prev = p->prev->next = new Node{ val, p->prev, p } 
	};
}	//done
template <typename T>
 typename List<T>::iterator List<T>::insert(iterator itr, T && val){
	//move version of insert
	Node *p = itr.current;	//new node = itr
	theSize++;		//increase List size
	return { p->prev = p->prev->next =
			new Node{ std::move(val), p->prev, p}
	};
}	//done
template <typename T>
 typename List<T>::iterator List<T>::erase(iterator itr){
	//erase one element
	Node *p = itr.current;
	iterator result{ p->next };	
	p->prev->next = p->next;	//remove link from prev to current
	p->next->prev = p->prev;	//remove link from current to next
	delete p;	//destroy node in list
	theSize--;	//shrink list size
}	//done
template <typename T>
 typename List<T>::iterator List<T>::erase(iterator start, iterator end){
	//erase [start, end)
	auto itr1 = start;
	while (itr1 != end){
		erase(itr1);
		itr1++;
	}
	return itr1;
}	//done

//* LIST PRIVATE METHOD FUNCTIONS & OVERLOADS: */

//Initialize member variables on creation
template <typename T>
 void List<T>::init(){
	theSize = 0;	//list size of 0
	head = new Node;	//new empty node
	tail = new Node;	//new empty node
	head->next = tail;	//head's next is tail
	tail->prev = head;	//tail's prev is head.
}	//done


//* COMPARISON OPERATOR OVERLOADING OUTSIDE LIST */

template <typename T>
 bool operator==(const List<T> & lhs, const List<T> & rhs){
 	if (lhs.size() == rhs.size()){
 		auto itr1 = lhs.begin();
 		auto itr2 = rhs.begin();
 		while (itr1 != lhs.end()){	//cause they're the same size, loop to end
 			if (*itr1 != *itr2)	//check node data in each list via iterator
 				return false;	//leave loop, return false if nodes are unequal	 				
 			++itr1;	//increment lhs element
 			++itr2;	//increment rhs element
 		}
 		if (itr1 == lhs.end())
 			return true;
 	}
 	else
 		return false;
}	//done
template <typename T>
 bool operator!=(const List<T> & lhs, const List<T> &rhs){
 	return !(lhs == rhs);
}	//done

//////////////
template <typename T>
 std::ostream & operator<<(std::ostream &os, const List<T> & lhs){
	//print out all the elments in list 1 by calling List<T>::print()
	lhs.print(os, ' ');
	return os;
}	//done

	