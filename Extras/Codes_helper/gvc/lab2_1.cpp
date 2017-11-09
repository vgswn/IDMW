#include <GL/glut.h>
#include <iostream>
void init(void)
{
    glClearColor(1.0,1.0,1.0,1.0);
    glClear(GL_COLOR_BUFFER_BIT);
    glMatrixMode(GL_MODELVIEW);
    gluOrtho2D(-300.0,300.0,-300.0,300.0);
}
void triangle()
{
    int i;
    for(i=1;i<=4;i++)
    {
        if(i==1)
        {
            glTranslated(0,150,0);
        }
        else if(i==2)
        {
            glTranslated(0,-150,0);
        }
        else if(i==3)
        {
            glTranslated(-100,-30,0);
            glRotated(90,0,0,1);
        }
        else
        {
            glTranslated(0,-200,0);
            
        }
        glColor3f(0.0,0.0,0.0);
        glBegin(GL_TRIANGLES);
        glVertex2i(0,0);
        glVertex2i(50,50);
        glVertex2i(50,-50);
        glEnd();
        glBegin(GL_LINES);
        glVertex2i(0,50);
        glVertex2i(0,-50);
        glVertex2i(-50,0);
        glVertex2i(100,0);
        glEnd();
        if(i==1)
        {
            glTranslated(0,-150,0);
        }
        else if(i==2)
        {
            glTranslated(0,150,0);
        }
    }

    glFlush();
}
int main(int argc,char ** argv)
{
    glutInit(&argc,argv);
    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);
    glutInitWindowPosition(50,100);
    glutInitWindowSize(500,500);
    glutCreateWindow("Line");
    init();
    glutDisplayFunc(triangle);
    glutMainLoop();
}
