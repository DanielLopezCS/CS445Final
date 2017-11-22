
/* Team: Daniel Lopez  Roberto Monica Say Bryce Metcalf David Escobedo

Class: Basic3D
Description: The driver class which calls on the FPCameraController

*/
import java.nio.ByteBuffer;
import javax.swing.ImageIcon;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

public class Basic3D {
private FPCameraController fp = new FPCameraController(0f,0f,0f);
private DisplayMode displayMode;
private ByteBuffer[] icons = new ByteBuffer[3];
public void start() {
try {
createWindow();
initGL();
fp.init();
fp.gameLoop();//render();
} catch (Exception e) {
e.printStackTrace();
}
}

private void createWindow() throws Exception{
Display.setFullscreen(false);

DisplayMode d[] =
Display.getAvailableDisplayModes();
for (int i = 0; i < d.length; i++) {
if (d[i].getWidth() == 640
&& d[i].getHeight() == 480
&& d[i].getBitsPerPixel() == 32) {
displayMode = d[i];
break;
}

}
Display.setDisplayMode(displayMode);
Display.setTitle("Minecraft");
Display.create();
}
private void initGL() {
    glEnableClientState(GL_VERTEX_ARRAY);
glEnableClientState(GL_COLOR_ARRAY);
glEnable(GL_DEPTH_TEST);
glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
glMatrixMode(GL_PROJECTION);
glLoadIdentity();
     glEnable(GL_TEXTURE_2D);
        glEnableClientState (GL_TEXTURE_COORD_ARRAY);
        
GLU.gluPerspective(100.0f, (float)displayMode.getWidth()/(float)
displayMode.getHeight(), 0.1f, 300.0f);
glMatrixMode(GL_MODELVIEW);
glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
}
public static void main(String[] args) {
Basic3D basic = new Basic3D();
basic.start();
}
}