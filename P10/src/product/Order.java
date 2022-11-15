package product;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;;
import java.io.IOException;
import java.lang.Integer;

public class Order {
    public Order(BufferedReader br) throws IOException {
        for(int i=0;i<Integer.parseInt(br.readLine()); ++i){
            addServing(new Serving(br));
        }
    }

    public void save(BufferedWriter bw) throws IOException{
        bw.write("" + servings.size() + '\n');
        for(Serving serv : servings){
            serv.save(bw);
        }
    }

    public void addServing(Serving serv){
        servings.add(serv);
    }

    @Override
    public String toString(){ 
        StringBuilder servList = new StringBuilder();
        for(Serving serv : servings){
            servList.append(serv.toString() + '\n');
        }
        return servList.toString();
    }
    private ArrayList<Serving> servings = new ArrayList<>();
}
