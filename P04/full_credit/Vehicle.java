public abstract class Vehicle {
    private int year;
    private String make;
    private String model;
    private BodyStyle bodystyle;

    @Override
    public String toString{}(

    )
    
    public double range();
    
    public double fuelConsumed();

    public double dollarsToTravel(double miles);

    public Vehicle(int year, String make, String model, BodyStyle bodyStyle){
        this.year = year;
        this.make = make;
        this.model = model;
        this.bodyStyle = bodyStyle;
    } 
}