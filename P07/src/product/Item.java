package product;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.lang.Integer;

public abstract class Item{
    protected String name;
    protected String description;
    protected int cost;
    protected int price;

    public Item(String name, String description, int cost, int price){
        dataValidation(name,description,cost,price);

        this.name           = name.trim();
        this.description    = description.trim();
        this.cost           = cost;
        this.price          = price;
    }

    public Item(BufferedReader br) throws IOException{
        String[] values = (br.readLine()).split(",");

        this.name           = values[0];
        this.description    = values[1];
        this.cost           = Integer.parseInt(values[3]);
        this.price          = Integer.parseInt(values[4]);
    }

    public void save(BufferedWriter bw) throws IOException{
        bw.write("" + 
                name + "," + 
                description + "," + 
                cost + "," + 
                price + '\n'
        );
    }

    private void dataValidation(String name, String description, int cost, int price){
        if(name.isEmpty()){
            throw new IllegalIceCreamException("Field \"name\" cannot be empty.\n");
        }
        if(description.isEmpty()){
            throw new IllegalIceCreamException("Field \"description\" cannot be empty.\n");
        }
        if(cost < 0){
            throw new IllegalIceCreamException("Cost cannot be negative.\n");
        }
        if((price < cost)){
            throw new IllegalIceCreamException("Price cannot be less than cost.\n");
        }
    }

    public String name(){
        return name; 
    }

    public String description(){
        return description;
    }

    public int price(){
        return price;
    }

    public int cost(){
        return cost;
    }

    @Override
    public String toString(){
        return name;
    }

    public class IllegalIceCreamException extends IllegalArgumentException{
        public IllegalIceCreamException(String message){
            super(message);
        }
    }

}
