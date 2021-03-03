#pragma once
#include "list_node.h"
#include <string>

template <typename T>
class basic_list
{
public:
	virtual void append(T* element) = 0;
	virtual void insert(T* element, int position_after) = 0;
	virtual T* getByNumber(int position) = 0;
	virtual void swap(T* left, T* right) = 0;
	virtual void remove(int position) = 0;
	virtual int  length() = 0;
	virtual void sort() = 0;
	virtual std::string to_string() = 0;
	virtual void task1() = 0;
};

