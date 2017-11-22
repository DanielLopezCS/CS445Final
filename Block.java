
/* Team: Daniel Lopez  Roberto Monica Say Bryce Metcalf David Escobedo

Class: Block
Description: Stores Block Information

*/
public class Block {
    
private boolean IsActive
;
private BlockType Type;
private float x,y,z
;
public enum BlockType
{
BlockType_Grass(0),
BlockType_Sand(1),
BlockType_Water(2),
BlockType_Dirt(3),
BlockType_Stone(4),
BlockType_Bedrock(5),
BlockType_Default(6);
private int BlockID;
BlockType
(int i) {
BlockID
=i;
}
public int GetID(){
return BlockID
;
}
//method: setID
//description: sets ID
public void SetID
(int i){
BlockID = i;
}
}
public Block(BlockType type){
Type= type;
}
//method: setCoords
//description: sets coordinates
public void setCoords(float x, float y, float z){
this.x = x;
this.y = y;
this.z = z;
}
//method: isActive
//description: returns if the block is active
public boolean IsActive() {
return IsActive;
}
//method: setActive
//description: sets active status
public void SetActive(boolean active){
IsActive=active;
}
//method: getID()
//description: returns ID
public int GetID(){
return Type.GetID();
}
}