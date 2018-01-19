
import java.util.Random;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Lab05cw implements GLEventListener{
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	      Lab05cw l = new Lab05cw();
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
       	  gl.glBegin (GL2.GL_LINES);//static field
          gl.glVertex3f(-0.5f, 0.5f, 0);
          gl.glVertex3f(-0.5f, -0.5f, 0);
          gl.glEnd();
          gl.glBegin (GL2.GL_LINES);//static field
          gl.glVertex3f(-0.5f, -0.5f, 0);
          gl.glVertex3f(0.5f, -0.5f, 0);
          gl.glEnd();
          gl.glBegin (GL2.GL_LINES);//static field
          gl.glVertex3f(0.5f, -0.5f, 0);
          gl.glVertex3f(0.5f, 0.5f, 0);
          gl.glEnd();
          gl.glBegin (GL2.GL_LINES);//static field
          gl.glVertex3f(0.5f, 0.5f, 0);
          gl.glVertex3f(-0.5f, 0.5f, 0);
          gl.glEnd();
          gl.glFlush();
          int nooflines=10;
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
                  gl.glBegin(GL2.GL_POINTS);
       		  points[i][0]=x;
                  points[i][1]=y;
                  
       		//gl.glVertex2d(points[i][0],points[i][1]);
                gl.glEnd();
       	  }
          points[0][0]=0.5f; points [0][1]=-0.8f;
          points[1][0]=0.3f; points [1][1]=0.8f;
          for(int i=0;i<noofpoints;i+=2){
              gl.glBegin (GL2.GL_LINES);//static field
              gl.glColor3f(0.0f, 0.0f, 1.0f);
              System.out.println(points[i][0]+" "+points[i][1]+" "+points[i+1][0]+" "+points[i+1][1]);
              gl.glVertex3f(points[i][0], points[i][1], 0);
              gl.glVertex3f(points[i+1][0], points[i+1][1], 0);
              gl.glEnd();
              gl.glFlush();
              cohensutherland(points[i][0], points[i][1], points[i+1][0], points[i+1][1], gl);
          }
          
          
          
   }
   void cohensutherland(float x0, float y0, float x1, float y1, GL2 gl){
       int code, code0, code1;
       int TOP = 8;
       int BOTTOM = 4;
       int RIGHT = 2;
       int LEFT = 1;
       float x=0, y=0;
       float xMax = +0.5f;
       float xMin = -0.5f;
       float yMax = +0.5f;
       float yMin = -0.5f;
       code0 = makeCode(x0, y0);
       code1 = makeCode(x1, y1);
       System.out.println("code0 "+Integer.toString(code0, 2));
       System.out.println("code1 "+Integer.toString(code1, 2));
       while(true){
           if((code0 | code1)==0){
               gl.glBegin (GL2.GL_LINES);//fully accepted
               gl.glColor3f(0.0f, 1.0f, 0.0f);
               System.out.println(x0+" "+y0+" "+x1+" "+y1);
               gl.glVertex3f(x0, y0, 0);
               gl.glVertex3f(x1, y1, 0);
               gl.glEnd();
               break;
           }
           else if((code0 & code1)>0){ // fully rejected
               gl.glBegin (GL2.GL_LINES);//static field
               gl.glColor3f(1.0f, 0.0f, 0.0f);
               gl.glVertex3f(x0, y0, 0);
               gl.glVertex3f(x1, y1, 0);
               gl.glEnd();
               break;
           }
           else{ // partial acceptence 
               if(code0>0) code=code0;
               else code=code1;
               if((code & TOP)>0){
                  y = yMax;
                  x = x0 + ((yMax - y0)/(y1-y0)*(x1-x0));
               }
               else if((code & BOTTOM)>0){
                   y = yMin;
                   x = x0 + ((yMin - y0)/(y1-y0)*(x1-x0));
               }
               else if((code & RIGHT)>0){
                   x = xMax;
                   y = y0 + ((xMax - x0)/(x1-x0)*(y1-y0));
               }
               else if((code & LEFT)>0){
                   x = xMin;
                   y = y0 + ((xMin - x0)/(x1-x0)*(y1-y0));
               }
               if(code == code0){
                   x0 = x;
                   y0 = y;
                   code0 = makeCode(x0, y0);
               }
               else if(code == code1){
                   x1 = x;
                   y1 = y;
                   code1 = makeCode(x1, y1);
               }              
           }// partial acceptence ends
       }//while loop ends
   }
   
   int makeCode(float x, float y){
       int code=0;
       float xMax = +0.5f;
       float xMin = -0.5f;
       float yMax = +0.5f;
       float yMin = -0.5f;
       int TOP = 8;
       int BOTTOM = 4;
       int RIGHT = 2;
       int LEFT = 1;
       if(y>yMax){
           code+=TOP;
       }
       else if(y<yMin){
           code+=BOTTOM;
       }
       if(x>xMax){
           code+=RIGHT;
       }
       else if(x<xMin){
           code+=LEFT;
       }
       return code;
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
