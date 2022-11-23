package product;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;;
import java.io.IOException;
import java.lang.Integer;

public class Scoop{
    public Scoop(IceCreamFlavor flavor){
        this.flavor = flavor;   
    } 

    public void addMixin(MixIn mixin){
        mixins.add(mixin);
    }

    public int price(){
        int price = flavor.price();
        for(MixIn mix : mixins){
            price += mix.price();
        }
        return price;
    }

    @Override
    public String toString(){ //return "FLAVOR" or "FLAVOR with MIXIN[0]..., MIXIN[n-1], MIXIN[n]"
        if(mixins.isEmpty()){
            return flavor.name();
        }

        StringBuilder mixinList = new StringBuilder();
        for(MixIn i : mixins){
            mixinList.append(", " + i.toString());
        }
        mixinList.delete(0,1); //removes first ", " separator

        return flavor.name() +" with"+ mixinList.toString(); 
    }

    public Scoop(BufferedReader br) throws IOException {
        this.flavor = new IceCreamFlavor(br);
        int numMixins = Integer.parseInt(br.readLine());
        for(int i = 0;i < numMixins; i++){
            addMixin(new MixIn(br));
        }
    }

    public void save(BufferedWriter bw) throws IOException{
        flavor.save(bw);
        bw.write("" + mixins.size() + '\n');
        for(MixIn mix : mixins){
            mix.save(bw);
        }
    }

    private IceCreamFlavor flavor;
    private ArrayList<MixIn> mixins = new ArrayList<MixIn>();
}
