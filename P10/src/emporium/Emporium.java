 package emporium;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.Order;
import product.Container;
import product.Serving;

import person.Customer;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.Integer;


public class Emporium{
    public Emporium(){}

    public Emporium(BufferedReader br) throws IOException{
        int arrSize = Integer.parseInt(br.readLine());
        int i;
        for(i = 0;i < arrSize; i++){
            icf.add(new IceCreamFlavor(br));
        }
        arrSize = Integer.parseInt(br.readLine());
        for(i = 0;i < arrSize; i++){
            mxf.add(new MixInFlavor(br));
        }
        arrSize = Integer.parseInt(br.readLine());
        for(i = 0;i < arrSize; i++){
            orders.add(new Order(br));
        }
        arrSize = Integer.parseInt(br.readLine());
        for(i = 0;i < arrSize; i++){
            containers.add(new Container(br));
        }
        arrSize = Integer.parseInt(br.readLine());
        for(i = 0;i < arrSize; i++){
            customers.add(new Customer(br));
        }
        // arrSize = Integer.parseInt(br.readLine());
        // for(i = 0;i < arrSize; i++){
        //     favoriteServings.add(new     (br));
        // }
    }

    public void save(BufferedWriter bw) throws IOException{
        bw.write("" + icf.size() + "\n");
        for(IceCreamFlavor flavor:icf){
            flavor.save(bw);
        }
        bw.write("" + mxf.size() + '\n');
        for(MixInFlavor flavor : mxf){
            flavor.save(bw);
        }
        bw.write("" + orders.size() + '\n');
        for(Order order : orders){
            order.save(bw);
        }
        bw.write("" + containers.size() + '\n');
        for(Container container : containers){
            container.save(bw);
        }
        bw.write("" + customers.size() + '\n');
        for(Customer customer : customers){
            customer.save(bw);
        }
    }
    
    public void addIcf(IceCreamFlavor flavor){
        icf.add(flavor);
    }
    public void addMxf(MixInFlavor flavor){
        mxf.add(flavor);
    }
    public void addOrder(Order order){
        orders.add(order);
        Customer customer = order.getCustomer();
        for(Object serv : order.servings()){
            favoriteServings.put(customer, (Serving)serv);
        }
    }
    public void addCont(Container container){
        containers.add(container);
    }
    public void addCust(Customer customer){
        customers.add(customer);
    }
    
    public Object[] icf(){
        return icf.toArray();
    }
    public Object[] mxf(){
        return mxf.toArray();
    }
    public Object[] order(){
        return orders.toArray();
    }
    public Object[] cont(){
        return containers.toArray();
    }
    public Object[] cust(){
        return customers.toArray();
    }
    public Object[] favoriteServings(Customer customer){
        return favoriteServings.get(customer);
    }

    private MultiMap<Customer,Serving> favoriteServings = new MultiMap<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<IceCreamFlavor> icf = new ArrayList<>();
    private ArrayList<MixInFlavor> mxf = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Container> containers = new ArrayList<>();
}