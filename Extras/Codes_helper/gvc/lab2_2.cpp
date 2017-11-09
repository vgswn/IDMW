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
    for(i=1;i<=40;i++)
    {
        glColor3f(0.0,0.0,0.0);
        glBegin(GL_LINE_STRIP);
            glVertex2d(0,0);
            glVertex2d(50,50);
            glVertex2d(0,100);
            glVertex2d(-50,50);
        glEnd();
        glRotated(9,0,0,1);
    }
    glRotated(-360,0,0,1);
    glBegin(GL_LINES);
        glVertex2d(0,0);
        glVertex2d(0,-200);
    glEnd();
    glBegin(GL_TRIANGLES);
        glVertex2d(0,-200);
        glVertex2d(10,-180);
        glVertex2d(0,-190);
        glVertex2d(0,-200);
        glVertex2d(0,-190);
        glVertex2d(-10,-180);
    glEnd();
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