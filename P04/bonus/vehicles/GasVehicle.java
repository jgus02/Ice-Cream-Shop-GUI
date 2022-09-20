package vehicles;

public class GasVehicle extends Vehicle {
    public static double dollarsPerGallonOfGas = Double.NaN;
    private double milesPerGallon;
    private double gallonsInTank;

    public GasVehicle(int year, String make, String model, BodyStyle bodyStyle,
                           double milesPerGallon, double gallonsInTank){
        super(year,make,model,bodyStyle);
        this.milesPerGallon = milesPerGallon;
        this.gallonsInTank = gallonsInTank;
    }

    @Override
    public double range(){
        return gallonsInTank * milesPerGallon;
    }

    @Override
    public double fuelConsumed(double miles){
        double range = range();
        try{
            if(miles > range) {
                throw new ArithmeticException();
            }
        }
        catch (ArithmeticException e) {
            return Double.NaN; //if trip could not be made with this vehicle
        }
        return miles / milesPerGallon;
    }
    
    @Override
    public double dollarsToTravel(double miles){
        double fuelRequired = fuelConsumed(miles);
        if (fuelRequired != fuelRequired){
            return fuelRequired;
            }
        return fuelRequired * dollarsPerGallonOfGas;
    }



}
