package nl.hanze.CarParkSimulation.logic;

import java.util.Calendar;

/**
 * Class Time
 *
 * @author Koen Hendriks
 * @version 0.1 (08-04-2016)
 */
public final class Time extends AbstractModel {

    private int day;
    private int hour;
    private int minute;

    private Calendar cal;

    public Time() {
        this.cal = Calendar.getInstance();

        this.day = 0;
        this.hour = 0;
        this.minute = 0;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
