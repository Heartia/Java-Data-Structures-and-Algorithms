package Fractals;

/*
 * KochSnowflake.java
 * 2/28/12
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A program that draws a Koch snowflake.
 *
 * @author YOUR_NAME_HERE
 * @author YOUR_NAME_HERE
 */
public class KochSnowflake extends JPanel implements ChangeListener {

    /** Panel dimension. */
    private static final Dimension PANEL_DIM = new Dimension(640, 480);

    /** Slider to specify the order of the Koch snowflake. */
    private JSlider orderSlider;

    /** Slider to specify the size of the protrusion. */
    private JSlider sizeSlider;

    /** One hundred times the maximum size of Koch snowflake protrusion. */
    private static final int MAX_SIZE = 100;

    /** One hundred times the minimum size of Koch snowflake protrusion. */
    private static final int MIN_SIZE = -100;

    /** One hundred times the initial size of Koch snowflake protrusion. */
    private static final int INIT_SIZE = 33;

    /** Maximum order of Koch snowflake. */
    private static final int MAX_ORDER = 12;

    /** Minimum order of Koch snowflake. */
    private static final int MIN_ORDER = 1;

    /** Initial order of Koch snowflake. */
    private static final int INIT_ORDER = 1;

    /** Decimal values used for calculations in paintComponent */
    private static final double ONE_HALF = 0.5;
    private static final double ONE_THIRD = 0.33;
    private static final double TWO_THIRDS = 0.66;
    private static final double ONE_HUNDREDTH = 0.01;

    /** Decimal values used for calculations for location in koch */
    private static final float i1 = 1f/3f;
    private static final float i2 = 2f/3f;

    /**
     * Run the program.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        JFrame frame = new JFrame("Koch Snowflake");
        frame.getContentPane().add(new KochSnowflake());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Initialize the window contents.
     */
    public KochSnowflake() {
        JLabel orderLabel = new JLabel("Order:");
        JLabel sizeLabel = new JLabel("Size:");
        orderSlider = new JSlider(JSlider.HORIZONTAL, MIN_ORDER, MAX_ORDER,
                INIT_ORDER);
        sizeSlider = new JSlider(JSlider.HORIZONTAL, MIN_SIZE, MAX_SIZE,
                INIT_SIZE);
        orderSlider.addChangeListener(this);
        sizeSlider.addChangeListener(this);
        add(orderLabel);
        add(orderSlider);
        add(sizeLabel);
        add(sizeSlider);
        setBackground(Color.white);
        setPreferredSize(PANEL_DIM);
    }

    /**
     * Paint a Koch snowflake to the window.
     *
     * @param g graphics object to draw to
     */
    public final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final int order = orderSlider.getValue();
        final double size = sizeSlider.getValue() * ONE_HUNDREDTH;
        final double width = getWidth();
        final double height = getHeight();
        Point2D point1, point2;
        point1 = new Point2D.Double(width * ONE_HALF, height * ONE_THIRD);
        point2 = new Point2D.Double(width * ONE_THIRD, height * TWO_THIRDS);
        koch(g, point1, point2, order, size);
        point1 = new Point2D.Double(width * ONE_THIRD, height * TWO_THIRDS);
        point2 = new Point2D.Double(width * TWO_THIRDS, height * TWO_THIRDS);
        koch(g, point1, point2, order, size);
        point1 = new Point2D.Double(width * TWO_THIRDS, height * TWO_THIRDS);
        point2 = new Point2D.Double(width * ONE_HALF, height * ONE_THIRD);
        koch(g, point1, point2, order, size);
    }

    /**
     * Draw a Koch snowflake segment.
     *
     * @param g the graphics object to draw to
     * @param point1 the first vertex of the line to draw
     * @param point5 the second vertex of the line to draw
     * @param order the order of the snowflake
     * @param size the size of the protrusion
     */
    public void koch(final Graphics g, final Point2D point1,
                     final Point2D point5, final int order, final double size) {
        // TODO: Implement recursive drawing of Koch snowflake segment
        // Equations used to determine points 2 and 4:
        //      xi = i * x2 + (1 - i) * x1
        //      yi = i * y2 + (1 - i) * y1
        // Equations used to determine point 3:
        //      x3 = x1 + Δx / 2 + Δy * p
        //      y3 = y1 + Δy / 2 + Δx * p
        //      Where:
        //          Δx = x5 - x1
        //          Δy = y5 - y1
        if (order > 1) {
            Point2D delta = new Point2D.Double(point5.getX() - point1.getX(), point5.getY() - point1.getY());
            Point2D point3 = new Point2D.Double(point1.getX() + delta.getX() / 2 - delta.getY() * size,
                    point1.getY() + delta.getY() / 2 + delta.getX() * size);

            Point2D point2 = new Point2D.Double(i1 * point5.getX() + (1 - i1) * point1.getX(),
                    i1 * point5.getY() + (1 - i1) * point1.getY());
            Point2D point4 = new Point2D.Double(i2 * point5.getX() + (1 - i2) * point1.getX(),
                    i2 * point5.getY() + (1 - i2) * point1.getY());

            koch(g, point1, point2, order - 1, size);
            koch(g, point2, point3, order - 1, size);
            koch(g, point3, point4, order - 1, size);
            koch(g, point4, point5, order - 1, size);
        }
        else {
            g.drawLine((int)(Math.round(point1.getX())), (int)(Math.round(point1.getY())), (int)(Math.round(point5.getX())), (int)(Math.round(point5.getY())));
        }
    }

    /**
     * Repaint the window if the slider moves.
     *
     * @param event change event
     */
    public final void stateChanged(final ChangeEvent event) {
        repaint();
    }

}
