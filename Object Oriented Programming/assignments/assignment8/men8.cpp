// Test program for HW 8
//

#include <iostream>

using std::cin;
using std::endl;

#include <string>

using std::string;

#include "list.h"  // List class definition

void instructions();

// function to test a List
template< class T >
void testList( List< T > &listObject, const string &typeName )
{
   cout << "Testing a List of " << typeName << " values\n";

   instructions();  // display instructions

   int choice;
   T value;
   int pos;

   do {
      cout << "? ";
      cin >> choice;

      switch ( choice ) {
         case 1:
            cout << "Enter " << typeName << ": ";
            cin >> value;
            listObject.insertAtFront( value );
            listObject.print();
            break;

         case 2:
            cout << "Enter " << typeName << ": ";
            cin >> value;
            listObject.insertAtBack( value );
            listObject.print();
            break;

         case 3:
            cout << "Enter " << typeName << ": ";
            cin >> value;
	    cout << "Enter position to insert: ";
	    cin >> pos;
            listObject.insertMiddle( value , pos );
            listObject.print();
            break;

         case 4:
            if ( listObject.removeFromFront( value ) )
               cout << value << " removed from list\n";

            listObject.print();
            break;

         case 5:
            if ( listObject.removeFromBack( value ) )
               cout << value << " removed from list\n";

            listObject.print();
            break;

         case 6:
	    cout << "Enter position to delete: ";
	    cin >> pos;
            if ( listObject.removeMiddle( value, pos ) )
               cout << value << " removed from list\n";

            listObject.print();
            break;

      } // end switch

   } while ( choice != 7 );  // end do/while

   cout << "End list test\n\n";

} // end function testList

// display program instructions to user
void instructions()
{
   cout << "Enter one of the following:\n"
        << "  1 to insert at beginning of list\n" 
        << "  2 to insert at end of list\n" 
	<< "  3 to insert at anywhere in the list\n"
        << "  4 to delete from beginning of list\n" 
        << "  5 to delete from end of list\n" 
	<< "  6 to delete from anywhere in the list\n"
        << "  7 to end list processing\n";

} // end function instructions

int main()
{
   // test List of int values
   List< int > integerList;
   testList( integerList, "integer" ); 

   // test List of double values
   List< double > doubleList;
   testList( doubleList, "double" ); 

   return 0;

} // end main
