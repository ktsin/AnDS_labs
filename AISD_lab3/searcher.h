#pragma once
#include <functional>
#include <vector>
#include "record.h"

using namespace std;

class searcher
{
public:
	static int barierSearch(vector<record>& data, function<bool(record&, record&)> comparer, record& target);
};

