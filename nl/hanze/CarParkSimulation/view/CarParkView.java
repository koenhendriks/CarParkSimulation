package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;

import java.awt.*;

/**
 * Class CarParkView
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class CarParkView extends AbstractView{

    private Image carParkImage;
    private Dimension size;

    /**
     * Constructor of AbstractView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public CarParkView(AbstractModel model) {
        super(model);
        this.size = new Dimension(800, 500);
    }

    @Override
    /**
     *  Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    @Override
    /**
     * The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     */
    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        } else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }
}
