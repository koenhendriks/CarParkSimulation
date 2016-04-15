package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.logic.Time;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.LinkedList;

/**
 * Class GridView
 *
 * This is a class for a grid display.
 *
 * @author Koen Hendriks, Joey Boum Bletterman
 * @version 0.2 (11-04-2016)
 */
public class GraphView extends AbstractView
{
    // grid dimensions
    private Dimension size;
    private CarPark carPark;
    private Time time;
    private int maxCars = 0 ;
    private int lastTotalCars = 1;
    private int lastTotalRevenues = 1;
    private int lastTotalPassholders = 1;
    private static int lastCarMinute = 0;
    private static LinkedList<Line2D> totalCarLines;
    private static LinkedList<Line2D> reservationLines;
    private static LinkedList<Line2D> passholderLines;
    private Image image;
    private static Line2D yAxis;
    private static Line2D xAxis;
    private static int drawnCarGraphs = 0;
    private static int drawnreservationLines = 0;
    private static int drawnPassholderLines = 0;
    private final int chartXOffset = 30;
    private final int chartYOffset = 40;
    private final int yZero = 170;


    /**
     * Constructor of GridView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public GraphView(AbstractModel model) {
        super(model);
        carPark = (CarPark) super.model;
        time = carPark.getTime();
        this.size = new Dimension(1200,180);
        this.maxCars = CarPark.getNumberOfFloors() * CarPark.getNumberOfPlaces() * CarPark.getNumberOfRows();
        this.image = createImage(size.width,size.height);
        totalCarLines = new LinkedList<>();
        reservationLines = new LinkedList<>();
        passholderLines = new LinkedList<>();

        yAxis = new Line2D.Float(chartXOffset, chartYOffset, chartXOffset, yZero);
        xAxis = new Line2D.Float(chartXOffset, yZero, 1190, yZero);

        totalCarLines.add(yAxis);
        totalCarLines.add(xAxis);
    }

    /**
     * Constructor of GridView that expects a model belonging to this view
     * and a given width and height to set the grid to.
     *
     * @param model AbstractModel that belongs to this view.
     * @param width int width of the application.
     * @param height int height of the application.
     */
    public GraphView(AbstractModel model, int width, int height) {
        super(model);
        carPark = (CarPark) super.model;
        time = carPark.getTime();
        this.size = new Dimension(width,height);
        this.maxCars = CarPark.getNumberOfFloors() * CarPark.getNumberOfPlaces() * CarPark.getNumberOfRows();
        this.image = createImage(size.width,size.height);
        totalCarLines = new LinkedList<>();
        reservationLines = new LinkedList<>();

        yAxis = new Line2D.Float(chartXOffset, chartYOffset, chartXOffset, yZero);
        xAxis = new Line2D.Float(chartXOffset, yZero, 1190, yZero);

        totalCarLines.add(yAxis);
        totalCarLines.add(xAxis);
    }

    @Override
    /**
     * Paint components on the graphic
     */
    public void paintComponent(Graphics g) {
        if (this.image == null) {
            return;
        }

        g.drawImage(this.image, 0, 0, null);
    }

