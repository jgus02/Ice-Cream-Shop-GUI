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

        System.out.print("\nHow many seconds to tick? ");
        seconds = in.nextInt();

        int tick = 1;
        if (seconds<0) {
            tick = -1;
            seconds = -seconds;
        }

        for(int i=0;i<seconds;i++) {
            clock = clock.add(tick);
            System.out.println(clock);
        }

        do {
            System.out.print("\nHow many seconds to add? (0 to continue): ");
            seconds = in.nextInt();
            clock = clock.add(seconds);
            System.out.println(clock);
        } while(seconds!=0);

        Clock tempClock = clock.add(clock);
        System.out.println(" + " + clock + " = " + tempClock);

    }
}
