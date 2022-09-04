import java.util.Scanner;

public class Clock {
    private int hours,minutes,seconds;
    
    public Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

        rationalize();
    }
    public Clock add(int seconds) {;

        Clock newSeconds = new Clock(this.hours, this.minutes, (seconds + this.seconds));

        return newSeconds;
    }

    public Clock add(Clock clock) { //create new clock based off given clock
        Scanner in = new Scanner(System.in);
        int hours,minutes,seconds;

        System.out.print("Add to previous clock:\nHour? ");
        hours = in.nextInt();

        System.out.print("Minute? ");
        minutes = in.nextInt();

        System.out.print("Second? ");
        seconds = in.nextInt();

        Clock derivedClock = new Clock((hours + this.hours),(minutes + this.minutes),(seconds + this.seconds));

        Clock temp = new Clock(hours,minutes,seconds);
        System.out.print(temp); //is this cheating
        return derivedClock;
    }

    @Override
    public String toString() {
        return twoDigit(hours)+":"+twoDigit(minutes)+":"+twoDigit(seconds);
    }

    private String twoDigit(int n) { //return 0X instead of X
        return String.format("%02d", n);
    }

    private void rationalize() {
        while (seconds<0) {
            seconds += 60;
            minutes -= 1;
        }
        while (seconds>59) {
            seconds -= 60;
            minutes += 1;
        }

        while (minutes<0) {
            minutes += 60;
            hours -= 1;
        }
        while (minutes>59) {
            minutes -= 60;
            hours += 1;
        }

        if (hours<0) {
            hours = 24 - ((hours*-1) % 24);
        }
        if(hours>23) {
            hours = hours % 24;
        }
    }
}
