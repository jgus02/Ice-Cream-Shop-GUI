package person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.lang.Integer;

public class Customer extends Person{
    public Customer(String name, String phone){
        super(name,phone);
    }

    public Customer(BufferedReader br) throws IOException {
        super(br);
    }   
}