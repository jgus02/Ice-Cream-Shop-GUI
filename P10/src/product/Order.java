package product;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;;
import java.io.IOException;
import java.lang.Integer;
import person.Customer;

public class Order {
    public Order(Customer customer){
        this.customer = customer;
    }   
    public Order(BufferedReader br) throws IOException {
        for(int i=0;i<Integer.parseInt(br.readLine()); ++i){
            addServing(new Serving(br));
        }
    }

    public Customer getCustomer(){
        return customer;
    }

    public int price(){
        int price = 0;
        for(Serving serv : servings){
            price += serv.price();
        }
        return price;
    }

    public void addServing(Serving serv){
        servings.add(serv);
    }
    
    public Object[] servings(){
        return servings.toArray();
    }

    @Override
    public String toString(){ 
        StringBuilder servString = new StringBuilder();
        for(Serving serv : servings){
            servString.append(serv.toString() + '\n');
        }
        return "$"+price()+" - "+servString.toString();
    }

    public void save(BufferedWriter bw) throws IOException{
        bw.write("" + servings.size() + '\n');
        for(Serving serv : servings){
            serv.save(bw);
        }
    }

    private Customer customer;
    private ArrayList<Serving> servings = new ArrayList<>();
}
