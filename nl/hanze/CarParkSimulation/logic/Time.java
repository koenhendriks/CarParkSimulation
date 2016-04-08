package nl.hanze.CarParkSimulation.logic;

import javax.swing.*;
import java.util.Calendar;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MINUTE;

/**
 * Class Time
 *
 * @author Koen Hendriks
 * @version 0.1 (08-04-2016)
 */
public final class Time extends AbstractModel {

    private Calendar startCalendar;
    private Calendar runningCalendar;
    private String startTime;

    public Time() {
        this.startCalendar = this.runningCalendar = Calendar.getInstance();
        this.startTime = this.getStringFromCalendar(this.startCalendar);
    }

    /**
     * Tick the minute away
     */
    public void tick() {
        this.runningCalendar.add(MINUTE,+1);
        super.notifyViews();
    }

    /**
     * This private method wil create a formatted string from a calendar object.
     * The format will be HH-MMh mm-dd-yyyy
     *
     * @param cal Calendar object to create the string from.
     * @return String with the time of the calendar formatted
     */
    private String getStringFromCalendar(Calendar cal){
        String timeString = String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))+"h";
        String dateString = cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR);

        return timeString+" "+dateString;
    }

    /**
     * Get the current time of the simulation
     *
     * @return String formatted nicely for the human eye
     */
    public String getCurrentTime(){
        return this.getStringFromCalendar(this.runningCalendar);
    }

    public int getMinute() {
        return this.runningCalendar.get(MINUTE);
    }

    public String getStartTime() {
        return startTime;
    }

    public boolean isWeekend(){
        return this.runningCalendar.get(DAY_OF_WEEK) <= 5;
    }
}
