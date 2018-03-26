#ifndef PASSSERVER_H
#define PASSSERVER_H

#include <iostream>
#include <utility>
#include <unistd.h>
#include <crypt.h>
#include <string>
#include <cstring>

#include "hashtable.h"

using namespace cop4530;

class PassServer{

	public:
		PassServer(size_t size = 101);	//create hashtable via HashTable(size)
		~PassServer(); //deconstructor
		bool load (const char *filename);	//load password file
		bool addUser(std::pair<string, string> & kv);	//add new user & password. encryted before insertion
		bool addUser(std::pair<string, string> && kv);	//move version
		bool removeUser(const string & k); //delete user with username k
		bool changePassword(const pair<string, string> & p, const string & newpassword);
		bool find(const string & user);
		void dump();
		size_t size();
		bool write_to_file(const char *filename);

	private:
		string encrypt(const string & str);
		HashTable<string, string> table;

};

#endif