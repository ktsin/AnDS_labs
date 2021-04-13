#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <chrono>
#include <random>
#include <vector>
#include "record.h"
#include "sorter.h"
#include "searcher.h"

#define DATA_SIZE 10
#define PRINT
#define DEFEND_

time_t inputDate();
bool cmpFirst_Up(record& a, record& b);
void print(std::vector<record>& data);
bool cmpFirst_Down(record& a, record& b);
bool cmpSecond_Up(record& a, record& b);
bool cmpSecond_Down(record& a, record& b);
bool cmpThird_Up(record& a, record& b);
bool cmpThird_Down(record& a, record& b);
bool cmpFirst_Exact(record& a, record& b);
bool cmpSecond_Exact(record& a, record& b);
bool cmpThird_Exact(record& a, record& b);
bool cmpAll_Exact(record& a, record& b);

using namespace std::chrono;

int main()
{
    srand(rand());
    std::ios::sync_with_stdio(false);
    std::cout << "Lab 3!\n";
#ifndef DEFEND_
    std::vector<record> data;
    data.resize(DATA_SIZE);
    for (int i = 0; i < DATA_SIZE; i++)
    {
        data[i] = record();
    }
#pragma region sorting
    auto sc = score();
    auto before = steady_clock::now();
    sorter::SortByFirst(data, cmpFirst_Down, sc);
    auto after = steady_clock::now();
    std::cout << "\nSorted by 1-st field:\n\n";
#ifdef PRINT
    print(data);
#endif // !PRINT
    std::cout << "Permutations: " << sc.permutations << "; compares: " << sc.compares << "; seconds: " << (after - before).count() * 1E-9 << "\n";

    before = steady_clock::now();
    sorter::SortByTwo(data, cmpSecond_Down, sc);
    after = steady_clock::now();
    std::cout << "\nSorted by 2-nd field:\n\n";
#ifdef PRINT
    print(data);
#endif
    std::cout << "Permutations: " << sc.permutations << "; compares: " << sc.compares << "; seconds: " << (after - before).count() * 1E-9 << "\n";
    sc.reset();
    before = steady_clock::now();
    sorter::SortByFirst(data, cmpFirst_Up, sc);
    sorter::SortByTwo(data, cmpSecond_Up, sc);
    sorter::SortByThree(data, cmpThird_Up, sc);
    after = steady_clock::now();
    std::cout << "\nSorted by 3-rd field:\n\n";
#ifdef PRINT
    print(data);
#endif // PRINT
    std::cout << "Permutations: " << sc.permutations << "; compares: " << sc.compares << "; seconds: " << (after - before).count() * 1E-9 << "\n";
    //with check!
    sc.reset();
    for (int i = 0; i < DATA_SIZE; i++)
    {
        data[i] = record();
    }
    before = steady_clock::now();
    sorter::SortByFirst(data, cmpFirst_Up, sc, true);
    sorter::SortByTwo(data, cmpSecond_Up, sc, true);
    sorter::SortByThree(data, cmpThird_Up, sc, true);
    after = steady_clock::now();
    std::cout << "\nSorted by 3-rd field with checking:\n\n";
#ifdef PRINT
    print(data);
#endif // PRINT
    std::cout << "Permutations: " << sc.permutations << "; compares: " << sc.compares << "; seconds: " << (after - before).count() * 1E-9 << "\n";

#pragma endregion
#pragma region search
    cout << "\tEnter first date: \n";
    time_t firstKey = inputDate();
    record tmp;
    tmp.field_1 = firstKey;
    int result = searcher::barierSearch(data, cmpFirst_Exact, tmp);
    if (result > 0)
        cout << data[result].to_string() << '\n';
    else
        cout << "not found!" << '\n';

    cout << "\tEnter second date: \n";
    time_t secondKey = inputDate();
    tmp.field_2 = secondKey;
    result = searcher::barierSearch(data, cmpSecond_Exact, tmp);
    if (result > 0)
        cout << data[result].to_string() << '\n';
    else
        cout << "not found!\n";

    cout << "\tEnter thrid key: \n";
    string thirdKey;
    cin >> thirdKey;
    thirdKey = thirdKey.substr(0, 5);
    tmp.field_3 = &thirdKey;
    result = searcher::barierSearch(data, cmpThird_Exact, tmp);
    if (result > 0)
        cout << data[result].to_string() << '\n';
    else
        cout << "not found!" << '\n';

    cout << "\t Exact search: \n";
    result = searcher::barierSearch(data, cmpAll_Exact, tmp);
    if (result > 0)
        cout << data[result].to_string() << '\n';
    else
        cout << "not found!" << '\n';
#pragma endregion

#endif // !DEFEND
#ifdef DEFEND_
    std::vector<drecord> data;
    data.resize(DATA_SIZE);
    for (int i = 0; i < DATA_SIZE; i++)
    {
        data[i] = drecord();
    }

#endif // DEFEND_

    
    }

time_t inputDate() {
    cout << "Enter day: ";
    int day(0);
    cin >> day;
    cout << "Enter month: ";
    int month(0);
    cin >> month;
    cout << "Enter year: ";
    int year(0);
    cin >> year;
    tm* dateStructure = new tm();
    dateStructure->tm_year = year - 1900;
    dateStructure->tm_mon = month - 1;
    dateStructure->tm_mday = day;
    return mktime(dateStructure);
}

void print(std::vector<record>& data)
{
    for (int i = 0; i < DATA_SIZE; i++)
    {
        std::cout << data[i].to_string() << "\r\n";
    }
}

bool cmpFirst_Up(record& a, record& b) {
    return a.field_1 >= b.field_1;
}

bool cmpFirst_Down(record& a, record& b) {
    return a.field_1 <= b.field_1;
}

bool cmpSecond_Up(record& a, record& b) {
    return a.field_2 > b.field_2;
}

bool cmpSecond_Down(record& a, record& b) {
    return a.field_2 < b.field_2;
}

bool cmpThird_Up(record& a, record& b) {
    return a.field_3->compare(*b.field_3) >= 0;
}

bool cmpThird_Down(record& a, record& b) {
    return a.field_3->compare(*b.field_3) <= 0;
}

bool cmpFirst_Exact(record& a, record& b) {
    return a.field_1 == b.field_1;
}

bool cmpSecond_Exact(record& a, record& b) {
    return a.field_2 == b.field_2;
}

bool cmpThird_Exact(record& a, record& b) {
    return a.field_3->compare(*b.field_3) == 0;
}

bool cmpAll_Exact(record& a, record& b) {
    return a.field_3->compare(*b.field_3) == 0 
        && a.field_2 == b.field_2 
        && a.field_1 == b.field_1;
}
