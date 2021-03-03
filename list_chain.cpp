#include "list_chain.h"

list_chain::list_chain(std::list<list_node_chain*> nodes)
{
	this->head = nodes.front();
	this->_length = nodes.size();
}

void list_chain::append(list_node_chain* element)
{
	if (head == nullptr)
		head = element;
	else {
		list_node_chain* top = head;
		while (top->next != nullptr)
			top = top->next;
		top->next = element;
	}
	_length++;
}

void list_chain::insert(list_node_chain* element, int position_after)
{
	list_node_chain* top = getByNumber(position_after);
	if (top == nullptr) {
		append(element);
		_length++;
		return;
	}
	else {
		auto tmp = top->next;
		top->next = element;
		element->next = tmp;
		_length++;
		return;
	}
}

list_node_chain* list_chain::getByNumber(int position)
{
	if (position < 0 || position >= _length)
		return nullptr;
	else {
		int i = 0;
		list_node_chain* top = head;
		while (i != position && top != nullptr)
		{
			top = top->next;
			i++;
		}
		return top;
	}
}

void list_chain::swap(list_node_chain* left, list_node_chain* right)
{
	if (left == right)
		return;
	auto* preLeft = head, * preRight = head, *top = head;
	while (top != left) {
		preLeft = top;
		top = top->next;
	}
	top = head;
	while (top!=right)
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

void list_chain::remove(int position)
{
	list_node_chain* top = getByNumber(position);
	if (top == nullptr)
		return;
	else
	{
		//get previous element
		if (position == 0) {
			head = head->next;
			_length--;
			return;
		}
		else
		{
			list_node_chain* top_previous = getByNumber(position - 1);
			top_previous->next = top->next;
			_length--;
			delete top;
			return;
		}
	}

}

void list_chain::sort()
{
	for (int i(0); i < length(); i++) {
		for (int j(1); j < length(); j++) {
			if (getByNumber(j)->value < getByNumber(j - 1)->value) {
				swap(getByNumber(j - 1), getByNumber(j));
			}
		}
	}
}

void list_chain::task1()
{
	//повторяющиеся переместить в конец
	int len = length();
	for (int i(0); i < len - 1; i++) {
		//ищем элемент в массиве. Если его больше 1-го, то все найденые
		//закидываем в конец, i = 0, len -= count(найденые эл-ы)
		bool flag = false;
		for (int j = i + 1; j < len; j++) {
			if (getByNumber(i)->value == getByNumber(j)->value) {
				this->swap(getByNumber(j), getByNumber(len-1));
				--len;
				flag = true;
			}
		}
		if (flag) {
			this->swap(getByNumber(i), getByNumber(len - 1));
			len--;
		}
	}
}

std::string list_chain::to_string()
{
	list_node_chain *top = head;
	std::string result = "";
	for(int i(0); i < length(); i++)
	{
		result += std::to_string(top->value) + " -> ";
		top = top->next;
	}
	return result + "NULL";
}
