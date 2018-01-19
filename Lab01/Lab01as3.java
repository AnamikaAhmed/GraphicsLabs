
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Lab01as3 implements GLEventListener{
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	      Lab01as3 l = new Lab01as3();
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
       	  File file=new File("C:\\Users\\User\\Documents\\NetBeansProjects\\Lab01as3\\src\\lab01as3\\text.txt");
       	  Scanner sc;
		try {
			sc = new Scanner(file);
			//String line=sc.nextLine();
       	    //System.out.println(line);
       	    while(sc.hasNextInt()){
       	    	float x=(float)(sc.nextInt())/1000;
       	    	float y=(float)(sc.nextInt())/1000;
       	    	System.out.println(x+" " +y);
       	    	gl.glVertex2d(x,y);
       	    	System.out.println("       ====================");
       	    	
       	    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found==============");
			e.printStackTrace();
		}
       	  
       	  
       	  
          /*gl.glVertex2d(0.5f,0.5f);
          gl.glVertex2d(0.5f,-0.5f);
          gl.glVertex2d(-0.5f,0.5f);
          gl.glVertex2d(-0.5f,-0.5f);*/
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