#pragma once
#include "basic_list.h"
#include "list_node.h"

class list_array :
    public basic_list<list_node_array>
{
	virtual void append(list_node_array element) override;
	virtual void insert(list_node_array element, int position_after) override;
	virtual list_node_array	 getByNumber(int position) override;
	virtual void swap(list_node_array left, list_node_array right) override;
	virtual void remove(int position) override;
	virtual int length() override;
};

