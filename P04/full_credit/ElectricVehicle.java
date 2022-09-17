public class ElectricVehicle extends Vehicle {
    public static double centsPerKwhOElectricity = Double.NaN;
    private double whPerMile;
    private double kwhInBattery;

    public ElectricVehicle(int year, String make, String model, BodyStyle bodyStyle,
                           double whPerMile, double kwhInBattery){
        super(year,make,model,bodyStyle);
        this.whPerMile = whPerMile;
        this.kwhInBattery = kwhInBattery;
    }

    @Override
    public double range(){
        return kwhInBattery / (whPerMile / 1000);
    }

    @Override
    public double fuelConsumed(double miles){
        try{
            double fuelRequired = miles * (whPerMile / 1000);
            if(fuelRequired > kwhInBattery) {
                throw new ArithmeticException;
            }
        }
        catch (ArithmeticException e) {
            if (fuelRequired == kwhInBattery) { //catches division by 0 which is
                return fuelRequired;            //equivalent to having exactly enough fuel
            }
            return Double.NaN; //if trip could not be made with this vehicle
        }
    }

    @Override
    public double dollarsToTravel(double miles){
        if (fuelConsumed == Double.NaN) return -1;
        return fuelConsumed(miles) * (centsPerKwhOElectricity / 100);
    }

    @Override
    public String toString(double miles){
        double cost = dollarsToTravel(miles);
        if(cost == -1) return "";
        
        return "$" + "%5d" + cost + " (range " + range() + ") " + this.year + this.make + this.bodyStyle + "\n";
    }

}