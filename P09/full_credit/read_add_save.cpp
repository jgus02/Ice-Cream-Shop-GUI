#include <iostream>
#include <fstream>
#include "Matrix3.h"
#include <string>

int main(int argc, char** argv){
    if(argc < 2){
        std::string exec{argv[0]};
        throw std::runtime_error{"Invalid number of arguments. Must have at least 2 arguments: \n\t\'"
        + exec + " {OUTPUT_FILE} {INPUT_FILE}\'"};
    }

    std::string save_file{argv[1]};
    Matrix3 m_sum;

    for(int i=2; i<argc; ++i){
        std::string curr_filename{argv[i]};
        std::ifstream ist{curr_filename};
        if (ist.fail() && !ist.eof()){
            throw std::runtime_error{"Error reading input file: " + curr_filename};
        }

        Matrix3 m;
        ist >> m; 
        m_sum = m_sum + m;
    }

    std::ofstream ofs{save_file};
    if(ofs.fail()){
        throw std::runtime_error{"Error outputing matrix to file: " + save_file};
    }

    ofs << m_sum;

}
//  Iterate over program arguments argv[2] ... argv[argc-1] (which should be files
// containing a single 3x3 matrix each). Open each file and load its matrix. Finally, write the sum to the file
// specified in the first argument, argv[1], in 3x3 array form.