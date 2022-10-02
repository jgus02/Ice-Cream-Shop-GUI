package product;

public class MixIn{
    private MixInFlavor flavor;
    private MixInAmount amount;

    public MixIn(MixInFlavor flavor, MixInAmount amount){
        this.flavor = flavor;
        this.amount = amount;
    }

    @Override
    public String toString(){
        if(amount==MixInAmount.Normal){
            return flavor;
        }
        return flavor + "(" + amount + ")";
    }
}