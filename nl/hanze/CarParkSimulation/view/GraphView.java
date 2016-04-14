package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.logic.Time;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
    private Graphics2D graphic = null;
    private int maxCars = 0 ;
    private int lastTotalCars = 1;
    private static int lastMinute = 0;
    private static LinkedList<Line2D> lines;
    private Image image;
    private static Line2D yAxis;
    private static Line2D xAxis;
    private static int drawnGraphs = 0;
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
        lines = new LinkedList<>();

        yAxis = new Line2D.Float(chartXOffset, chartYOffset, chartXOffset, yZero);
        xAxis = new Line2D.Float(chartXOffset, yZero, 1190, yZero);

        lines.add(yAxis);
        lines.add(xAxis);
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
        this.lines = new LinkedList<>();

        yAxis = new Line2D.Float(chartXOffset, chartYOffset, chartXOffset, yZero);
        xAxis = new Line2D.Float(chartXOffset, yZero, 1190, yZero);

        lines.add(yAxis);
        lines.add(xAxis);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (this.image == null) {
            return;
        }

        g.drawImage(this.image, 0, 0, null);
    }

    @Override
    public void updateView() {

        int runningMinutes = Integer.valueOf(time.getRunningMinutes());

        if(runningMinutes > lastMinute) {

            // create a new car park image if the size has changed.
            image = createImage(size.width, size.height);

            Graphics graphics = image.getGraphics();

            Graphics2D g = (Graphics2D) graphics;

            g.drawString(Language.get("graphTotalCars"), 10,25);

            int totalCars = carPark.getTotalCars();

            double carPercentOld = ((double) lastTotalCars / (double) maxCars) * 100;
            double carPercentNew = ((double) totalCars / (double) maxCars) * 100;

            int labelPercent = (int) Math.floor(carPercentNew);

            for(int i = 0; i < 101; i = i +10){
                g.drawString(""+i+"%", 0, (yZero - (int) Math.floor(1.3 * i) ));
            }

            int oldCars = (int) Math.floor(1.3 * carPercentOld);
            int newCars = (int) Math.floor(1.3 * carPercentNew);

            int xFrom = (lastMinute + chartXOffset) - (drawnGraphs * (size.width - chartXOffset));
            int yFrom = yZero - oldCars;


            int xTo = (runningMinutes + chartXOffset)  - (drawnGraphs * (size.width - chartXOffset));
            int yTo = yZero - newCars;

            if(xTo > size.width){
                drawnGraphs ++;
                lines = new LinkedList<>();
                lines.add(yAxis);
                lines.add(xAxis);
            }

            Line2D line = new Line2D.Float(xFrom, yFrom, xTo, yTo);

            lines.add(line);

            g.drawString(labelPercent+"% at "+time.getCurrentTime(),xTo-10,yTo-20);

            if(!lines.isEmpty()){
                for (Line2D line2D : lines) {
                    g.draw(line2D);
                }
            }

            lastMinute = runningMinutes;
            lastTotalCars = totalCars;

            lastTotalCars++;

            setVisible(true);
            super.updateView();
        }
    }

    public static void reset(){
        lastMinute = 0;
        drawnGraphs = 0;
        lines = new LinkedList<>();
        lines.add(yAxis);
        lines.add(xAxis);
    }
}
