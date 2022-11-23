package product;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;;
import java.io.IOException;
import java.lang.Integer;

public class Serving{
    public Serving(Container container){
        this.container = container;   
    } 

    public int price(){
        int price = 0;
        for(Scoop s : scoops){
            price += s.price();
        }
        for(MixIn top : toppings){
            price += top.price();
        }
        return price;
    }
    
    public Container container(){
        return container;
    }

    public void addTopping(MixIn top){
        toppings.add(top);
    }

    public void addScoop(Scoop scp){
        scoops.add(scp);
    }

    public int numScoops(){
        return scoops.size();
    }

                        //one scoop: CONTAINER with a scoop of
                             // SCOOP with SCOOP MIX-INS topped with TOPPINGS
    @Override           //many scoops: CONTAINER with scoops of SCOOP with MIX-INS, ...                           
    public String toString(){//..., and SCOOP with MIXINS topped with TOPPINGS 
        StringBuilder contents = new StringBuilder(container.toString());

        switch (scoops.size()){
            case 0:
                break;
            case 1:
                contents.append(" with a scoop of " + scoops.get(0).toString());
                break;
            default:
                int i;
                contents.append(" with scoops of ");
                for(i = 0; i<(scoops.size() - 1);++i) {
                    contents.append(scoops.get(i).toString() + ", ");
                }
                contents.append("and " + scoops.get(i).toString());
        }

        switch (toppings.size()){
            case 0:
                break;
            case 1:
                contents.append(" topped with " + scoops.get(0).toString());
                break;
            default:
                int i;
                contents.append(" topped with ");
                for(i = 0; i < (toppings.size() - 1);++i) {
                    contents.append(toppings.get(i).toString() + ", ");
                }
                contents.append("and " + (toppings.get(i)).toString());
        }

        return contents.toString(); 
    }

    public Serving(BufferedReader br) throws IOException {
        this.container = new Container(br);
        for(int i=0;i<Integer.parseInt(br.readLine()); ++i){ //load mix-ins as 'toppings'
            addTopping(new MixIn(br));
        }
        for(int i=0;i<Integer.parseInt(br.readLine()); ++i){ //load scoops
            addScoop(new Scoop(br));
        }
    }

    public void save(BufferedWriter bw) throws IOException{
        container.save(bw);
        bw.write("" + toppings.size() + '\n');
        for(MixIn top : toppings){
            top.save(bw);
        }
        bw.write("" + scoops.size() + '\n');
        for(Scoop scp : scoops){
            scp.save(bw);
        }
    }

    private Container container;
    private ArrayList<MixIn> toppings = new ArrayList<MixIn>();
    private ArrayList<Scoop> scoops = new ArrayList<Scoop>();
}
