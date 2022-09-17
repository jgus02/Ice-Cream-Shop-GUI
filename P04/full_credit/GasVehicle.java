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
        gallonsInTank * milesPerGallon
    }
    @Override
    public double fuelConsumed(double miles){
        double fuelRequired = Double.NaN;
        try{
            fuelRequired = miles * milesPerGallon;
            if(fuelRequired > gallonsInTank) {
                throw new ArithmeticException();
            }
        }
        catch (ArithmeticException e) {
            if (fuelRequired == gallonsInTank) { //catches division by 0 which is
                return fuelRequired;            //equivalent to having exactly enough fuel
            }
            fuelRequired = Double.NaN; //if trip could not be made with this vehicle
        }
        return fuelRequired;
    }
    
    public double dollarsToTravel(double miles){
        double fuelRequired = fuelConsumed(miles);
        if (fuelRequired == Double.NaN) return -1;
        return fuelRequired * dollarsPerGallonOfGas;
    }



}