import java.util.Scanner;

public class Clock {
    private int hours;
    private int minutes;
    private int seconds;
    
    public Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    private String twoDigit(int n) {
        return String.format("%02d", n);
    }

    @Override
    public String toString() {
        return twoDigit(hours)+":"+twoDigit(minutes)+":"+twoDigit(seconds);
        //return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


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
