#include <iostream>
#include "taskHost.h"
#include "list_array.h"
#include "list_chain.h"
#include "list_node.h"
#include "basic_list.h"


int main()
{
    srand(rand());
    std::cout << "Hello World!\n";
    std::cout << "Enter size of array: ";
    int tempInt = 0;
    std::cin >> tempInt;
    list_array* array = new list_array();
    list_chain* chain = new list_chain();
    for (int i(0); i < tempInt; i++) {
        array->append(new list_node_array(rand() % 12));
        chain->append(new list_node_chain(rand() % 12));
    }
    std::cout << "Array and list: " << std::endl;
    std::cout << "Array: " << array->to_string() << std::endl;
    std::cout << "List: " << chain->to_string() << std::endl;

    std::cout << std::endl << "==== \t Appending \t ====" << std::endl;

    std::cout << "Enter value to append: ";
    std::cin >> tempInt;

    array->append(new list_node_array(tempInt));
    chain->append(new list_node_chain(tempInt));

    std::cout << "Array and list: " << std::endl;
    std::cout << "Array: " << array->to_string() << std::endl;
    std::cout << "List: " << chain->to_string() << std::endl;

    std::cout << std::endl << "==== \t Delete at position \t ====" << std::endl;

    std::cout << "Enter index: ";
    std::cin >> tempInt;

    array->remove(tempInt);
    chain->remove(tempInt);

    std::cout << "Array and list: " << std::endl;
    std::cout << "Array: " << array->to_string() << std::endl;
    std::cout << "List: " << chain->to_string() << std::endl;

    std::cout << std::endl << "==== \t Insert after \t ====" << std::endl;

    std::cout << "Enter index: ";
    std::cin >> tempInt;

    int value = 0;
    std::cout << "Enter value: ";
    std::cin >> value;

    array->insert(new list_node_array(value) , tempInt);
    chain->insert(new list_node_chain(value), tempInt);

    std::cout << "Array and list: " << std::endl;
    std::cout << "Array: " << array->to_string() << std::endl;
    std::cout << "List: " << chain->to_string() << std::endl;

    std::cout << std::endl << "==== \t Sorting \t ====" << std::endl;

    array->sort();
    chain->sort();

    std::cout << "Array and list: " << std::endl;
    std::cout << "Array: " << array->to_string() << std::endl;
    std::cout << "List: " << chain->to_string() << std::endl;
}