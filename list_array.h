#pragma once
#include "basic_list.h"
#include "list_node.h"
#include <vector>

class list_array :
    public basic_list<list_node_array>
{
public:
	list_array() {};
	list_array(std::vector<list_node_array> _values);
	virtual void append(list_node_array* element) override;
	virtual void insert(list_node_array* element, int position_after) override;
	virtual list_node_array* getByNumber(int position) override;
	virtual void swap(list_node_array* left, list_node_array* right) override;
	virtual void remove(int position) override;
	virtual int  length() override { return _array.size(); }
	virtual void sort() override;
	virtual std::string to_string() override;
	bool checkConsitency();
	int indexOfPointer(list_node_array* ptr);
private:
	std::vector<list_node_array> _array  = std::vector<list_node_array>();
	list_node_array* head = nullptr;
	int _length = 0;
	int headNumber = 0;
};

