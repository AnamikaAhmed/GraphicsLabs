package lab06cw;
import java.util.Random;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Lab06cw implements GLEventListener{
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	      Lab06cw l = new Lab06cw();
	      //creating frame
	      glcanvas.addGLEventListener(l);
	      glcanvas.setSize(600, 600);
	      
	      final JFrame frame = new JFrame ("straight Line");
	      //adding canvas to frame
	      frame.getContentPane().add(glcanvas);
	      frame.setSize(frame.getContentPane().getPreferredSize());
	      frame.setVisible(true);
              frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	      
	   }
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
       	  gl.glBegin (GL2.GL_POINTS);//static field
          cohensutherland(gl);
          gl.glEnd();
          
   }
   
   void cohensutherland(GL2 gl){
       float r=0.2f;
       r=r*1000;
       float dinit=1-r;
       float xc=0;
       float yc=0;
       float x=0;
       float y=r;
       while(x<y){
           System.out.println(dinit);
           if(dinit<0){
               dinit+=2*x+1;
           }
           else{
               dinit+=2*x-2*y+1;
               y--;
           }
           x++;
           draw8ways(gl, x, y, xc, yc);
       }
   }
   
   void draw8ways(GL2 gl, float x, float y, float xc, float yc){
       x=x/1000;
       y=y/1000;
       gl.glVertex2d(x+xc, y+yc);
       gl.glVertex2d(-x+xc, y+yc);
       gl.glVertex2d(x+xc, -y+yc);
       gl.glVertex2d(-x+xc, -y+yc);
       
       gl.glVertex2d(y+xc, x+yc);
       gl.glVertex2d(-y+xc, x+yc);
       gl.glVertex2d(y+xc, -x+yc);
       gl.glVertex2d(-y+xc, -x+yc);
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
}//end of classimport javax.media.opengl.GL2;