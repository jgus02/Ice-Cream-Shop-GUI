package person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.lang.Integer;
import java.util.Map;

public class Person{
    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    //@Override
    public boolean equals(Person rhs){
        if((name.compareTo(rhs.name) == 0) && (phone.compareTo(rhs.phone) == 0)){
            return true;
        }
        return false;
    }
    @Override
    public boolean equals(Object oRhs) {
        if(oRhs instanceof Person){
            Person rhs = (Person)oRhs;
            if((name.compareTo(rhs.name) == 0) && (phone.compareTo(rhs.phone) == 0)) return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return ("" + name + phone).hashCode();
    }

    public String name(){
        return name;
    }
    public String phone(){
        return phone;
    }
    @Override
    public String toString(){
        return (name + " - " + phone);
    }

    public Person(BufferedReader br) throws IOException {
        String[] values = (br.readLine()).split(",");

        this.name      = values[0];
        this.phone      = values[1];
    }
    
    public void save(BufferedWriter bw) throws IOException {
        bw.write("" + 
            name + "," + 
            phone + '\n'
        );
    }

    protected String name;
    protected String phone;
}