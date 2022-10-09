package product;
import java.util.ArrayList;

public class Scoop{
    private IceCreamFlavor flavor;
    private ArrayList<MixIn> mixins; //arraylist or array?

    public Scoop(IceCreamFlavor flavor){
        this.flavor = flavor;
        mixins = new ArrayList<MixIn>();
    } 

    public void addMixin(MixIn mixin){
        mixins.add(mixin);
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
        mixinList.delete(0,1); //remove first ", " separator

        return flavor.name() +" with"+ mixinList.toString(); //TODO: Remove extra space at the 
    }                                                       //start of mixinList
}
