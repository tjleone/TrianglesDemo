import java.awt.*;
import java.awt.event.*;

/**
 * Present a canvas that draws a spiral of 50 triangles inside each other.
 * Original version published in Section 1.3 of Ammeraal, L. and K. Zhang
 * (2007). Computer Graphics for Java Programmers, 2nd Edition, Chichester: John
 * Wiley. Modified by TJ Leone for Xlint:deprecation compliance and reformatted
 * for pretty printing and javadocs.
 *
 * @author Ammeraal, L. and K. Zhang.
 * @author TJ Leone.
 * @version 9/2014
 */
@SuppressWarnings("serial")
public class Triangles extends Frame
{

    /**
     * The main method for the Triangles program.
     *
     * @param args Not used
     */
    public static void main(String[] args)
    {
        new Triangles();
    }

    /**
     * Constructs a frame with a CvTriangles canvas inside.
     */
    private Triangles()
    {
        super("Triangles: 50 triangles inside each other");
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        setSize(600, 400);
        add("Center", new CvTriangles());
        setVisible(true);
    }
}
