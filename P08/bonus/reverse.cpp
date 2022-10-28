#include <string>
#include <algorithm>
#include <iostream>
using std::string;

int main(int argc, char** argv){
   // std::vector<std::string> PLEASE(argv, argv + argc);
    string tmp;
    for(int i = 1; i <= (argc - 1); i++){
        tmp = argv[i];
        std::reverse(tmp.begin(), tmp.end());
        std::cout << tmp << std::endl;
    }
}
