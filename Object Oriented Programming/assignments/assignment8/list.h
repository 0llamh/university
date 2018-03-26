// Fig. 21.4: list.h
// Template List class definition.
#ifndef LIST_H
#define LIST_H

#include <iostream>

using std::cout;

#include <new>
#include "listnode.h"  // ListNode class definition

template< class NODETYPE >
class List {

public:
   List();      // constructor
   ~List();     // destructor
   void insertAtFront( const NODETYPE & );
   void insertMiddle( const NODETYPE & , int position );
   void insertAtBack( const NODETYPE & );
   bool removeFromFront( NODETYPE & );
   bool removeMiddle( NODETYPE &, int position );
   bool removeFromBack( NODETYPE & );
   bool isEmpty() const;
   void print() const;

private:
   ListNode< NODETYPE > *firstPtr;  // pointer to first node
   ListNode< NODETYPE > *lastPtr;   // pointer to last node

   // utility function to allocate new node
   ListNode< NODETYPE > *getNewNode( const NODETYPE & );

}; // end class List

// default constructor
template< class NODETYPE >
List< NODETYPE >::List() 
   : firstPtr( 0 ), 
     lastPtr( 0 ) 
{ 
   // empty body

} // end List constructor

// destructor
template< class NODETYPE >
List< NODETYPE >::~List()
{
   if ( !isEmpty() ) {    // List is not empty
//      cout << "Destroying nodes ...\n";

      ListNode< NODETYPE > *currentPtr = firstPtr;
      ListNode< NODETYPE > *tempPtr;

      while ( currentPtr != 0 )         // delete remaining nodes
      {  
         tempPtr = currentPtr;

// commented out the output -- no need to print what we are deallocating
//         cout << tempPtr->data << '\n';  

         currentPtr = currentPtr->nextPtr;
         delete tempPtr;

      }

   }

//   cout << "All nodes destroyed\n\n";

} // end List destructor

// insert node at front of list
template< class NODETYPE >
void List< NODETYPE >::insertAtFront( const NODETYPE &value )
{
   ListNode< NODETYPE > *newPtr = getNewNode( value );

   if ( isEmpty() )  // List is empty
      firstPtr = lastPtr = newPtr;

   else {  // List is not empty
      newPtr->nextPtr = firstPtr;
      firstPtr = newPtr;

   } // end else

} // end function insertAtFront

// insert node in between front and back 
template< class NODETYPE >
void List< NODETYPE >::insertMiddle( const NODETYPE &value, int position)
{
	ListNode< NODETYPE > *newPtr = getNewNode ( value );
	ListNode< NODETYPE > *coutingPtr = firstPtr;		
	ListNode< NODETYPE > *currentPtr = firstPtr;	
	int nodeSize = 0;

    while ( coutingPtr->nextPtr != lastPtr ) {
		coutingPtr = coutingPtr->nextPtr;
		nodeSize++;
	} // counts the number of nodes in the list
	nodeSize += 2; // accounts for first and last ptr
//	cout << '\n' << "List size is " << nodeSize  << " before change"<< '\n';
	
	if  ( isEmpty() ) // empty list
		firstPtr = lastPtr = newPtr;
	else if ( position <= 1) {	
		insertAtFront(value);	// just add to front
	}
	else if ( position > (nodeSize) ) {
		insertAtBack(value);	// just add to back
	}
	else {		
		for ( int i = 0; i < position-2; i++) 	// loops until the node before
			currentPtr = currentPtr->nextPtr;	// the insert position
		newPtr->nextPtr = currentPtr->nextPtr;	// newptr needs to point to the currentptr's address
		currentPtr->nextPtr = newPtr;	// and the currentptr needs to point to the newptr
	}
}

// insert node at back of list
template< class NODETYPE >
void List< NODETYPE >::insertAtBack( const NODETYPE &value )
{
   ListNode< NODETYPE > *newPtr = getNewNode( value );

   if ( isEmpty() )  // List is empty
      firstPtr = lastPtr = newPtr;

   else {  // List is not empty
      lastPtr->nextPtr = newPtr;
      lastPtr = newPtr;

   } // end else

} // end function insertAtBack

