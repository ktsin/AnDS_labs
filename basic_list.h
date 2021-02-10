#pragma once
#include "list_node.h"
#include <string>

template <typename T>
class basic_list
{
	public:
		virtual void append(T* element) {};
		virtual void insert(T* element, int position_after) {};
		virtual T*	 getByNumber(int position) {};
		virtual void swap(T* left, T* right) {};
		virtual void remove(int position) {};
		virtual int  length() {};
		virtual void sort() {};
		virtual std::string to_string() {};
};

