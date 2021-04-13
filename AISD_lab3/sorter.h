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

class dsorter
{
public:
	static void BubbleSort(std::vector<drecord>& array, std::function<bool(drecord&, drecord&)> cmp, score& sc, bool check = false);
	static void BubbleSortBy2(std::vector<drecord>& array, std::function<bool(drecord&, drecord&)> cmp, score& sc, bool check = false);
	static bool isSorted(std::vector<drecord>& array, std::function<bool(drecord&, drecord&)> cmp, score& sc);
	static bool isSorted(std::vector<drecord>& array, std::function<bool(drecord&, drecord&)> cmp, score& sc, int start, int last);
};
