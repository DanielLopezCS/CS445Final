
/* Team: Daniel Lopez  Roberto Monica Say Bryce Metcalf David Escobedo

Class: Vector3Float
Description: Stores the camera's x,y, and z coordinates.

*/
public class Vector3Float {
    private float x, y, z;
    
    
    //Method: Vector3Float
    //Description:Contructor which takes in all 3 coordinates and stores them
    public Vector3Float(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    //Method: getX()
    //Description: Returns X
    public float getX() {
        return x;
    }
       //Method: set X()
    //Description: sets X
    public void setX(float x) {
        this.x = x;
    }
       //Method: getZ()
    //Description: Returns Z
    public float getY() {
        return y;
    }
       //Method: setY()
    //Description: sets Y
    public void setY(float y) {
        this.y = y;
    }
       //Method: getZ()
    //Description: ReturnsZ
    public float getZ() {
        return z;
    }
       //Method: setX()
    //Description: Returns Z
    public void setZ(float z) {
        this.z = z;
    }
    
}