package vehicles;

public abstract class Vehicle {
    private int year;
    private String make;
    private String model;
    private BodyStyle bodyStyle;

    public String toString(double miles){
        double cost = dollarsToTravel(miles);
        if(cost!=cost){
            return "--" + year + " " + make + " " + model + " " + bodyStyle + " could not make this trip.\n";
        }
        else{
            return String.format("$%6.2f (range: %.2f) %d %s %s %s\n",cost,range(),year,make,model,bodyStyle);
        }
    }
    
    public abstract double range();
    
    public abstract double fuelConsumed(double miles);

    public abstract double dollarsToTravel(double miles);

    public Vehicle(int year, String make, String model, BodyStyle bodyStyle){
        this.year = year;
        this.make = make;
        this.model = model;
        this.bodyStyle = bodyStyle;
    } 
}
