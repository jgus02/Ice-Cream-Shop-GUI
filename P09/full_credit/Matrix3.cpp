#include "Matrix3.h"
#include <iomanip>
#include <iostream>
#include <istream>
#include <ostream>
#include <vector>

Matrix3::Matrix3() : data{{0,0,0},{0,0,0},{0,0,0}} {
}

Matrix3::Matrix3(int m00,int m10,int m20,
                 int m01,int m11,int m21, 
                 int m02,int m12,int m22) 
                : data{{m00,m10,m20},{m01,m11,m21},{m02,m12,m22}} {
}

Matrix3::Matrix3(int a[3][3]){
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            data[i][j] = a[i][j];
        }
    }
}


int Matrix3::get(int x, int y){
    if(x > 2 || x < 0  ||  y > 2 || y < 0){
        throw std::runtime_error{"Invalid index; must be in range 0-2."};
    }
    return Matrix3::data[x][y];
}

Matrix3 Matrix3::operator+(Matrix3 rhs){
    Matrix3 m{*this};
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            m.data[i][j] += rhs.get(i,j);
        }
    }
    return m;
}
bool Matrix3::operator==(Matrix3 rhs){
    Matrix3 m{*this};
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            if(m.data[i][j] != rhs.get(i,j)){
                return false;
            }
        }
    }
    return true;
}
std::ostream& operator<<(std::ostream& out, Matrix3 m){
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            out << std::setw(3) << m.data[i][j];
            out << " ";
        }
        out << std::endl;
    }
    return out;
}   
std::istream& operator>>(std::istream& in, Matrix3& m){
    int tmp;
    for(int i=0;i<3;++i){
        for(int j=0;j<3;++j){
            in >> m.data[i][j];
        }
    }
    return in;
}