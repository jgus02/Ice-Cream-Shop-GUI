import java.util.Scanner;

public class ClockDriver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int hours,minutes,seconds;
        System.out.print("Hour? ");
        hours = in.nextInt();
        System.out.print("Minute? ");
        minutes = in.nextInt();
        System.out.print("Second? ");
        seconds = in.nextInt();

        Clock clock = new Clock(hours,minutes,seconds);

        System.out.println("The time is " + clock);
    }
}
