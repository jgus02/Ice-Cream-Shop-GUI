#include "Matrix3.h"
#include <algorithm>
#include <iostream>
#include <sstream>
#include <string>

void io_test(Matrix3* m, std::string in_str, int in_arr[3][3]);
void out_test(Matrix3 m, Matrix3 m_expected, std::string in_str);

int err_count = 0; //global variable, it's a tiny regression
                  //test I am not trying to write efficient code!!

int main(int argc, char** argv){
    Matrix3 m_in1;
    std::string in_str{""};
    int in_arr1[3][3];

    //store user input in arr and str and compare created matrixes against eachother
    std::cout << "Enter 9 whitespace-separated integers: " << std::endl;
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            std::cin >> in_arr1[i][j];
            in_str.append("" + std::to_string(in_arr1[i][j]));
            in_str.append(" ");
        }
    }
    io_test(&m_in1,in_str,in_arr1);

    //2nd arr
    in_str = "";
    Matrix3 m_in2;
    int in_arr2[3][3];
    

    std::cout <<"Enter 9 whitespace-separated integers: " << std::endl;
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            std::cin >> in_arr2[i][j];
            in_str.append("" + std::to_string(in_arr2[i][j]));
            in_str.append(" ");
        }
    }
    io_test(&m_in2,in_str,in_arr2);

    //sum test arr 1 & 2 against arrays from input
    std::cout << "Sum of input 1 + input 2: " << std::endl;
    Matrix3 m_sumtest_actual = (m_in1 + m_in2);
    int exp_sum_arr[3][3];
    int sum_err = 0;

    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            exp_sum_arr[i][j] = in_arr1[i][j] + in_arr2[i][j];
            if(m_sumtest_actual.get(i,j) != exp_sum_arr[i][j]){
                ++sum_err;
            }
        }
    }

    std::cout << m_sumtest_actual << std::endl;

    if(sum_err){
        Matrix3 m_exp_sum{exp_sum_arr};
        err_count += sum_err;
        std::cerr << "Error: " << sum_err << " summed indices do not match expected. should be: \n"
        << m_exp_sum << std::endl;
    }
    
    /*std::cout << "\n\nIntentionally broken test, expected 3 errors: " << std::endl;
    in_str = "-1 42 -22\n2 2 2\n2 2 2 ";
    io_test(&m_in2,in_str,in_arr1);
    Matrix3 m_broken{-3,-3,3,1,-1,1,4,4,4};
    out_test((m_broken + m_in1), m_in1, in_str);

    std::cout << "Sum of broken matrix + input 2: " << std::endl;
    Matrix3 m_sumtest_broken = (m_broken + m_in1);
    int brk_sum_arr[3][3] = {0};
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            brk_sum_arr[i][j] = 5 + in_arr2[i][j];
            if(m_sumtest_broken.get(i,j) != brk_sum_arr[i][j]){
                ++sum_err;
            }
        }
    }
    std::cout << m_sumtest_broken << std::endl;

    if(sum_err){
        Matrix3 m_exp_sum{exp_sum_arr};
        err_count += sum_err;
        std::cerr << "Error: " << sum_err << " summed indices do not match expected. should be: \n"
        << m_exp_sum << std::endl;
    }*/


    

    return err_count;
}/*Read two matrices from standard in_str (std::cin) and print the sum to the
console (std::cout).*/

void io_test(Matrix3* m, std::string in_str, int in_arr[3][3]){
    std::istringstream iss{in_str};
    iss >> *m;
    Matrix3 m_expected{in_arr};
    if(!(*m == m_expected)){
        std::cerr << "Error: Matrix from user input does not match expected, should be: \n" 
        << m_expected 
        << "is: " << std::endl;
        ++err_count;
    }
    else{
       std::cout << "Created: " << std::endl;
    }
    std::cout << *m;

    out_test(*m,m_expected,in_str);
}

//no output unless unexpected behavior OR unless called individually and given incorrect input;
//input from io_test is CORRECT so it will not break unless class is broken
void out_test(Matrix3 m, Matrix3 m_expected, std::string in_str){
    std::ostringstream oss;
    oss << m;

    std::string expected_str{in_str};
    std::string actual_str{oss.str()};
    expected_str.erase(std::remove_if(expected_str.begin(), expected_str.end(), isspace), expected_str.end());
    actual_str.erase(std::remove_if(actual_str.begin(), actual_str.end(), isspace), actual_str.end());

    if(expected_str != actual_str){
        std::cerr << "Error: Matrix output from stream does not match expected, should be: \n" 
        << m_expected
        << "is: \n"
        << m;
        ++err_count;
    }
}

