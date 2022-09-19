public class ElectricVehicle extends Vehicle {
    public static double centsPerKwhOfElectricity = Double.NaN;
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
        double range = 0;
        try{
            range = range();
            if(miles > range) {
                throw new ArithmeticException();
            }
        }
        catch (ArithmeticException e) {
            if (miles == range) { //catches division by 0 which is
                return range;            //equivalent to having exactly enough fuel
            }
            return Double.NaN; //if trip could not be made with this vehicle
        }
        return miles * (whPerMile / 1000);
    }

    @Override
    public double dollarsToTravel(double miles){
        double fuelRequired = fuelConsumed(miles);
        if (fuelRequired != fuelRequired){
            return fuelRequired;
        }
        return fuelRequired * (centsPerKwhOfElectricity / 100);
    }

}
