#ifndef HASHTABLE_H
#define HASHTABLE_H

#include <vector>
#include <list>
#include <string>
#include <iostream>
#include <fstream>
#include <utility>

namespace cop4530{

// max_prime is used by the helpful functions provided
// to you.
static const unsigned int max_prime = 1301081;
// the default_capacity is used if the initial capacity 
// of the underlying vector of the hash table is zero. 
static const unsigned int default_capacity = 11;
 

template <typename K, typename V>
class HashTable{

	public:

		HashTable(size_t size = 101);	//create hashtable, where the size of the vector is set to prime_below(size) (default 101),
		~HashTable();	//delete all elements in the hash table

		bool contains(const K & k); //check if key k is in the hashtable
		bool match(const std::pair<K,V> &kv) const; //check if key-value pair is in the hashtable
		bool insert(const std::pair<K,V> & kv);	//add the key-value pair into the hash table if not already in it.
			//if already inside, update the key-value pair with new data. True if success.
		bool insert(std::pair<K,V> && kv);	//move version of insert
		bool remove(const K & k); //delete the key and its value pair if in the hashtable. True if removed.
		void clear(); //delete all elements in the hashtable
		bool load(const char *filename); //load the content of the file into the hash table.
			//in the file, each line contains a single key-value pair, seperated by whitespace
		void dump(); // display all entries in the hashtable. If multiple entries, seperate by (;)
		bool write_to_file(const char *filename); //write all elements in the hash table to a file, opposite of load()
		size_t size();	//returns currentSize;

	private:

		void makeEmpty(); //delete all elemtns in the hashtable. called by clear()
		void rehash(); //rehash the finction. called when the number of elements > vector size
		size_t myhash(const K &k); //return the index of the vector entry where k should be stored
		unsigned long prime_below(unsigned long); //
		void setPrimes(vector <unsigned long>&);

		struct HashObj{
			K key;
			V val;

			HashObj(const K & k = K(), const V & v = V())
				: key{k}, val{v} { }
			HashObj(K && k, V && v)
				: key{k}, val{v} { }
		};

		std::vector<std::list<HashObj>> theLists;
		int currentSize;


};

#include "hashtable.hpp"

}	//end namespace cop4530

#endif