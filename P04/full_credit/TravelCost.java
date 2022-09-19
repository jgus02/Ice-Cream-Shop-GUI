import java.util.ArrayList;
import java.util.Scanner;

public class TravelCost {
    public static void main(String[] args) {


        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new ElectricVehicle(2022, "Telsa",    "Model S Plaid",   BodyStyle.Sedan,     297, 100  ));
        vehicles.add(new ElectricVehicle(2022, "Telsa",    "Model 3 LR",      BodyStyle.Sedan,     242,  82  ));
        vehicles.add(new ElectricVehicle(2022, "GM",       "Bolt",            BodyStyle.Hatchback, 286,  66  ));
        vehicles.add(new ElectricVehicle(2022, "Nissan",   "LEAF",            BodyStyle.Hatchback, 269,  60  ));
        vehicles.add(new ElectricVehicle(2022, "Ford",     "Mustang Mach-E",  BodyStyle.SUV,       347,  88  ));
        vehicles.add(new ElectricVehicle(2022, "Ford",     "F-150 Lightning", BodyStyle.Truck,     511, 131  ));
        vehicles.add(new GasVehicle(     2022, "Ford",     "F-150",           BodyStyle.Truck,      25,  23  ));
        vehicles.add(new GasVehicle(     2022, "Toyota",   "Prius Hybrid",    BodyStyle.Hatchback,  55,  11.4));
        vehicles.add(new GasVehicle(     2022, "Toyota",   "RAV4",            BodyStyle.Crossover,  31,  14.5));
        vehicles.add(new GasVehicle(     2022, "Nissan",   "Rogue",           BodyStyle.Hatchback,  33,  14.5));
        vehicles.add(new GasVehicle(     2022, "Chrysler", "Pacifica",        BodyStyle.Minivan,    24,  19  ));
        vehicles.add(new GasVehicle(     2022, "Chrysler", "Pacifica Hybrid", BodyStyle.Minivan,    30,  16.5));

        Scanner userIn = new Scanner(System.in);

        System.out.print("What is the price per gallon of gas in dollars?: ");
        GasVehicle.dollarsPerGallonOfGas = userIn.nextDouble();
        System.out.print("What is the price per kWh of electricity in cents?: ");
        ElectricVehicle.centsPerKwhOfElectricity = userIn.nextDouble();
        System.out.print("How many miles is the trip?: ");
        double miles = userIn.nextDouble();

        Vehicle vehicle;
        for(int i=0;i<vehicles.size();i++){
            vehicle = vehicles.get(i);
            System.out.print(vehicle.toString(miles));
        }
    }
}
