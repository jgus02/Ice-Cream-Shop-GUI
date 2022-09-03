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
    }
}
