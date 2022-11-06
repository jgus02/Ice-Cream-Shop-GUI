#include "Matrix3.h"
#include <iostream>

int main(){
    Matrix3 empty;
    int tot_err_count = 0;
    int err_curr_task = 0;

    //EMPTY MATRIX
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            if (empty.get(i,j) != 0){
                ++err_curr_task;
            }
        }
    }
    if(err_curr_task){
        tot_err_count+=err_curr_task;
        std::cerr << "Error in creation of empty matrix: " << err_curr_task << " cells contain unexpected values.\n";
    }
    std::cout << "Empty matrix: \n" << empty << std::endl;

    //MATRIX 1 (from 9 ints)
    err_curr_task = 0;
    Matrix3 m1{0,1,2,1,2,3,2,3,4};
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            if (m1.get(i,j) != (i+j)){
                ++err_curr_task;
            }
        }
    }
    if(err_curr_task){
        tot_err_count+=err_curr_task;
        std::cerr << "Error in creation of matrix 1: " << err_curr_task << " cells contain unexpected values.\n";
    }
    std::cout << "Matrix 1 (from 9 ints): \n" << m1 << std::endl;
    
    //MATRIX 2 (from int[3][3] because it enables me to be lazy later)
    err_curr_task = 0;
    int arr[3][3] = {{0,-1,-2},{-1,-2,-3},{-2,-3,-4}};
    Matrix3 m2{arr};
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            if (m2.get(i,j) != (-i-j)){
                ++err_curr_task;
            }
        }
    }

    if(err_curr_task){
        tot_err_count+=err_curr_task;
        std::cerr << "Error in creation of matrix 2: " << err_curr_task << " cells contain unexpected values.\n";
    }
    std::cout << "Matrix 2 (from int** array): \n" << m2 << std::endl;

    if(!((m1 + m2) == empty)){
        std::cerr << "Error in addition of two matrices: \n";
        ++tot_err_count;
    }
    std::cout << m1 << " + \n" << m2 << " = \n" << (m1 + m2) << std::endl;

    return tot_err_count;
}
