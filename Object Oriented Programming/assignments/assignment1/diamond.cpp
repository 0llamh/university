//--------------Diamond.cpp----------------- 
//MEMBER FUNCTION DEFINITIONS FOR THE DIAMOND

#include <iostream>
#include <cmath>
#include <iomanip>
#include "diamond.h"
using namespace std;

Diamond::Diamond(int s, char b, char f)
//INITIALIZATION AND ERROR CHECKING CONSTRUCTOR
{
   size = s;				//STORAGE OF  THE SIZE,
   border = b;				//BORDER CHARACTER
   fill = f;				//FILL CHARACTER

   if (size < 1)			//ERROR CHECKING
	size = 1;			//FOR DIAMOND BOUNDARIES
   if (size > 39)
	size = 39;
}

int Diamond::GetSize()
//ALLOWS THE USER TO ACCESS THE STORED SIZE VARIABLE
{
   return size;
}

int Diamond::Perimeter()
//CALCULATES THE PERIMETER USING THE STORED SIZE VARIABLE FOR THE USER
{
   int perimeter = (size * 4);
   return perimeter;
}

double Diamond::Area()
//CALCULATES THE AREA USING THE STORED SIZE VARIABLE FOR THE USER
{
   double area = (((sqrt (3.0)) / 4) * size * size) * 2;	//FORMULA FOR CALCULATING THE AREA OF THE DIAMOND
   cout << setprecision(2) << fixed;				//ONLY PRINTS 2 DIGITS AFTER THE DECIMAL
   return area;
}

void Diamond::Grow()
//GROWS THE DIAMOND BY ONE IN SIZE
{
   if (size < 39)
	size++;
}

void Diamond::Shrink()
//SHRINKS THE DIAMOND BY ONE IN SIZE
{
   if (size > 1)
	size--;
}

char Diamond::SetBorder(char border)
//ALLOWS CHANGING OF THE BORDER CHARACTER
{
   /* Intentionally left blank. border character changes with function call parameter*/
}

char Diamond::SetFill(char fill)
//ALLOWS CHANGING OF THE FILL CHARACTER
{
   /* Intentionally left blank. fill character changes with function call parameter*/
}

void Diamond::Draw()
//PRINTS THE DIAMOND ONTO THE SCREEN
{
   char b = border;
   char f = fill;
   int fillamt;				//HOW MANY FILL CHARACTERS TO BE USED IN A SINGLE LAYER (CHANGES VALUE IN THE LOOP)
   cout << left;
   
   //TOP HALF OF THE DIAMOND LOOP INCLUDING THE MIDDLE LAYER
   for (int layer = 1; layer <= size; layer++)
   {
      for (int spaces = size - layer; spaces >= 0; spaces--)
          cout << ' ';

      cout << b << ' ';

      fillamt = (layer  - 2);		//NUMBER OF FILLS START AT ONE ON THE THIRD LAYER SO THIS DELAYS THAT COUNTER
      if (layer >= 3)
      {
          for (int fillcount = 0; fillcount < fillamt; fillcount++)
              cout << f << ' ';
      } 
      
      if (layer >= 2)			//SECOND BORDER CHARACTER ON EVERY LAYER BESIDES THE FIRST
          cout << b;
      
     
      cout << endl;			//ENDS THE LAYER TO START A NEW ONE IN THE LOOP
   }

   //BOTTOM HALF OF THE DIAMOND LOOP WITHOUT THE MIDDLE LAYER
   for (int layer = size - 1; layer >= 1; layer--)			//EVERYTHING IS SWITCHED TO DECREASE IN VALUE. MIRRORS THE PREVIOUS LOOP
   {
      for (int spaces = layer - size; spaces <= 0; spaces++)
          cout << ' ';

      cout << b << ' ';

      fillamt = (layer - 2);		
      if (layer >= 3)			//FILL COUNT STARTING ON LAYER 3 FROM THE BOTTOM
      {
          for (int fillcount = fillamt; fillcount > 0; fillcount--)
              cout << f << ' ';
      }

   if (layer >= 2)			//SECCOND BORDER CHARACTER ON ALL LAYERS BUT THE LAST
      cout << b;

   cout <<  endl;

   }
}

void Diamond::Summary()
//PRINTS OUT ALL INFORMATION CALCULATED IN OTHER MEMBER FUNCTIONS AS ONE CALL
{ 
   cout << "\nSize of the diamond's side = " << GetSize() << " units.";
   cout << "\nPerimeter of diamond = " << Perimeter() << " units.";
   cout << "\nArea of diamond = " << Area() << " units.\n";
   Draw();
}
