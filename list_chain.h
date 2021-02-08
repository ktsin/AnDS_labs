#pragma once
#include "basic_list.h"
#include "list_node.h"

class list_chain :
    public basic_list<list_node_chain>
{
public:
    virtual void append(list_node_chain element) override;
	virtual void insert(list_node_chain element, int position_after) override;
	virtual list_node_chain	 getByNumber(int position) override;
	virtual void swap(list_node_chain left, list_node_chain right) override;
	virtual void remove(int position) override;
	virtual int length() override;
};

