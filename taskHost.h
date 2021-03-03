#pragma once
#include "basic_list.h"
#include "list_node.h"
#include <iostream>

class taskHost
{
public:
	/// <summary>
	/// ѕовтор€ющиес€ числа перенести в конец массива
	/// </summary>
	/// <typeparam name="T">“ип узла</typeparam>
	/// <param name="list">Ёкземпл€р списка</param>
	template <typename T>
	void static task1(basic_list<T> &list) {
		int len = list.length();
		for (int i(0); i < len; i++) {
			auto tmp = list.getByNumber(i);
			//если найдЄм, то обмен€ем значени€, сократив len
			bool find = false;
			for (int j(0); j < list.length() && !find; j++) {
				if (tmp->value == list.getByNumber(j)->value) {
					list.swap(tmp, list.getByNumber(j));
					find = true;
					len--;
					i = 0;
				}
			}
		}
	}
};

//struct A {
//	A() { a = 0; }
//	int a;
//	A* next;
//};
//
//struct B
//{
//	B() { a = 100; }
//	int a;
//	int next;
//};
//template <typename T>
//int test(T* ptr) {
//	std::cout << ptr->a << std::endl;
//	return -1;
//}
//
//void test2() {
//	test<A>(new A);
//	test<B>(new B);
//}

