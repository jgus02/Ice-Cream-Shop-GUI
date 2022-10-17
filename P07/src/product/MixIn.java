package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.String;
import java.lang.Integer;

public class MixIn{
    private MixInFlavor flavor;
    private MixInAmount amount;

    public MixIn(MixInFlavor flavor, MixInAmount amount){
        this.flavor = flavor;
        this.amount = amount;
    }

    public MixIn(BufferedReader br) throws IOException {
        this.flavor = new MixInFlavor(br);
        this.amount = MixInAmount.values()[Integer.parseInt(br.readLine())];
    }

    public void save(BufferedWriter bw) throws IOException{
        flavor.save(bw);
        bw.write("" + amount.ordinal() + "\n");
    }
    
    @Override
    public String toString(){
        if(amount==MixInAmount.Normal){
            return flavor.name();
        }
        return flavor.name() + " (" + amount.name() + ")";
    }
}