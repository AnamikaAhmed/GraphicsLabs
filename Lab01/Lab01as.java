
import java.util.Random;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Lab01as implements GLEventListener{
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	      Lab01as l = new Lab01as();
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
       		//gl.glVertex2d(points[i][0],points[i][1]);
       	  }
          //sorting based on X
       	  for(int i=0;i<noofpoints;){
              if(points[i][0]>points[i+1][0]){
                  float temp = points [i][0];
                  points[i][0]= points[i+1][0];
                  points[i+1][0]=temp;
              }
//              if(points[i][1]>points[i+1][1]){
//                  float temp = points [i][1];
//                  points[i][1]= points[i+1][1];
//                  points[i+1][1]=temp;
//              }
              i+=2;
          }
          
//          points[0][0]=-0.5f; points[0][1]=0.9f;
//          points[1][0]=0.5f; points[1][1]=0.2f;
//          points[2][0]=-0.5f; points[2][1]=-0.5f;
//          points[3][0]=0.5f; points[3][1]=0.6f;
//          points[4][0]=-0.5f; points[4][1]=-0.5f;
//          points[5][0]=0.5f; points[5][1]=0.7f;
//          points[6][0]=-0.5f; points[6][1]=-0.5f;
//          points[7][0]=0.5f; points[7][1]=0.8f;
//          points[8][0]=-0.5f; points[8][1]=-0.5f;
//          points[9][0]=0.5f; points[9][1]=0.9f;
//          points[10][0]=-0.5f; points[10][1]=-0.5f;
//          points[11][0]=0.5f; points[11][1]=-0.1f;
//          points[12][0]=-0.5f; points[12][1]=-0.5f;
//          points[13][0]=0.5f; points[13][1]=-0.5f;
//          points[14][0]=-0.5f; points[14][1]=-0.5f;
//          points[15][0]=0.5f; points[15][1]=-0.3f;
//          points[16][0]=-0.5f; points[16][1]=-0.5f;
//          points[17][0]=0.5f; points[17][1]=-0.4f;
//          points[18][0]=-0.5f; points[18][1]=-0.5f;
//          points[19][0]=0.5f; points[19][1]=-0.45f;
//          
          for(int i=0;i<noofpoints;i++){
              System.out.println(" x"+i+" "+points[i][0]+" y"+i+" "+points[i][1]);
          }
          //determining the slope
          float [] m = new float [nooflines];
          int c=0;
          for(int i=0;i<noofpoints;){
              m[c]=(points[i+1][1]-points[i][1])/(points[i+1][0]-points[i][0]);
              //m[c]=+m[c];
              System.out.println(m[c]);
              c++;
              i+=2;
          }
       	  //implementing DDA algorithm
          int c1=0;
          for(int i=0;i<noofpoints;i+=2){
              float x;
              float prev_x;
              float y=0;
              float prev_y;
              if(m[c1]>-1 && m[c1]<1){
                  gl.glColor3f(0.0f, 0.0f, 1.0f);
                 gl.glVertex2d(points[i][0],points[i][1]);
                 System.out.println(points[i][0]+" "+points[i][1]+" "+points[i+1][0]+" "+points[i+1][1]+" "+m[c1]);
                 x=points[i][0];
                 y=points[i][1];
                 prev_y=points[i][1];
                for(x=points[i][0];x<points[i+1][0];){
                    x+=0.01;
                     if(points[i][1]<points[i+1][1])
                        y=prev_y+(m[c1])/100;
                    else if(points[i][1]>points[i+1][1]){
                        y=prev_y+(m[c1])/100; System.out.println("entering here, decrementing y value");
                    }
                    prev_y=y;
                    gl.glColor3f(1.0f, 0.0f, 0.0f);
                    gl.glVertex2d(x,y);
                    System.out.println("printing line a "+x+" "+y);
                }
                //gl.glColor3f(1.0f, 0.0f, 1.0f);
                 gl.glVertex2d(points[i+1][0],points[i+1][1]);
              }
              else{
                  gl.glColor3f(0.0f, 0.0f, 1.0f);
                 gl.glVertex2d(points[i][0],points[i][1]);
                 System.out.println(points[i][0]+" "+points[i][1]+" "+points[i+1][0]+" "+points[i+1][1]+" "+m[c1]+"=========================================================================");
                 prev_x=points[i][0];
                 x=points[i][0];
                 y=points[i][1];
                for(x=points[i][0]; x<points[i+1][0];){
                    if(points[i][1]<points[i+1][1]){
                        y+=0.01;
                        x=(prev_x+(1/(m[c1]*100)));
                    }
                    else if(points[i][1]>points[i+1][1]){
                        y-=0.01;
                    x=(prev_x-(1/(m[c1]*100)));
                    }
                    prev_x=x;
                    //gl.glColor3f(0.0f, 1.0f, 0.0f);
                    gl.glVertex2d(x,y);
                    System.out.println("printing line b "+x+" "+y);
                }
                //gl.glColor3f(1.0f, 0.0f, 1.0f);
                 gl.glVertex2d(points[i+1][0],points[i+1][1]);
              }
              c1++;
              
              System.out.println("printed one line "+i);
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
