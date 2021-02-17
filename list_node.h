#pragma once
class list_node_chain
{
public:
	list_node_chain() { next = nullptr; }
	list_node_chain(int i) {
		value = i;
		next = nullptr;
	}
	int value;
	list_node_chain* next;
};

struct list_node_array
{
public:
	list_node_array() { next = -1; }
	list_node_array(int _value) { value = _value; next = -1; }
	int value;
	int next;
};



