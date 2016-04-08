package nl.hanze.CarParkSimulation.logic;

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

    private Calendar startCal;
    private Calendar cal;

    public Time() {

        this.startCal = Calendar.getInstance();
        this.cal = Calendar.getInstance();
    }

    /**
     * Tick the minute away
     */
    public void tick() {

        cal.add(MINUTE,+1);
        super.notifyViews();
    }

    public int getMinute() {
        return cal.get(MINUTE);
    }

    public boolean isWeekend(){
        return cal.get(DAY_OF_WEEK) <= 5;
    }
}
