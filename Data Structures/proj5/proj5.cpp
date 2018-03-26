#include <iostream>

using namespace std;

#include "passserver.cpp"

void Menu();
void load(PassServer & ps);
void addUser(PassServer & ps);
void removeUser(PassServer & ps);
void changePass(PassServer & ps);
void findUser(PassServer & ps);
void dump(PassServer & ps);
void getSize(PassServer & ps);
void write(PassServer & ps);

int main(){

  PassServer ps;
  char choice = ' ';

  do{

    Menu(); //prints menu

    cin >> choice;

    if (choice == 'l'){
      load(ps);
    }
    else if (choice == 'a'){
      addUser(ps);
    }
    else if (choice == 'r'){
      removeUser(ps);
    }
    else if (choice == 'c'){
      changePass(ps);
    }
    else if (choice == 'f'){
      findUser(ps);
    }
    else if (choice == 'd'){
      dump(ps);
    }
    else if (choice == 's'){
      getSize(ps);
    }
    else if (choice == 'w'){
      write(ps);
    }
    else{
      if (choice != 'x')
        cout << "Invalid Selection. Try again.\n";
    }
  }while(choice != 'x');

  return 0;
}

void Menu(){
  cout << "\n\n";
  cout << "l - Load From File" << endl;
  cout << "a - Add User" << endl;
  cout << "r - Remove User" << endl;
  cout << "c - Change User Password" << endl;
  cout << "f - Find User" << endl;
  cout << "d - Dump HashTable" << endl;
  cout << "s - HashTable Size" << endl;
  cout << "w - Write to Password File" << endl;
  cout << "x - Exit program" << endl;
  cout << "\nEnter choice : ";
}
void load(PassServer & ps){
  char* filename = new char[20];
  cout << "Loading from file\n File: ";
  cin >> filename;
  ps.load(filename) ? cout << "File loaded." : "Error";
}
void addUser(PassServer & ps){
  string username, password;
  cout << "Adding a new user\n Username: ";
  cin >> username;
  cout << "Password: ";
  cin >> password;
  std::pair<string, string> newuser = {username, password};
  ps.addUser(newuser) ? cout << "User added." : cout << "Error.";
}
void removeUser(PassServer & ps){
  string username;
  cout << "Removing user\n Username: ";
  cin >> username;
  ps.removeUser(username) ? cout << "User removed." : cout << "Error.";
}
void changePass(PassServer & ps){
  string username, oldpass, newpass;
  cout << "Changing password\n Username: ";
  cin >> username;
  cout << "Old password: ";
  cin >> oldpass;
  cout << "New password: ";
  cin >> newpass;
  std::pair<string, string> newpair = {username, oldpass};
  ps.changePassword(newpair, newpass) ? cout << "Password updated." : cout << "Error";  
}
void findUser(PassServer & ps){
  string username;
  cout << "Searching for a user\n Username: ";
  cin >> username;
  ps.find(username) ? cout << "User found." : cout << "User not found.";
}
void dump(PassServer & ps){
  ps.dump();
}
void getSize(PassServer & ps){
  cout << "HashTable size: " << ps.size();
}
void write(PassServer & ps){
  char* filename = new char[20];
  cout << "Writing to file\n File: ";
  cin >> filename;
  ps.write_to_file(filename) ? cout << "File written." : "Error";
}