    @Override
    /**
     * Method that gets called by model if the view needs to be updated
     */
    public void updateView() {

        int runningMinutes = Integer.valueOf(time.getRunningMinutes());

        // only draw the grapgh ones a minute
        if(runningMinutes > lastCarMinute) {

            // create a new car park image if the size has changed.
            image = createImage(size.width, size.height);

            Graphics graphics = image.getGraphics();

            Graphics2D g = (Graphics2D) graphics;

            g.drawString(Language.get("graphTotalCars"), 10,25);

            int totalCars = carPark.getTotalCars();
            int reservations = carPark.getTotalReservationIndex();
            int passholders = carPark.getTotalPassholderIndex();

            /**
             * Calculate the old and new percentage of the indexes
             * to draw the lines in a percental scale.
             */
            double carPercentOld = ((double) lastTotalCars / (double) maxCars) * 100;
            double carPercentNew = ((double) totalCars / (double) maxCars) * 100;

            double reservationPercentOld = ((double) lastTotalRevenues / (double) maxCars) * 100;
            double reservationPercentNew = ((double) reservations / (double) maxCars) * 100;

            double passholderPercentOld = ((double) lastTotalPassholders / (double) maxCars) * 100;
            double passholderPercentNew = ((double) passholders / (double) maxCars) * 100;

            // add a label to the total cars line
            int labelPercent = (int) Math.floor(carPercentNew);

            // keep the label at the latest point
            for(int i = 0; i < 101; i = i +10){
                g.drawString(""+i+"%", 0, (yZero - (int) Math.floor(1.3 * i) ));
            }

            /**
             * Calculate integers to set the x and y coordinates where
             * the line should draw from and the x and y coordinates where
             * the line should draw to.
             */
            int oldCars = (int) Math.floor(1.3 * carPercentOld);
            int newCars = (int) Math.floor(1.3 * carPercentNew);

            int oldReservations = (int) Math.floor(1.3 * reservationPercentOld);
            int newReservations = (int) Math.floor(1.3 * reservationPercentNew);

            int oldPassholders = (int) Math.floor(1.3 * passholderPercentOld);
            int newPassholders = (int) Math.floor(1.3 * passholderPercentNew);

            int xFrom = (lastCarMinute + chartXOffset) - (drawnCarGraphs * (size.width - chartXOffset));
            int yFrom = yZero - oldCars;

            int xFromReservation = (lastCarMinute + chartXOffset) - (drawnreservationLines * (size.width - chartXOffset));
            int yFromReservation = yZero - oldReservations;

            int xFromPassholder = (lastCarMinute + chartXOffset) - (drawnPassholderLines * (size.width - chartXOffset));
            int yFromPassholder = yZero - oldPassholders;

            int xTo = (runningMinutes + chartXOffset)  - (drawnCarGraphs * (size.width - chartXOffset));
            int yTo = yZero - newCars;

            int xToReservations = (runningMinutes + chartXOffset)  - (drawnreservationLines * (size.width - chartXOffset));
            int yToReservations = yZero - newReservations;

            int xToPassholder = (runningMinutes + chartXOffset)  - (drawnPassholderLines * (size.width - chartXOffset));
            int yToPassholder = yZero - newPassholders;

            /**
             * Reset the width for the graph if we have no screen to draw
             * on anymore we want to reset the graph to x 0 with the offset
             */
            if(xTo > size.width){
                drawnCarGraphs++;
                totalCarLines = new LinkedList<>();
                reservationLines = new LinkedList<>();
                passholderLines = new LinkedList<>();

                totalCarLines.add(yAxis);
                totalCarLines.add(xAxis);

                drawnreservationLines++;
                drawnPassholderLines++;
            }

            /**
             * Create the new lines for the totalcars, reservations and passholders
             * in the current step.
             */
            Line2D line = new Line2D.Float(xFrom, yFrom, xTo, yTo);
            Line2D lineReservation = new Line2D.Float(xFromReservation, yFromReservation, xToReservations, yToReservations);
            Line2D linePassholder = new Line2D.Float(xFromPassholder, yFromPassholder, xToPassholder, yToPassholder);

            // add the lines to the lists
            totalCarLines.add(line);
            reservationLines.add(lineReservation);
            passholderLines.add(linePassholder);

            // draw the label on the latest line of the total cars
            g.drawString(labelPercent+"% at "+time.getCurrentTime(),xTo-10,yTo-20);

            // draw the totalcar graph
            if(!totalCarLines.isEmpty()){
                for (Line2D line2D : totalCarLines) {
                    g.draw(line2D);
                }
            }

            // draw the total reservation graph
            if(!reservationLines.isEmpty()){
                for (Line2D line2D : reservationLines) {
                    g.setColor(Color.GREEN);
                    g.draw(line2D);
                }
            }

            // draw the total passholder graph
            if(!passholderLines.isEmpty()){
                for (Line2D line2D : passholderLines) {
                    g.setColor(Color.BLUE);
                    g.draw(line2D);
                }
            }

            // update the start values to the new lines
            lastCarMinute = runningMinutes;
            lastTotalCars = totalCars;
            lastTotalRevenues = reservations;
            lastTotalPassholders = passholders;

            lastTotalCars++;
            lastTotalRevenues++;
            lastTotalPassholders++;

            setVisible(true);
            super.updateView();
        }
    }

    /**
     * Method to reset the graph
     */
    public static void reset(){
        lastCarMinute = 0;
        drawnCarGraphs = 0;
        drawnPassholderLines = 0;
        drawnreservationLines = 0;
        totalCarLines = new LinkedList<>();
        reservationLines = new LinkedList<>();
        passholderLines = new LinkedList<>();
        totalCarLines.add(yAxis);
        totalCarLines.add(xAxis);
    }
}
