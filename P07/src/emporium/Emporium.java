 
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
            icf.add(new IceCreamFlavor(br));
        }

        arrSize = Integer.parseInt(br.readLine());
        for(i = 0;i < arrSize; i++){
            mxf.add(new MixInFlavor(br));
        }

        arrSize = Integer.parseInt(br.readLine());
        for(i = 0;i < arrSize; i++){
            scp.add(new Scoop(br));
        }
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

        bw.write("" + scp.size() + '\n');
        for(Scoop scoop : scp){
            scoop.save(bw);
        }


    }
    
    public void addIcf(IceCreamFlavor flavor){
        icf.add(flavor);
    }
    public void addMxf(MixInFlavor flavor){
        mxf.add(flavor);
    }
    public void addScp(Scoop scoop){
        scp.add(scoop);
    }
    
    public Object[] icf(){
        return icf.toArray();
    }
    public Object[] mxf(){
        return mxf.toArray();
    }
    public Object[] scp(){
        return scp.toArray();
    }

    private ArrayList<IceCreamFlavor> icf = new ArrayList<>();
    private ArrayList<MixInFlavor> mxf = new ArrayList<>();
    private ArrayList<Scoop> scp = new ArrayList<>();
}