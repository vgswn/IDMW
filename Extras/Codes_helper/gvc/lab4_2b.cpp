#include <GL/glut.h>

void display() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glTranslatef(1.5f, 0.0f, -7.0f);



    /*Colourful Triangle*/
    glLoadIdentity();
    glTranslatef(-1.5f, 1.5f, -7.0f);

    glBegin(GL_TRIANGLES);
    // Back
    glColor3f(1.0f, 0.0f, 0.0f);
    glVertex3f(0.0f, 1.0f, 0.0f);

    glColor3f(0.0f, 1.0f, 0.0f);
    glVertex3f(3.0f, -.5f, 0.0f);

    glColor3f(0.0f, 0.0f, 1.0f);
    glVertex3f(0.0f, 0.0f, 0.0f);

    // Left
    glColor3f(1.0f,0.0f,0.0f);
    glVertex3f( 0.0f, 0.0f, 0.0f);

    glColor3f(0.0f,0.0f,1.0f);
    glVertex3f(3.0f,-0.5f,0.0f);

    glColor3f(0.0f,1.0f,0.0f);
    glVertex3f(-1.0f,-0.5f, 1.5f);

    // Right
    glColor3f(1.0f, 0.0f, 0.0f);
    glVertex3f(0.0f,0.0f, 0.0f);

    glColor3f(0.0f, 0.0f, .10f);
    glVertex3f(0.0f, 1.0f, 0.0f);

    glColor3f(0.0f, 1.0f, 0.0f);
    glVertex3f(-1.0f, -.5f, 1.5f);


    glEnd();

    glutSwapBuffers();
}

void reshape(GLsizei width, GLsizei height) {  // GLsizei for non-negative integer
    // Compute aspect ratio of the new window
    if (height == 0) height = 1;
    GLfloat aspect = (GLfloat)width / (GLfloat)height;
    glViewport(0, 0, width, height);

    glMatrixMode(GL_PROJECTION);  // To operate on the Projection matrix
    glLoadIdentity();
    // Enable perspective projection with fovy, aspect, zNear and zFar
    gluPerspective(45.0f, aspect, 0.1f, 100.0f);
}

/* Main function: GLUT runs as a console application starting at main() */
int main(int argc, char** argv) {
    glutInit(&argc, argv);            // Initialize GLUT
    glutInitDisplayMode(GLUT_DOUBLE); // Enable double buffered mode
    glutInitWindowSize(640, 480);   // Set the window's initial width & height
    glutInitWindowPosition(900, 500); // Position the window's initial top-left corner
    glutCreateWindow("Q1. 3D Structure");          // Create window with the given title
    glutDisplayFunc(display);       // Register callback handler for window re-paint event
    glutReshapeFunc(reshape);       // Register callback handler for window re-size event

    glutMainLoop();                 // Enter the infinite event-processing loop
    return 0;
}
