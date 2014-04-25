package world;

public class tileHandler {
	//Create, register tiles
	public Tile tileDirt = new Tile();
	public Tile tileGrass = new Tile();
	public Tile tileStone = new Tile();
	
	public void setTileProperties() {
		//Set IDs
		tileDirt.id = 0;
		tileGrass.id = 1;
		tileStone.id = 2;
		
		//Set display names
		tileDirt.displayName = "Dirt";
		tileGrass.displayName = "Grassy Dirt";
		tileStone.displayName = "Stone";
		
		//Set tile hardness
		tileDirt.hardness = 0.01f;
		tileGrass.hardness = tileDirt.hardness;
		tileStone.hardness = 0.1f;	
	}
	//TODO: What else does this thing do?
	//Pass stuff to inventory handler (TODO)
	//Handle special tiles (crafting table etc)
}
