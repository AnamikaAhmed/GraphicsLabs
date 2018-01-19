
import java.util.Random;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Lab04Cw implements GLEventListener{
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	      Lab04Cw l = new Lab04Cw();
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
          int nooflines=1;
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
                  
       	  }
          for(float i=-1.0f;i<=1.0f;i+=0.001){
              gl.glColor3f(1.0f, 0.0f, 1.0f);
              gl.glVertex2d(i,0);
          }
          for(float i=-1.0f;i<=1.0f;i+=0.001){
              gl.glColor3f(1.0f, 0.0f, 1.0f);
              gl.glVertex2d(0,i);
          }
//          points[0][0]=0.5f; points [0][1]=-0.5f;
//          points[1][0]=0.3f; points [1][1]=0.5f;
          System.out.println("x "+points[0][0]+" y "+points[0][1]);
          System.out.println("x "+points[1][0]+" y "+points[1][1]);
       		gl.glVertex2d(points[0][0],points[0][1]);
                gl.glVertex2d(points[1][0],points[1][1]);
          float dx[]= new float [nooflines];
          float dy[]= new float [nooflines];
          for(int i=0,c=0;i<noofpoints;i+=2,c++){
          dx[c]= points[i+1][0] - points[i][0];
          dy[c]= points[i+1][1] - points [i][1];
          }
          int zone[]=new int[nooflines];
          for(int i=0, c=0;i<noofpoints;i+=2,c++){
              zone[c] = det_zone(points[i][0], points[i][1], points[i+1][0], points[i+1][1], dx[c], dy[c]);
          }
          int c1=0;
//          if(points[0][0]<0){
//                  points[0][0]=-points[0][0];
//              }
//              if(points[0][1]<0){
//                  points[0][1]=-points[0][1];
//              }
//              if(points[1][1]<0){
//                  points[1][1]=-points[1][1];
//              }
//              if(points[1][0]<0){
//                  points[1][0]=-points[1][0];
//              }
//          if(zone[c1]==1||zone[c1]==2||zone[c1]==5||zone[c1]==6){
//              float temp=points[0][0];
//              points[0][0]=points[0][1];
//              points[0][1]=temp;
//              
//              temp=points[1][1];
//              points[1][1]=points[1][0];
//              points[1][0]=temp;
//              
//          }
          for(int i=0;i<noofpoints;i++){
              float x1=points[i][0];
              float y1=points[i][1];
              if(zone[0]==0){
                  x1=x1;
                  y1=y1;
              }
              else if(zone[0]==1){
                  float temp=x1;
                  x1=y1;
                  y1=temp;
              }
              else if(zone[0]==2){
                  float temp=x1;
                  x1=-y1;
                  y1=temp;
              }
              else if(zone[0]==3){
                  x1=-x1;
                  y1=y1;
              }
              else if(zone[0]==4){
                  x1=-x1;
                  y1=-y1;
              }
              else if(zone[0]==5){
                  float temp=x1;
                  x1=-y1;
                  y1=-temp;
              }
              else if(zone[0]==6){
                  float temp=x1;
                  x1=y1;
                  y1=-temp;
              }
              else if(zone[0]==7){
                  x1=x1;
                  y1=-y1;
              }
              points[i][0]=x1;
              points[i][1]=y1;
          }
          if(points[0][0]>points[1][0]){
              float temp=points[0][0];
              points[0][0]=points[1][0];
              points[1][0]=temp;
          }
          if(points[0][1]>points[1][1]){
              float temp=points[0][1];
              points[0][1]=points[1][1];
              points[1][1]=temp;
          }
          System.out.println(points[0][0]+" "+points[0][1]+" "+points[1][0]+" "+points[1][1]+" ");
          System.out.println("dx "+dx[0]);
          System.out.println("dy "+dy[0]);
          float x=points[0][0],  y=points[0][1];
          dx[0]= points[1][0] - points[0][0];
          dy[0]= points[1][1] - points [0][1];
          float d=2*dy[0]-dx[0];
          System.out.println("d "+d);
          float dNE= 2* (dy[0]-dx[0]);
          System.out.println("dNE "+dNE);
          float dE= 2*dy[0];
          System.out.println("dE "+dE);
          int c2=0;
          while(x<points[1][0]){
              if(d<0){
                  d+=dE;
              }
              else{
                  d+=dNE;
                  y+=0.01;
              }
              System.out.println("d "+d);
              System.out.println("dNE "+dNE);
              System.out.println("dE "+dE);
              x+=0.01;
              
              //convert back
              if(zone[0]==0){
                  gl.glColor3f(1.0f, 0.0f, 0.0f);
                  gl.glVertex2d(x, y);
                  System.out.println("zone 0 x"+x+" y"+y);
                  System.out.println("x "+x+" y"+y);
              }
              else if(zone[0]==1){
                  gl.glColor3f(1.0f, 1.0f, 0.0f);
                  gl.glVertex2d(y, x);
                  System.out.println("zone 0 x"+x+" y"+y);
                  System.out.println("x "+y+" y"+x);
              }
              else if(zone[0]==2){
                  gl.glColor3f(0.0f, 0.0f, 1.5f);
                  gl.glVertex2d(y, -x);
                  System.out.println("x "+-y+" y"+x);
              }
              else if(zone[0]==3){
                  gl.glColor3f(0.0f, 0.0f, 1.0f);
                  gl.glVertex2d(-x, y);
                  System.out.println("x "+-x+" y"+y);
              }
              else if(zone[0]==4){
                  gl.glColor3f(0.0f, 1.0f, 0.0f);
                  gl.glVertex2d(-x, -y);
                  System.out.println("x "+-x+" y"+-y);
              }
              else if(zone[0]==5){
                  gl.glColor3f(0.0f, 1.0f, 1.0f);
                  gl.glVertex2d(-y, -x);
                  System.out.println("x "+-y+" y"+-x);
              }
              else if(zone[0]==6){
                  gl.glColor3f(1.0f, 0.0f, 0.0f);
                  gl.glVertex2d(-y, x);
                  System.out.println("zone 0 x"+x+" y"+y);
                  System.out.println("zone 6 x "+y+" y"+-x);
              }
              else if(zone[0]==7){
                  gl.glColor3f(1.0f, 0.5f, 0.5f);
                  gl.glVertex2d(x, -y);
                  System.out.println("x "+x+" y"+-y);
              }
              gl.glColor3f(2.0f, 2.0f, 2.0f);
              gl.glVertex2d(x, y);
              c2++;
          }
          
          
          
          gl.glEnd();
          
   }
   
   public int det_zone(float x0,float y0, float x1, float y1, float dx, float dy){
       int zone=0;
       float abs_dx=0;
       float abs_dy=0;
       if(dx<0){
           abs_dx=-dx;
       }
       else{
           abs_dx=dx;
       }
       if(dy<0){
           abs_dy=-dy;
       }
       else{
           abs_dy=dy;
       }
       if(abs_dx>abs_dy){//zone 0, 7, 3, 4
           if(dx>0 && dy>0){
               zone=0;
           }
           else if(dx<0 && dy>0){
               zone=3;
           }
           else if(dx<0 && dy<0){
               zone=4;
           }
           else if(dx>0 && dy<0){
               zone=7;
           }
       }
       else{//zones 1,2,5,6
           if(dx>0 && dy>0){
               zone=1;
           }
           else if(dx<0 && dy>0){
               zone=2;
           }
           else if(dx<0 && dy<0){
               zone=5;
           }
           else if(dx>0 && dy<0){
               zone=6;
           }
       }
       System.out.println("Zone ="+zone);
      return zone; 
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