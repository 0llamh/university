//------------------------- Diamond.h ---------------------------

class Diamond
{
	public:
		Diamond(int size, char border = '#', char fill = '*');
		//Diamond class constructor

		int GetSize();	
		int Perimeter();
		double Area();
		//accessor functions

		//MUTATORS
		void Grow();
		void Shrink();                        //increment and decrement of the Diamond size
		char SetBorder(char border = '#');
		char SetFill(char fill = '*');                       //allows changing of the border and fill parameters from the defaults
		void Draw();                          //prints a picture of the diamond using the variables and the characters defined
		void Summary();                       //prints out all defined information of the diamond
	private:
		int size;
		int border;
		int fill;
};
