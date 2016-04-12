package nl.hanze.CarParkSimulation.logic;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MINUTE;

/**
 * Class Time
 *
 * Class for determining time values.
 *
 * @author Koen Hendriks, Joey Boum Bletterman
 * @version 0.2 (11-04-2016)
 */
public class Time extends AbstractModel implements TimeInterface
{
    private Calendar startCalendar;
    private Calendar runningCalendar;
    private String startTime;

    /**
     * Constructor for the Time object.
     */
    public Time() {
        this.startCalendar = Calendar.getInstance();
        this.runningCalendar = Calendar.getInstance();
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
     * The format will be HH-MMh mm-dd-yyyy.
     *
     * @param cal Calendar object to create the string from.
     * @return String with the time of the calendar formatted.
     */
    private String getStringFromCalendar(Calendar cal){
        String timeString = String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))+"h";
        String dateString = cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR);

        return timeString+" "+dateString;
    }

    /**
     * Method to reset the time
     */
    public void reset(){
        this.startCalendar = Calendar.getInstance();;
        this.runningCalendar = Calendar.getInstance();;
        this.startTime = this.getStringFromCalendar(this.startCalendar);
        super.notifyViews();
    }

    /**
     * Get the current time of the simulation.
     *
     * @return String formatted nicely for the human eye.
     */
    public String getCurrentTime(){
        return this.getStringFromCalendar(this.runningCalendar);
    }

    /**
     * Here we get both calendar objects as milliseconds. We can then calculate the
     * amount of milliseconds that differ between the start and current time.
     *
     * @return long with the amount that the calendars differ in milliseconds.
     */
    public long runningDifference() {
        return Math.abs(this.runningCalendar.getTimeInMillis() - this.startCalendar.getTimeInMillis());
    }

    /**
     * Here we turn the difference in milliseconds into days.
     *
     * @return String that shows the amount of days that passed.
     */
    public String getRunningDays(){
        return Objects.toString(TimeUnit.MILLISECONDS.toDays(runningDifference()),null);
    }

    /**
     * Here we turn the difference in milliseconds into hours.
     *
     * @return String that shows the amount of hours that passed.
     */
    public String getRunningHours(){
        return Objects.toString(TimeUnit.MILLISECONDS.toHours(runningDifference()),null);
    }

    /**
     * Here we turn the difference in milliseconds into minutes.
     *
     * @return String that shows the amount of minutes that passed.
     */
    public String getRunningMinutes(){
        return Objects.toString(TimeUnit.MILLISECONDS.toMinutes(runningDifference()),null);
    }

    /**
     * To get the amount of weeks we just get the days and see if we can
     * divide it by 7. If we floor this number we get the amount of weeks.
     *
     * @return String that shows the amount of weeks that passed.
     */
    public String getRunningWeeks(){
        long days = TimeUnit.MILLISECONDS.toDays(runningDifference());
        return Objects.toString((int) Math.floor(days/7),null);
    }

    /**
     * Get the formatted start time string.
     *
     * @return String with time and date in nice human format.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Boolean to check if its weekend.
     *
     * @return boolean if its weekend.
     */
    public boolean isWeekend(){
        return this.runningCalendar.get(DAY_OF_WEEK) == Calendar.SATURDAY || this.runningCalendar.get(DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * Method for resetting the time.
     */
    public void resetTime(){
        this.startCalendar = Calendar.getInstance();
        this.runningCalendar = Calendar.getInstance();
        this.startTime = this.getStringFromCalendar(this.startCalendar);
    }
}
