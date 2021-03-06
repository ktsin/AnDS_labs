#pragma once
#include "basic_list.h"
#include "list_node.h"
#include <list>

class list_chain :
    public basic_list<list_node_chain>
{
public:
	list_chain() {};
	list_chain(std::list<list_node_chain*> nodes);
    virtual void append(list_node_chain* element) override;
	virtual void insert(list_node_chain* element, int position_after) override;
	virtual list_node_chain* getByNumber(int position) override;
	virtual void swap(list_node_chain* left, list_node_chain* right) override;
	virtual void remove(int position) override;
	virtual int  length() override { return this->_length; }
	virtual void sort() override;
	virtual void task1() override;
	virtual std::string to_string() override;
	virtual void defence() override;
private:
	list_node_chain* head = nullptr;
	int _length = 0;
};

