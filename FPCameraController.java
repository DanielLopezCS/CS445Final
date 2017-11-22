
/* Team: Daniel Lopez  Roberto Monica Say Bryce Metcalf David Escobedo

Class: FPCameraController
Description: Does all of the rendering and movement

*/
import java.awt.Toolkit;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.Sys;

public class FPCameraController {
//3d vector to store the camera's position in
private Vector3f position = null;
private Vector3f lPosition = null;
//the rotation around the Y axis of the camera
private float yaw = 0.0f;
//the rotation around the X axis of the camera
private float pitch = 0.0f;
private Chunk myChunk;

private Vector3Float me;
    public FPCameraController(float x, float y, float z)
{
//instantiate position Vector3f to the x y z params.
position = new Vector3f(x, y, z);
lPosition = new Vector3f(x,y,z);
lPosition.x = 0f;
lPosition.y = 15f;
lPosition.z = 0f;
}
  //increment the camera's current yaw rotation
public void yaw(float amount)
{
//increment the yaw by the amount param
yaw += amount;
}
//increment the camera's current yaw rotation
public void pitch(float amount)
{
//increment the pitch by the amount param
pitch -= amount;
}
    public void walkForward(float distance)
{
float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
position.x -= xOffset;
position.z += zOffset;
}
    public void walkBackwards(float distance)
{
float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
position.x += xOffset;
position.z -= zOffset;
}
    public void strafeLeft(float distance)
{
float xOffset = distance * (float)Math.sin(Math.toRadians(yaw-90));
float zOffset = distance * (float)Math.cos(Math.toRadians(yaw-90));
position.x -= xOffset;
position.z += zOffset;
}
    public void strafeRight(float distance)
{
float xOffset = distance * (float)Math.sin(Math.toRadians(yaw+90));
float zOffset = distance * (float)Math.cos(Math.toRadians(yaw+90));
position.x -= xOffset;
position.z += zOffset;
}
    public void moveUp(float distance)
{
position.y -= distance;
}
//moves the camera down
public void moveDown(float distance)
{
position.y += distance;
}
    public void lookThrough()
{
//roatate the pitch around the X axis
glRotatef(pitch, 1.0f, 0.0f, 0.0f);
//roatate the yaw around the Y axis
glRotatef(yaw, 0.0f, 1.0f, 0.0f);
//translate to the position vector's location
glTranslatef(position.x, position.y, position.z);
}
    //method: init
    //description: instantiates the Chunk class from which we render our environment
    public void init(){
         myChunk = new Chunk(0, 4,0);
         

    }
        
