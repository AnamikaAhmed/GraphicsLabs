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


public class Lab02 {
    
    
    private static final int SIZE_X = 1000;
    private static final int SIZE_Y = 1000;
    
    public static void main(String[] args) {
        
        GLCapabilities capabilities = new GLCapabilities(GLProfile.get(GLProfile.GL2));
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        
        //This is where we put graphics event listener. 
        //Implement the Event Listener class to use a more object
        //oriented approach
        glcanvas.addGLEventListener(new SlopeDependent());
        
        glcanvas.setSize(SIZE_X, SIZE_Y);
        JFrame frame = new JFrame("My GL canvas");
        //Close on frame close button
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        frame.getContentPane().add(glcanvas);
        frame.setSize(SIZE_X, SIZE_Y);
        frame.setVisible(true);
    }
    
    
    private static class Point
    {
        private float x1;
        private float y1;
        private float x2;
        private float y2;
        private byte region = -1;
        private static final float FACTOR = 0.001f;
    
        public Point(float x1, float y1, float x2, float y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            try
            {
                int slope = (Math.round(this.y2) - Math.round(this.y1)) / (Math.round(this.x2) - Math.round(this.x1));
                
                if(slope == 1)
                {
                    region = 1;
                }
                else if(slope == -1)
                {
                    region = 2;
                }
                else if(slope == 0)
                {
                    region = 3;
                }
                else
                {
                    System.err.println("Invalid");
                }
            }
            catch(ArithmeticException ex)
            {
                region = 4;
            }
            
            
        }
        
        
        private void draw(GL2 canvas)
        {
            System.out.println(region);
            if(region == 1)
            {
                if(x1 < x2)
                {
                    while(x1 <= x2)
                    {
                        canvas.glVertex2d(x1, y1);
                        x1 += FACTOR;
                        y1 += FACTOR;
                    }
                }
                else
                {
                    while(x2 <= x1)
                    {
                        canvas.glVertex2d(x2, y2);
                        x2 += FACTOR;
                        y2 += FACTOR;
                    }
                }
            }
            else if(region == 2)
            {
                if(x1 < x2)
                {
                    while(x1 <= x2)
                    {
                        canvas.glVertex2d(x1, y1);
                        x1 += FACTOR;
                        y1 -= FACTOR;
                    }
                }
                else
                {
                    while(x2 <= x1)
                    {
                        canvas.glVertex2d(x2, y2);
                        x2 += FACTOR;
                        y2 -= FACTOR;
                    }
                }
            }
            else if(region == 3)
            {
                if(x1 < x2)
                {
                    while(x1 <= x2)
                    {
                        canvas.glVertex2d(x1, y1);
                        x1 += FACTOR;
                        
                    }
                }
                else
                {
                    while(x2 <= x1)
                    {
                        canvas.glVertex2d(x2, y2);
                        x2 += FACTOR;
                    }
                }
            }
            else if(region == 4)
            {
                if(y1 < y2)
                {
                    while(y1 <= y2)
                    {
                        canvas.glVertex2d(x1, y1);
                        y1 += FACTOR;
                        
                    }
                }
                else
                {
                    while(y2 <= y1)
                    {
                        canvas.glVertex2d(x2, y2);
                        y2 += FACTOR;
                    }
                }
            }
        }
        
        
    }
    
    static class SlopeDependent implements GLEventListener
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
            try {
                reader = new BufferedReader(new FileReader(new File("slope_points")));
                String line = null;
                while((line = reader.readLine()) != null)
                {
                    String points[] = line.split("\\s+");
                    float x1 = Float.parseFloat(points[0]) / SIZE_X;
                    float y1 = Float.parseFloat(points[1]) / SIZE_Y;
                    float x2 = Float.parseFloat(points[2]) / SIZE_X;
                    float y2 = Float.parseFloat(points[3]) / SIZE_Y;
                    new Point(x1, y1, x2, y2).draw(gl);
                    
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Lab02.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Lab02.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(Lab02.class.getName()).log(Level.SEVERE, null, ex);
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
