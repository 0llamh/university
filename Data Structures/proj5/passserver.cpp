#ifndef PASSSERVER_CPP
#define PASSSERVER_CPP

#include "passserver.h"
using namespace cop4530;

PassServer::PassServer(size_t size){
	table = HashTable<string, string>(size);
}

PassServer::~PassServer(){
	table.clear();	//empties the table
}

bool PassServer::load (const char *filename){
	return table.load(filename);	//loads filename into the table
}

bool PassServer::addUser(std::pair<string, string> & kv){
	std::pair<string, string> newpair = {std::get<0>(kv), encrypt(std::get<1>(kv))};	
	return table.insert(newpair);
}

bool PassServer::addUser(std::pair<string, string> && kv){
	std::pair<string, string> newpair = {std::get<0>(kv), encrypt(std::get<1>(kv))};		
	return table.insert(newpair);
}

bool PassServer::removeUser(const string & k){
	return table.remove(k);
}

bool PassServer::changePassword(const pair<string, string> & p, const string & newpassword){

	std::pair<string, string> oldpair = {std::get<0>(p), encrypt(std::get<1>(p))};
	
	if (!table.contains(std::get<0>(p)))
		return false;	//if user doesnt exist
	else if (!table.match(oldpair))
		return false;	//if passed in username/password pair doesnt match the one in table
	else{	//there is a match between p and the hashtable's
		std::pair<string, string> newpair = {std::get<0>(p), encrypt(newpassword)};
			//create new pair with original key and new password
		return table.insert(newpair);	//use insert to simply update the key with the new password
	}
	return false;
}

bool PassServer::find(const string & user){
	return table.contains(user);
}

void PassServer::dump(){
	cout << "HashTable: \n";
	cout << "__________\n";
	table.dump();
	cout << "__________\n";
}

size_t PassServer::size(){
	return table.size();
}

bool PassServer::write_to_file(const char *filename){
	return table.write_to_file(filename);
}

/** PRIVATE MEMBER FUNCTIONS */

string PassServer::encrypt(const string & str){

	char salt[] = "$1$########";
	char * encryptpass = new char [100];
	strcpy(encryptpass, crypt(str.c_str(), salt));
	string encryptpass_str(encryptpass);
	return encryptpass_str;
}

#endif