#include "searcher.h"

int searcher::barierSearch(vector<record>& data, function<bool(record&, record&)> comparer, record& target)
{
	record rec = data.back();

	int i = 0;
	if (comparer(rec, target)) {
		return data.size() - 1;
	}
	(*data.rbegin()) = target;
	while (!comparer(data[i], target)) {
		i++;
	}
	(*data.rbegin()) = rec;
	if (i == data.size() - 1) {
		return -1;
	}
	else {
		return i;
	}
}
