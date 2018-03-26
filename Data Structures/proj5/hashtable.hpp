#ifndef HASHTABLE_HPP
#define HASHTABLE_HPP

template <typename K, typename V>
HashTable<K,V>::HashTable(size_t size){

	theLists.resize(prime_below(size));
	currentSize = 0;
	//cout << theLists.size();

}

template <typename K, typename V>
HashTable<K,V>::~HashTable(){

	makeEmpty();
}

template <typename K, typename V>
bool HashTable<K,V>::contains(const K & k){

	for (auto& thisList : theLists){//iterate through theLists : for loop?
		if (thisList.empty())//check if element in vector is empty
			{}//if empty move on
		else{//if filled
			for (auto& user : thisList){	//iterate through list
				if(user.key == k)	//is element in list k == k parameter?
					return true;	//return true if is
			}//false, move on to next list
		}
	}
	return false; 	//default

}

template <typename K, typename V>
bool HashTable<K,V>::match(const std::pair<K,V> &kv) const{


	for (auto& thisList : theLists){//iterate through theLists : for loop?
		if (thisList.empty())//check if element in vector is empty
			{}//if empty move on
		else{//if filled
			for (auto& user : thisList){	//iterate through list
				if(user.key == std::get<0>(kv) && user.val == std::get<1>(kv))	//is element in list k == k parameter?
					return true;	//return true if is
			}//false, move on to next list
		}
	}
	//else, return true is never reached
	return false;

}

template <typename K, typename V>
bool HashTable<K,V>::insert(const std::pair<K,V> & kv){

	if (match(kv)){
		//already in hashtable, do nothing
	}
	else if (contains(std::get<0>(kv))){
		//key already exists, need to update
		for(auto& user : theLists[myhash(std::get<0>(kv))]){
			if (user.key == std::get<0>(kv))
				user.val = std::get<1>(kv);
		}
	}
	else{
		HashObj hobj = HashObj(std::get<0>(kv), std::get<1>(kv));
		//cout << "Pushing new HashObj with new data into list at the needed index.\n";
			//Segfault here. Don't know where the issue lies. Either here or my constructor.
		theLists[myhash(std::get<0>(kv))].push_back(hobj);
		currentSize++; 
	}

	if (++currentSize > theLists.size())
		rehash();

	return true;

}

template <typename K, typename V>
bool HashTable<K,V>::insert(std::pair<K,V> && kv){

	if (match(kv)){}
		//already in hashtable, do nothing
	else if (contains(std::get<0>(kv))){
		//key already exists, need to update
		for(auto& user : theLists[myhash(std::get<0>(kv))]){
			if (user.key == std::get<0>(kv))
				user.val = std::get<1>(kv);
		}
	}
	else{
		theLists[myhash(std::get<0>(kv))].push_back(std::move(std::get<0>(kv), std::get<1>(kv)));
		currentSize++;
	}

	if (++currentSize > theLists.size())
		rehash();

	return true;
}

template <typename K, typename V>
bool HashTable<K,V>::remove(const K & k){

	for (auto itr = theLists[myhash(k)].begin(); itr != theLists[myhash(k)].end(); itr++){
		if (itr->key == k){
			theLists[myhash(k)].erase(itr);
			currentSize--;
			return true;
		}
	}

	return false;
}

template <typename K, typename V>
void HashTable<K,V>::clear(){

	makeEmpty();
}

template <typename K, typename V>
bool HashTable<K,V>::load(const char *filename){

	string newuser = "test";
	string previous = "dummy";
	ifstream ifile;
	ifile.open(filename);

	while(true){
		getline(ifile, newuser);
		if (newuser != previous){
			previous = newuser;
			for (unsigned int i = 0; i < newuser.size(); i++){
				if (newuser[i] == ' '){
					string tmpkey = newuser.substr(0,i);
					string tmpval = newuser.substr(i+1);
					std::pair<string, string> tmppair = {tmpkey, tmpval};
					insert(tmppair);
				}
			}
		}
		else
			break;
	}

	ifile.close();

	return true;

}

template <typename K, typename V>
void HashTable<K,V>::dump(){
	//print() function
	int index = 0;
	cout << "Index\t: Data\n";

	for (auto& thisList : theLists){ //iterate through vector
		if(!thisList.empty()){	//if list is not empty
			for(auto& user : thisList){
				cout << index << "\t: " << user.key << ' ' << user.val << "; ";
			}	//print each item in the list
			cout << '\n';
		}
		index++;
		//cout << '\n';
	}
}

template <typename K, typename V>
bool HashTable<K,V>::write_to_file(const char *filename){

	ofstream ofile;
	ofile.open(filename);

	for (auto& thisList : theLists){ //iterate through vector
		if(!thisList.empty()){	//if list is not empty
			for(auto& user : thisList){
				ofile << user.key << ' ' << user.val << "; ";
			}	//print each item in the list
		}
		ofile << '\n';
	}

	ofile.close();
	return true;

}

template <typename K, typename V>
size_t HashTable<K,V>::size(){
	return currentSize;
}

/** PRIVATE HASHTABLE FUNCTIONS */

template <typename K, typename V>
void HashTable<K,V>::makeEmpty(){
	for (auto & thisList : theLists)
		thisList.clear();
}

template <typename K, typename V>
void HashTable<K,V>::rehash(){
	//grow() function

	std::vector<std::list<HashObj>> oldLists = theLists;

	makeEmpty();
	theLists.resize(prime_below(2*theLists.size()));

	for (auto& thisList : theLists){
		for (auto& user : thisList){
			std::pair<string, string> newpair = {user.key, user.val};
			insert(newpair);
		}
	}
}

template <typename K, typename V>
size_t HashTable<K,V>::myhash(const K & k){

	std::hash<K> hash_f;
	return (hash_f(k) % theLists.size());
}

// returns largest prime number <= n or zero if input is too large
// This is likely to be more efficient than prime_above(), because
// it only needs a vector of size n
template <typename K, typename V>
unsigned long HashTable<K,V>::prime_below (unsigned long n){
  if (n > max_prime)
    {
      std::cerr << "** input too large for prime_below()\n";
      return 0;
    }
  if (n == max_prime)
    {
      return max_prime;
    }
  if (n <= 1)
    {
		std::cerr << "** input too small \n";
      return 0;
    }

  // now: 2 <= n < max_prime
  std::vector<unsigned long> v (n+1);
  setPrimes(v);
  while (n > 2)
    {
      if (v[n] == 1)
		return n;
      --n;
    }

  return 2;
}

//Sets all prime number indexes to 1. Called by method prime_below(n) 
template <typename K, typename V>
void HashTable<K,V>::setPrimes(std::vector<unsigned long>& vprimes){
  int i = 0;
  int j = 0;

  vprimes[0] = 0;
  vprimes[1] = 0;
  int n = vprimes.capacity();

  for (i = 2; i < n; ++i)
    vprimes[i] = 1;

  for( i = 2; i*i < n; ++i)
    {
      if (vprimes[i] == 1)
        for(j = i + i ; j < n; j += i)
          vprimes[j] = 0;
    }
}

#endif