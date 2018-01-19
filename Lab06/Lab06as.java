package lab06as;
import java.util.Random;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Lab06as implements GLEventListener{
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	      Lab06as l = new Lab06as();
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
          int nooflines=600;
          int noofpoints=nooflines*2;
       	  
          float [][] points = new float[noofpoints][2];
          float yMax= 0.3f;
          float yMin= -0.3f;
          float xMax= 0.55f;
          float xMin= -0.45f;
          float ydec= (yMax - yMin)/nooflines;
       	  for(int i=0;i<noofpoints;i+=2){
              points[i][0]=xMin;
              points[i+1][0]=xMax;
              points[i][1]=yMax;
              points[i+1][1]=yMax;
              yMax-=ydec;
          }
          
          for(int i=0;i<noofpoints;i++){
              //System.out.println(" x"+i+" "+points[i][0]+" y"+i+" "+points[i][1]);
          }
          //determining the slope
          float [] m = new float [nooflines];
          int c=0;
          for(int i=0;i<noofpoints;){
              m[c]=(points[i+1][1]-points[i][1])/(points[i+1][0]-points[i][0]);
              //m[c]=+m[c];
              //System.out.println(m[c]);
              c++;
              i+=2;
          }
       	  //implementing DDA algorithm
          int c1=0;
          float green=0.4f;
          for(int i=0;i<noofpoints;i+=2){
              green=green+0.0005f;
              float x;
              float prev_x;
              float y=0;
              float prev_y;
              if(m[c1]>-1 && m[c1]<1){
                  gl.glColor3f(0.0f, green, 0.2f);
                 gl.glVertex2d(points[i][0],points[i][1]);
                 //System.out.println(points[i][0]+" "+points[i][1]+" "+points[i+1][0]+" "+points[i+1][1]+" "+m[c1]);
                 x=points[i][0];
                 y=points[i][1];
                 prev_y=points[i][1];
                for(x=points[i][0];x<points[i+1][0];){
                    x+=0.001;
                     if(points[i][1]<points[i+1][1])
                        y=prev_y+(m[c1])/1000;
                    else if(points[i][1]>points[i+1][1]){
                        y=prev_y+(m[c1])/1000; //System.out.println("entering here, decrementing y value");
                    }
                    prev_y=y;
                    //gl.glColor3f(1.0f, 0.0f, 0.0f);
                    gl.glVertex2d(x,y);
                    //System.out.println("printing line a "+x+" "+y);
                }
                //gl.glColor3f(1.0f, 0.0f, 1.0f);
                 gl.glVertex2d(points[i+1][0],points[i+1][1]);
              }
              else{
                 // gl.glColor3f(0.0f, 0.0f, 1.0f);
                 gl.glVertex2d(points[i][0],points[i][1]);
                 //System.out.println(points[i][0]+" "+points[i][1]+" "+points[i+1][0]+" "+points[i+1][1]+" "+m[c1]+"=========================================================================");
                 prev_x=points[i][0];
                 x=points[i][0];
                 y=points[i][1];
                for(x=points[i][0]; x<points[i+1][0];){
                    if(points[i][1]<points[i+1][1]){
                        y+=0.001;
                        x=(prev_x+(1/(m[c1]*1000)));
                    }
                    else if(points[i][1]>points[i+1][1]){
                        y-=0.001;
                    x=(prev_x-(1/(m[c1]*100)));
                    }
                    prev_x=x;
                    //gl.glColor3f(0.0f, 1.0f, 0.0f);
                    gl.glVertex2d(x,y);
                    //System.out.println("printing line b "+x+" "+y);
                }
                //gl.glColor3f(1.0f, 0.0f, 1.0f);
                 gl.glVertex2d(points[i+1][0],points[i+1][1]);
              }
              c1++;
              
              //System.out.println("printed one line "+i);
          }
          nooflines=1200;
          noofpoints=nooflines*2;
          float [][] points2 = new float[noofpoints][2];
          yMax= 0.3f;
          yMin= -0.9f;
          xMax= -0.45f;
          xMin= -0.5f;
          ydec= (yMax - yMin)/nooflines;
       	  for(int i=0;i<noofpoints;i+=2){
              points2[i][0]=xMin;
              points2[i+1][0]=xMax;
              points2[i][1]=yMax;
              points2[i+1][1]=yMax;
              yMax-=ydec;
          }
          float [] m1 = new float [nooflines];
          c=0;
          for(int i=0;i<noofpoints;){
              m1[c]=(points2[i+1][1]-points2[i][1])/(points2[i+1][0]-points2[i][0]);
              //m[c]=+m[c];
              //System.out.println(m[c]);
              c++;
              i+=2;
          }
       	  //implementing DDA algorithm
          c1=0;
          for(int i=0;i<noofpoints;i+=2){
              
              float x;
              float prev_x;
              float y=0;
              float prev_y;
              if(m1[c1]>-1 && m1[c1]<1){
                  gl.glColor3f(0.4f, 0.2f, 0.0f);
                 gl.glVertex2d(points2[i][0],points2[i][1]);
                 //System.out.println(points[i][0]+" "+points[i][1]+" "+points[i+1][0]+" "+points[i+1][1]+" "+m[c1]);
                 x=points2[i][0];
                 y=points2[i][1];
                 prev_y=points2[i][1];
                for(x=points2[i][0];x<points2[i+1][0];){
                    x+=0.001;
                     if(points2[i][1]<points2[i+1][1])
                        y=prev_y+(m1[c1])/1000;
                    else if(points2[i][1]>points2[i+1][1]){
                        y=prev_y+(m1[c1])/1000; //System.out.println("entering here, decrementing y value");
                    }
                    prev_y=y;
                    //gl.glColor3f(1.0f, 0.0f, 0.0f);
                    gl.glVertex2d(x,y);
                    //System.out.println("printing line a "+x+" "+y);
                }
                //gl.glColor3f(1.0f, 0.0f, 1.0f);
                 gl.glVertex2d(points2[i+1][0],points2[i+1][1]);
              }
              else{
                 // gl.glColor3f(0.0f, 0.0f, 1.0f);
                 gl.glVertex2d(points2[i][0],points2[i][1]);
                 //System.out.println(points[i][0]+" "+points[i][1]+" "+points[i+1][0]+" "+points[i+1][1]+" "+m[c1]+"=========================================================================");
                 prev_x=points2[i][0];
                 x=points2[i][0];
                 y=points2[i][1];
                for(x=points2[i][0]; x<points2[i+1][0];){
                    if(points2[i][1]<points2[i+1][1]){
                        y+=0.001;
                        x=(prev_x+(1/(m1[c1]*1000)));
                    }
                    else if(points2[i][1]>points2[i+1][1]){
                        y-=0.001;
                    x=(prev_x-(1/(m1[c1]*100)));
                    }
                    prev_x=x;
                    //gl.glColor3f(0.0f, 1.0f, 0.0f);
                    gl.glVertex2d(x,y);
                    //System.out.println("printing line b "+x+" "+y);
                }
                //gl.glColor3f(1.0f, 0.0f, 1.0f);
                 gl.glVertex2d(points2[i+1][0],points2[i+1][1]);
              }
              c1++;
              
              //System.out.println("printed one line "+i);
          }
          float red=0.85f;
          for(float r=0.2f;r>0;r-=0.001){
              cohensutherland(gl, r, red);
              red+=0.001f;
          }
          
          gl.glEnd();
          
   }
   void cohensutherland(GL2 gl, float r, float red){
       
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
           draw8ways(gl, x, y, xc, yc, red);
       }
   }
   
   void draw8ways(GL2 gl, float x, float y, float xc, float yc, float red){
       x=x/1000;
       y=y/1000;
       gl.glColor3f(red, 0, 0);
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
