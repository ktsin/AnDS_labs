#include "list_array.h"
#include <iostream>

list_array::list_array(std::vector<list_node_array> _values)
{
	this->_array = _values;
	this->head = (_array.data());
}

void list_array::append(list_node_array* element)
{
	auto last = this->getByNumber(_array.size() - 1);
	if(last != nullptr)
		last->next = _array.size();
	_array.push_back(*element);
}

void list_array::insert(list_node_array* element, int position_after)
{
	if (position_after >= _array.size() || element == nullptr)
		return;
	_array.push_back(*element);
	auto last = this->getByNumber(position_after);
	if (last != nullptr) {
		auto tmp = last->next;
		last->next = _array.size() - 1;
		_array.back().next = tmp;
	}
}

list_node_array* list_array::getByNumber(int position)
{
	if (position < 0 || position >= _array.size())
		return nullptr;
	else {
		int i = 0;
		auto top = &(_array.data()[headNumber]);
		while (i != position && top != nullptr)
		{
			top = &(_array.data()[(top->next)]);
			i++;
		}
		return top;
	}
}

void list_array::swap(list_node_array* left, list_node_array* right)
{
	//if (left == right)
	//	return;
	//auto head = &_array.data()[headNumber];
	//auto* preLeft = head, * preRight = head, * top = head;
	//while (top != left) {
	//	preLeft = top;
	//	top = &_array[top->next];
	//}
	//top = head;
	//while (top != right)
	//{
	//	preRight = top;
	//	top = &_array[top->next];
	//}
	//top = head;
	//if (preLeft == left) {
	//	//we are in start => must change head pointer
	//	this->head = right;
	//	headNumber = indexOfPointer(right);
	//	auto tmp = right->next;
	//	right->next = left->next;
	//	left->next = tmp;
	//	preRight->next = indexOfPointer(left);
	//	return;
	//}
	//else {
	//	preLeft->next = indexOfPointer(right);
	//	preRight->next = indexOfPointer(left);
	//	auto tmp = left->next;
	//	left->next = right->next;
	//	right->next = tmp;
	//}
	if (left == right)
		return;
	auto* preLeft = head, * preRight = head, * top = head;
	while (top != left) {
		preLeft = top;
		top = top->next;
	}
	top = head;
	while (top != right)
	{
		preRight = top;
		top = top->next;
	}
	top = head;
	if (preLeft == left) {
		if (left->next == right) {
			auto tmp = right->next;
			left->next = tmp;
			right->next = left;
			this->head = right;
		}
		else {
			this->head = right;
			auto rN = right->next;
			right->next = left->next;
			left->next = rN;
			preRight->next = left;
		}
		return;
	}
	else {
		if (left != preRight) {
			preLeft->next = right;
			preRight->next = left;
			auto rN = right->next;
			right->next = left->next;
			left->next = rN;
		}
		else {
			preLeft->next = right;
			left->next = right->next;
			right->next = left;
		}
	}
}

void list_array::remove(int position)
{
	_array.erase(_array.begin() + position);
	//tckb next > position => next--
	for (auto iter = _array.begin(); iter != _array.end(); iter++) {
		if ((*iter).next > position)
			(*iter).next--;
	}
}

void list_array::sort()
{
	for (int i(0); i < length(); i++) {
		for (int j(1); j < length(); j++) {
			if (getByNumber(j)->value < getByNumber(j - 1)->value) {
				swap(getByNumber(j - 1), getByNumber(j));
			}
		}
	}
}

std::string list_array::to_string()
{
	if (this->length() > 0) {
		std::string result = "";
		for (int i(0); i < this->length(); i++) {
			auto element = this->getByNumber(i);
			result += std::string("(") + std::to_string(element->value) + std::string("; ") + std::to_string(element->next) + std::string(") -> ");
		}
		result += std::string("NULL");
		return result;
	}
	else
		return std::string("NULL");
}

bool list_array::checkConsitency()
{
	try {
		//начиаем с головы, если пройденых эл-в меньше / больше длины вектора, то возвращаем false
		int realLen = 0;
		//////////////////////////////////////////////////////////////////////////////////////////
		auto top = _array.data();
		while (top->next != -1 && _array.size() + 100 >= realLen)
		{
			realLen++;
			top = &_array[top->next];
		}
		if (top->next == -1)
			return true;
		else {
			std::cout << "Cycle found in list!!!!!!!!\r\n";
			return false;
		}
	}
	catch (std::exception& ex) {
		std::cout << ex.what() << std::endl;
		return false;
	}
	return false;
}

int list_array::indexOfPointer(list_node_array* ptr)
{
	int number = 0;
	auto top = _array.data();
	while (top != ptr && number < _array.size())
	{
		number++;
		top = &_array.data()[number];
	}
	if (top == ptr)
		return number;
	else
		return -1;
}
