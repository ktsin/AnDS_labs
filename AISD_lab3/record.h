#pragma once
#pragma warning(disable : 4996)
#define _CRT_SECURE_NO_WARNINGS
#include <ctime>
#include <string>
#include <iomanip>
#include <random>
#include <sstream>

using namespace std;

class record
{
public:
	record() {
		auto field_1_tm = new tm();
		auto field_2_tm = new tm();
		field_1_tm->tm_year = field_2_tm->tm_year = 121;
		field_1_tm->tm_mday = field_2_tm->tm_mday = rand() % 10;
		field_1_tm->tm_mon = rand() % 6 + 1;
		field_2_tm->tm_mon = rand() % 4 + 3;
		field_1 = mktime(field_1_tm);
		field_2 = mktime(field_2_tm);
		delete field_1_tm;
		delete field_2_tm;
		field_3 = new string("     ");
		for (int i(0); i < 5; i++) {
			(*field_3)[i] = rand() % 5 + 97;
		}
	}
	string to_string()
	{
		std::stringstream ss;
		char* buffer = new char[255]{ 0 };
		strftime(buffer, 255, "%d/%m/%G", localtime(&this->field_1));
		auto tmp = string(buffer);
		ss << tmp << "; ";
		strftime(buffer, 255, "%d/%m/%G", localtime(&this->field_2));
		tmp = string(buffer);
		ss << tmp << "; " << *this->field_3;
		return ss.str();
	}
	time_t field_1 = 0;
	time_t field_2 = 0;
	string* field_3 = nullptr;
};

class score {
public:
	unsigned long permutations = 0;
	unsigned long compares = 0;
	void reset() {
		permutations = compares = 0;
	}
};
