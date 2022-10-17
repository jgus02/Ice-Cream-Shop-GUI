 
package emporium;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.Scoop;
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
            iceCreamFlavors.add(new IceCreamFlavor(br));
        }

        arrSize = Integer.parseInt(br.readLine());
        for(i = 0;i < arrSize; i++){
            mixInFlavors.add(new MixInFlavor(br));
        }

        arrSize = Integer.parseInt(br.readLine());
        for(i = 0;i < arrSize; i++){
            scoops.add(new Scoop(br));
        }
    }

    public void save(BufferedWriter bw) throws IOException{
        bw.write("" + iceCreamFlavors.size() + "\n");
        for(IceCreamFlavor flavor:iceCreamFlavors){
            flavor.save(bw);
        }
        
        bw.write("" + mixInFlavors.size() + '\n');
        for(MixInFlavor flavor : mixInFlavors){
            flavor.save(bw);
        }

        bw.write("" + scoops.size() + '\n');
        for(Scoop scoop : scoops){
            scoop.save(bw);
        }


    }
    
    public void addIceCreamFlavor(IceCreamFlavor flavor){
        iceCreamFlavors.add(flavor);
    }
    public void addMixInFlavor(MixInFlavor flavor){
        mixInFlavors.add(flavor);
    }
    public void addScoop(Scoop scoop){
        scoops.add(scoop);
    }
    
    public Object[] iceCreamFlavors(){
        return iceCreamFlavors.toArray();
    }
    public Object[] mixInFlavors(){
        return mixInFlavors.toArray();
    }
    public Object[] scoops(){
        return scoops.toArray();
    }

    private ArrayList<IceCreamFlavor> iceCreamFlavors = new ArrayList<>();
    private ArrayList<MixInFlavor> mixInFlavors = new ArrayList<>();
    private ArrayList<Scoop> scoops = new ArrayList<>();
}