// delete node from front of list
template< class NODETYPE >
bool List< NODETYPE >::removeFromFront( NODETYPE &value )
{
   if ( isEmpty() )  // List is empty
      return false;  // delete unsuccessful

   else {  
      ListNode< NODETYPE > *tempPtr = firstPtr;

      if ( firstPtr == lastPtr )
         firstPtr = lastPtr = 0;
      else
         firstPtr = firstPtr->nextPtr;

      value = tempPtr->data;  // data being removed
      delete tempPtr;

      return true;  // delete successful

   } // end else

} // end function removeFromFront

// remove a middle node  
template< class NODETYPE >
bool List< NODETYPE >::removeMiddle( NODETYPE &value, int position)
{
	ListNode< NODETYPE > *currentPtr = firstPtr;
	ListNode< NODETYPE > *countingPtr = firstPtr;
	int nodeSize = 0;
	
	while ( countingPtr->nextPtr != lastPtr ) {
			countingPtr = countingPtr->nextPtr;
			nodeSize++;
		} // counts the number of nodes in the list
	nodeSize += 2; // accounts for first and last ptr
//	cout << '\n' << "List size is " << nodeSize  << " before change"<< '\n';
		
	if ( isEmpty() || position < 1)
		return false;				// do nothing
	else if ( position == 1) {
		removeFromFront(value);		// just remove front
		return true;			// and exit function
	}
	else if ( position == nodeSize) {
		removeFromBack(value);		// just remove the back
		return true;			// and exit function
	}	
	else if ( position > nodeSize)
		return false;				// do nothing
	else {
		for ( int i = 0; i < position-2;i++)	// loops until the ptr before 
			currentPtr = currentPtr->nextPtr;	// the soon-to-be-deleted ptr
		ListNode< NODETYPE > *tempPtr = currentPtr->nextPtr;	// new ptr to store nextptr
		currentPtr->nextPtr = tempPtr->nextPtr;	
		value = tempPtr->data;	// stores the deleted ptr's data to parameter
		delete tempPtr;		// deletes node
		return true;		// exit function
	}
	
}

// delete node from back of list
template< class NODETYPE >
bool List< NODETYPE >::removeFromBack( NODETYPE &value )
{
   if ( isEmpty() )
      return false;  // delete unsuccessful

   else {
      ListNode< NODETYPE > *tempPtr = lastPtr;

      if ( firstPtr == lastPtr )
         firstPtr = lastPtr = 0;
      else {
         ListNode< NODETYPE > *currentPtr = firstPtr;

         // locate second-to-last element
         while ( currentPtr->nextPtr != lastPtr )
            currentPtr = currentPtr->nextPtr;

         lastPtr = currentPtr;
         currentPtr->nextPtr = 0;

      } // end else

      value = tempPtr->data;
      delete tempPtr;

      return true;  // delete successful

   } // end else

} // end function removeFromBack

// is List empty?
template< class NODETYPE > 
bool List< NODETYPE >::isEmpty() const 
{ 
   return firstPtr == 0; 
   
} // end function isEmpty

// return pointer to newly allocated node
template< class NODETYPE >
ListNode< NODETYPE > *List< NODETYPE >::getNewNode( 
   const NODETYPE &value )
{
   return new ListNode< NODETYPE >( value );

} // end function getNewNode

// display contents of List
template< class NODETYPE >
void List< NODETYPE >::print() const
{
   if ( isEmpty() ) {
      cout << "The list is empty\n\n";
      return;

   } // end if

   ListNode< NODETYPE > *currentPtr = firstPtr;

   cout << "The list is: ";

   while ( currentPtr != 0 ) {
      cout << currentPtr->data << ' ';
      currentPtr = currentPtr->nextPtr;

   } // end while

   cout << "\n\n";

} // end function print

#endif

/**************************************************************************
 * (C) Copyright 1992-2003 by Deitel & Associates, Inc. and Prentice      *
 * Hall. All Rights Reserved.                                             *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/