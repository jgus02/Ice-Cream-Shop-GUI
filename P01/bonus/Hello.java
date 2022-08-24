import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        String name;
        System.out.print("Enter your name: ");
        name = input.next();
        System.out.println("");
        System.out.print("Hello, "+name+"!");
    }
}