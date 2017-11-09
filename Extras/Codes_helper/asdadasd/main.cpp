#include <GL/glut.h>
#include <bits/stdc++.h>
const float DEG2RAD = 3.14159/180.0;
void drawEllipse(float xradius, float yradius);
void drawtriangle();
void init2D(float r , float g , float b){
	glClearColor(r,g,b,1.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluOrtho2D(-10.0,10.0,-10.0,10.0);
}

void display(){
	glClear(GL_COLOR_BUFFER_BIT);
	drawEllipse(1.0, 0.2);

 	drawtriangle();
	glFlush();
}

void drawEllipse(float xradius, float yradius)
{
	glBegin(GL_LINE_LOOP);

	for(int i=0; i < 360; i++)
	{
		 //convert degrees into radians
		float degInRad = i*DEG2RAD;
		glVertex2f(cos(degInRad)*xradius,sin(degInRad)*yradius);
	}
	glEnd();
}

void drawtriangle()
{
	glBegin(GL_LINE_LOOP);
	glVertex2f(1.0, 0.0);
	glVertex2f(1.5, -0.5);
	glVertex2f(1.5, 0.5);
	glEnd();

	glBegin(GL_LINE_LOOP);
	glVertex2f(0.0, 0.2);
	glVertex2f(-0.3, 0.5);
	glVertex2f(0.3, 0.5);
	glEnd();

	glBegin(GL_LINE_LOOP);
	glVertex2f(0.0, -0.2);
	glVertex2f(-0.3, -0.5);
	glVertex2f(0.3, -0.5);
	glEnd();
}
int main(int argc , char *argv[]){

	glutInit(&argc,argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(800,800);
	glutInitWindowPosition(100,100);
	glutCreateWindow("Pyramid");
	init2D(0.0 , 0.0 , 0.0);
	glutDisplayFunc(display);
	glutMainLoop();
}
