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
	if (!comparer(rec, target)) {
		(*data.rbegin()) = rec;
		return -1;
	}
	else {
		(*data.rbegin()) = rec;
		return i;
	}
}
