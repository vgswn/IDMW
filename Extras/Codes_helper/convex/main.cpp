#include <GL/glut.h>
#include <bits/stdc++.h>
using namespace std;
int x[6],y[6];
bool PolygonIsConvex()
{
   	int i,countpos=0,countneg=0,ind1,ind2,ind3,check;
   	for(i=0;i<6;i++)
   	{
   		ind1=i;
   		ind2=(i+1)%6;
   		ind3=(i+2)%6;
   		check=(x[ind2]-x[ind1])*(y[ind3]-y[ind2])-(y[ind2]-y[ind1])*(x[ind3]-x[ind2]);
   		if(check<0)
   			countneg++;
   		else
   			countpos++;
   	}
   	if(countpos==0||countneg==0)
   		return true;
   	return false;
}
void init2D(float r, float g, float b)
{
	glClearColor(r, g, b, 0.0);
	glMatrixMode(GL_PROJECTION);
	gluOrtho2D(-50.0, 50.0, -50.0, 50.0); //left right bottom top clipping planes
}
void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(1.0, 0.0, 0.0);
	glBegin(GL_POLYGON);
		glVertex2i(x[0],y[0]);
		glVertex2i(x[1],y[1]);
		glVertex2i(x[2],y[2]);
		glVertex2i(x[3],y[3]);
		glVertex2i(x[4],y[4]);
		glVertex2i(x[5],y[5]);
	glEnd();
	glFlush();
}
void takeinput()
{
 	int i;
	printf("give six vertices of polygon\n");
	for(i=0;i<6;i++)
	{
		scanf("%d%d",&x[i],&y[i]);
	}
	if(PolygonIsConvex())
		printf("Convex\n");
	else
		printf("Concave\n");
}
int main(int argc, char *argv[])
{
	takeinput();
	glutInit(&argc,argv);
	glutInitDisplayMode(GLUT_SINGLE |
	GLUT_RGB);
	glutInitWindowSize(500, 500);
	glutInitWindowPosition(100, 100);
	glutCreateWindow("polygon");
	init2D(0.0, 0.0, 0.0);
	glutDisplayFunc(display);
	glutMainLoop();
	return 0;
}
