package product;

import java.io.BufferedReader;
import java.io.IOException;

public class IceCreamFlavor extends Item{
    public IceCreamFlavor(String name, String description, int cost, int price){
        super(name,description,cost,price);
    }
    public IceCreamFlavor(BufferedReader br) throws IOException {
        super(br);
    }
}