    public void gameLoop()
{
FPCameraController camera = new FPCameraController(0, 0, 0);
float dx = 0.0f;
float dy = 0.0f;
float dt = 0.0f; //length of frame
float lastTime = 0.0f; // when the last frame was
long time = 0;
float mouseSensitivity = 0.09f;
float movementSpeed = .35f;
//hide the mouse
Mouse.setGrabbed(true);
    // keep looping till the display window is closed the ESC key is down
while (!Display.isCloseRequested() &&
!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
{
time = Sys.getTime();
lastTime = time;
//distance in mouse movement
//from the last getDX() call.
dx = Mouse.getDX();
//controll camera yaw from x movement fromt the mouse
camera.yaw(dx * mouseSensitivity);

//distance in mouse movement
//from the last getDY() call.
dy = Mouse.getDY();
//controll camera pitch from y movement fromt the mouse
camera.pitch(dy * mouseSensitivity);
    //when passing in the distance to move
//we times the movementSpeed with dt this is a time scale
//so if its a slow frame u move more then a fast frame
//so on a slow computer you move just as fast as on a fast computer
if (Keyboard.isKeyDown(Keyboard.KEY_W))//move forward
{
camera.walkForward(movementSpeed);
}
if (Keyboard.isKeyDown(Keyboard.KEY_S))//move backwards
{
camera.walkBackwards(movementSpeed);
}
    if (Keyboard.isKeyDown(Keyboard.KEY_A))
    {//strafe left {
camera.strafeLeft(movementSpeed);
}
if (Keyboard.isKeyDown(Keyboard.KEY_D))
{//strafe right {
camera.strafeRight(movementSpeed);
}
if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
{//move up {
camera.moveUp(movementSpeed);
}
if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
camera.moveDown(movementSpeed);
}
    //set the modelview matrix back to the identity
glLoadIdentity();
//look through the camera before you draw anything
camera.lookThrough();
glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

//you would draw your scene here.
 

myChunk.render();


//render();
//draw the buffer to the screen
Display.update();
Display.sync(60);
}

Display.destroy();
}
private void render() {

try{

glBegin(GL_QUADS);
//Top
glColor3f(0.2f,0.8f,0.8f);
glVertex3f( 5.0f, 5.0f,-5.0f);
glVertex3f(-5.0f, 5.0f,-5.0f);
glVertex3f(-5.0f, 5.0f, 5.0f);
glVertex3f( 5.0f, 5.0f, 5.0f);
glEnd();

glBegin(GL_QUADS);
//Bottom
glColor3f(0.0f,0.0f,0.5f);
glVertex3f( 5.0f,-5.0f, 5.0f);
glVertex3f(-5.0f,-5.0f, 5.0f);
glVertex3f(-5.0f,-5.0f,-5.0f);
glVertex3f( 5.0f,-5.0f,-5.0f);
glEnd();
glBegin(GL_QUADS);
//Front
glColor3f(0.5f,0.0f,1.0f);
glVertex3f( 5.0f, 5.0f, 5.0f);
glVertex3f(-5.0f, 5.0f, 5.0f);
glVertex3f(-5.0f,-5.0f, 5.0f);
glVertex3f( 5.0f,-5.0f, 5.0f);
glEnd();

glBegin(GL_QUADS);
//Back
glColor3f(0.5f,0.5f,1.0f);
glVertex3f( 5.0f,-5.0f,-5.0f);
glVertex3f(-5.0f,-5.0f,-5.0f);
glVertex3f(-5.0f, 5.0f,-5.0f);
glVertex3f( 5.0f, 5.0f,-5.0f);
glEnd();
glBegin(GL_QUADS);
//Left
glColor3f(0.0f,0.5f,0.5f);
glVertex3f(-5.0f, 5.0f, 5.0f);
glVertex3f(-5.0f, 5.0f,-5.0f);
glVertex3f(-5.0f,-5.0f,-5.0f);
glVertex3f(-5.0f,-5.0f, 5.0f);
glEnd();
glBegin(GL_QUADS);
//Right
glColor3f(0.0f,1.0f,0.0f);
glVertex3f( 5.0f, 5.0f,-5.0f);
glVertex3f( 5.0f, 5.0f, 5.0f);
glVertex3f( 5.0f,-5.0f, 5.0f);
glVertex3f( 5.0f,-5.0f,-5.0f);
glEnd();

    /*
glBegin(GL_QUADS);
    //Left
glVertex3f(-15.0f, 15.0f,5.0f);
glVertex3f(-15.0f, 15.0f,-5.0f);
glVertex3f(-15.0f,-15.0f,-5.0f);
glVertex3f(-15.0f,-15.0f, 5.0f);


//Right

glVertex3f( 15.0f, 15.0f,-5.0f);
glVertex3f( 15.0f, 15.0f, 5.0f);
glVertex3f( 15.0f,-15.0f, 5.0f);
glVertex3f( 15.0f,-15.0f,-5.0f);


//Top
glColor3f(0.0f,0.0f,0.0f);
glVertex3f( 15.0f, 15.0f,-5.0f);
glVertex3f(-15.0f, 15.0f,-5.0f);
glVertex3f(-15.0f, 15.0f, 5.0f);
glVertex3f( 15.0f, 15.0f, 5.0f);


//Bottom
glVertex3f( 15.0f,-15.0f, 5.0f);
glVertex3f(-15.0f,-15.0f, 5.0f);
glVertex3f(-15.0f,-15.0f,-5.0f);
glVertex3f( 15.0f,-15.0f,-5.0f);


//Front
glVertex3f( 15.0f, 15.0f, 5.0f);
glVertex3f(-15.0f, 15.0f, 5.0f);
glVertex3f(-15.0f,-15.0f, 5.0f);
glVertex3f( 15.0f,-15.0f, 5.0f);

                                                                            

//Back
glVertex3f( 15.0f,-15.0f,-5.0f);
glVertex3f(-15.0f,-15.0f,-5.0f);
glVertex3f(-15.0f, 15.0f,-5.0f);
glVertex3f( 15.0f, 15.0f,-5.0f);


//Left
glVertex3f(-15.0f, 15.0f, 5.0f);
glVertex3f(-15.0f, 15.0f,-5.0f);
glVertex3f(-15.0f,-15.0f,-5.0f);
glVertex3f(-15.0f,-15.0f, 5.0f);


//Right
glVertex3f( 15.0f, 15.0f,-5.0f);
glVertex3f( 15.0f, 15.0f, 5.0f);
glVertex3f( 15.0f,-15.0f, 5.0f);
glVertex3f( 15.0f,-15.0f,-5.0f);
glEnd();
*/
}
catch(Exception e){
}
}


}
