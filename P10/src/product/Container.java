package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.String;
import java.lang.Integer;

public class Container {
    public Container(String name, String desc, int maxScoops) {
        this.name = name;
        this.desc = desc;
        this.maxScoops = maxScoops;
    }
    public Container(BufferedReader br) throws IOException {
        String[] values = (br.readLine()).split(",");

        this.name      = values[0];
        this.desc      = values[1];
        this.maxScoops = Integer.parseInt(values[2]);
    }

    public String name(){
        return name; 
    }
    public String desc(){
        return desc;
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write("" + 
            name + "," + 
            desc + "," + 
            maxScoops + "\n"
        );
    }

    private String name;
    private String desc;
    private int maxScoops;
}