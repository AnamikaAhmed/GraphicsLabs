
import java.util.Random;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Lab01as2 implements GLEventListener{
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	      Lab01as2 l = new Lab01as2();
	      //creating frame
	      glcanvas.addGLEventListener(l);
	      glcanvas.setSize(600, 400);
	      
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
          int nooflines=300;
          int noofpoints=nooflines*2;
       	  Random ran=new Random();
          float [][] points = new float[noofpoints][2];
          //random points generation
       	  for(int i=0;i<noofpoints;i++){
       		  float x=ran.nextFloat();
       		  float y=ran.nextFloat();
       		  int c=(int)(Math.random()*4+1);
       		  if(c==1){
       			  x=-x;
       		  }
       		  else if(c==2){
       			  y=-y;
       		  }
       		  else if(c==3){
       			  x=-x;y=-y;
       		  }
       		  else{       			  
       		  }
       		  points[i][0]=x;
                  points[i][1]=y;
       		gl.glVertex2d(points[i][0],points[i][1]);
       	  }
          
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
}//end of classimport javax.media.opengl.GL2;