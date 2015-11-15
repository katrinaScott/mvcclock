package src;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


/**
 * Classic 3 dials view for digital Clock
 */
class DigitalClock3DialsView extends DigitalClockView {
	
	private static Color colors[] = {Color.pink, Color.lightGray, Color.gray};
	private static BasicStroke bs1 = new BasicStroke(1.0f);
	
	// three arms of clock
    private Line2D lines[] = new Line2D[3];
    private int rAmt[] = new int[lines.length];
    private int speed[] = new int[lines.length];
    private BasicStroke strokes[] = new BasicStroke[lines.length];
    private GeneralPath path;
    private Point2D[] pts;
    private float size;
    private Ellipse2D ellipse = new Ellipse2D.Double();
	private BufferedImage bimg;

	/**
	 * init background
	 */
	
    public void init() {
    	
        setBackground(Color.white);
        
    } // end of method init

    public void draw() {
    	
        repaint();
        
    } // end of method draw
    
    /**
     * reset view: the shape of arms, etc
     */
    public void reset(int w, int h) {
    	
        size = (w > h) ? h/6f : w/6f;
        
        for (int i = 0; i < lines.length; i++) {
        	
            lines[i] = new Line2D.Float(0,0,size,0);
            strokes[i] = new BasicStroke(size/3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
            rAmt[i] = 270;		// vertical
            
        } // end of for
        
        speed[0] = 0;	// make second to move forward, but not the other // changed 6 to 0
        speed[1] = 0;
        speed[2] = 0;

        path = new GeneralPath();
        path.moveTo(size, -size/2);
        path.lineTo(size+size/2, 0);
        path.lineTo(size, +size/2);

        ellipse.setFrame(w/2-size*2-4.5f,h/2-size*2-4.5f,size*4,size*4);
        double linApproxLen = 0.75*size*0.258819;	// sin(15 degree)
        PathIterator pi = ellipse.getPathIterator(null, linApproxLen);
        Point2D[] points = new Point2D[100];
        int num_pts = 0;
        
        while ( !pi.isDone() ) {
        	
            float[] pt = new float[6];
            
            switch ( pi.currentSegment(pt) ) {
            
                case FlatteningPathIterator.SEG_MOVETO:
                case FlatteningPathIterator.SEG_LINETO:
                    points[num_pts] = new Point2D.Float(pt[0], pt[1]);
                    num_pts++;
                    
            } // end of switch
            
            pi.next();
        } // end of while
        
        pts = new Point2D[num_pts];
        System.arraycopy(points, 0, pts, 0, num_pts);
        
    } // end of method reset
    
	public void paint(Graphics g) {	
		
		Dimension d = getSize();
		/*// DD: **Note how the view is updating itself based on a calculation (the step())
        // call. This is *wrong* -- it should get a notification from
        // the controller, which is alerted by the model, what the current time is. 
		step(d.width, d.height);
		Graphics2D g2 = createGraphics2D(d.width, d.height);
		drawClockArms(d.width, d.height, g2);
		g2.dispose();
		g.drawImage(bimg, 0, 0, this);*/
		
		step();
		
		Graphics2D g2 = createGraphics2D(d.width, d.height);
		drawClockArms(d.width, d.height, g2);
		g2.dispose();
		g.drawImage(bimg, 0, 0, this);
		
	} // end of method paint
	
    public Graphics2D createGraphics2D(int w, int h) {
    	
    	// boilerplate Java 2D graphics code
        Graphics2D g2 = null;
        
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
        	
            bimg = (BufferedImage) createImage(w, h);
            reset(w, h);
            
        } // end of if
        
        g2 = bimg.createGraphics();
        g2.setBackground(getBackground());
        g2.clearRect(0, 0, w, h);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        return g2;
        
    } // end of method createGraphics2D
    
	private void drawClockArms(int w, int h, Graphics2D g2) {

        ellipse.setFrame(w/2-size,h/2-size,size*2,size*2);
        g2.setColor(Color.black);
        g2.draw(ellipse);

        for (int i = 0; i < lines.length; i++) {
        	
            AffineTransform at = AffineTransform.getTranslateInstance(w/2,h/2);
            at.rotate(Math.toRadians(rAmt[i]));
            g2.setStroke(strokes[i]);
            g2.setColor(colors[i]);
            g2.draw(at.createTransformedShape(lines[i]));
            g2.draw(at.createTransformedShape(path));
            
        } // end of for

        g2.setStroke(bs1);
        g2.setColor(Color.black);
        
        for (int i = 0; i < pts.length; i++) {
        	
            ellipse.setFrame(pts[i].getX(), pts[i].getY(), 9, 9);
            g2.draw(ellipse);
            
        } // end of for
        
    } // end of method drawClockArms
	
	/**
	 * step forward on the display: move arm forward
	 */
	
	// POSSIBLY change
    public void step() {
    	
    	rAmt[0] = model.getSecond() * 6 - 90;
        rAmt[1] = model.getMinute() * 6 - 90;
        rAmt[2] = model.getHour() * 30 - 90;
        
    } // end of method step
	
} // end of class DigitalClock3DDialsView