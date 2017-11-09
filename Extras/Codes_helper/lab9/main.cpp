#include <GL/glut.h>
#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;
void init(void)
{
    glClearColor(0.0,0.0,0.0,0.0);
    glMatrixMode(GL_MODELVIEW);
    gluOrtho2D(-300.0,300.0,-300.0,300.0);
}
void drawheli(){
        glScalef(2,1,0);
    int i;
    for(i=1;i<=2400;i++)
    {
        glBegin(GL_LINES);
            glVertex2f(0,0);
            glVertex2f(4,4);
        glEnd();
        glRotated(0.15,0,0,1.0);
    }
    glScalef(0.5,1,0);
    glBegin(GL_POLYGON);
    glVertex3d(10,0,0);
    glVertex3d(20,-3,0);
    glVertex3d(20,3,0);
    glEnd();
        glBegin(GL_POLYGON);
    glVertex3d(0,5,0);
    glVertex3d(3,10,0);
    glVertex3d(-3,10,0);
    glEnd();
        glBegin(GL_POLYGON);
    glVertex3d(0,-5,0);
    glVertex3d(3,-10,0);
    glVertex3d(-3,-10,0);
    glEnd();

}
void triangle()
{
    glClear(GL_COLOR_BUFFER_BIT);
    int i = 0;

    srand((unsigned)time(0));
    float r1,r2,r3;

    for ( i = 0; i < 20; i++){
        r1 = ((rand()%1000)+1)/1000.0;
        r2 = ((rand()%1000)+1)/1000.0;
        r3 =((rand()%1000)+1)/1000.0;
        glColor3f(r1,r2,r3);
        double x, y;
        x= ((rand()%300)+1);
        y= ((rand()%300)+1);
        int a, b;
        a = (rand()%4);
        if ( a == 0){
            a = 1; b = 1;
        } else if ( a == 1){
            a = 1; b = -1;
        } else if ( a == 2) {
            a = -1; b = -1;
        } else {
            a = -1; b = 1;
        }
        x = a*x;
        y = b*y;
        glTranslated(x,y,0);
        drawheli();
        glTranslated(x*-1, y*-1,0);
    }

    glFlush();
}

int main(int argc,char ** argv)
{
    glutInit(&argc,argv);
    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);
    glutInitWindowPosition(50,100);
    glutInitWindowSize(600,400);
    glutCreateWindow("Hey");
    init();
    glutDisplayFunc(triangle);
    glutMainLoop();
}
