#pragma once
#include "record.h"
#include <functional>
class sorter
{
public:
	static void SortByFirst(std::vector<record>& array, std::function<bool(record&, record&)> cmp, score& sc, bool check = false);
	static void SortByTwo(std::vector<record>& array, std::function<bool(record&, record&)> cmp, score& sc, bool check = false);
	static void SortByThree(std::vector<record>& array, std::function<bool(record&, record&)> cmp, score& sc, bool check = false);
	static bool isSorted(std::vector<record>& array, std::function<bool(record&, record&)> cmp, score& sc);
};

