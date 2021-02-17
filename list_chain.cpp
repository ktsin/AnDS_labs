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
	list_node_chain* preLeft = head, * preRight = head, *top = head;
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
		//we are in start => must change head pointer
		head = right;
		auto tmp = right->next;
		right->next = left->next;
		left->next = tmp;
		preRight->next = left;
		return;
	}
	else {
		preLeft->next = right;
		preRight->next = left;
		auto tmp = left->next;
		left->next = right->next;
		right->next = tmp;
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
	for (int i(0); i < _length; i++) {
		for (int j(1); j < _length; j++) {
			if (getByNumber(j)->value < getByNumber(j - 1)->value) {
				swap(getByNumber(j - 1), getByNumber(j));
			}
		}
	}
}

std::string list_chain::to_string()
{
	list_node_chain *top = head;
	std::string result = "";
	while (top != nullptr)
	{
		result += std::to_string(top->value) + " -> ";
	}
	return result + "NULL";
}
