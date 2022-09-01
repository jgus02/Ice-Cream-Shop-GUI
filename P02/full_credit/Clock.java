import java.util.Scanner;

public class Clock {
    static int hours;
    static int minutes;
    static int seconds;
    
    Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public static String twoDigit(int helpme) {
        if(helpme < 10){
            return "0" + Integer.toString(helpme);
        }
        else {
            return Integer.toString(helpme);
        }
    }

    public String toString() {
        return twoDigit(hours)+":"+twoDigit(minutes)+":"+twoDigit(seconds);
        //return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
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