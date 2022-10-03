package product;
import java.util.ArrayList;
import java.util.Scanner;

public class TestScoop{
    private static String errorFormat;

    private boolean failed(Item item, String expected){
        if((item.name()+item.description()+item.cost+item.price).equals(expected)){
            errorFormat = item.name()+item.description()+item.cost+item.price;
            return false;
        }
        return true;
    }
    private boolean failed(Scoop scoop, String expected){

    }

    public void main(String[] args){
        //MIX-IN FLAVOR
        try{    //normal
            if(failed(new MixInFlavor("name","desc",0,1),"namedesc01")){
                System.err.println("\nERROR: Valid MixInFlavor behaved unexpectedly: " + errorFormat);
            }
        }
        catch (IllegalArgumentException e){
            System.err.println("\nERROR: Valid MixInFlavor was not able to be created: " + e.getMessage());
        }

        try{    //no desc
            failed(new MixInFlavor("name","",0,0),"name00");
            System.err.println("\nERROR: Invalid MixInFlavor (no description, cost == 0, price = cost  ) was successfully created: " + errorFormat);
        }
        catch (IllegalArgumentException e){
        }

        try{    //no name, no desc, cost<0, price<cost
            failed(new MixInFlavor("","",-1,-2),"-1-2");
            System.err.println("\nERROR: Invalid MixInFlavor (no name or description, cost < 0, price < cost) was successfully created: " + errorFormat);
        }
        catch (IllegalArgumentException e){
        }

        try{    //foreign letters in name
            if(failed(new MixInFlavor("машина","машина",0,1),"машинамашина01")){
                System.err.println("\nERROR: Valid MixInFlavor behaved unexpectedly: " + errorFormat);
            }
        }
        catch (IllegalArgumentException e){
            System.err.println("\nERROR: Valid MixInFlavor was not able to be created: " + e.getMessage());
        }
        //ICE-CREAM FLAVOR
        try{    //normal
            if(failed(new IceCreamFlavor("name","desc",0,1),"namedesc01")){
                System.err.println("\nERROR: Valid IceCreamFlavor behaved unexpectedly: " + errorFormat);
            }
        }
        catch (IllegalArgumentException e){
            System.err.println("\nERROR: Valid IceCreamFlavor was not able to be created: " + e.getMessage());
        }

        try{
            Scoop testScoop = new Scoop(new IceCreamFlavor("name","desc",0,1));
        }
        catch (IllegalArgumentException e){
            System.err.println("\nERROR: Valid IceCreamFlavor was not able to be created within Scoop: " + e.getMessage());
        }
    }
}
