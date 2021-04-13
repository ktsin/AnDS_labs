#include "sorter.h"

void sorter::SortByFirst(std::vector<record>& array, std::function<bool(record&, record&)> cmp, score& sc, bool check) {
	//шейкерная сортировка
	int control = array.size() - 1;
	int left = 0;
	int right = array.size() - 1;
	do {
		for (int i = left; i < right; i++) {
			sc.compares++;
			if (cmp(array[i], array[i + 1])) {
				std::swap(array[i], array[i + 1]);
				sc.permutations++;
				control = i;
			}
		}
		right = control;
		for (int i = right; i > left; i--) {
			sc.compares++;
			if (!cmp(array[i], array[i - 1])) {
				sc.permutations++;
				std::swap(array[i], array[i - 1]);
				control = i;
			}
		}
		left = control;
		if (check && sorter::isSorted(array, cmp, sc))
			break;
	} while (left < right);
}

void sorter::SortByTwo(std::vector<record>& array, std::function<bool(record&, record&)> cmp, score& sc, bool check) {
	//SortByFirst(array);
	auto element = array[0].field_1;
	int left = 0, right = 1;
	bool sortFlag = true;
	while (sortFlag && right < array.size())
	{
		while (array.size() > right && element == array[right].field_1)
		{
			sc.compares++;
			right++;
		}
		if (right < array.size())
			element = array[right].field_1;
		else
		{
			sortFlag = false;
			continue;
		}
		right--;
		int control = right;
		do {
			for (int i = left; i < right; i++) {
				sc.compares++;
				if (cmp(array[i], array[i + 1])) {
					sc.permutations++;
					std::swap(array[i], array[i + 1]);
					control = i;
				}
			}
			right = control;
			for (int i = right; i > left; i--) {
				sc.compares++;
				if (!cmp(array[i], array[i - 1])) {
					sc.permutations++;
					std::swap(array[i], array[i - 1]);
					control = i;
				}
			}
			left = control;
			if (check && sorter::isSorted(array, cmp, sc))
				break;
		} while (left < right);
		right++;
		left = right;

	}

}

void sorter::SortByThree(std::vector<record>& array, std::function<bool(record&, record&)> cmp, score& sc, bool check) {
	//SortByTwo(array);
	auto element = array[0];
	int left = 0, right = 1;
	bool sortFlag = true;
	while (right < array.size())
	{
		while (right < array.size()
			&& element.field_2 == array[right].field_2
			&& element.field_1 == array[right].field_1)
		{
			sc.compares += 2;
			right++;
		}
		if (right < array.size())
			element = array[right];
		else
		{
			sortFlag = false;
			continue;
		}
		right--;
		int control = right;
		do {
			for (int i = left; i < right; i++) {
				sc.compares++;
				if (cmp(array[i], array[i + 1])) {
					sc.permutations++;
					std::swap(array[i], array[i + 1]);
					control = i;
				}
			}
			right = control;
			for (int i = right; i > left; i--) {
				sc.compares++;
				if (!cmp(array[i], array[i - 1])) {
					sc.permutations++;
					std::swap(array[i], array[i - 1]);
					control = i;
				}
			}
			left = control;
			if (check && sorter::isSorted(array, cmp, sc))
				break;
		} while (left < right);
		right++;
		left = right;
	}
}

bool sorter::isSorted(std::vector<record>& array, std::function<bool(record&, record&)> cmp, score& sc)
{
	bool isSorted = true;
	for (int i(0); i < array.size() - 1 && isSorted; i++) {
		sc.compares++;
		if (!cmp(array[i], array[i + 1])) {
			isSorted = false;
		}
	}
	return isSorted;
}

void dsorter::BubbleSort(std::vector<drecord>& array, std::function<bool(drecord&, drecord&)> cmp, score& sc, bool check)
{
	bool isSorted = false;
	for (int i(0); i < array.size() && !isSorted; i++) {
		for (int j(1); j < array.size() && !isSorted; j++) {
			if (check)
				isSorted = dsorter::isSorted(array, cmp, sc);
			sc.compares++;
			if (!cmp(array[j], array[j - 1])) {
				sc.permutations++;
				std::swap(array[j], array[j - 1]);
			}
		}
	}
}

void dsorter::BubbleSortBy2(std::vector<drecord>& array, std::function<bool(drecord&, drecord&)> cmp, score& sc, bool check)
{
	bool isSorted = false;
	int start = 0, last = array.size() - 1;
	while (last <= array.size())
	{
		time_t element = array[last].field_1;
		start = last;
		while (array[last].field_1 == element)
		{
			last++;
			sc.compares++;
		}
		for (int i(start); i < last && !isSorted; i++) {
			for (int j(start + 1); j < last && !isSorted; j++) {
				if (check)
					isSorted = dsorter::isSorted(array, cmp, sc, start, last);
				sc.compares++;
				if (!cmp(array[j], array[j - 1])) {
					sc.permutations++;
					std::swap(array[j], array[j - 1]);
				}
			}
		}
	}
}

bool dsorter::isSorted(std::vector<drecord>& array, std::function<bool(drecord&, drecord&)> cmp, score& sc)
{
	bool isSorted = true;
	for (int i(0); i < array.size() - 1 && isSorted; i++) {
		sc.compares++;
		if (!cmp(array[i], array[i + 1])) {
			isSorted = false;
		}
	}
	return isSorted;
}

bool dsorter::isSorted(std::vector<drecord>& array, std::function<bool(drecord&, drecord&)> cmp, score& sc, int start, int last)
{
	bool isSorted = true;
	for (int i(start); i < last && isSorted; i++) {
		sc.compares++;
		if (!cmp(array[i], array[i + 1])) {
			isSorted = false;
		}
	}
	return isSorted;
}
