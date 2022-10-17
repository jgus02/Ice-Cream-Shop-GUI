import java.util.ArrayList;
import java.util.Scanner;
import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Item;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;

public class TestScoop{
    private static String errorFormat;

    private static boolean failed(Item item, String expected){
        if((item.name()+item.description()+item.cost()+item.price()).equals(expected)){
            errorFormat = item.name()+item.description()+item.cost()+item.price();
            return false;
        }
        return true;
    }
    private static boolean failed(Scoop scoop, String expected){
        if((scoop.toString()).equals(expected)){
            errorFormat = scoop.toString();
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        //MIX-IN FLAVOR

        try{    //normal
            if(failed(new MixInFlavor("name","desc",0,1),"namedesc01")){
                System.err.println("\nERROR: Valid MixInFlavor behaved unexpectedly, returned: " + errorFormat);
            }
        }
        catch (Exception e){
            System.err.println("\nERROR: Valid MixInFlavor was not able to be created: " + e.getClass() + " = " + e.getMessage());
        }

        try{    //no desc
            failed(new MixInFlavor("name","",0,1),"name01");
            System.err.println("\nERROR: Invalid MixInFlavor (no description) was successfully created: " + errorFormat);
        }
        catch (Exception e){
            System.err.println("\nERROR: MixInFlavor could not be created: " + e.getClass() + " = " + e.getMessage());
        }

        try{    //no name
            failed(new MixInFlavor("","desc",0,1),"desc01");
            System.err.println("\nERROR: Invalid MixInFlavor (no name) was successfully created: " + errorFormat);
        }
        catch (Exception e){
            System.err.println("\nERROR: MixInFlavor could not be created: " + e.getClass() + " = " + e.getMessage());
        }

        try{    //cost<0, price<cost
            failed(new MixInFlavor("name","desc",-1,-2),"namedesc-1-2");
            System.err.println("\nERROR: Invalid MixInFlavor (cost < 0, price < cost) was successfully created: " + errorFormat);
        }
        catch (Exception e){
            System.err.println("\nERROR: MixInFlavor could not be created: " + e.getClass() + " = " + e.getMessage());
        }
        
        try{    //cost<0
            failed(new MixInFlavor("name","desc",-1,1),"namedesc-11");
            System.err.println("\nERROR: Invalid MixInFlavor (cost < 0) was successfully created: " + errorFormat);
        }
        catch (Exception e){
            System.err.println("\nERROR: MixInFlavor could not be created: " + e.getClass() + " = " + e.getMessage());
        }

        try{    //cost>price
            failed(new MixInFlavor("name","desc",3,1),"namedesc31");
            System.err.println("\nERROR: Invalid MixInFlavor (cost > price) was successfully created: " + errorFormat);
        }
        catch (Exception e){
            System.err.println("\nERROR: MixInFlavor could not be created: " + e.getClass() + " = " + e.getMessage());
        }

        try{    //foreign letters in name
            if(failed(new MixInFlavor("машина","машина",0,1),"машинамашина01")){
                System.err.println("\nERROR: Valid MixInFlavor (foreign letters) behaved unexpectedly, returned: " + errorFormat);
            }
        }
        catch (Exception e){
            System.err.println("\nERROR: Valid MixInFlavor (foreign letters) was not able to be created: " + e.getClass() + " = " + e.getMessage());
        }


        //ICE-CREAM FLAVOR
        try{    //normal
            if(failed(new IceCreamFlavor("name","desc",0,1),"namedesc01")){
                System.err.println("\nERROR: Valid IceCreamFlavor behaved unexpectedly, returned: " + errorFormat);
            }
        }
        catch (Exception e){
            System.err.println("\nERROR: Valid IceCreamFlavor was not able to be created: " + e.getClass() + " = " + e.getMessage());
        }


        //SCOOP
        Scoop testScoop;
        try{    //initializing with ice cream flavor
            testScoop = new Scoop(new IceCreamFlavor("name","desc",0,1));
        }
        catch (Exception e){
            System.err.println("\nERROR: Valid IceCreamFlavor was not able to be created within Scoop: " + e.getClass() + " = " + e.getMessage());
        }

        //toString() upon initialization
        testScoop = new Scoop(new IceCreamFlavor("name","desc",0,1)); //same as test;
        if(failed(testScoop, "name")){   
            System.err.println("\nERROR: Valid Scoop (no mix-ins) behaved unexpectedly, returned: " + errorFormat);
        }

        //toString() w/ 1 mixin
        testScoop.addMixin(new MixIn(new MixInFlavor("one","desc",0,1), MixInAmount.Normal));
        if(failed(testScoop, "name with one")){
            System.err.println("\nERROR: Valid Scoop (one normal mix-in) behaved unexpectedly, returned: " + errorFormat);
        }

        //... w/ 2 mixins
        testScoop.addMixin(new MixIn(new MixInFlavor("two","desc",0,1), MixInAmount.Light)); 
        if(failed(testScoop, "name with one, two (Light)")){
            System.err.println("\nERROR: Valid Scoop (normal, light mix-in) behaved unexpectedly, returned: " + errorFormat);
        }

        //...w/ 3 mixins 
        testScoop.addMixin(new MixIn(new MixInFlavor("three","desc",0,1), MixInAmount.Drenched)); 
        if(failed(testScoop, "name with one, two (Light), three (Drenched)")){
            System.err.println("\nERROR: Valid Scoop (normal, light, drenched mix-in) behaved unexpectedly, returned: " + errorFormat);
        }
    }
}
