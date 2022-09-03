import java.util.Scanner;

public class Clock {
    private int hours,minutes,seconds;
    
    public Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

        rationalize();
    }

    @Override
    public String toString() {
        return twoDigit(hours)+":"+twoDigit(minutes)+":"+twoDigit(seconds);
    }

    private String twoDigit(int n) { //return 0X instead of X
        return String.format("%02d", n);
    }

    private void rationalize() { //HOW DO I MAKE THIS COMPACT WITHOUT PASS
        while (seconds<0) {     //BY REFERENCE OR EXTRA METHODS OR ARRAYS
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
