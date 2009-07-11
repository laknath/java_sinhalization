/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package localization;
import java.awt.*;

/** Class which gives (x,y) points where the frame must display on */
public class Location {
    private double screenX ;
    private double screenY  ;
    private Dimension screen ;
    private int locationX ;
    private int locationY ;

    public Location(int x,int y)   
    {
        screen = new Dimension(Toolkit.getDefaultToolkit().getScreenSize() ) ;
        screenX = screen.getWidth() ;
        screenY = screen.getHeight() ;

        locationX = (int)(screenX-x)/2 ;
        locationY = (int)(screenY-y)/2 ;
    }

    public Dimension getLocation()
    {
        return new Dimension(locationX,locationY) ;
    }

    public int getX()
    {
        return locationX ;
    }

    public int getY()
    {
        return locationY ;
    }
    
    public Point getPoint(){
        return new Point(this.getX(),this.getY());
    }

}


