//-------------- digitime.h --------------

// Declarations of classes Display and Timer

class Display
// A Display object stores and displays a single integer.
// That integer is always in the range 0 .. limit-1, where limit
// is also stored in the class.
{
public:
   Display(int lim);               // Initialize a new Display object.

   void Increment();               // Add 1 to value.
   void SetValue(int val);         // Set the value.

   int GetValue();                 // Return the current value.
   void ShowValue();                    // Show the current value.
   int GetLimit();		   // Return the limit

private:
   int limit,                      // largest possible value
       value;                      // current value (0 .. limit - 1)
};

class Timer
// A Timer object consists of two Displays,one for hours and one for minutes.
// When the timer is incremented and minutes becomes 60, minutes is reset
// to 0
//and hours is incremented.
{
public:
   Timer();                        // Initialize a new Timer object,
                                   // setting max hours to 24 and
                                   // max minutes to 60
   void Increment();               // Add 1 minute to timer.
   void Set();                     // Set hours and minutes.

   void Show();                    // Show hours and minutes.

private:
   Display hours,                  // two displays, one for hours,
           minutes;                // and one for minutes
};

