
/**
 * CvTriangles.java
 *
 * Based on code from Section 1.3 of Ammeraal, L. and K. Zhang (2007). Computer
 * Graphics for Java Programmers, 2nd Edition, Chichester: John Wiley.
 *
 */
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Canvas for drawing the spiral of triangles.
 */
@SuppressWarnings("serial")
public class CvTriangles extends Canvas
{

    /**
     * number of triangles to be drawn in spiral of triangles
     */
    final private static int TRIANGLE_COUNT = 50;

    /**
     * ratio of starting (biggest) triangle side to Math.min(maxX, maxY)
     * (bounding square's side)
     */
    final private static float DRAWING_TO_CANVAS_RATIO = 0.95F;
    /**
     * a scale factor to determine how tight the spiral is. Must be between 0
     * and 1.
     */
    final private float q;
    /**
     * a scale factor to determine how tight the spiral is. Initialized to 1 -
     * q.
     */
    final private float p;
    /**
     * logical coordinate for vertex of triangle
     */
    private float xA, yA, xB, yB, xC, yC;
    /**
     * max device y coordinate
     */
    private int maxY;

    /**
     * Initializes a newly created CvTriangles with a scale factor of 0.05F.
     */
    public CvTriangles()
    {
        this(0.05F);
    }

    /**
     * Initializes a newly created CvTriangles
     *
     * @param q scale factor that determines how tight the spiral is.
     * @throws IllegalArgumentException if q &le; 0 or q &ge; 1
     */
    public CvTriangles(float q)
    {
        if (q <= 0 || q >= 1) {
            throw new IllegalArgumentException(
                    "Illegal fraction of side length: " + q);
        }
        this.q = q;
        this.p = 1 - q;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(Graphics g)
    {
        Dimension d = getSize();
        initializeVertices(d.width - 1, d.height - 1);
        for (int i = 0; i < TRIANGLE_COUNT; i++) {
            drawTriangle(g);
            calculateNextVerticies();
        }
    }

    /**
     * Sets the vertices (xA, yA), (xB, yB) and (xC, yC) for the first (biggest)
     * triangle to be drawn.
     *
     * @param maxX largest device x coordinate on the canvas
     * @param maxY largest device y coordinate on the canvas
     */
    public void initializeVertices(int maxX, int maxY)
    {
        this.maxY = maxY;
        int xCenter, yCenter;
        float side = DRAWING_TO_CANVAS_RATIO * Math.min(maxX, maxY);
        float sideHalf = 0.5F * side;
        float h = sideHalf * (float) Math.sqrt(3);
        xCenter = maxX / 2;
        yCenter = maxY / 2;
        xA = xCenter - sideHalf;
        yA = yCenter - 0.5F * h;
        xB = xCenter + sideHalf;
        yB = yA;
        xC = xCenter;
        yC = yCenter + 0.5F * h;
    }

    /**
     * Calculates the vertices (xA, yA), (xB, yB) and (xC, yC) for the next
     * (smaller rotated) triangle to be drawn.
     */
    public void calculateNextVerticies()
    {
        float xA1, yA1, xB1, yB1, xC1, yC1;
        xA1 = p * xA + q * xB;
        yA1 = p * yA + q * yB;
        xB1 = p * xB + q * xC;
        yB1 = p * yB + q * yC;
        xC1 = p * xC + q * xA;
        yC1 = p * yC + q * yA;
        xA = xA1;
        xB = xB1;
        xC = xC1;
        yA = yA1;
        yB = yB1;
        yC = yC1;
    }

    /**
     * Draws a triangle with vertices (xA, yA), (xB, yB) and (xC, yC)
     *
     * @param g the graphics context to use for drawing
     */
    public void drawTriangle(Graphics g)
    {
        g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
        g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
        g.drawLine(iX(xC), iY(yC), iX(xA), iY(yA));
    }

    /**
     * Converts x from a logical coordinate to a device coordinate.
     *
     * @param x a logical x coordinate
     * @return the corresponding device x coordinate
     */
    private int iX(float x)
    {
        return Math.round(x);
    }

    /**
     * Converts y from a logical coordinate to a device coordinate.
     *
     * @param y a logical y coordinate
     * @return the corresponding device y coordinate
     */
    private int iY(float y)
    {
        return maxY - Math.round(y);
    }
}