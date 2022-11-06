#ifndef MATRIX_3_H_
#define MATRIX_3_H_
#include <vector>
#include <istream>
#include <ostream>

class Matrix3 {
        friend std::ostream& operator<<(std::ostream& out, Matrix3 m);
        friend std::istream& operator>>(std::istream& in, Matrix3& m);
    public:
        Matrix3();
        Matrix3(int m00, int m10, int m20,
                int m01, int m11, int m21, 
                int m02, int m12, int m22);
        Matrix3(int a[3][3]);

        int get(int x, int y);

        Matrix3 operator+(Matrix3 rhs);
        bool operator==(Matrix3 rhs);



    private:
        int data[3][3];
        //std::vector<std::vector<int>> data;
};

#endif