import java.util.ArrayList;
// import java.util.Scanner;

public class testinheritance {
    private static java.io.Console console = System.console();
    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        ElectricVehicle acar = new ElectricVehicle(2022, "Telsa",    "Model S Plaid",   BodyStyle.Sedan,     297, 100  );

        acar.aough();
    }
}
