#include <iostream>
using namespace std;

#include "mystring.h"

int main()
{
   MyString s1;
   MyString s2("Hello, World");
   MyString s3 = "Welcome to Florida, have a nice day";
   MyString s4 = 12345;

   cout << "s1 = " << s1 << '\n';
   cout << "s2 = " << s2 << '\n';
   cout << "s3 = " << s3 << '\n';
   cout << "s4 = " << s4 << '\n';
   cout << '\n';

   cout << "Making the calls:\n"
        << "\tcin >> s1\n"
        << "\tgetline(cin, s2, ',')\n"
        << "\tgetline(cin, s3)\n";
   cout << "Enter some sentences: ";
  
   cin >> s1;
   getline(cin,s2,',');
   getline(cin,s3);

   cout << "\nNew string values:\n";

   cout << "s1 = " << s1 << '\n';
   cout << "s2 = " << s2 << '\n';
   cout << "s3 = " << s3 << '\n';
   cout << "---------------------------\n";

// ----------------------------------

   s1 = "Dog";
   s2 = "Food";
   MyString result = s1 + s2;
   cout << "result = " << result << '\n';
   s1 += s2;
   cout << "s1 = " << s1 << endl;

   const MyString s5 = "The concatenation of the catapult is a catamaran";
   cout << "s5 = " << s5 << endl;
   cout << "s5.indexOf(\"cat\") returns " << s5.indexOf("cat") << '\n';
   cout << "s5.indexOf(\"dog\") returns " << s5.indexOf("dog") << '\n';

   cout << "s5.getLength() = " << s5.getLength() << '\n';

   cout << "s5[4] = " << s5[4] << '\n';
   cout << "s5[10] = " << s5[10] << '\n';
   cout << "s5[15] = " << s5[15] << '\n';
   cout << "s5[52] = ascii " << static_cast<int>(s5[52]) << '\n';

   cout << "s5.substring(10,16) = " << s5.substring(10,16) << '\n';
   cout << "s5.substring(23) = " << s5.substring(23) << '\n';

   cout << "-----------------------------\n";

   MyString words = "Greetings, Earthling";

   cout << "words = " << words << '\n';
   cout << "words.getLength() = " << words.getLength() << '\n';
   words[0] = 'K';
   words[4] = 'p';
   words[16] = 'z';

   cout << "words = " << words << '\n';

   words[25] = 'Q';
   cout << "words = " << words << '\n';

   words.insert(11, "Insane ");
   cout << "words = " << words << '\n';

   cout << "-----------------------------\n";

   MyString x = "apple", y = "apply";
   cout << "x = " << x << '\n';
   cout << "y = " << y << '\n';

   if (x < y)		cout << "x < y is true\n";
   if (x > y)		cout << "x > y is true\n";
   if (x <= y)		cout << "x <= y is true\n";
   if (x >= y)		cout << "x >= y is true\n";
   if (x == y)		cout << "x == y is true\n";
   if (x != y)		cout << "x != y is true\n";



}
