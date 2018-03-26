#ifndef COP4530_STACK_H
#define COP4530_STACK_H

#include <iostream>
#include <vector>

namespace cop4530
{

template <typename T>
class Stack;

//----------------------------------
//     Stack<T>
//----------------------------------

template <typename T>
class Stack
{
public:
  // Constructor, Copy-constructor and destructor
  Stack();
  Stack(const Stack<T>& source);
  Stack(Stack<T> && source);
  ~Stack(); 

  // member operators
  Stack<T>& operator=(const Stack<T>& rhs);
  Stack<T> & operator=(Stack<T> && rhs);
  const T& operator[](int) const;
  T& operator [] (int);

  // other methods
  int size() const;

  // Container class protocol
  bool empty() const;
  void clear();
  void push(const T&);
  void push(T &&);
  void pop();
  T& top();
  const T& top() const;


  // Display methods 
  void print(std::ostream& os, char ofc) const;

protected:
  std::vector<T> mystack;   // pointer to the stack

} ;

// operator overloads
template < class T >
std::ostream& operator<<(std::ostream& os, const Stack<T>& a);

template < class T >
bool operator==(const Stack<T>& lhs, const Stack<T>& rhs); 

template < class T >
bool operator!=(const Stack<T>& lhs, const Stack<T>& rhs); 

template < class T >
bool operator<=(const Stack<T>& lhs, const Stack<T>& rhs);
  
#include "stack.hpp"

}   // namespace cop4530
#endif