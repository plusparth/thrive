package world;

public class tileHandler {
	public Tile dirt = new Tile();
	public Tile grass = new Tile();
	public Tile stone = new Tile();
	
	public void setTileProperties() {
		dirt.internalName = "tileDirt";
		grass.internalName = "tileGrass";
		stone.internalName = "tileStone";
		
		dirt.displayName = "Dirt";
		grass.internalName = "Grassy Dirt";
		stone.internalName = "Stone";
		
		dirt.hardness = 0.01f;
		grass.hardness = dirt.hardness;
		stone.hardness = 0.1f;
		
		
	}
	//TODO: What else does this thing do?
	//Pass stuff to inventory handler (TODO)
	//Handle special tiles (crafting table etc)
}
