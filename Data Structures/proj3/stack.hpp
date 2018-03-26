// PAUL CAMERON SMITH
// ASSIGNMENT/PROJECT 3

#ifndef COP4530_STACK_HPP
#define COP4530_STACK_HPP

//----------------------------------------
//     Stack<T>:: Implementations
//----------------------------------------


// operator overloads

template <typename T>
std::ostream& operator<<(std::ostream& os, const Stack<T>& s){
	s.print(os);	
		//calls our already defined print() function
	return os;
}

template <typename T>
bool operator==(const Stack<T>& lhs, const Stack<T>& rhs){
	if(lhs.size() != rhs.size())
		return false;	//different size stacks, unequal
	else{
		for (unsigned int i = 0; i < lhs.size(); i++){
			if(lhs[i] != rhs[i])
				return false;
		}
		return true;
	}
}

template <typename T>
bool operator!= (const Stack<T>& lhs, const Stack<T>& rhs){
	return !(lhs == rhs);
		//opposite operator==, just call the same function
}

template <typename T>
bool operator<=(const Stack<T>& lhs, const Stack<T>& rhs){
	// if(lhs.size() != rhs.size())
	// 	return false;	//different size stacks, unequal
	// else
	 if(lhs.size() <= rhs.size()){
		for (unsigned int i = 0; i < lhs.size(); i++){
			if(lhs[i] > rhs[i])
				return false;
		}
		return true;
	}
	else if(lhs.size() > rhs.size()){
		for (unsigned int i = 0; i < rhs.size(); i++){
			if (lhs[i] > rhs[i])
				return false;
		}
		return true;
	}

}

// public methods

template <typename T>
Stack<T>::Stack(){
	//Constructor
	mystack.clear();
}

template <typename T>
Stack<T>::Stack(const Stack<T>& source){
	//Copy-constructor
	for (unsigned int i = 0; i < source.size(); i++)
		push(source[i]);	//pushes each element of source to our stack via operator[]
}

template <typename T>
Stack<T>::Stack(Stack<T> && source){
    // Move copy constructor
    for (unsigned int i = 0; i < source.size(); i++)
    	push(std::move(source[i]));
}

template <typename T>
Stack<T>::~Stack(){
	//Destructor
	if (!empty())
		clear();
}

template <typename T>
Stack<T>& Stack<T>::operator=(const Stack<T>& rhs){
	// assignment operator
	clear();	//empty current stack
	for (unsigned int i = 0; i < rhs.size(); i++){
		push(rhs[i]);
	}
}

template <typename T>
Stack<T>& Stack<T>::operator=(Stack<T> && rhs){
    return this.move(rhs);	//call copy constructor
}

template <typename T>
const T& Stack<T>::operator [] (int i) const{
	return mystack.at(i);		//call predefined vector operator[]
}

template <typename T>
T& Stack<T>::operator [] (int i){
	// element operator
	return mystack.at(i);		//call predefined vector operator[]
} 

template <typename T>
bool Stack<T>::empty() const{
	return mystack.empty();
}

template <typename T>
int Stack<T>::size() const{
 	return mystack.size();	//uses vector size() function
}

template <typename T>
void Stack<T>::push(const T& Val){
	mystack.push_back(Val);	//uses vector push_back() function to only access one end
}

template <typename T>
void Stack<T>::push(T && Val){
    mystack.push_back(Val);	//uses move function
}

template <typename T>
void Stack<T>::pop(){
	mystack.pop_back();	
}

template <typename T>
void Stack<T>::clear(){
	mystack.clear();
}

template <typename T>
T&  Stack<T>::top(){
	return mystack.back();	//back of our vector will be our stack top
}

template <typename T>
const T&  Stack<T>::top() const{
	return mystack.back();	//same method as the non-const version
}

template <typename T>
void Stack<T>::print(std::ostream& os, char ofc = ' ') const{
	for (unsigned int i = 0; i < mystack.size(); ++i)
		std::cout << ofc << mystack.at(i);
		//traverses through the vector from back to front printing each element
}

#endif
