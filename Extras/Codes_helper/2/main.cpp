#include<GL/glut.h>
#include<stdlib.h>
#include<math.h>

const int screenWidth=640;
const int screenHeight=480;

GLdouble a=0,b=0,c=0,d=0;

void myInit()
{
     glClearColor(1.0,1.0,1.0,0.0);
     glColor3f(1.0,0.0,0.0);
     glPointSize(3.0);
     glMatrixMode(GL_PROJECTION);
     glLoadIdentity();
     gluOrtho2D(0.0,(GLdouble)screenWidth,0.0,(GLdouble)screenHeight);
     a=100;
     b=0.0;
     c=d=100;

     }

     void myDisplay()
     {
         double x;
          GLdouble i=0;
          glClear(GL_COLOR_BUFFER_BIT);
          glBegin(GL_POINTS);

                                       for (x = -10.0; x <=10.0; x+=0.25){
                if(x==0.0){
                    glVertex2f(0.0,1.0);
                    continue;
                }
                GLfloat y = sin(3.14 * x) / (3.14 * x);
                glVertex2f (x,y);
            }
                                                    glEnd();

                                                    glBegin(GL_LINES);
                                                    glColor3f(0.0,0.0,1.0);
                                                    glVertex2i(0.0,screenHeight/2);
                                                    glVertex2i(screenWidth,screenHeight/2);
                                                    glEnd();

                                                    //to test
                                                    glColor3f(0.0,1.0,0.0);
                                                    glBegin(GL_POINTS);
                                                    glVertex2i(68,48);
                                                    glEnd();

                                                    glFlush();
    }





     int main(int argc, char **argv)
     {
         glutInit(&argc,argv);
         glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
         glutInitWindowSize(640,480);
         glutCreateWindow("exponential plot");
         glutDisplayFunc(myDisplay);

         myInit();

         glutMainLoop();
         }
