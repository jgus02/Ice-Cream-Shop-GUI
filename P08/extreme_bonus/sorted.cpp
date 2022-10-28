#include <vector>
#include <string>
#include <algorithm>
#include <iostream>

using std::string;

int main(int argc, char** argv){
    string tmp;
    std::vector<string> in;

    while(std::getline(std::cin, tmp)){ //CTRL-D
        in.push_back(tmp);
    }
    std::cout << std::endl;
    std::sort(in.begin(), in.end());

    for(string &out : in){
        std::cout << out << std::endl;
    }
    
}