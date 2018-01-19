
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;


public class Lab03 {
    
    
    private static final int SIZE_X = 1000;
    private static final int SIZE_Y = 1000;
    
    public static void main(String[] args) {
        
        GLCapabilities capabilities = new GLCapabilities(GLProfile.get(GLProfile.GL2));
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        
        //This is where we put graphics event listener. 
        //Implement the Event Listener class to use a more object
        //oriented approach
        glcanvas.addGLEventListener(new SlopeIndependent());
        
        glcanvas.setSize(SIZE_X, SIZE_Y);
        JFrame frame = new JFrame("My GL canvas");
        //Close on frame close button
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        frame.getContentPane().add(glcanvas);
        frame.setSize(SIZE_X, SIZE_Y);
        frame.setVisible(true);
    }
    
    static class ConvertedPoint
    {
        float x;
        float y;

        public ConvertedPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
        
        
    }
    
    static interface ZoneCalculator
    {
        ConvertedPoint calculate(float x, float y); 
    }
    
    
    
    static class Point
    {
        private float x1;
        private float x2;
        private float y1;
        private float y2;
        private final GL2 canvas;
        
        private final ZoneCalculator calculator;
        private static final float FACTOR = 0.0001f;
        
        public Point(float x1, float x2, float y1, float y2, GL2 canvas) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.canvas = canvas;
            calculator = findZone();
        }
        
        public void drawPoint()
        {
            float dy = y2 - y1;
            float dx = x2 - x1;
            
            float decisionP = dy - (dx / 2);
            float tempX = x1, tempY = y1;
            ConvertedPoint point = calculator.calculate(tempX, tempY);
            canvas.glVertex2d(point.getX(), point.getY());
            
            while(tempX <= x2)
            {
                tempX += FACTOR;
                if(decisionP < 0)
                {
                    //E chosen
                    decisionP += dy;
                }
                else
                {
                    decisionP += dy - dx;
                    tempY += FACTOR;
                }
                point = calculator.calculate(tempX, tempY);
                canvas.glVertex2d(point.getX(), point.getY());
            }
        }
        
        private ZoneCalculator findZone()
        {
            float dx = x2 - x1;
            float dy = y2 - y1;
            System.out.println("here dx " + dx);
            System.out.println("here dy " + dy);
            
            if(dx >= 0 && dy >= 0)
            {
                //z0 or z1
                if(Math.abs(dx) >= Math.abs(dy))
                {
                    //z0
                    System.out.println("ZONE 0");
                    return new ZoneCalculator() {
                        @Override
                        public ConvertedPoint calculate(float x, float y) {
                            return new ConvertedPoint(x, y);
                        }
                    };
                }
                else
                {
                    //z1
                    float temp = x1;
                    x1 = y1;
                    y1 = temp;
                    
                    temp = x2;
                    x2 = y2;
                    y2 = temp;
                    
                    return new ZoneCalculator() {
                        @Override
                        public ConvertedPoint calculate(float x, float y) {
                            float temp = x;
                            x = y;
                            y = temp;
                            return new ConvertedPoint(x, y);
                        }
                    };
                }
            }
            else if(dx < 0 && dy >= 0)
            {
                //z2 or z3
                if(Math.abs(dx) >= Math.abs(dy))
                {
                    //z3
                    x2 *= -1;
                    System.out.println("ZONE 3");
                    return new ZoneCalculator() {
                        @Override
                        public ConvertedPoint calculate(float x, float y) {
                            return new ConvertedPoint(x * -1, y);
                        }
                    };
                }
                else
                {
                    //z2
                    x2 *= -1;
                    float temp = x1;
                    x1 = y1;
                    y1 = temp;
                    
                    temp = x2;
                    x2 = y2;
                    y2 = temp;
                    
                    return new ZoneCalculator() {
                        @Override
                        public ConvertedPoint calculate(float x, float y) {
                            x *= -1;
                            return new ConvertedPoint(y, x);
                        }
                    };
                }
            }
            else if(dx < 0 && dy < 0)
            {
                //z4 or z5
                if(Math.abs(dx) >= Math.abs(dy))
                {
                    //z4
                    x2 *= -1;
                    y2 *= -1;
                    
                    return new ZoneCalculator() {
                        @Override
                        public ConvertedPoint calculate(float x, float y) {
                            return new ConvertedPoint(x * -1, y * -1);
                        }
                    };
                }
                else
                {
                    //z5
                    x2 *= -1;
                    y2 *= -1;
                    
                    float temp = x1;
                    x1 = y1;
                    y1 = temp;
                    
                    temp = x2;
                    x2 = y2;
                    y2 = temp;
                    
                    return new ZoneCalculator() {
                        @Override
                        public ConvertedPoint calculate(float x, float y) {
                            x *= -1;
                            y *= -1;
                            return new ConvertedPoint(y, x);
                        }
                    };
                }
            }
            else
            {
                //z6 or z7
                if(Math.abs(dx) >= Math.abs(dy))
                {
                    //z7
                    y2 *= -1;
                    return new ZoneCalculator() {
                        @Override
                        public ConvertedPoint calculate(float x, float y) {
                            return new ConvertedPoint(x, y * -1);
                        }
                    };
                }
                else
                {
                    y2 *= -1;
                    float temp = x1;
                    x1 = y1;
                    y1 = temp;
                    
                    temp = x2;
                    x2 = y2;
                    y2 = temp;
                    
                    return new ZoneCalculator() {
                        @Override
                        public ConvertedPoint calculate(float x, float y) {
                            y *= -1;
                            return new ConvertedPoint(y, x);
                        }
                    };
                }
            }
        }
        
             
    }
    
    static class SlopeIndependent implements GLEventListener
    {

        @Override
        public void init(GLAutoDrawable glad) {
        
        }

        @Override
        public void dispose(GLAutoDrawable glad) {
        }

        @Override
        public void display(GLAutoDrawable drawable) {
            GL2 gl = drawable.getGL().getGL2();
            gl.glBegin(GL2.GL_POINTS);
            BufferedReader reader = null;
            
            
            gl.glVertex2i(1, 1);
            
            try {
                reader = new BufferedReader(new FileReader(new File("no_slope_points")));
                String line = null;
                while((line = reader.readLine()) != null)
                {
                    String points[] = line.split("\\s+");
                    float x1 = Float.parseFloat(points[0]) / SIZE_X;
                    float y1 = Float.parseFloat(points[1]) / SIZE_Y;
                    float x2 = Float.parseFloat(points[2]) / SIZE_X;
                    float y2 = Float.parseFloat(points[3]) / SIZE_Y;
                    System.out.println("");
                    new Point(x1, x2, y1, y2, gl).drawPoint();
                    
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Lab03.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Lab03.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(Lab03.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    gl.glEnd();
                }
                
            }
            
        }

        @Override
        public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        
        }
        
    }
}