import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import java.util.Scanner;

public class AfterRotation implements GLEventListener{
    
    static int dg;
    
    static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
       
       Scanner kb = new Scanner (System.in);
       System.out.println("degree ? ");
       dg = kb.nextInt();
          //getting the capabilities object of GL2 profile
               AfterRotation l = new AfterRotation();
          //creating frame
          glcanvas.addGLEventListener(l);
          glcanvas.setSize(600, 600);
          
          final JFrame frame = new JFrame ("straight Line");
          //adding canvas to frame
          frame.getContentPane().add(glcanvas);
          frame.setSize(frame.getContentPane().getPreferredSize());
          frame.setVisible(true);
          
       }
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
            gl.glBegin (GL2.GL_LINES);//static field
            
          // side 0.6
           double x1=-0.3,y1=0.3;
         double x2=0.3,y2=0.3;
         double x3=-0.3,y3=-0.3;
         double x4=0.3,y4=-0.3;
         double x5=0.0,y5=0.0;
         double x6=0.6,y6=0.0;
         double x7=0.0,y7=-0.6;
         double x8=0.6,y8=-0.6;
 
          //1st square
          gl.glVertex2d(x1,y1);
          gl.glVertex2d(x2,y2);
          gl.glVertex2d(x1,y1);
          gl.glVertex2d(x3,y3);
          gl.glVertex2d(x3,y3);
          gl.glVertex2d(x4,y4);
          gl.glVertex2d(x2,y2);
          gl.glVertex2d(x4,y4);
        //2nd square
          gl.glVertex2d(x5,y5);
          gl.glVertex2d(x6,y6);
          gl.glVertex2d(x5,y5);
          gl.glVertex2d(x7,y7);
          gl.glVertex2d(x7,y7);
          gl.glVertex2d(x8,y8);
          gl.glVertex2d(x6,y6);
          gl.glVertex2d(x8,y8);
          //corner connect
          gl.glVertex2d(x1,y1);
          gl.glVertex2d(x5,y5);
          gl.glVertex2d(x2,y2);
          gl.glVertex2d(x6,y6);
          gl.glVertex2d(x3,y3);
          gl.glVertex2d(x7,y7);
          gl.glVertex2d(x4,y4);
          gl.glVertex2d(x8,y8);
          
          int degree;
          double radian = Math.toRadians(dg);
          //new points
          double x1new=x1*Math.cos(radian) - y1*Math.sin(radian);
          double y1new=x1*Math.sin(radian) + y1*Math.cos(radian);
          double x2new=x2*Math.cos(radian) - y2*Math.sin(radian);
          double y2new=x2*Math.sin(radian) + y2*Math.cos(radian);
          double x3new=x3*Math.cos(radian) - y3*Math.sin(radian);
          double y3new=x3*Math.sin(radian) + y3*Math.cos(radian);
          double x4new=x4*Math.cos(radian) - y4*Math.sin(radian);
          double y4new=x4*Math.sin(radian) + y4*Math.cos(radian);
          double x5new=x5*Math.cos(radian) - y5*Math.sin(radian);
          double y5new=x5*Math.sin(radian) + y5*Math.cos(radian);
          double x6new=x6*Math.cos(radian) - y6*Math.sin(radian);
          double y6new=x6*Math.sin(radian) + y6*Math.cos(radian);
          double x7new=x7*Math.cos(radian) - y7*Math.sin(radian);
          double y7new=x7*Math.sin(radian) + y7*Math.cos(radian);
          double x8new=x8*Math.cos(radian) - y8*Math.sin(radian);
          double y8new=x8*Math.sin(radian) + y8*Math.cos(radian);
          
          //new 1 square
          gl.glVertex2d(x1new,y1new);
          gl.glVertex2d(x2new,y2new);
          gl.glVertex2d(x1new,y1new);
          gl.glVertex2d(x3new,y3new);
          gl.glVertex2d(x3new,y3new);
          gl.glVertex2d(x4new,y4new);
          gl.glVertex2d(x2new,y2new);
          gl.glVertex2d(x4new,y4new);
        
          //new 2 square
          gl.glVertex2d(x5new,y5new);
          gl.glVertex2d(x6new,y6new);
          gl.glVertex2d(x5new,y5new);
          gl.glVertex2d(x7new,y7new);
          gl.glVertex2d(x7new,y7new);
          gl.glVertex2d(x8new,y8new);
          gl.glVertex2d(x6new,y6new);
          gl.glVertex2d(x8new,y8new);
          //new cube
          gl.glVertex2d(x1new,y1new);
          gl.glVertex2d(x5new,y5new);
          gl.glVertex2d(x2new,y2new);
          gl.glVertex2d(x6new,y6new);
          gl.glVertex2d(x3new,y3new);
          gl.glVertex2d(x7new,y7new);
          gl.glVertex2d(x4new,y4new);
          gl.glVertex2d(x8new,y8new);
          
          
 
          
          gl.glEnd();
              
   }
   
   public void dispose(GLAutoDrawable arg0) {
      //method body
   }

   
   public void init(GLAutoDrawable drawable) {
      // method body
       //4. drive the display() in a loop
        }
   
   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
      // method body
   }
   //end of main
}//end of class import javax.media.opengl.GL2;